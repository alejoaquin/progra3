package Project;

import Lib.Cultivo;
import Lib.CultivoSeleccionado;
import Project.models.Marca;

import java.util.List;

public interface Alternativa {
    List<CultivoSeleccionado> generar(Marca[][] marca, Cultivo cultivo, double[][] riesgos);
}