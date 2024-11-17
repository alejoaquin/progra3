package Project;

import Lib.Cultivo;
import Project.models.CultivoSeleccionadoV2;
import Project.models.Marca;

import java.util.List;

public interface Alternativa {
    List<CultivoSeleccionadoV2> generar(Marca[][] marca, Cultivo cultivo, double[][] riesgos);
}
