package Project.impl;

import Lib.Cultivo;
import Project.Ganancia;
import Project.models.CultivoSeleccionadoV2;

import java.util.List;

public class GananciaImpl implements Ganancia {
    @Override
    public double calcularGananciaTotal(List<CultivoSeleccionadoV2> seleccionados) {
        return seleccionados.stream()
                .mapToDouble(CultivoSeleccionadoV2::getGananciaObtenida)
                .sum();
    }

    @Override
    public void calcularGananciaCultivo(CultivoSeleccionadoV2 seleccionado, double[][] riesgos, Cultivo cultivo) {
        int xInicio = seleccionado.getEsquinaSuperiorIzquierda().getX();
        int yInicio = seleccionado.getEsquinaSuperiorIzquierda().getY();
        int xFin = seleccionado.getEsquinaInferiorDerecha().getX();
        int yFin = seleccionado.getEsquinaInferiorDerecha().getY();

        double potencialSumatoria = 0;
        double riesgoSumatoria = 0;
        int cantParcelas = 0;

        for (int i = xInicio; i <= xFin; i++) {
            for (int j = yInicio; j <= yFin; j++) {
                cantParcelas++;
                double riesgoSeleccionado = riesgos[i][j];
                riesgoSumatoria += riesgoSeleccionado;
                potencialSumatoria += getPotencial(riesgoSeleccionado, cultivo);
            }
        }
        seleccionado.setRiesgoAsociado(riesgoSumatoria / cantParcelas);
        seleccionado.setGananciaObtenida(potencialSumatoria - cultivo.getInversionRequerida());
        seleccionado.setMontoInvertido(cultivo.getCostoPorParcela() * cantParcelas + cultivo.getInversionRequerida());
    }

    private double getPotencial(double riesgo, Cultivo cultivo) {
        return (1 - riesgo) * (cultivo.getPrecioDeVentaPorParcela() - cultivo.getCostoPorParcela());
    }
}
