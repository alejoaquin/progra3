package Project.impl;

import Lib.Coordenada;
import Project.AreaNiveles;
import Project.models.Area;

import java.util.ArrayList;
import java.util.List;

public class AreaNivelesImpl implements AreaNiveles {

    @Override
    public List<Area> generar(int rows, int cols) {
        List<Area> areas = new ArrayList<>();

        for (int xInicio = 0; xInicio < rows; xInicio++) {
            for (int yInicio = 0; yInicio < cols; yInicio++) {
                // Iteramos sobre todas las posibles esquinas inferiores derechas
                for (int xFin = xInicio; xFin < rows; xFin++) {
                    for (int yFin = yInicio; yFin < cols; yFin++) {
                        // Verificamos que la suma de las longitudes no supere 11
                        int longitudHorizontal = xFin - xInicio + 1;
                        int longitudVertical = yFin - yInicio + 1;

                        if (longitudHorizontal + longitudVertical <= 11) {
                            // Generar el CultivoSeleccionadoV2 para este rectángulo
                            areas.add(new Area(new Coordenada(xInicio, yInicio), new Coordenada(xFin, yFin)));
                        }
                    }
                }
            }

        }

        return areas;
    }
}
