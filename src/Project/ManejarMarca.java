package Project;

import Lib.CultivoSeleccionado;
import Project.models.Marca;

// Marca la matriz
public interface ManejarMarca {
    // Parametro <marcar> si es true agregamos el cultivo, sino ponemos null
    //ej: M[0][1] = marcar? new Marca(nombre) : null;
    Marca[][] marcarMatriz(CultivoSeleccionado cultivo, Marca[][] matrizActual, boolean marcar);
}
