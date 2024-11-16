package Project.impl;

import Lib.CultivoSeleccionado;
import Project.EvaluacionDeAlternativa;
import Project.models.Marca;

public class EvaluacionDeAlternativaImpl implements EvaluacionDeAlternativa {
    @Override
    public boolean esValida(Marca[][] marcas, CultivoSeleccionado cultivo) {
        return false;
    }

    @Override
    public boolean esRellenoValido(Marca[][] marcas, CultivoSeleccionado cultivo) {
        return false;
    }
}
