package com.ies.ejercicios.genericos.pila;

import com.ies.ejercicios.genericos.ColeccionSimpleGenerica;

import java.util.LinkedList;

public class Pila<T> implements ColeccionSimpleGenerica<T> {

    private LinkedList<T> elementos;

    public Pila() {
        this.elementos = new LinkedList<>();
    }

    @Override
    public boolean estaVacia() {
        return elementos.isEmpty();
    }

    @Override
    public T extraer() {
        return elementos.removeFirst();
    }

    @Override
    public T primero() {
        return elementos.getFirst();
    }

    @Override
    public void aniadir(T e) {
        elementos.addFirst(e);
    }

    public String toString() {
        if (estaVacia()) {
            return "La pila esta vacía";
        }

        String coleccion = "";

        for (T elemento: elementos) {
            coleccion += elemento + " ";
        }
        return coleccion.trim();
    }

}
