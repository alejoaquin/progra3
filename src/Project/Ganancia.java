package Project;

import Lib.CultivoSeleccionado;

import java.util.List;

public interface Ganancia {
    double calcularGananciaTotal(List<CultivoSeleccionado> cultivos);
    double calcularGananciaCultivo(CultivoSeleccionado cultivo);
}
