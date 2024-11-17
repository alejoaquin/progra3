package Project.impl;

import Lib.Coordenada;
import Lib.Cultivo;
import Project.Alternativa;
import Project.EvaluacionDeAlternativa;
import Project.Ganancia;
import Project.models.CultivoSeleccionadoV2;
import Project.models.Marca;

import java.util.ArrayList;
import java.util.List;

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
                    List<Double> riesgosAsociados = new ArrayList<>();
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
}

