package Project.models;

import Lib.Coordenada;

import java.util.List;

//ToDo: eliminar cuando tengamos versión 2 de la lib
public class CultivoSeleccionadoV2 {
    private String nombreCultivo;
    private Coordenada esquinaSuperiorIzquierda;
    private Coordenada esquinaInferiorDerecha;
    private double montoInvertido;
    private List<Double> riesgosAsociado;
    private double gananciaObtenida;

    public CultivoSeleccionadoV2(String nombreCultivo, Coordenada esquinaSuperiorIzquierda, Coordenada esquinaInferiorDerecha, double montoInvertido, List<Double> riesgosAsociado, double gananciaObtenida) {
        this.nombreCultivo = nombreCultivo;
        this.esquinaSuperiorIzquierda = esquinaSuperiorIzquierda;
        this.esquinaInferiorDerecha = esquinaInferiorDerecha;
        this.montoInvertido = montoInvertido;
        this.riesgosAsociado = riesgosAsociado;
        this.gananciaObtenida = gananciaObtenida;
    }

    public CultivoSeleccionadoV2() {
    }

    public String getNombreCultivo() {
        return nombreCultivo;
    }

    public void setNombreCultivo(String nombreCultivo) {
        this.nombreCultivo = nombreCultivo;
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

    public List<Double> getRiesgoAsociado() {
        return riesgosAsociado;
    }

    public void setRiesgoAsociado(List<Double> riesgosAsociado) {
        this.riesgosAsociado = riesgosAsociado;
    }

    public double getGananciaObtenida() {
        return gananciaObtenida;
    }

    public void setGananciaObtenida(double gananciaObtenida) {
        this.gananciaObtenida = gananciaObtenida;
    }
}
