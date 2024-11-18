package Project.models;

import Lib.Cultivo;

import java.util.ArrayList;
import java.util.List;

public class ESBacktrackingRelleno {
    public List<CultivoSeleccionadoV2> plantacion = new ArrayList<>();
    public List<CultivoSeleccionadoV2> rellenoParcial = new ArrayList<>();
    public List<CultivoSeleccionadoV2> rellenoResultado = new ArrayList<>();
    public Marca[][] marcas;
    public double[][] riesgos;
    public List<Cultivo> cultivos = new ArrayList<>(); // cultivos filtrados por temporada
    public int etapa;
    public double gananciaMejor;
    public String cultivoDeRelleno;
    public List<Area> niveles = new ArrayList<>();
}
