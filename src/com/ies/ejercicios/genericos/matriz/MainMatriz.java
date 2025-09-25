package com.ies.ejercicios.genericos.matriz;

public class MainMatriz {

    private static int FILAS = 4;
    private static int COLUMNAS = 2;

    /**
     * Punto de entrada del programa
     * @param args
     */
    public static void main(String[] args) {
        MainMatriz app = new MainMatriz();
        app.run();
    }

    private void run() {
        Matriz<Integer> matriz = new Matriz<>(FILAS, COLUMNAS);

        int contador = 1;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS;j++) {
                matriz.set(i, j, contador++);
            }
        }

        int celda12 = matriz.get(0, 1);

        System.out.println("-------------------");
        System.out.println("MATRIZ COMPLETA");
        System.out.println("-------------------");
        System.out.println(matriz);
        System.out.println("-------------------");
        System.out.print("CONTENIDO FILA 1 COLUMNA 2 = ");
        System.out.println(celda12);
        System.out.println("-------------------");
    }
}
