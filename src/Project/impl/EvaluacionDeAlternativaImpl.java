package Project.impl;

import Lib.Coordenada;
import Lib.CultivoSeleccionado;
import Project.EvaluacionDeAlternativa;
import Project.models.Marca;

public class EvaluacionDeAlternativaImpl implements EvaluacionDeAlternativa {

    @Override
    public boolean esValida(Marca[][] marcas, CultivoSeleccionado alternativa) {
        // Obtener las coordenadas del cultivo
        Coordenada superiorIzquierda = alternativa.getEsquinaSuperiorIzquierda();
        Coordenada inferiorDerecha = alternativa.getEsquinaInferiorDerecha();

        int xInicio = superiorIzquierda.getX();
        int yInicio = superiorIzquierda.getY();
        int xFin = inferiorDerecha.getX();
        int yFin = inferiorDerecha.getY();

        // Verificar si la suma de longitudes horizontal y vertical no supera 11
        int longitudHorizontal = xFin - xInicio + 1;
        int longitudVertical = yFin - yInicio + 1;

        if (longitudHorizontal + longitudVertical > 11) {
            return false;
        }

        // Verificar si todas las posiciones dentro del área están libres
        for (int i = xInicio; i <= xFin; i++) {
            for (int j = yInicio; j <= yFin; j++) {
                if (marcas[i][j] != null) { // Si hay un cultivo ya plantado
                    return false;
                }
            }
        }

        // Si no se ha encontrado ninguna colisión, es válido
        return true;
    }

    @Override
    public boolean esRellenoValido(Marca[][] marcas, CultivoSeleccionado mismoCultivoPlantado, CultivoSeleccionado alternativaAEvaluar) {
        // Obtener las coordenadas del cultivo a evaluar
        Coordenada superiorIzquierda = alternativaAEvaluar.getEsquinaSuperiorIzquierda();
        Coordenada inferiorDerecha = alternativaAEvaluar.getEsquinaInferiorDerecha();

        int xInicio = superiorIzquierda.getX();
        int yInicio = superiorIzquierda.getY();
        int xFin = inferiorDerecha.getX();
        int yFin = inferiorDerecha.getY();
        String nombreCultivo = alternativaAEvaluar.getNombreCultivo();

        // Verificar si la suma de longitudes horizontal y vertical no supera 11
        int longitudHorizontal = xFin - xInicio + 1;
        int longitudVertical = yFin - yInicio + 1;
        if (longitudHorizontal + longitudVertical > 11) {
            return false;  // No cumple la restricción de longitud total
        }

        // Si no hay cultivo plantado, verificamos solo la nueva alternativa
        if (mismoCultivoPlantado == null) {
            return verificarPosicionesLibres(marcas, xInicio, yInicio, xFin, yFin, nombreCultivo);
        }

        // Si hay un cultivo plantado, verificamos la adyacencia entre ambos cultivos
        Coordenada superiorIzquierdaPlantado = mismoCultivoPlantado.getEsquinaSuperiorIzquierda();
        Coordenada inferiorDerechaPlantado = mismoCultivoPlantado.getEsquinaInferiorDerecha();

        int xInicioPlantado = superiorIzquierdaPlantado.getX();
        int yInicioPlantado = superiorIzquierdaPlantado.getY();
        int xFinPlantado = inferiorDerechaPlantado.getX();
        int yFinPlantado = inferiorDerechaPlantado.getY();

        // Verificar la longitud de la adyacencia combinada
        if (hayAdyacencia(xInicio, xFin, xInicioPlantado, xFinPlantado) || hayAdyacencia(yInicio, yFin, yInicioPlantado, yFinPlantado)
        ) {
            //ToDo: chequear si se suma sólo el eje adyacente o es total
            int longitudXAdyacente = calcularLongitudAdyacente(xInicio, xFin, xInicioPlantado, xFinPlantado);
            int longitudYAdyacente = calcularLongitudAdyacente(yInicio, yFin, yInicioPlantado, yFinPlantado);
            int longitudTotalAdyacente = longitudXAdyacente + longitudYAdyacente;
            // Si la longitud total de la adyacencia excede 11, no es válido
            if (longitudTotalAdyacente > 11) {
                return false;
            }
        }

        // Verificar las posiciones ocupadas por otros cultivos y la adyacencia
        return verificarPosicionesLibres(marcas, xInicio, yInicio, xFin, yFin, nombreCultivo);
    }

    // Verificar si las posiciones dentro del área están libres o adyacentes
    private boolean verificarPosicionesLibres(Marca[][] marcas, int xInicio, int yInicio, int xFin, int yFin, String nombreCultivo) {
        for (int i = xInicio; i <= xFin; i++) {
            for (int j = yInicio; j <= yFin; j++) {
                // Verificar si la posición está ocupada por otro cultivo
                if (marcas[i][j] != null && !marcas[i][j].nombre.equals(nombreCultivo)) {
                    return false; // Colisión con otro cultivo
                }
            }
        }
        return true; // No hay colisión
    }

    private boolean hayAdyacencia(int alternativaInicio, int alternativaFin, int inicioPlantado, int finPlantado) {
        return (inicioPlantado <= alternativaInicio && alternativaInicio <= finPlantado) || (alternativaInicio <= inicioPlantado && inicioPlantado <= alternativaFin);
    }

    private int calcularLongitudAdyacente(int alternativaInicio, int alternativaFin, int inicioPlantado, int finPlantado) {
        return Math.abs(Math.max(alternativaFin, finPlantado) - Math.min(alternativaInicio, inicioPlantado)) + 1;
    }
}
