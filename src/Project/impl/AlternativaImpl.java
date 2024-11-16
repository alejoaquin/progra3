package Project.impl;

import Lib.Cultivo;
import Lib.CultivoSeleccionado;
import Project.Alternativa;
import Project.models.Marca;

import java.util.List;

public class AlternativaImpl implements Alternativa {
    @Override
    public List<CultivoSeleccionado> generar(Marca[][] marca, Cultivo cultivo, double[][] riesgos) {
        return List.of();
    }
}
