package Project.models;

import Lib.Coordenada;
import Lib.Cultivo;

//ToDo: eliminar cuando tengamos versión 2 de la lib
public class CultivoSeleccionadoV2 {
    private Cultivo cultivo;
    private Coordenada esquinaSuperiorIzquierda;
    private Coordenada esquinaInferiorDerecha;
    private double montoInvertido;
    private double riesgoAsociado;
    private double gananciaObtenida;

    public CultivoSeleccionadoV2(Cultivo cultivo, Coordenada esquinaSuperiorIzquierda, Coordenada esquinaInferiorDerecha, double montoInvertido, double riesgoAsociado, double gananciaObtenida) {
        this.cultivo = cultivo;
        this.esquinaSuperiorIzquierda = esquinaSuperiorIzquierda;
        this.esquinaInferiorDerecha = esquinaInferiorDerecha;
        this.montoInvertido = montoInvertido;
        this.riesgoAsociado = riesgoAsociado;
        this.gananciaObtenida = gananciaObtenida;
    }

    public CultivoSeleccionadoV2() {
    }

    public String getNombreCultivo() {
        return cultivo.getNombre();
    }

    public Cultivo getCultivo() {
        return cultivo;
    }

    public void setCultivo(Cultivo cultivo) {
        this.cultivo = cultivo;
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

    public double getMontoInvertido() {
        return montoInvertido;
    }

    public void setMontoInvertido(double montoInvertido) {
        this.montoInvertido = montoInvertido;
    }

    public double getRiesgoAsociado() {
        return this.riesgoAsociado;
    }

    public double getGananciaObtenida() {
        return gananciaObtenida;
    }

    public void setGananciaObtenida(double gananciaObtenida) {
        this.gananciaObtenida = gananciaObtenida;
    }
}
