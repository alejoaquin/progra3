package Project.impl;

import Lib.Cultivo;
import Project.*;
import Project.models.CultivoSeleccionadoV2;
import Project.models.ESBacktracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlantacionOptimaImpl implements PlantacionOptima {

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


            for (int i = 0; i <= alternativas.size(); i++) {
                CultivoSeleccionadoV2 alternativa = null;
                // Si es la ultima iteración manejamos la alternativa de no poner el cultivo
                if (i < alternativas.size()) {
                    System.out.println("No es la aternativa nula.");
                    alternativa = alternativas.get(i);
                    manejarMarca.marcarMatriz(alternativa, p.marcas, true);
                    p.cultivosParcial.add(alternativa);
                } else {
                    System.out.println("Aternativa nula.");
                }

                System.out.println("solo para frenar");

                // Si es la última etapa, evaluamos la solución
                if (p.etapa == p.cultivos.size() - 1) {
                    double gananciaParcial = ganancia.calcularGananciaTotal(p.cultivosParcial);
                    System.out.println("gananciaParcial: " + gananciaParcial);
                    System.out.println("mejor ganancia: " + p.gananciaMejor);

                    if (gananciaParcial > p.gananciaMejor) {
                        p.gananciaMejor = gananciaParcial;
                        p.cultivosResultado = new ArrayList<>();
                        p.cultivosResultado.addAll(p.cultivosResultado);
                        System.out.println("cultivosResultado size: " + p.cultivosResultado.size());
                    }
                    System.out.println("Después de ganancias");
                } else {
                    System.out.println("Entro en else.");
                    if (p.etapa < p.cultivos.size() - 1) {
                        System.out.println("Entro en if.");
                        System.out.println("Evaluamos siguiente etapa dentro del for.");
                        p.etapa += 1;
                        backtracking(p);
                    }
                }

                if (Objects.nonNull(alternativa)) {
                    manejarMarca.marcarMatriz(alternativa, p.marcas, false);
                    p.cultivosParcial.remove(alternativa);
                }
            }
        } else {
            // Llamar backtracking con la siguiente etapa
            System.out.println("Cultivo no en temporada. Temporada " + p.temporada + " - Cultivo temporada: " + cultivo.getTemporadaOptima());
            if (p.etapa < p.cultivos.size() - 1) {
                System.out.println("Evaluamos siguiente etapa.");
                p.etapa += 1;
                backtracking(p);
            }
        }
    }
}
