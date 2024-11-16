package Project.impl;

import Lib.Cultivo;
import Lib.CultivoSeleccionado;
import Lib.PlanificarCultivos;

import java.util.ArrayList;
import java.util.List;

public class PlanificarCultivosImpl implements PlanificarCultivos {

    @Override
    public List<CultivoSeleccionado> obtenerPlanificacion(List<Cultivo> cultivosDisponibles, double[][] riesgos, String temporada) {
        return new ArrayList<>();
    }
}
