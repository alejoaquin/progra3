import Lib.Coordenada;
import Lib.Cultivo;
import Lib.CultivoSeleccionado;
import Lib.PlanificarCultivos;
import Project.*;
import Project.impl.*;
import Project.models.*;
import Project.utils.CultivoUtils;

import java.util.ArrayList;
import java.util.List;

;

public class Main {
    public static void main(String[] args) {
        PlanificarCultivos planificador = new PlanificarCultivosImpl();
        List<Cultivo> cultivos = new ArrayList<>();

        Cultivo cultivo = new Cultivo();
        cultivo.setNombre("Lechuga");
        cultivo.setCostoPorParcela(100.5);
        cultivo.setInversionRequerida(2000);
        cultivo.setPrecioDeVentaPorParcela(500);
        cultivo.setTemporadaOptima("Otoño");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Remolacha");
        cultivo.setCostoPorParcela(105);
        cultivo.setInversionRequerida(1000);
        cultivo.setPrecioDeVentaPorParcela(450);
        cultivo.setTemporadaOptima("Otoño");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Calabaza");
        cultivo.setCostoPorParcela(97);
        cultivo.setInversionRequerida(1500);
        cultivo.setPrecioDeVentaPorParcela(475);
        cultivo.setTemporadaOptima("Otoño");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Tomate");
        cultivo.setCostoPorParcela(110.0);
        cultivo.setInversionRequerida(2500);
        cultivo.setPrecioDeVentaPorParcela(600);
        cultivo.setTemporadaOptima("Verano");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Zanahoria");
        cultivo.setCostoPorParcela(90.0);
        cultivo.setInversionRequerida(1200);
        cultivo.setPrecioDeVentaPorParcela(430);
        cultivo.setTemporadaOptima("Primavera");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Maíz");
        cultivo.setCostoPorParcela(130.0);
        cultivo.setInversionRequerida(2200);
        cultivo.setPrecioDeVentaPorParcela(580);
        cultivo.setTemporadaOptima("Verano");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Espinaca");
        cultivo.setCostoPorParcela(85.0);
        cultivo.setInversionRequerida(1400);
        cultivo.setPrecioDeVentaPorParcela(460);
        cultivo.setTemporadaOptima("Invierno");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Brócoli");
        cultivo.setCostoPorParcela(115.0);
        cultivo.setInversionRequerida(1800);
        cultivo.setPrecioDeVentaPorParcela(510);
        cultivo.setTemporadaOptima("Otoño");
        cultivos.add(cultivo);

        int size = 2;

        double[][] riesgos = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                riesgos[i][j] = (i + j) / 200.0;
            }
        }
        //imprimirMatrizDeRiesgos(riesgos);

        List<CultivoSeleccionado> res = planificador.obtenerPlanificacion(cultivos, riesgos, "Otoño");
        imprimirResultado(res);
    }

    private static void testBacktrackingConRelleno() {
        long startTime = System.currentTimeMillis();
        System.out.println("startTime: " + startTime);
        PlantacionOptima plantacionOptima = new PlantacionOptimaImpl();
        List<Cultivo> cultivos = new ArrayList<>();

        Cultivo cultivo = new Cultivo();
        cultivo.setNombre("Lechuga");
        cultivo.setCostoPorParcela(1);
        cultivo.setInversionRequerida(1);
        cultivo.setPrecioDeVentaPorParcela(500);
        cultivo.setTemporadaOptima("Otoño");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Remolacha");
        cultivo.setCostoPorParcela(1);
        cultivo.setInversionRequerida(1);
        cultivo.setPrecioDeVentaPorParcela(450);
        cultivo.setTemporadaOptima("Otoño");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Calabaza");
        cultivo.setCostoPorParcela(1);
        cultivo.setInversionRequerida(1);
        cultivo.setPrecioDeVentaPorParcela(475);
        cultivo.setTemporadaOptima("Verano");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Tomate");
        cultivo.setCostoPorParcela(1);
        cultivo.setInversionRequerida(1);
        cultivo.setPrecioDeVentaPorParcela(600);
        cultivo.setTemporadaOptima("Verano");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Zanahoria");
        cultivo.setCostoPorParcela(1);
        cultivo.setInversionRequerida(1);
        cultivo.setPrecioDeVentaPorParcela(430);
        cultivo.setTemporadaOptima("Invierno");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Maíz");
        cultivo.setCostoPorParcela(1);
        cultivo.setInversionRequerida(1);
        cultivo.setPrecioDeVentaPorParcela(580);
        cultivo.setTemporadaOptima("Invierno");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Espinaca");
        cultivo.setCostoPorParcela(1);
        cultivo.setInversionRequerida(1);
        cultivo.setPrecioDeVentaPorParcela(460);
        cultivo.setTemporadaOptima("Invierno");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Brócoli");
        cultivo.setCostoPorParcela(1);
        cultivo.setInversionRequerida(1);
        cultivo.setPrecioDeVentaPorParcela(510);
        cultivo.setTemporadaOptima("Otoño");
        cultivos.add(cultivo);

        int size = 2;

        double[][] riesgos = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                riesgos[i][j] = (i + j) / 200.0;
                // System.out.print(riesgos[i][j] + "\t");
            }
            // System.out.print("\n");
        }
        List<CultivoSeleccionadoV2> cultivosResultado = new ArrayList<>();
        List<CultivoSeleccionadoV2> cultivosParcial = new ArrayList<>();
        Marca[][] marcas = new Marca[size][size];


        ESBacktracking parametro = new ESBacktracking(
                cultivosResultado,
                cultivosParcial,
                marcas,
                riesgos,
                "Verano",
                cultivos,
                0,
                0
        );


        plantacionOptima.backtracking(parametro);
        imprimirResultadoV2(parametro.cultivosResultado);

        long endTime = System.currentTimeMillis();
        long durationInMillis = endTime - startTime;

        // Conversión a diferentes unidades
        double seconds = durationInMillis / 1000.0;
        double minutes = seconds / 60.0;

        System.out.println("Tiempo de ejecución:");
        System.out.printf("Milisegundos: %d ms%n", durationInMillis);
        System.out.printf("Segundos: %.2f s%n", seconds);
        System.out.printf("Minutos: %.2f min%n", minutes);

        //CultivoUtils.imprimirMatrizConColision(parametro.marcas, parametro.cultivosResultado.get(0));
    }

    //ToDo: poner devuelta en el main para la entrega
    private static void testBacktracking() {
        PlantacionOptima plantacionOptima = new PlantacionOptimaImpl();
        List<Cultivo> cultivos = new ArrayList<>();

        Cultivo cultivo = new Cultivo();
//        cultivo.setNombre("Lechuga");
//        cultivo.setCostoPorParcela(1);
//        cultivo.setInversionRequerida(1);
//        cultivo.setPrecioDeVentaPorParcela(500);
//        cultivo.setTemporadaOptima("Otoño");
//        cultivos.add(cultivo);
//
//        cultivo = new Cultivo();
//        cultivo.setNombre("Remolacha");
//        cultivo.setCostoPorParcela(1);
//        cultivo.setInversionRequerida(1);
//        cultivo.setPrecioDeVentaPorParcela(450);
//        cultivo.setTemporadaOptima("Otoño");
//        cultivos.add(cultivo);
//
//        cultivo = new Cultivo();
//        cultivo.setNombre("Calabaza");
//        cultivo.setCostoPorParcela(1);
//        cultivo.setInversionRequerida(1);
//        cultivo.setPrecioDeVentaPorParcela(475);
//        cultivo.setTemporadaOptima("Otoño");
//        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Tomate");
        cultivo.setCostoPorParcela(1);
        cultivo.setInversionRequerida(1);
        cultivo.setPrecioDeVentaPorParcela(600);
        cultivo.setTemporadaOptima("Verano");
        cultivos.add(cultivo);

//        cultivo = new Cultivo();
//        cultivo.setNombre("Zanahoria");
//        cultivo.setCostoPorParcela(1);
//        cultivo.setInversionRequerida(1);
//        cultivo.setPrecioDeVentaPorParcela(430);
//        cultivo.setTemporadaOptima("Primavera");
//        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Maíz");
        cultivo.setCostoPorParcela(1);
        cultivo.setInversionRequerida(1);
        cultivo.setPrecioDeVentaPorParcela(580);
        cultivo.setTemporadaOptima("Verano");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Espinaca");
        cultivo.setCostoPorParcela(1);
        cultivo.setInversionRequerida(1);
        cultivo.setPrecioDeVentaPorParcela(460);
        cultivo.setTemporadaOptima("Verano");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Brócoli");
        cultivo.setCostoPorParcela(1);
        cultivo.setInversionRequerida(1);
        cultivo.setPrecioDeVentaPorParcela(510);
        cultivo.setTemporadaOptima("Otoño");
        cultivos.add(cultivo);

        int size = 7;

        double[][] riesgos = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                riesgos[i][j] = (i + j) / 200.0;
                System.out.print(riesgos[i][j] + "\t");
            }
            System.out.print("\n");
        }
        List<CultivoSeleccionadoV2> cultivosResultado = new ArrayList<>();
        List<CultivoSeleccionadoV2> cultivosParcial = new ArrayList<>();
        Marca[][] marcas = new Marca[size][size];


        ESBacktracking parametro = new ESBacktracking(
                cultivosResultado,
                cultivosParcial,
                marcas,
                riesgos,
                "Verano",
                cultivos,
                0,
                0
        );


        plantacionOptima.backtracking(parametro);
        imprimirResultadoV2(parametro.cultivosResultado);

    }

    private static void testBacktrackingRelleno() {
        long startTime = System.currentTimeMillis();
        int size = 3;
        AreaNiveles areaNiveles = new AreaNivelesImpl();
        MejorRelleno mejorRelleno = new MejorRellenoImpl();
        List<Cultivo> cultivos = new ArrayList<>();

        Cultivo cultivo = new Cultivo();
        cultivo.setNombre("Tomate");
        cultivo.setCostoPorParcela(1);
        cultivo.setInversionRequerida(1);
        cultivo.setPrecioDeVentaPorParcela(600);
        cultivo.setTemporadaOptima("Verano");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Maíz");
        cultivo.setCostoPorParcela(1);
        cultivo.setInversionRequerida(1);
        cultivo.setPrecioDeVentaPorParcela(580);
        cultivo.setTemporadaOptima("Verano");
        cultivos.add(cultivo);

        Marca[][] matriz = new Marca[size][size];
        matriz[0][0] = new Marca("Maíz");
        //matriz[3][3] = new Marca("Tomate");

        double[][] riesgos = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                riesgos[i][j] = (i + j) / 200.0;
                //System.out.print(riesgos[i][j] + "\t");
            }
            //System.out.print("\n");
        }


        List<CultivoSeleccionadoV2> cultivosResultado = new ArrayList<>();

        CultivoSeleccionadoV2 cultivoSeleccionado = new CultivoSeleccionadoV2();
//        cultivoSeleccionado.setNombreCultivo("Tomate");
//        cultivoSeleccionado.setEsquinaInferiorDerecha(new Coordenada(3, 3));
//        cultivoSeleccionado.setEsquinaSuperiorIzquierda(new Coordenada(3, 3));
//        cultivoSeleccionado.setMontoInvertido(2);
//        cultivoSeleccionado.setRiesgoAsociado(0.03);
//        cultivoSeleccionado.setGananciaObtenida(600);
//        cultivosResultado.add(cultivoSeleccionado);

        cultivoSeleccionado = new CultivoSeleccionadoV2();
        cultivoSeleccionado.setNombreCultivo("Maíz");
        cultivoSeleccionado.setEsquinaInferiorDerecha(new Coordenada(0, 0));
        cultivoSeleccionado.setEsquinaSuperiorIzquierda(new Coordenada(0, 0));
        cultivoSeleccionado.setMontoInvertido(2);
        cultivoSeleccionado.setRiesgoAsociado(0);
        cultivoSeleccionado.setGananciaObtenida(580);
        cultivosResultado.add(cultivoSeleccionado);


        List<CultivoSeleccionadoV2> rellenoResultado = new ArrayList<>(cultivosResultado);
        List<CultivoSeleccionadoV2> rellenoParcial = new ArrayList<>(cultivosResultado);

        List<Area> areas = areaNiveles.generar(size, size);

        ESBacktrackingRelleno parametro = new ESBacktrackingRelleno(
                rellenoParcial,
                rellenoResultado,
                matriz,
                riesgos,
                cultivos,
                0,
                580,
                null,
                areas
        );

        System.out.println("Llamando mejorRelleno.backtracking");
        mejorRelleno.backtracking(parametro);
        imprimirResultadoV2(parametro.rellenoResultado);

        long endTime = System.currentTimeMillis();
        long durationInMillis = endTime - startTime;

        // Conversión a diferentes unidades
        double seconds = durationInMillis / 1000.0;
        double minutes = seconds / 60.0;

        System.out.println("Tiempo de ejecución:");
        System.out.printf("Milisegundos: %d ms%n", durationInMillis);
        System.out.printf("Segundos: %.2f s%n", seconds);
        System.out.printf("Minutos: %.2f min%n", minutes);
    }

    //ToDo: eliminar esto para la entrega
    private static void testEvaluacionDeAlternativa() {
        EvaluacionDeAlternativa evaluador = new EvaluacionDeAlternativaImpl();

        Marca[][] matriz = new Marca[10][10];
        matriz[0][6] = new Marca("Maíz");
        matriz[5][5] = new Marca("Frijol");

        // este lo hace invalido, commentarlo para testear opci´øn valida
        matriz[4][5] = new Marca("Frijol");

        CultivoSeleccionadoV2 cultivo = new CultivoSeleccionadoV2();
        cultivo.setNombreCultivo("Tomate");
        cultivo.setEsquinaSuperiorIzquierda(new Coordenada(1, 1));
        cultivo.setEsquinaInferiorDerecha(new Coordenada(4, 5));

        CultivoUtils.imprimirMatrizConColision(matriz, cultivo);

        boolean esValido = evaluador.esValida(matriz, cultivo);
        System.out.println("Es válido: " + esValido);
    }

    //ToDo: eliminar esto para la entrega
    private static void testEvaluacionDeAlternativaRelleno() {
        EvaluacionDeAlternativa evaluador = new EvaluacionDeAlternativaImpl();

        Marca[][] matriz = new Marca[10][10];
        matriz[0][0] = new Marca("Maíz");
        matriz[0][1] = new Marca("Maíz");
        matriz[0][2] = new Marca("Maíz");
        matriz[0][3] = new Marca("Maíz");
        matriz[1][0] = new Marca("Maíz");
        matriz[1][1] = new Marca("Maíz");
        matriz[1][2] = new Marca("Maíz");
        matriz[1][3] = new Marca("Maíz");

        matriz[4][3] = new Marca("Maíz");
        matriz[4][4] = new Marca("Maíz");
        matriz[5][3] = new Marca("Maíz");
        matriz[5][4] = new Marca("Maíz");

        // Descomentar para probar uno no valido
//        matriz[2][0] = new Marca("Maíz");
//        matriz[3][0] = new Marca("Maíz");
//        matriz[2][1] = new Marca("Maíz");
//        matriz[3][1] = new Marca("Maíz");

        CultivoSeleccionadoV2 alternativaAEvaluar = new CultivoSeleccionadoV2();
        alternativaAEvaluar.setNombreCultivo("Maíz");
        alternativaAEvaluar.setEsquinaSuperiorIzquierda(new Coordenada(2, 2));
        alternativaAEvaluar.setEsquinaInferiorDerecha(new Coordenada(3, 9));

        boolean esValido = evaluador.esRellenoValido(matriz, alternativaAEvaluar);
        System.out.println("Es válido: " + esValido);
        CultivoUtils.imprimirMatrizConColision(matriz, alternativaAEvaluar);
    }

    private static void testAlternativaRelleno() {
        Alternativa alternativa = new AlternativaImpl();

        Marca[][] matriz = new Marca[10][10];
        matriz[0][0] = new Marca("Maíz");
        matriz[0][1] = new Marca("Maíz");
        matriz[0][2] = new Marca("Maíz");
        matriz[0][3] = new Marca("Maíz");
        matriz[1][0] = new Marca("Maíz");
        matriz[1][1] = new Marca("Maíz");
        matriz[1][2] = new Marca("Maíz");
        matriz[1][3] = new Marca("Maíz");

        matriz[4][3] = new Marca("Maíz");
        matriz[4][4] = new Marca("Maíz");
        matriz[5][3] = new Marca("Maíz");
        matriz[5][4] = new Marca("Maíz");

        // Descomentar para probar uno no valido
        matriz[2][0] = new Marca("Maíz");
        matriz[3][0] = new Marca("Maíz");
        matriz[2][1] = new Marca("Maíz");
        matriz[3][1] = new Marca("Maíz");


        CultivoUtils.imprimirMatrizMarca(matriz);

        List<Cultivo> cultivos = new ArrayList<>();

        Cultivo cultivo = new Cultivo();

        cultivo = new Cultivo();
        cultivo.setNombre("Maíz");
        cultivo.setCostoPorParcela(1);
        cultivo.setInversionRequerida(1);
        cultivo.setPrecioDeVentaPorParcela(580);
        cultivo.setTemporadaOptima("Verano");
        cultivos.add(cultivo);

        cultivo = new Cultivo();
        cultivo.setNombre("Espinaca");
        cultivo.setCostoPorParcela(1);
        cultivo.setInversionRequerida(1);
        cultivo.setPrecioDeVentaPorParcela(460);
        cultivo.setTemporadaOptima("Verano");
        cultivos.add(cultivo);

        int size = 10;

        double[][] riesgos = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                riesgos[i][j] = (i + j) / 200.0;
            }
        }

        Area area = new Area(new Coordenada(2, 2), new Coordenada(3, 9));
        System.out.println("Calculando alternativas");
        List<CultivoSeleccionadoV2> cultivoSeleccionadoV2s = alternativa.generarAlternativaRelleno(matriz, cultivos, riesgos, area, "Maiz");
        imprimirResultadoV2(cultivoSeleccionadoV2s);

        //CultivoUtils.imprimirMatrizConColision(matriz, cultivoSeleccionadoV2s.get(0));

    }

    //ToDo: eliminar esto para la entrega
    private static void tesMarcaPorReferencia() {
        ManejarMarca manejarMarca = new ManejarMarcaImpl();

        Marca[][] matriz = new Marca[10][10];

        CultivoSeleccionadoV2 cultivo = new CultivoSeleccionadoV2();
        cultivo.setNombreCultivo("Tomate");
        cultivo.setEsquinaSuperiorIzquierda(new Coordenada(1, 1));
        cultivo.setEsquinaInferiorDerecha(new Coordenada(4, 5));

        manejarMarca.marcarMatriz(cultivo, matriz, true);
        System.out.println("Marcar");
        CultivoUtils.imprimirMatrizMarca(matriz);


        System.out.println("Desmarcar");
        manejarMarca.marcarMatriz(cultivo, matriz, false);
        CultivoUtils.imprimirMatrizMarca(matriz);

    }

    private static void imprimirMatrizDeRiesgos(double[][] riesgos) {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.print(riesgos[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private static void imprimirResultadoV2(List<CultivoSeleccionadoV2> res) {
        System.out.println("Imprimiendo resultados:");
        for (CultivoSeleccionadoV2 cultivo : res) {
            System.out.println(cultivo);
        }
    }
    private static void imprimirResultado(List<CultivoSeleccionado> res) {
        System.out.println("Imprimiendo resultados:");
        for (CultivoSeleccionado cultivo : res) {
            System.out.println(cultivo);
        }
    }
}