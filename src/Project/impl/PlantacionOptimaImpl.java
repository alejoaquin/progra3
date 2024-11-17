package Project.impl;

import Lib.Cultivo;
import Project.*;
import Project.models.CultivoSeleccionadoV2;
import Project.models.ESBacktracking;
import Project.models.Marca;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlantacionOptimaImpl implements PlantacionOptima {
    private ESBacktracking parametroPorReferencia;

    public PlantacionOptimaImpl(ESBacktracking parametroPorReferencia) {
        this.parametroPorReferencia = parametroPorReferencia;
    }

    private Alternativa alternativa = new AlternativaImpl();
    private ManejarMarca manejarMarca = new ManejarMarcaImpl();
    private MejorRelleno mejorRelleno = new MejorRellenoImpl();
    private Ganancia ganancia = new GananciaImpl();

    @Override
    public void backtracking(ESBacktracking p) {
        Cultivo cultivo = p.cultivos.get(p.etapa);

        List<CultivoSeleccionadoV2> alternativas = cultivo.getTemporadaOptima().equals(p.temporada) ?
                obtenerAlternativas(p.marcas, cultivo, p.riesgos) :
                new ArrayList<>();

        alternativas.forEach(
                alternativa -> {
                    if (Objects.nonNull(alternativa)) {
                        manejarMarca.marcarMatriz(alternativa, p.marcas, true);
                        p.cultivosParcial.add(alternativa);
                    }
                    ESBacktracking conRelleno = p.clonar();
                    double gananciaParcialSinRelleno = ganancia.calcularGananciaTotal(p.cultivosParcial);

                    // Si es la última etapa, evaluamos la solución
                    if (alternativas.indexOf(alternativa) == alternativas.size() - 1) {
                        conRelleno.gananciaMejor = gananciaParcialSinRelleno;
                        mejorRelleno.calcular(conRelleno);
                        double gananciaParcial = ganancia.calcularGananciaTotal(conRelleno.cultivosParcial);

                        if (gananciaParcial > p.gananciaMejor) {
                            p.gananciaMejor = gananciaParcial;
                            p.cultivosResultado = conRelleno.cultivosParcial;
                        }
                    } else {
                        p.etapa += 1;
                        backtracking(p);
                    }

                    if (Objects.nonNull(alternativa)) {
                        manejarMarca.marcarMatriz(alternativa, p.marcas, false);
                        p.cultivosParcial.remove(alternativa);
                    }
                }
        );
    }

    private List<CultivoSeleccionadoV2> obtenerAlternativas(Marca[][] marcas, Cultivo cultivo, double[][] riesgos) {
        // Generar todas las alternativas válidas para el cultivo actual
        List<CultivoSeleccionadoV2> alternativas = alternativa.generar(marcas, cultivo, riesgos);
        // También consideramos la alternativa de no usar el cultivo actual
        alternativas.add(null);
        return alternativas;
    }
}
