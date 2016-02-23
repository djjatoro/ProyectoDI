/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.escenario;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alumno
 */
public class PosicionTest {
    
    public PosicionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test Constructor: correcta inicialización de las coordenadas.
     */
    @Test
    public void testPosicion1() {
        System.out.println("Test Constructor1");
        
        int x=1;
        int y=1;
        Posicion p = new Posicion(x, y);
        assertEquals(x, p.getX());
        assertEquals(y, p.getY());
        // TODO review the generated test code and remove the default call to fail.
    }    

//    /**
//     * Test of getCoordX method, of class Posicion.
//     */
//    @Test
//    public void testGetCoordX() {
//        System.out.println("getCoordX");
//        Posicion instance = new Posicion();
//        int expResult = 0;
//        int result = instance.getCoordX();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCoordY method, of class Posicion.
//     */
//    @Test
//    public void testGetCoordY() {
//        System.out.println("getCoordY");
//        Posicion instance = new Posicion();
//        int expResult = 0;
//        int result = instance.getCoordY();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of desplaza method, of class Posicion.
//     */
//    @Test
//    public void testDesplaza() {
//        System.out.println("desplaza");
//        int desplazamiento = 0;
//        Orientacion o = null;
//        Posicion instance = new Posicion();
//        Posicion expResult = null;
//        Posicion result = instance.desplaza(desplazamiento, o);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of adyacente method, of class Posicion.
//     */
//    @Test
//    public void testAdyacente() {
//        System.out.println("adyacente");
//        int x = 0;
//        int y = 0;
//        Orientacion o = null;
//        Posicion instance = new Posicion();
//        Boolean expResult = null;
//        Boolean result = instance.adyacente(x, y, o);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of adyacencia method, of class Posicion.
//     */
//    @Test
//    public void testAdyacencia() {
//        System.out.println("adyacencia");
//        Posicion pos = null;
//        Posicion instance = new Posicion();
//        Orientacion expResult = null;
//        Orientacion result = instance.adyacencia(pos);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of origen method, of class Posicion.
//     */
//    @Test
//    public void testOrigen() {
//        System.out.println("origen");
//        Posicion instance = new Posicion();
//        Posicion expResult = null;
//        Posicion result = instance.origen();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of distancia method, of class Posicion.
//     */
//    @Test
//    public void testDistancia() {
//        System.out.println("distancia");
//        int x = 0;
//        int y = 0;
//        Posicion instance = new Posicion();
//        Double expResult = null;
//        Double result = instance.distancia(x, y);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
