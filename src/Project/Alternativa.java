package Project;

import Lib.Cultivo;
import Project.models.Area;
import Project.models.CultivoSeleccionadoV2;
import Project.models.Marca;

import java.util.List;

public interface Alternativa {
    List<CultivoSeleccionadoV2> generar(Marca[][] marca, Cultivo cultivo, double[][] riesgos);

    // agregar validación de alternativa
    List<CultivoSeleccionadoV2> generarAlternativaRelleno(Marca[][] marca, List<Cultivo> cultivos, double[][] riesgos, Area area, String cultivoDeRelleno);
}
