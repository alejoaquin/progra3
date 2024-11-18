package Project.models;

import Lib.Cultivo;

import java.util.ArrayList;
import java.util.List;

public class ESBacktrackingRelleno {
    public List<CultivoSeleccionadoV2> rellenoParcial = new ArrayList<>();
    public List<CultivoSeleccionadoV2> rellenoResultado = new ArrayList<>();
    public Marca[][] marcas;
    public double[][] riesgos;
    public List<Cultivo> cultivos = new ArrayList<>(); // cultivos filtrados por temporada
    public int etapa;
    public double gananciaMejor;
    public String cultivoDeRelleno;
    public List<Area> niveles = new ArrayList<>();

    public ESBacktrackingRelleno(List<CultivoSeleccionadoV2> rellenoParcial, List<CultivoSeleccionadoV2> rellenoResultado, Marca[][] marcas, double[][] riesgos, List<Cultivo> cultivos, int etapa, double gananciaMejor, String cultivoDeRelleno, List<Area> niveles) {
        this.rellenoParcial = rellenoParcial;
        this.rellenoResultado = rellenoResultado;
        this.marcas = marcas;
        this.riesgos = riesgos;
        this.cultivos = cultivos;
        this.etapa = etapa;
        this.gananciaMejor = gananciaMejor;
        this.cultivoDeRelleno = cultivoDeRelleno;
        this.niveles = niveles;
    }

    public ESBacktrackingRelleno() {
    }

    public List<CultivoSeleccionadoV2> getRellenoParcial() {
        return rellenoParcial;
    }

    public void setRellenoParcial(List<CultivoSeleccionadoV2> rellenoParcial) {
        this.rellenoParcial = rellenoParcial;
    }

    public List<CultivoSeleccionadoV2> getRellenoResultado() {
        return rellenoResultado;
    }

    public void setRellenoResultado(List<CultivoSeleccionadoV2> rellenoResultado) {
        this.rellenoResultado = rellenoResultado;
    }

    public Marca[][] getMarcas() {
        return marcas;
    }

    public void setMarcas(Marca[][] marcas) {
        this.marcas = marcas;
    }

    public double[][] getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(double[][] riesgos) {
        this.riesgos = riesgos;
    }

    public List<Cultivo> getCultivos() {
        return cultivos;
    }

    public void setCultivos(List<Cultivo> cultivos) {
        this.cultivos = cultivos;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public double getGananciaMejor() {
        return gananciaMejor;
    }

    public void setGananciaMejor(double gananciaMejor) {
        this.gananciaMejor = gananciaMejor;
    }

    public String getCultivoDeRelleno() {
        return cultivoDeRelleno;
    }

    public void setCultivoDeRelleno(String cultivoDeRelleno) {
        this.cultivoDeRelleno = cultivoDeRelleno;
    }

    public List<Area> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<Area> niveles) {
        this.niveles = niveles;
    }
}
