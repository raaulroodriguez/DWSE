package com.ies.ejercicios.genericos.test;

import com.ies.ejercicios.genericos.pila.PilaArray;
import org.junit.Test;

import static org.junit.Assert.*;

public class PilaArrayTest {

    PilaArray<Integer>  pilaArrayInteger = new PilaArray<>();
    PilaArray<Double>  pilaArrayDouble = new PilaArray<>();
    PilaArray<String>  pilaArrayString = new PilaArray<>();

    // TEST PILA ARRAY

    @Test
    public void pilaVacia() {
        assertTrue(pilaArrayInteger.estaVacia());
        assertTrue(pilaArrayDouble.estaVacia());
        assertTrue(pilaArrayString.estaVacia());
    }

    @Test
    public void aniadirElemento() {
        pilaArrayInteger.aniadir(1);
        pilaArrayInteger.aniadir(2);
        pilaArrayInteger.aniadir(3);
        assertFalse(pilaArrayInteger.estaVacia());
        assertEquals("1 2 3", pilaArrayInteger.toString());
    }

    @Test
    public void extraerElemento() {
        pilaArrayDouble.aniadir(1.1);
        assertFalse(pilaArrayDouble.estaVacia());
        pilaArrayDouble.extraer();
        assertTrue(pilaArrayDouble.estaVacia());
    }

    @Test
    public void extraerVariosElementos() {
        pilaArrayString.aniadir("UNO");
        pilaArrayString.aniadir("DOS");
        pilaArrayString.aniadir("TRES");
        assertFalse(pilaArrayString.estaVacia());
        assertEquals("TRES", pilaArrayString.primero());
        pilaArrayString.extraer();
        assertEquals("DOS", pilaArrayString.primero());
        assertFalse(pilaArrayString.estaVacia());
        pilaArrayString.extraer();
        pilaArrayString.extraer();
        assertTrue(pilaArrayString.estaVacia());
    }
}
