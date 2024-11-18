package Project.impl;

import Lib.Coordenada;
import Project.EvaluacionDeAlternativa;
import Project.models.CultivoSeleccionadoV2;
import Project.models.Marca;
import Project.models.Rectangulo;

import java.util.ArrayList;
import java.util.List;

import static Project.utils.CultivoUtils.imprimirRectangulo;

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
    public boolean esRellenoValido(Marca[][] marcas, CultivoSeleccionadoV2 alternativaAEvaluar) {
        // Obtener las coordenadas del cultivo que se evalúa
        Coordenada superiorIzquierda = alternativaAEvaluar.getEsquinaSuperiorIzquierda();
        Coordenada inferiorDerecha = alternativaAEvaluar.getEsquinaInferiorDerecha();

        int xInicio = superiorIzquierda.getX();
        int yInicio = superiorIzquierda.getY();
        int xFin = inferiorDerecha.getX();
        int yFin = inferiorDerecha.getY();
        String nombreCultivo = alternativaAEvaluar.getNombreCultivo();

        // Verificar que la longitud horizontal + la longitud vertical no exceda 11
        int longitudHorizontal = xFin - xInicio + 1;
        int longitudVertical = yFin - yInicio + 1;
        if (longitudHorizontal + longitudVertical > 11) {
            return false;
        }

        List<Coordenada> esquinasSuperioresIzquierda = generarCoordenadasSuperiorIzquierda(marcas, xInicio, yInicio, xFin, yFin, nombreCultivo);
        esquinasSuperioresIzquierda.add(superiorIzquierda);
        List<Coordenada> esquinasInferioresDerecha = generarCoordenadasInferiorIdquierda(marcas, xInicio, yInicio, xFin, yFin, nombreCultivo);
        esquinasInferioresDerecha.add(inferiorDerecha);

        // Verificamos las adyacencias para formar nuevos rectángulos
        List<Rectangulo> rectangulosNuevos = generarRectangulosConCoordenadas(esquinasSuperioresIzquierda, esquinasInferioresDerecha, marcas, nombreCultivo);

        System.out.println("Cantidad de rectangulosNuevos: " + rectangulosNuevos.size());
        // Verificar si cada rectángulo cumple con la condición
        for (Rectangulo rect : rectangulosNuevos) {
            imprimirRectangulo(rect, marcas, rectangulosNuevos.indexOf(rect));
            if (rect.getLongitudHorizontal() + rect.getLongitudVertical() > 11) {
                return false;  // Si alguna adyacencia es inválida, retornar false
            }
        }

        return true;  // Si todas las adyacencias son válidas
    }

    // Generar los rectángulos adyacentes posibles
    public List<Rectangulo> generarRectangulosConCoordenadas(List<Coordenada> esquinasSuperioresIzquierda,
                                                             List<Coordenada> esquinasInferioresDerecha,
                                                             Marca[][] marcas,
                                                             String nombreCultivo) {
        List<Rectangulo> rectangulosValidos = new ArrayList<>();

        // Recorrer todas las combinaciones entre las EsquinasSuperioresIzquierda y EsquinasInferioresDerecha
        for (int i = 0; i < esquinasSuperioresIzquierda.size(); i++) {
            for (int j = 0; j < esquinasInferioresDerecha.size(); j++) {
                Coordenada esquinaSuperiorIzquierda = esquinasSuperioresIzquierda.get(i);
                Coordenada esquinaInferiorDerecha = esquinasInferioresDerecha.get(j);

                int xInicio = esquinaSuperiorIzquierda.getX();
                int yInicio = esquinaSuperiorIzquierda.getY();
                int xFin = esquinaInferiorDerecha.getX();
                int yFin = esquinaInferiorDerecha.getY();

                // Filtrar combinaciones incompatibles: la esquina superior izquierda debe estar por encima y a la izquierda de la esquina inferior derecha
                if (xInicio > xFin || yInicio > yFin) {
                    continue; // Si las coordenadas no forman un rectángulo válido, se descarta esta combinación
                }

                // Verificar si el área del rectángulo está completamente ocupada por el cultivo y no tiene celdas vacías
                if (esAreaLlenadaConCultivo(marcas, xInicio, yInicio, xFin, yFin, nombreCultivo)) {
                    // Si el área está completamente ocupada por el cultivo, creamos el rectángulo
                    Rectangulo rect = new Rectangulo(xInicio, yInicio, xFin, yFin);
                    rectangulosValidos.add(rect); // Añadimos el rectángulo válido a la lista
                }
            }
        }

        return rectangulosValidos;
    }

    // Método para verificar si el área está completamente ocupada por el mismo cultivo (sin celdas vacías)
    private boolean esAreaLlenadaConCultivo(Marca[][] marcas, int xInicio, int yInicio, int xFin, int yFin, String nombreCultivo) {
        for (int i = xInicio; i <= xFin; i++) {
            for (int j = yInicio; j <= yFin; j++) {
                // Si la celda está vacía o no es del mismo cultivo, retornar false
                if (marcas[i][j] == null || !marcas[i][j].nombre.equals(nombreCultivo)) {
                    return false;
                }
            }
        }
        return true;  // Si todas las celdas están ocupadas por el mismo cultivo
    }

    private List<Coordenada> generarCoordenadasSuperiorIzquierda(Marca[][] marcas, int xInicio, int yInicio, int xFin, int yFin, String nombreCultivo) {
        List<Coordenada> coordenadas = new ArrayList<>();

        // Recorrer posibles adyacencias horizontales hacia la izquierda
        if (yInicio != 0) {
            for (int i = xInicio; i <= xFin; i++) {
                for (int j = yInicio - 1; j >= 0; j--) {
                    if (marcas[i][j] != null && marcas[i][j].nombre.equals(nombreCultivo)) {
                        Coordenada nueva = new Coordenada(i, j);
                        coordenadas.add(nueva);
                    }
                }
            }
        }

        if (xInicio != 0) {
            for (int j = yInicio; j <= yFin; j++) {
                for (int i = xInicio - 1; i >= 0; i--) {
                    if (marcas[i][j] != null && marcas[i][j].nombre.equals(nombreCultivo)) {
                        Coordenada nueva = new Coordenada(i, j);
                        coordenadas.add(nueva);
                    }
                }
            }
        }

        return coordenadas;
    }

    private List<Coordenada> generarCoordenadasInferiorIdquierda(Marca[][] marcas, int xInicio, int yInicio, int xFin, int yFin, String nombreCultivo) {
        List<Coordenada> coordenadas = new ArrayList<>();
        int logitudMatrizX = marcas.length;
        int logitudMatrizY = marcas[0].length;

        // Recorrer posibles adyacencias horizontales hacia la izquierda
        if (yInicio != logitudMatrizY) {
            for (int i = xInicio; i <= xFin; i++) {
                for (int j = yFin + 1; j <= logitudMatrizY; j++) {
                    if (marcas[i][j] != null && marcas[i][j].nombre.equals(nombreCultivo)) {
                        Coordenada nueva = new Coordenada(i, j);
                        coordenadas.add(nueva);
                    }
                }
            }
        }

        if (xInicio != logitudMatrizX) {
            for (int j = yInicio; j <= yFin; j++) {
                for (int i = xFin + 1; i <= logitudMatrizX; i++) {
                    if (marcas[i][j] != null && marcas[i][j].nombre.equals(nombreCultivo)) {
                        Coordenada nueva = new Coordenada(i, j);
                        coordenadas.add(nueva);
                    }
                }
            }
        }

        return coordenadas;
    }

}
