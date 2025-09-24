package com.ies.ejercicios.genericos;

import java.util.ArrayList;
import java.util.LinkedList;

public class PilaArray<T> implements ColeccionSimpleGenerica<T> {

    private T[] lista;
    private int numElementos;

    public PilaArray() {
        this.numElementos = 0;
        lista = (T[]) new Object[0];
    }

    @Override
    public boolean estaVacia() {
        return this.numElementos == 0;
    }

    @Override
    public T extraer() {
        if (estaVacia()) {
            throw new IllegalArgumentException("La lista esta vacía");
        }
        return lista[--numElementos];
    }

    @Override
    public T primero() {
        if (estaVacia()) {
            throw new IllegalArgumentException("La lista esta vacía");
        }
        return lista[numElementos-1];
    }

    @Override
    public void aniadir(T e) {
        T[] nuevaLista = null;

        if(numElementos == lista.length) {
            nuevaLista = (T[]) new Object[lista.length+1];
        }
        for(int i = 0; i < numElementos; i++) {
            nuevaLista[i] = lista[i];
        }
        lista = nuevaLista;
        lista[numElementos++] = e;
    }

    public String toString() {
        if (estaVacia()) {
            return "La pila esta vacía";
        }

        String coleccion = "";

        for (T elemento: lista) {
            coleccion += elemento + " ";
        }
        return coleccion.trim();
    }
}