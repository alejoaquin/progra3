package Project.impl;

import Lib.Coordenada;
import Project.EvaluacionDeAlternativa;
import Project.models.CultivoSeleccionadoV2;
import Project.models.Marca;

import java.util.ArrayList;
import java.util.List;

public class EvaluacionDeAlternativaImpl implements EvaluacionDeAlternativa {

    @Override
    public boolean esValida(Marca[][] marcas, CultivoSeleccionadoV2 alternativa) {
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
    public boolean esRellenoValido(Marca[][] marcas, List<CultivoSeleccionadoV2> mismoCultivoPlantado, CultivoSeleccionadoV2 alternativaAEvaluar) {
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
//        Coordenada superiorIzquierdaPlantado = mismoCultivoPlantado.getEsquinaSuperiorIzquierda();
//        Coordenada inferiorDerechaPlantado = mismoCultivoPlantado.getEsquinaInferiorDerecha();
//
//        int xInicioPlantado = superiorIzquierdaPlantado.getX();
//        int yInicioPlantado = superiorIzquierdaPlantado.getY();
//        int xFinPlantado = inferiorDerechaPlantado.getX();
//        int yFinPlantado = inferiorDerechaPlantado.getY();
//
//        boolean esAdyacenteEnX = hayAdyacencia(xInicio, xFin, xInicioPlantado, xFinPlantado);
//        boolean esAdyacenteEnY = hayAdyacencia(yInicio, yFin, yInicioPlantado, yFinPlantado);
//
//        // Verificar la longitud de la adyacencia combinada
//        if (esAdyacenteEnX || esAdyacenteEnY) {
//            //ToDo: chequear si se suma sólo el eje adyacente o es total
//            int longitudXAdyacente = calcularLongitudAdyacente(xInicio, xFin, xInicioPlantado, xFinPlantado, esAdyacenteEnX);
//            int longitudYAdyacente = calcularLongitudAdyacente(yInicio, yFin, yInicioPlantado, yFinPlantado, esAdyacenteEnY);
//            int longitudTotalAdyacente = longitudXAdyacente + longitudYAdyacente;
//            // Si la longitud total de la adyacencia excede 11, no es válido
//            if (longitudTotalAdyacente > 11) {
//                return false;
//            }
//        }

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
        return Math.abs(alternativaInicio - finPlantado) == 1 || Math.abs(inicioPlantado - alternativaFin) == 1;
    }

    private void recorrerPuntosAdyacentes(Marca[][] marcas, CultivoSeleccionadoV2 alternativa) {
        // Obtener las coordenadas del cultivo a evaluar
        Coordenada superiorIzquierda = alternativa.getEsquinaSuperiorIzquierda();
        Coordenada inferiorDerecha = alternativa.getEsquinaInferiorDerecha();

        int xInicio = superiorIzquierda.getX();
        int yInicio = superiorIzquierda.getY();
        int xFin = inferiorDerecha.getX();
        int yFin = inferiorDerecha.getY();

        List<Coordenada> adyacentesArriba = new ArrayList<>();
        List<Coordenada> adyacentesAbajo = new ArrayList<>();
        List<Coordenada> adyacentesIzquierda = new ArrayList<>();
        List<Coordenada> adyacentesDerecha = new ArrayList<>();
    }

    private boolean hayAdyacenteSuperiorX(Marca[][] marcas, Coordenada coordenada, String nombreCultivo) {
        return nombreCultivo.equals(marcas[coordenada.getX() + 1][coordenada.getY()].nombre);
    }

    private boolean hayAdyacenteSuperiorY(Marca[][] marcas, Coordenada coordenada, String nombreCultivo) {
        return nombreCultivo.equals(marcas[coordenada.getX()][coordenada.getY() + 1].nombre);
    }

    private boolean hayAdyacenteInferiorX(Marca[][] marcas, Coordenada coordenada, String nombreCultivo) {
        return nombreCultivo.equals(marcas[coordenada.getX() - 1][coordenada.getY()].nombre);
    }

    private boolean hayAdyacenteInferiorY(Marca[][] marcas, Coordenada coordenada, String nombreCultivo) {
        return nombreCultivo.equals(marcas[coordenada.getX()][coordenada.getY() - 1].nombre);
    }

    private List<Coordenada> obtenerCordenadasAdyacentesDerecha(Marca[][] marcas, CultivoSeleccionadoV2 alternativa) {
        List<Coordenada> adyacentesDerecha = new ArrayList<>();

        int xInicio = alternativa.getEsquinaSuperiorIzquierda().getX();
        int xFin = alternativa.getEsquinaInferiorDerecha().getX();
        int yFin = alternativa.getEsquinaInferiorDerecha().getY();

        if (yFin + 1 < marcas[0].length) {
            for (int i = xInicio; i <= xFin; i++) {
                if (alternativa.getCultivo().getNombre().equals(marcas[i][yFin + 1].nombre)) {
                    Coordenada coordenada = new Coordenada(i, yFin + 1);
                    adyacentesDerecha.add(coordenada);
                }
            }
        }

        return adyacentesDerecha;
    }

    private List<Coordenada> obtenerCordenadasAdyacentesIzquierda(Marca[][] marcas, CultivoSeleccionadoV2 alternativa) {
        List<Coordenada> adyacentesIzquierda = new ArrayList<>();

        int xInicio = alternativa.getEsquinaSuperiorIzquierda().getX();
        int xFin = alternativa.getEsquinaInferiorDerecha().getX();
        int yInicio = alternativa.getEsquinaSuperiorIzquierda().getY();

        if (yInicio - 1 >= 0) {
            for (int i = xInicio; i <= xFin; i++) {
                if (alternativa.getCultivo().getNombre().equals(marcas[i][yInicio - 1].nombre)) {
                    Coordenada coordenada = new Coordenada(i, yInicio - 1);
                    adyacentesIzquierda.add(coordenada);
                }
            }
        }

        return adyacentesIzquierda;
    }

    private List<Coordenada> obtenerCordenadasAdyacentesArriba(Marca[][] marcas, CultivoSeleccionadoV2 alternativa) {
        List<Coordenada> adyacentesArriba = new ArrayList<>();

        int yInicio = alternativa.getEsquinaSuperiorIzquierda().getY();
        int yFin = alternativa.getEsquinaInferiorDerecha().getY();
        int xInicio = alternativa.getEsquinaSuperiorIzquierda().getX();

        if (xInicio - 1 >= 0) {
            for (int j = yInicio; j <= yFin; j++) {
                if (alternativa.getCultivo().getNombre().equals(marcas[xInicio - 1][j].nombre)) {
                    Coordenada coordenada = new Coordenada(xInicio - 1, j);
                    adyacentesArriba.add(coordenada);
                }
            }
        }

        return adyacentesArriba;
    }

    private List<Coordenada> obtenerCordenadasAdyacentesAbajo(Marca[][] marcas, CultivoSeleccionadoV2 alternativa) {
        List<Coordenada> adyacentesAbajo = new ArrayList<>();

        int yInicio = alternativa.getEsquinaSuperiorIzquierda().getY();
        int yFin = alternativa.getEsquinaInferiorDerecha().getY();
        int xFin = alternativa.getEsquinaInferiorDerecha().getX();

        if (xFin + 1 <= marcas.length) {
            for (int j = yInicio; j <= yFin; j++) {
                if (alternativa.getCultivo().getNombre().equals(marcas[xFin + 1][j].nombre)) {
                    Coordenada coordenada = new Coordenada(xFin + 1, j);
                    adyacentesAbajo.add(coordenada);
                }
            }
        }

        return adyacentesAbajo;
    }

}
