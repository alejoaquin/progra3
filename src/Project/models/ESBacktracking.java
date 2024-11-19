package Project.models;

import Lib.Cultivo;

import java.util.ArrayList;
import java.util.List;

public class ESBacktracking {
    public List<CultivoSeleccionadoV2> cultivosResultado = new ArrayList<>();
    public List<CultivoSeleccionadoV2> cultivosParcial = new ArrayList<>();
    public Marca[][] marcas; // para solución parcial
    public double[][] riesgos;
    public String temporada;
    public List<Cultivo> cultivos = new ArrayList<>();
    public int etapa;
    public double gananciaMejor;

    public ESBacktracking(List<CultivoSeleccionadoV2> cultivosResultado, List<CultivoSeleccionadoV2> cultivosParcial, Marca[][] marcas, double[][] riesgos, String temporada, List<Cultivo> cultivos, int etapa, double gananciaMejor) {
        this.cultivosResultado = cultivosResultado;
        this.cultivosParcial = cultivosParcial;
        this.marcas = marcas;
        this.riesgos = riesgos;
        this.temporada = temporada;
        this.cultivos = cultivos;
        this.etapa = etapa;
        this.gananciaMejor = gananciaMejor;
    }
}
