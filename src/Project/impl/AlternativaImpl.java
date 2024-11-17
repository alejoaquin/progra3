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

/*
GenerarAlternativasValidas(Matriz<Cultivo> solucionParcial, entero E) -> Vector<Alternativa>
    alternativas ← Vector<Alternativa> vacío

    // Para cada posición posible en el campo
    PARA fila DESDE 0 HASTA solucionParcial.filas - 1 HACER
        PARA col DESDE 0 HASTA solucionParcial.columnas - 1 HACER
            // Para cada dimensión rectangular posible que cumpla n1+m1 ≤ 11
            PARA n1 DESDE 1 HASTA 10 HACER
                PARA m1 DESDE 1 HASTA (11 - n1) HACER
                    SI EsAlternativaValida(solucionParcial, fila, col, n1,
  m1) ENTONCES
                        nueva_alt ← Alternativa{
                            fila_inicio: fila,
                            col_inicio: col,
                            n1: n1,
                            m1: m1
                        }
                        alternativas.agregar(nueva_alt)
                    FIN SI
                FIN PARA
            FIN PARA
        FIN PARA
    FIN PARA

    RETORNAR alternativas

 */
