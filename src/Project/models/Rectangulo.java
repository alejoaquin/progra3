package Project.models;

public class Rectangulo {
    public int getxInicio() {
        return xInicio;
    }

    public int getyInicio() {
        return yInicio;
    }

    public int getxFin() {
        return xFin;
    }

    public int getyFin() {
        return yFin;
    }

    private int xInicio, yInicio, xFin, yFin;

    public Rectangulo(int xInicio, int yInicio, int xFin, int yFin) {
        this.xInicio = xInicio;
        this.yInicio = yInicio;
        this.xFin = xFin;
        this.yFin = yFin;
    }

    public int getLongitudHorizontal() {
        return xFin - xInicio + 1;
    }

    public int getLongitudVertical() {
        return yFin - yInicio + 1;
    }
}