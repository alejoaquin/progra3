package Project.impl;

import Lib.Cultivo;
import Project.*;
import Project.models.Area;
import Project.models.CultivoSeleccionadoV2;
import Project.models.ESBacktracking;
import Project.models.ESBacktrackingRelleno;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlantacionOptimaImpl implements PlantacionOptima {
    private AreaNiveles areaNiveles = new AreaNivelesImpl();
    private Alternativa alternativa = new AlternativaImpl();
    private ManejarMarca manejarMarca = new ManejarMarcaImpl();
    private MejorRelleno mejorRelleno = new MejorRellenoImpl();
    private Ganancia ganancia = new GananciaImpl();

    @Override
    public void backtracking(ESBacktracking p) {
        Cultivo cultivo = p.cultivos.get(p.etapa);

        List<CultivoSeleccionadoV2> alternativas = new ArrayList<>();

        if (cultivo.getTemporadaOptima().equals(p.temporada)) {
            alternativas = alternativa.generar(p.marcas, cultivo, p.riesgos);
        }
        for (int i = 0; i <= alternativas.size(); i++) {
            CultivoSeleccionadoV2 alternativa = null;
            // Si es la ultima iteración manejamos la alternativa de no poner el cultivo
            if (i < alternativas.size()) {
                alternativa = alternativas.get(i);
                manejarMarca.marcarMatriz(alternativa, p.marcas, true);
                p.cultivosParcial.add(alternativa);
            }
            // Si es la última etapa, evaluamos la solución
            if (p.etapa == p.cultivos.size() - 1) {
                double gananciaParcial = ganancia.calcularGananciaTotal(p.cultivosParcial);

                List<Area> niveles = areaNiveles.generar(p.riesgos.length, p.riesgos[0].length);

                ESBacktrackingRelleno esBacktrackingRelleno = new ESBacktrackingRelleno(
                        new ArrayList<>(p.cultivosParcial),
                        new ArrayList<>(p.cultivosParcial),
                        p.marcas,
                        p.riesgos,
                        p.cultivos.stream().filter(c -> c.getTemporadaOptima().equals(p.temporada)).toList(),
                        0,
                        gananciaParcial,
                        null,
                        niveles
                );

                mejorRelleno.backtracking(esBacktrackingRelleno);
                gananciaParcial = esBacktrackingRelleno.gananciaMejor;

                if (gananciaParcial > p.gananciaMejor) {
                    p.gananciaMejor = gananciaParcial;
                    p.cultivosResultado = new ArrayList<>(esBacktrackingRelleno.rellenoResultado);
                }
            } else {
                p.etapa += 1;
                backtracking(p);
                p.etapa -= 1;
            }

            if (Objects.nonNull(alternativa)) {
                manejarMarca.marcarMatriz(alternativa, p.marcas, false);
                p.cultivosParcial.remove(alternativa);
            } else {
                // Llamar backtracking con la siguiente etapa
                if (p.etapa < p.cultivos.size() - 1) {
                    p.etapa += 1;
                    backtracking(p);
                    p.etapa -= 1;
                } else {
                    double gananciaParcial = ganancia.calcularGananciaTotal(p.cultivosParcial);

                    List<Area> niveles = areaNiveles.generar(p.riesgos.length, p.riesgos[0].length);

                    ESBacktrackingRelleno esBacktrackingRelleno = new ESBacktrackingRelleno(
                            new ArrayList<>(p.cultivosParcial),
                            new ArrayList<>(p.cultivosParcial),
                            p.marcas,
                            p.riesgos,
                            p.cultivos.stream().filter(c -> c.getTemporadaOptima().equals(p.temporada)).toList(),
                            0,
                            gananciaParcial,
                            null,
                            niveles
                    );

                    mejorRelleno.backtracking(esBacktrackingRelleno);
                    gananciaParcial = esBacktrackingRelleno.gananciaMejor;

                    if (gananciaParcial > p.gananciaMejor) {
                        p.gananciaMejor = gananciaParcial;
                        p.cultivosResultado = new ArrayList<>(esBacktrackingRelleno.rellenoResultado);
                    }
                }
            }
        }
    }

}
