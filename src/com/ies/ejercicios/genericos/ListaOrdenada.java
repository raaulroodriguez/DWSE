package com.ies.ejercicios.genericos;

import java.util.ArrayList;
import java.util.List;

public class ListaOrdenada<E extends Comparable> {

    public List<E> listaOrdenada;

    public ListaOrdenada() {
        listaOrdenada = new ArrayList<E>();
    }

    void add(E o) {
        listaOrdenada.add(o);
    }

    E get(int index) {
        return listaOrdenada.get(index);
    }

    int size() {
        return listaOrdenada.size();
    }

    boolean isEmpty() {
        return listaOrdenada.isEmpty();
    }

    boolean remove(E o) {
        return listaOrdenada.remove(o);
    }

    int indexOf(E o) {
        return listaOrdenada.indexOf(o);
    }

    public String toString() {
        if (listaOrdenada.isEmpty()) {
            return "[]";
        }
        String resultado = "[";

        for (int i = 0; i < listaOrdenada.size(); i++) {
            resultado += listaOrdenada.get(i);
            if (i < listaOrdenada.size() - 1) {
                resultado += ", ";
            }
        }
        resultado += "]";
        return resultado;
    }
}
