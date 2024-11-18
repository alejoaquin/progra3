package Project.impl;

import Project.Alternativa;
import Project.Ganancia;
import Project.ManejarMarca;
import Project.MejorRelleno;
import Project.models.Area;
import Project.models.CultivoSeleccionadoV2;
import Project.models.ESBacktrackingRelleno;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MejorRellenoImpl implements MejorRelleno {
    ManejarMarca manejarMarca = new ManejarMarcaImpl();
    Alternativa alternativa = new AlternativaImpl();
    Ganancia ganancia = new GananciaImpl();

    @Override
    public void backtracking(ESBacktrackingRelleno p) {
        Area area = p.niveles.get(p.etapa); // Selecciono un area generica que podrá ser ocupada o no por cualquier cultivo seleccionado.

        List<CultivoSeleccionadoV2> alternativas = alternativa.generarAlternativaRelleno(p.marcas, p.cultivos, p.riesgos, area, p.cultivoDeRelleno);

        for (int i = 0; i <= alternativas.size(); i++) {
            CultivoSeleccionadoV2 alternativa = null;
            // Si es la ultima iteración manejamos la alternativa de no poner el cultivo
            if (i < alternativas.size()) {
                alternativa = alternativas.get(i);
                manejarMarca.marcarMatriz(alternativa, p.marcas, true);
                p.rellenoParcial.add(alternativa);
                p.cultivoDeRelleno = alternativa.getNombreCultivo();
            }
            // Si es la última etapa, evaluamos la solución
            if (p.etapa == p.niveles.size() - 1) {
                double gananciaParcial = ganancia.calcularGananciaTotal(p.rellenoParcial);

                if (gananciaParcial > p.gananciaMejor) {
                    p.gananciaMejor = gananciaParcial;
                    p.rellenoResultado = new ArrayList<>(p.rellenoParcial);
                }
            } else {
                p.etapa += 1;
                backtracking(p);
                p.etapa -= 1;
                p.cultivoDeRelleno = null;
            }

            if (Objects.nonNull(alternativa)) {
                manejarMarca.marcarMatriz(alternativa, p.marcas, false);
                p.rellenoParcial.remove(alternativa);
            } else {
                // Llamar backtracking con la siguiente etapa
                if (p.etapa < p.niveles.size() - 1) {
                    p.etapa += 1;
                    backtracking(p);
                    p.etapa -= 1;
                    p.cultivoDeRelleno = null;
                } else {
                    double gananciaParcial = ganancia.calcularGananciaTotal(p.rellenoParcial);
                    if (gananciaParcial > p.gananciaMejor) {
                        p.gananciaMejor = gananciaParcial;
                        p.rellenoResultado = new ArrayList<>(p.rellenoParcial);
                    }
                }
            }
        }
    }
}
