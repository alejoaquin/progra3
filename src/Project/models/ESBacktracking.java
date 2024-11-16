package Project.models;

import Lib.Cultivo;
import Lib.CultivoSeleccionado;

import java.util.ArrayList;
import java.util.List;

public class ESBacktracking {
    public List<CultivoSeleccionado> cultivosResultado = new ArrayList<>();
    public List<CultivoSeleccionado> cultivosParcial = new ArrayList<>();
    public Marca[][] marcas; // para solución parcial
    public double[][] riesgos;
    public String temporada;
    public List<Cultivo> cultivos = new ArrayList<>();
    public int etapa;
    public double gananciaMejor;

    public ESBacktracking(List<CultivoSeleccionado> cultivosResultado, List<CultivoSeleccionado> cultivosParcial, Marca[][] marcas, double[][] riesgos, String temporada, List<Cultivo> cultivos, int etapa, double gananciaMejor) {
        this.cultivosResultado = cultivosResultado;
        this.cultivosParcial = cultivosParcial;
        this.marcas = marcas;
        this.riesgos = riesgos;
        this.temporada = temporada;
        this.cultivos = cultivos;
        this.etapa = etapa;
        this.gananciaMejor = gananciaMejor;
    }

    public ESBacktracking clonar() {
        return new ESBacktracking(
                this.cultivosResultado,
                this.cultivosParcial,
                this.marcas,
                this.riesgos,
                this.temporada,
                this.cultivos,
                this.etapa,
                this.gananciaMejor
        );
    }
}
