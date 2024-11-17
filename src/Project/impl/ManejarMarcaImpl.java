package Project.impl;

import Lib.Coordenada;
import Project.ManejarMarca;
import Project.models.CultivoSeleccionadoV2;
import Project.models.Marca;

public class ManejarMarcaImpl implements ManejarMarca {
    @Override
    public void marcarMatriz(CultivoSeleccionadoV2 cultivo, Marca[][] matrizActual, boolean marcar) {
        // Obtener las coordenadas del cultivo
        Coordenada superiorIzquierda = cultivo.getEsquinaSuperiorIzquierda();
        Coordenada inferiorDerecha = cultivo.getEsquinaInferiorDerecha();

        int xInicio = superiorIzquierda.getX();
        int yInicio = superiorIzquierda.getY();
        int xFin = inferiorDerecha.getX();
        int yFin = inferiorDerecha.getY();

        // Recorrer las posiciones en la matriz según el área del cultivo
        for (int i = xInicio; i <= xFin; i++) {
            for (int j = yInicio; j <= yFin; j++) {
                if (marcar) {
                    // Si 'marcar' es verdadero, asignamos el cultivo en la posición
                    matrizActual[i][j] = new Marca(cultivo.getNombreCultivo());
                } else {
                    // Si 'marcar' es falso, desmarcamos, es decir, dejamos la celda vacía
                    matrizActual[i][j] = null;
                }
            }
        }
    }
}
