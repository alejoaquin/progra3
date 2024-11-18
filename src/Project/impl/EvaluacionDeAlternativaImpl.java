package Project.impl;

import Lib.Coordenada;
import Project.EvaluacionDeAlternativa;
import Project.ManejarMarca;
import Project.models.CultivoSeleccionadoV2;
import Project.models.Marca;
import Project.models.Rectangulo;

import java.util.ArrayList;
import java.util.List;

public class EvaluacionDeAlternativaImpl implements EvaluacionDeAlternativa {
    ManejarMarca manejarMarca = new ManejarMarcaImpl();

    @Override
    public boolean esValida(Marca[][] marcas, CultivoSeleccionadoV2 alternativa) {
        return areaValida(alternativa) && areaLibre(marcas, alternativa);
    }

    @Override
    public boolean esRellenoValido(Marca[][] marcas, CultivoSeleccionadoV2 alternativaAEvaluar) {
        if (!areaValida(alternativaAEvaluar) || !areaLibre(marcas, alternativaAEvaluar)) {
            return false;
        }
        // Obtener las coordenadas del cultivo que se evalúa
        Coordenada superiorIzquierda = alternativaAEvaluar.getEsquinaSuperiorIzquierda();
        Coordenada inferiorDerecha = alternativaAEvaluar.getEsquinaInferiorDerecha();

        int xInicio = superiorIzquierda.getX();
        int yInicio = superiorIzquierda.getY();
        int xFin = inferiorDerecha.getX();
        int yFin = inferiorDerecha.getY();
        String nombreCultivo = alternativaAEvaluar.getNombreCultivo();

        manejarMarca.marcarMatriz(alternativaAEvaluar, marcas, true);

        List<Coordenada> esquinasSuperioresIzquierda = generarCoordenadasSuperiorIzquierda(marcas, xInicio, yInicio, xFin, yFin, nombreCultivo);
        esquinasSuperioresIzquierda.add(superiorIzquierda);
        List<Coordenada> esquinasInferioresDerecha = generarCoordenadasInferiorDerecha(marcas, xInicio, yInicio, xFin, yFin, nombreCultivo);
        esquinasInferioresDerecha.add(inferiorDerecha);

        // Verificamos las adyacencias para formar nuevos rectángulos
        List<Rectangulo> rectangulosAreaInvalida = optenerRectangulosAreasInvalidasConCoordenadas(esquinasSuperioresIzquierda, esquinasInferioresDerecha, marcas, nombreCultivo);

        manejarMarca.marcarMatriz(alternativaAEvaluar, marcas, false);
        return rectangulosAreaInvalida.isEmpty();  // Si todas las adyacencias son válidas
    }

    // Generar los rectángulos adyacentes posibles
    public List<Rectangulo> optenerRectangulosAreasInvalidasConCoordenadas(List<Coordenada> esquinasSuperioresIzquierda,
                                                                           List<Coordenada> esquinasInferioresDerecha,
                                                                           Marca[][] marcas,
                                                                           String nombreCultivo) {
        List<Rectangulo> rectangulosValidos = new ArrayList<>();

        // Recorrer todas las combinaciones entre las EsquinasSuperioresIzquierda y EsquinasInferioresDerecha
        for (int i = 0; i < esquinasSuperioresIzquierda.size(); i++) {
            for (int j = 0; j < esquinasInferioresDerecha.size(); j++) {
                Coordenada esquinaSuperiorIzquierda = esquinasSuperioresIzquierda.get(i);
                Coordenada esquinaInferiorDerecha = esquinasInferioresDerecha.get(j);

                if (rectanguloValido(esquinaSuperiorIzquierda, esquinaInferiorDerecha)) {
                    // Verificar si el área del rectángulo está completamente ocupada por el cultivo y no tiene celdas vacías
                    if (areaSoloDelCultivo(marcas, esquinaSuperiorIzquierda, esquinaInferiorDerecha, nombreCultivo)) {
                        // Si el área está completamente ocupada por el cultivo, creamos el rectángulo
                        Rectangulo rect = new Rectangulo(
                                esquinaSuperiorIzquierda.getX(),
                                esquinaSuperiorIzquierda.getY(),
                                esquinaInferiorDerecha.getX(),
                                esquinaInferiorDerecha.getY()
                        );

                        if (rect.getLongitudHorizontal() + rect.getLongitudVertical() > 11) {
                            rectangulosValidos.add(rect); // Añadimos el rectángulo válido a la lista
                        }
                    }
                }
            }
        }

        return rectangulosValidos;
    }

    private List<Coordenada> generarCoordenadasSuperiorIzquierda(Marca[][] marcas, int xInicio, int yInicio, int xFin, int yFin, String nombreCultivo) {
        List<Coordenada> coordenadas = new ArrayList<>();

        // Recorrer posibles adyacencias horizontales hacia la izquierda
        if (yInicio > 0) {
            for (int i = xInicio; i <= xFin; i++) {
                for (int j = yInicio - 1; j >= 0; j--) {
                    if (marcas[i][j] != null && marcas[i][j].nombre.equals(nombreCultivo)) {
                        coordenadas.add(new Coordenada(i, j));
                    }
                }
            }
        }

        // Recorrer hacia la parte izquierda (celdas arriba)
        if (xInicio > 0) {
            for (int j = yInicio; j <= yFin; j++) {
                for (int i = xInicio - 1; i >= 0; i--) {
                    if (marcas[i][j] != null && marcas[i][j].nombre.equals(nombreCultivo)) {
                        coordenadas.add(new Coordenada(i, j));
                    }
                }
            }
        }

        return coordenadas;
    }

    private List<Coordenada> generarCoordenadasInferiorDerecha(Marca[][] marcas, int xInicio, int yInicio, int xFin, int yFin, String nombreCultivo) {
        List<Coordenada> coordenadas = new ArrayList<>();
        int longitudMatrizX = marcas.length;
        int longitudMatrizY = marcas[0].length;

        // Recorrer posibles adyacencias horizontales hacia la izquierda
        if (yFin < longitudMatrizY - 1) {
            for (int i = xInicio; i <= xFin; i++) {
                for (int j = yFin + 1; j < longitudMatrizY; j++) {
                    if (marcas[i][j] != null && marcas[i][j].nombre.equals(nombreCultivo)) {
                        coordenadas.add(new Coordenada(i, j));
                    }
                }
            }
        }

        // Recorrer hacia la derecha (celdas hacia abajo)
        if (xFin < longitudMatrizX - 1) {
            for (int j = yInicio; j <= yFin; j++) {
                for (int i = xFin + 1; i < longitudMatrizX; i++) {
                    if (marcas[i][j] != null && marcas[i][j].nombre.equals(nombreCultivo)) {
                        coordenadas.add(new Coordenada(i, j));
                    }
                }
            }
        }

        return coordenadas;
    }

    private boolean rectanguloValido(Coordenada esquinaSuperiorIzquierda, Coordenada esquinaInferiorDerecha) {
        return esquinaSuperiorIzquierda.getX() <= esquinaInferiorDerecha.getX() &&
                esquinaSuperiorIzquierda.getY() <= esquinaInferiorDerecha.getY();
    }

    private boolean areaSoloDelCultivo(Marca[][] marcas, Coordenada esquinaSuperiorIzquierda,
                                       Coordenada esquinaInferiorDerecha, String nombreCultivo) {
        int xInicio = esquinaSuperiorIzquierda.getX();
        int yInicio = esquinaSuperiorIzquierda.getY();
        int xFin = esquinaInferiorDerecha.getX();
        int yFin = esquinaInferiorDerecha.getY();

        for (int i = xInicio; i <= xFin; i++) {
            for (int j = yInicio; j <= yFin; j++) {
                if (marcas[i][j] == null || !marcas[i][j].nombre.equals(nombreCultivo)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean areaValida(CultivoSeleccionadoV2 alternativa) {
        int xInicio = alternativa.getEsquinaSuperiorIzquierda().getX();
        int yInicio = alternativa.getEsquinaSuperiorIzquierda().getY();
        int xFin = alternativa.getEsquinaInferiorDerecha().getX();
        int yFin = alternativa.getEsquinaInferiorDerecha().getY();

        int longitudHorizontal = xFin - xInicio + 1;
        int longitudVertical = yFin - yInicio + 1;

        return longitudHorizontal + longitudVertical <= 11;
    }

    private boolean areaLibre(Marca[][] marcas, CultivoSeleccionadoV2 alternativa) {
        int xInicio = alternativa.getEsquinaSuperiorIzquierda().getX();
        int yInicio = alternativa.getEsquinaSuperiorIzquierda().getY();
        int xFin = alternativa.getEsquinaInferiorDerecha().getX();
        int yFin = alternativa.getEsquinaInferiorDerecha().getY();

        for (int i = xInicio; i <= xFin; i++) {
            for (int j = yInicio; j <= yFin; j++) {
                if (marcas[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }
}
