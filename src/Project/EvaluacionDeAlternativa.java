package Project;

import Lib.CultivoSeleccionado;
import Project.models.Marca;

public interface EvaluacionDeAlternativa {
    // Verifica área válida: si está libre, si entra en la matriz y que no supere n+m > 11
    boolean esValida(Marca[][] marcas, CultivoSeleccionado cultivo);

    // Verifica que si es adyacente al mismo cultivo siga cumpliendo la condición de no superar n+m > 11
    boolean esRellenoValido(Marca[][] marcas, CultivoSeleccionado cultivo);
}
