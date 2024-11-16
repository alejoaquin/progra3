package Project;

import Lib.Cultivo;
import Lib.CultivoSeleccionado;
import Project.models.Marca;

import java.util.List;

public interface GenerarAlternativa {
    List<CultivoSeleccionado> generar(Marca[][] marca, Cultivo cultivo);
}