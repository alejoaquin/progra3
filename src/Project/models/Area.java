package Project.models;

import Lib.Coordenada;

public class Area {
    private Coordenada esquinaSuperiorIzquierda;
    private Coordenada esquinaInferiorDerecha;

    public Area(Coordenada esquinaSuperiorIzquierda, Coordenada esquinaInferiorDerecha) {
        this.esquinaSuperiorIzquierda = esquinaSuperiorIzquierda;
        this.esquinaInferiorDerecha = esquinaInferiorDerecha;
    }

    public Coordenada getEsquinaSuperiorIzquierda() {
        return esquinaSuperiorIzquierda;
    }

    public void setEsquinaSuperiorIzquierda(Coordenada esquinaSuperiorIzquierda) {
        this.esquinaSuperiorIzquierda = esquinaSuperiorIzquierda;
    }

    public Coordenada getEsquinaInferiorDerecha() {
        return esquinaInferiorDerecha;
    }

    public void setEsquinaInferiorDerecha(Coordenada esquinaInferiorDerecha) {
        this.esquinaInferiorDerecha = esquinaInferiorDerecha;
    }
}
