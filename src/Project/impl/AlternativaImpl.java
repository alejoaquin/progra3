package Project.impl;

import Lib.Coordenada;
import Lib.Cultivo;
import Project.Alternativa;
import Project.EvaluacionDeAlternativa;
import Project.Ganancia;
import Project.models.Area;
import Project.models.CultivoSeleccionadoV2;
import Project.models.Marca;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AlternativaImpl implements Alternativa {
    private EvaluacionDeAlternativa evaluacionDeAlternativa = new EvaluacionDeAlternativaImpl();
    private Ganancia ganancia = new GananciaImpl();

    @Override
    public List<CultivoSeleccionadoV2> generar(Marca[][] marca, Cultivo cultivo, double[][] riesgos) {
        List<CultivoSeleccionadoV2> alternativas = new ArrayList<>();
        int rows = marca.length;
        int cols = marca[0].length;

        // Iteramos sobre todas las posibles esquinas superiores izquierdas
        for (int xInicio = 0; xInicio < rows; xInicio++) {
            for (int yInicio = 0; yInicio < cols; yInicio++) {
                // Iteramos sobre todas las posibles esquinas inferiores derechas
                for (int xFin = xInicio; xFin < rows; xFin++) {
                    for (int yFin = yInicio; yFin < cols; yFin++) {
                        // Verificamos que la suma de las longitudes no supere 11
                        int longitudHorizontal = xFin - xInicio + 1;
                        int longitudVertical = yFin - yInicio + 1;

                        if (longitudHorizontal + longitudVertical <= 11) {
                            // Generar el CultivoSeleccionadoV2 para este rectángulo
                            CultivoSeleccionadoV2 alternativa = new CultivoSeleccionadoV2();
                            alternativa.setNombreCultivo(cultivo.getNombre());
                            alternativa.setEsquinaSuperiorIzquierda(new Coordenada(xInicio, yInicio));
                            alternativa.setEsquinaInferiorDerecha(new Coordenada(xFin, yFin));
                            ganancia.calcularGananciaCultivo(alternativa, riesgos, cultivo);

                            // Agregar la alternativa a la lista
                            alternativas.add(alternativa);
                        }
                    }
                }
            }

        }
        return alternativas.stream()
                .filter(alt -> evaluacionDeAlternativa.esValida(marca, alt))
                .toList();
    }

    @Override
    public List<CultivoSeleccionadoV2> generarAlternativaRelleno(Marca[][] marcas, List<Cultivo> cultivos, double[][] riesgos, Area area, String cultivoDeRelleno) {
        // Si no es nulo iteramos la lista y convertimos a seleccionados
        // Sino convertimos a seleccionado sólo el cultivo de relleno

        return cultivos.stream()
                .filter(c -> Objects.isNull(cultivoDeRelleno) || c.getNombre().equals(cultivoDeRelleno))
                .map(c -> convertirASeleccionado(c, area, riesgos))
                .filter(c -> evaluacionDeAlternativa.esRellenoValido(marcas, c))
                .peek(c -> calcularGanancia(riesgos, c, cultivos))
                .collect(Collectors.toList());
    }

    private CultivoSeleccionadoV2 convertirASeleccionado(Cultivo cultivo, Area area, double[][] riesgos) {
        CultivoSeleccionadoV2 seleccion = new CultivoSeleccionadoV2();
        seleccion.setNombreCultivo(cultivo.getNombre());
        seleccion.setEsquinaSuperiorIzquierda(area.getEsquinaSuperiorIzquierda());
        seleccion.setEsquinaInferiorDerecha(area.getEsquinaInferiorDerecha());
        return seleccion;
    }

    private void calcularGanancia(double[][] riesgos, CultivoSeleccionadoV2 seleccion, List<Cultivo> cultivos) {
        Cultivo cultivo = cultivos.stream().filter(c -> c.getNombre().equals(seleccion.getNombreCultivo())).findFirst().orElse(null);
        ganancia.calcularGananciaCultivo(seleccion, riesgos, cultivo);
    }
}

