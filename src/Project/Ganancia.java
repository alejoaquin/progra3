package Project;


import Project.models.CultivoSeleccionadoV2;

import java.util.List;

public interface Ganancia {
    double calcularGananciaTotal(List<CultivoSeleccionadoV2> cultivos);

    void calcularGananciaCultivo(CultivoSeleccionadoV2 cultivo, double[][] riesgos);
}
