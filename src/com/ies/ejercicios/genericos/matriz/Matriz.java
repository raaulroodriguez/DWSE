package com.ies.ejercicios.genericos.matriz;

public class Matriz<T> {

    private T[][] matriz;
    private int filas;
    private int columnas;

    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        matriz = (T[][]) new Object[filas][columnas];
    }

    public void set(int fila, int columna, T elemento) {
        matriz[fila][columna] = (T) elemento;
    }

    public T get(int fila, int columna) {
        return matriz[fila][columna];
    }

    public int columnas() {
        return this.columnas;
    }

    public int filas() {
        return this.filas;
    }

    public String toString() {
        if (filas == 0 || columnas == 0) {
            return "Matriz vacía";
        }

        String resultado = "";

        for (int i = 0; i < filas; i++) {
            resultado += "[ ";
            for (int j = 0; j < columnas; j++) {
                if (matriz[i][j] != null) {
                    resultado += matriz[i][j];
                }
                if (j < columnas - 1) {
                    resultado += ", ";
                }
            }
            resultado += " ]";

            if (i < filas - 1) {
                resultado += "\n";
            }
        }
        return resultado;
    }
}
