package Project;

import Lib.CultivoSeleccionado;
import Project.models.Marca;

public interface EvaluacionDeAlternativa {
    boolean esValida(Marca[][] marcas, CultivoSeleccionado cultivo);

    // Verifica que si es adyacente al mismo cultivo siga cumpliendo la condición de no superar n+m > 11
    boolean esRellenoValido(Marca[][] marcas, CultivoSeleccionado mismoCultivoPlantado, CultivoSeleccionado alternativaAEvaluar);
}
