package Project.impl;

import Lib.Cultivo;
import Lib.CultivoSeleccionado;
import Lib.PlanificarCultivos;
import Project.PlantacionOptima;
import Project.models.CultivoSeleccionadoV2;
import Project.models.ESBacktracking;
import Project.models.Marca;

import java.util.ArrayList;
import java.util.List;

public class PlanificarCultivosImpl implements PlanificarCultivos {
    PlantacionOptima plantacionOptima = new PlantacionOptimaImpl();

    @Override
    public List<CultivoSeleccionado> obtenerPlanificacion(List<Cultivo> cultivosDisponibles, double[][] riesgos, String temporada) {
        List<CultivoSeleccionadoV2> cultivosResultado = new ArrayList<>();
        List<CultivoSeleccionadoV2> cultivosParcial = new ArrayList<>();
        Marca[][] marcas = new Marca[riesgos.length][riesgos[0].length];

        ESBacktracking parametro = new ESBacktracking(
                cultivosResultado,
                cultivosParcial,
                marcas,
                riesgos,
                temporada,
                cultivosDisponibles,
                0,
                0
        );

        plantacionOptima.backtracking(parametro);

        // En nuestra implementación utilizamos CultivoSeleccionadoV2 porque el riesgo asociado debe ser un double
        return parametro.cultivosResultado.stream()
                .map(c -> new CultivoSeleccionado(
                        c.getNombreCultivo(),
                        c.getEsquinaSuperiorIzquierda(),
                        c.getEsquinaInferiorDerecha(),
                        c.getMontoInvertido(),
                        c.getRiesgoAsociado(),
                        c.getGananciaObtenida()
                ))
                .toList();
    }
}
