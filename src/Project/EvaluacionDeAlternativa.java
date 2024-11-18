package Project;


import Project.models.CultivoSeleccionadoV2;
import Project.models.Marca;

import java.util.List;

public interface EvaluacionDeAlternativa {
    boolean esValida(Marca[][] marcas, CultivoSeleccionadoV2 cultivo);

    // Verifica que si es adyacente al mismo cultivo siga cumpliendo la condición de no superar n+m > 11
    boolean esRellenoValido(Marca[][] marcas, CultivoSeleccionadoV2 alternativaAEvaluar);
}
