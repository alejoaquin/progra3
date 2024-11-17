package Project;


import Project.models.CultivoSeleccionadoV2;
import Project.models.Marca;

// Marca la matriz
public interface ManejarMarca {
    // Parametro <marcar> si es true agregamos el cultivo, sino ponemos null
    //ej: M[0][1] = marcar? new Marca(nombre) : null;
    Marca[][] marcarMatriz(CultivoSeleccionadoV2 cultivo, Marca[][] matrizActual, boolean marcar);
}
