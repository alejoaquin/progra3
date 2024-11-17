package Project;


import Project.models.CultivoSeleccionadoV2;

import java.util.List;

public interface Ganancia {
    double calcularGananciaTotal(List<CultivoSeleccionadoV2> cultivos);

    // Tiene que modificar el riesgo asociado y la ganancia optenida
    void calcularGananciaCultivo(CultivoSeleccionadoV2 cultivo, double[][] riesgos);
}
