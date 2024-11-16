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
    public double gananciaParcial;
    public double gananciaMejor;
}
