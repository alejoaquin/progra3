package Project.utils;

import Lib.Coordenada;
import Project.models.CultivoSeleccionadoV2;
import Project.models.Marca;
import Project.models.Rectangulo;

public class CultivoUtils {

    public static void imprimirMatrizMarca(Marca[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                // Verificamos si hay un cultivo en la posición
                if (matriz[i][j] != null) {
                    // Imprimir solo la primera letra del nombre del cultivo, seguido de '|'
                    System.out.print(matriz[i][j].nombre.charAt(0) + " | ");
                } else {
                    // Si la posición está vacía, se imprime un espacio, seguido de '|'
                    System.out.print("  | ");
                }
            }
            System.out.println(); // Nueva línea después de cada fila
        }
    }

    public static void imprimirMatrizConColision(Marca[][] matriz, CultivoSeleccionadoV2 cultivo) {
        // Obtener las coordenadas del cultivo
        Coordenada superiorIzquierda = cultivo.getEsquinaSuperiorIzquierda();
        Coordenada inferiorDerecha = cultivo.getEsquinaInferiorDerecha();

        int xInicio = superiorIzquierda.getX();
        int yInicio = superiorIzquierda.getY();
        int xFin = inferiorDerecha.getX();
        int yFin = inferiorDerecha.getY();
        String nombreCultivo = cultivo.getNombreCultivo();

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                // Verificamos si la celda está dentro del área del cultivo
                boolean dentroDelArea = i >= xInicio && i <= xFin && j >= yInicio && j <= yFin;

                if (dentroDelArea) {
                    // Si hay un cultivo y se produce una colisión, mostramos '#'
                    if (matriz[i][j] != null) {
                        System.out.print("# | ");
                    } else {
                        // Si está libre, mostramos solo la primera letra del nombre del cultivo
                        System.out.print(nombreCultivo.charAt(0) + " | ");
                    }
                } else {
                    // Si está fuera del área del cultivo, mostramos el cultivo en la matriz si existe
                    if (matriz[i][j] != null) {
                        System.out.print(matriz[i][j].nombre.charAt(0) + " | ");
                    } else {
                        System.out.print("  | ");
                    }
                }
            }
            System.out.println(); // Nueva línea después de cada fila
        }
    }

    public static void imprimirRectangulo(Rectangulo rect, Marca[][] matriz, int id) {
        System.out.println("Rectangle id" + id);
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                String text = estaEnMedio(i, j, rect) ? "x" : "";
                // Verificamos si hay un cultivo en la posición
                if (matriz[i][j] != null) {
                    // Imprimir solo la primera letra del nombre del cultivo, seguido de '|'
                    System.out.print(text + matriz[i][j].nombre.charAt(0) + " | ");
                } else {
                    // Si la posición está vacía, se imprime un espacio, seguido de '|'
                    String text2 = estaEnMedio(i, j, rect) ? "x" + " | " : "  | ";
                    System.out.print(text2);
                }
            }
            System.out.println(); // Nueva línea después de cada fila


        }

    }

    public static boolean estaEnMedio(int i, int j, Rectangulo rect) {
        boolean estaEnMedioX = rect.getxInicio() <= i && i <= rect.getxFin();
        boolean estaEnMedioY = rect.getyInicio() <= j && j <= rect.getyFin();

        return estaEnMedioX && estaEnMedioY;
    }
}
