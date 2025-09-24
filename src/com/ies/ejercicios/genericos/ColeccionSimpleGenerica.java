package com.ies.ejercicios.genericos;

public interface ColeccionSimpleGenerica<T> {

    /**
     * Comprueba si la colección esta vacía
     * @return Devuelve true si la colección está vacía, false en caso contrario.
     */
   boolean estaVacia();

    /**
     * Devuelve y elimina el primer elemento de la colección.
     * @return El primer elemente de la coleccion
     */
    T extraer();

    /**
     * Devuelve el primer elemento de la colección sin eliminarlo.
     * @return El primer elemente de la coleccion
     */
    T primero();

    /**
     * Añade un objeto por el extremo que corresponda a la implementación.
     * @param e elemento a añadir
     */
    void aniadir(T e);
}


