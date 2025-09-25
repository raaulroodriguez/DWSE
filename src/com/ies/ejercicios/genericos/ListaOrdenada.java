package com.ies.ejercicios.genericos;

import java.util.ArrayList;
import java.util.List;

public class ListaOrdenada<E extends Comparable<E>> {

    public List<E> listaOrdenada;

    public ListaOrdenada() {
        listaOrdenada = new ArrayList<E>();
    }

    void add(E o) {
        if (listaOrdenada.isEmpty()) {
            listaOrdenada.add(o);
            return;
        }
        int posicion = 0;
        for (int i = 0; i < listaOrdenada.size(); i++) {
            if (o.compareTo(listaOrdenada.get(i)) <= 0) {
                posicion = i;
                break;
            }
            posicion = i + 1;
        }
        listaOrdenada.add(posicion, o);
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
