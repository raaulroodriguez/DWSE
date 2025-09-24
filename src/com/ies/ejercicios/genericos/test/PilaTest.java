package com.ies.ejercicios.genericos.test;

import com.ies.ejercicios.genericos.Pila;
import org.junit.Test;

import static org.junit.Assert.*;

public class PilaTest {

    Pila<Integer> pilaInteger = new Pila<>();
    Pila<Double> pilaDouble = new Pila<>();
    Pila<String> pilaString = new Pila<>();

    // TEST PILA INTEGER //

    @Test
    public void pilaVacia() {
        assertTrue(pilaInteger.estaVacia());
    }

    @Test
    public void aniadirElemento() {
        pilaInteger.aniadir(1);
        pilaInteger.aniadir(2);
        pilaInteger.aniadir(3);
        assertFalse(pilaInteger.estaVacia());
        assertEquals("3 2 1", pilaInteger.toString());
    }

    @Test
    public void extraerElemento() {
        pilaInteger.aniadir(1);
        assertFalse(pilaInteger.estaVacia());
        pilaInteger.extraer();
        assertTrue(pilaInteger.estaVacia());
    }

    @Test
    public void extraerVariosElementos() {
        pilaInteger.aniadir(1);
        pilaInteger.aniadir(2);
        pilaInteger.aniadir(3);
        assertFalse(pilaInteger.estaVacia());
        assertEquals((Integer) 3, pilaInteger.primero());
        pilaInteger.extraer();
        assertFalse(pilaInteger.estaVacia());
        assertEquals((Integer) 2, pilaInteger.primero());
        pilaInteger.extraer();
        pilaInteger.extraer();
        assertTrue(pilaInteger.estaVacia());
    }

    @Test
    public void primerElemento() {
        pilaInteger.aniadir(1);
        pilaInteger.aniadir(2);
        assertEquals((Integer) 2, pilaInteger.primero());
    }

    // TEST PILA DOUBLE //

    @Test
    public void pilaVacia2() {
        assertTrue(pilaDouble.estaVacia());
    }

    @Test
    public void aniadirElemento2() {
        pilaDouble.aniadir(1.1);
        pilaDouble.aniadir(2.2);
        pilaDouble.aniadir(3.3);
        assertFalse(pilaDouble.estaVacia());
        assertEquals("3.3 2.2 1.1", pilaDouble.toString());
    }

    @Test
    public void extraerElemento2() {
        pilaDouble.aniadir(1.1);
        assertFalse(pilaDouble.estaVacia());
        pilaDouble.extraer();
        assertTrue(pilaDouble.estaVacia());
    }

    @Test
    public void extraerVariosElementos2() {
        pilaDouble.aniadir(1.1);
        pilaDouble.aniadir(2.2);
        pilaDouble.aniadir(3.3);
        assertFalse(pilaDouble.estaVacia());
        assertEquals((Double) 3.3, pilaDouble.primero());
        pilaDouble.extraer();
        assertFalse(pilaDouble.estaVacia());
        assertEquals((Double) 2.2, pilaDouble.primero());
        pilaDouble.extraer();
        pilaDouble.extraer();
        assertTrue(pilaDouble.estaVacia());
    }

    @Test
    public void primerElemento2() {
        pilaDouble.aniadir(1.1);
        pilaDouble.aniadir(2.2);
        assertEquals((Double) 2.2, pilaDouble.primero());
    }

    // TEST PILA STRING //

    @Test
    public void pilaVacia3() {
        assertTrue(pilaString.estaVacia());
    }

    @Test
    public void aniadirElemento3() {
        pilaString.aniadir("UNO");
        pilaString.aniadir("DOS");
        pilaString.aniadir("TRES");
        assertFalse(pilaString.estaVacia());
        assertEquals("TRES DOS UNO", pilaString.toString());
    }

    @Test
    public void extraerElemento3() {
        pilaString.aniadir("UNO");
        assertFalse(pilaString.estaVacia());
        pilaString.extraer();
        assertTrue(pilaString.estaVacia());
    }

    @Test
    public void extraerVariosElementos3() {
        pilaString.aniadir("UNO");
        pilaString.aniadir("DOS");
        pilaString.aniadir("TRES");
        assertFalse(pilaString.estaVacia());
        assertEquals( "TRES", pilaString.primero());
        pilaString.extraer();
        assertFalse(pilaString.estaVacia());
        assertEquals("DOS", pilaString.primero());
        pilaString.extraer();
        pilaString.extraer();
        assertTrue(pilaString.estaVacia());
    }

    @Test
    public void primerElemento3() {
        pilaString.aniadir("UNO");
        pilaString.aniadir("DOS");
        assertEquals("DOS", pilaString.primero());
    }
}
