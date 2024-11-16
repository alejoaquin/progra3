package Project.utils;

import Lib.Coordenada;
import Lib.CultivoSeleccionado;

public class CultivoUtils {
    public static boolean coordenadasExcluyentes(CultivoSeleccionado cultivo1, CultivoSeleccionado cultivo2) {
        Coordenada c1SuperiorIzquierda = cultivo1.getEsquinaSuperiorIzquierda();
        Coordenada c1InferiorDerecha = cultivo1.getEsquinaInferiorDerecha();

        Coordenada c2SuperiorIzquierda = cultivo2.getEsquinaSuperiorIzquierda();
        Coordenada c2InferiorDerecha = cultivo2.getEsquinaInferiorDerecha();

        return c1InferiorDerecha.getX() < c2SuperiorIzquierda.getX() ||
                c1SuperiorIzquierda.getX() > c2InferiorDerecha.getX() ||
                c1InferiorDerecha.getY() < c2SuperiorIzquierda.getY() ||
                c1SuperiorIzquierda.getY() > c2InferiorDerecha.getY();
    }
}
