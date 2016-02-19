/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.escenario;

import java.util.LinkedList;
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
public class CeldaTest {
    
    private Celda celda1;
    private Celda celda2;
    private Celda cArriba;
    private Celda cAbajo;
    private Celda cDerecha;
    private Celda cIzquierda;
    
    public CeldaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Posicion pos = new Posicion (2, 3);
        celda1 = new Celda(pos, 3);
        celda2 = new Celda(pos);
        Posicion pArriba = new Posicion (2,4);
        Posicion pAbajo = new Posicion (2,2);
        Posicion pDerecha = new Posicion (3,3);
        Posicion pIzquierda = new Posicion (1,3);
        cArriba = new Celda(pArriba);
        cAbajo = new Celda(pAbajo);
        cDerecha = new Celda(pDerecha);
        cIzquierda = new Celda(pIzquierda);        
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of conecta method, of class Celda.
     */
    @Test
    public void testConecta() {
        System.out.println("conecta");
        celda1.conecta(Orientacion.ARRIBA, cArriba);
        celda1.conecta(Orientacion.ABAJO, cAbajo);
        celda1.conecta(Orientacion.DERECHA, cDerecha);
        celda1.conecta(Orientacion.IZQUIERDA, cIzquierda);
        assertTrue(celda1.getCeldasVecinas().contains(cArriba));
        assertTrue(celda1.getCeldasVecinas().contains(cAbajo));
        assertTrue(celda1.getCeldasVecinas().contains(cDerecha));
        assertTrue(celda1.getCeldasVecinas().contains(cIzquierda));
        assertTrue(cArriba.getCeldasVecinas().contains(celda1));
        assertTrue(cAbajo.getCeldasVecinas().contains(celda1));
        assertTrue(cDerecha.getCeldasVecinas().contains(celda1));
        assertTrue(cIzquierda.getCeldasVecinas().contains(celda1));
        // TODO review the generated test code and remove the default call to fail.
    }    

    /**
     * Test of arrastrar method, of class Celda.
     */
    @Test
    public void testArrastrar() {
        System.out.println("arrastrar");
        Posicion p = celda1.getPosicion();
        Posicion pA=cArriba.getPosicion();
        celda1.arrastrar(Orientacion.ABAJO, 2);
        assertEquals(celda1.getPosicion().getCoordY(), p.getCoordY()-2);
        assertEquals(cArriba.getPosicion().getCoordY(),pA.getCoordY()-2);
        assertEquals(cAbajo.getPosicion().getCoordY(),0);
  
        // TODO review the generated test code and remove the default call to fail.
    }    
    
    /**
     * Test of empujar method, of class Celda.
     */
    @Test
    public void testEmpujar() {
        System.out.println("empujar");
        Posicion p=celda1.getPosicion();
        celda1.empujar(celda1, Orientacion.ABAJO, 2);
        assertEquals(celda1.getPosicion().getCoordY(), p.getCoordY()-2);
        // TODO review the generated test code and remove the default call to fail.
        
    }
//
//    /**
//     * Test of colocar method, of class Celda.
//     */
//    @Test
//    public void testColocar() {
//        System.out.println("colocar");
//        int x = 0;
//        int y = 0;
//        Celda instance = null;
//        instance.colocar(x, y);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }    

//    /**
//     * Test of getPosicion method, of class Celda.
//     */
//    @Test
//    public void testGetPosicion() {
//        System.out.println("getPosicion");
//        Celda instance = null;
//        Posicion expResult = null;
//        Posicion result = instance.getPosicion();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCeldaVecina method, of class Celda.
//     */
//    @Test
//    public void testGetCeldaVecina() {
//        System.out.println("getCeldaVecina");
//        Orientacion o = null;
//        Celda instance = null;
//        Celda expResult = null;
//        Celda result = instance.getCeldaVecina(o);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCeldasVecinas method, of class Celda.
//     */
//    @Test
//    public void testGetCeldasVecinas() {
//        System.out.println("getCeldasVecinas");
//        Celda instance = null;
//        LinkedList<Celda> expResult = null;
//        LinkedList<Celda> result = instance.getCeldasVecinas();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCapacidadLibre method, of class Celda.
//     */
//    @Test
//    public void testGetCapacidadLibre() {
//        System.out.println("getCapacidadLibre");
//        Celda instance = null;
//        int expResult = 0;
//        int result = instance.getCapacidadLibre();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isLlena method, of class Celda.
//     */
//    @Test
//    public void testIsLlena() {
//        System.out.println("isLlena");
//        Celda instance = null;
//        boolean expResult = false;
//        boolean result = instance.isLlena();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addEntidad method, of class Celda.
//     */
//    @Test
//    public void testAddEntidad() {
//        System.out.println("addEntidad");
//        Entidad e = null;
//        Celda instance = null;
//        boolean expResult = false;
//        boolean result = instance.addEntidad(e);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of removeEntidad method, of class Celda.
//     */
//    @Test
//    public void testRemoveEntidad() {
//        System.out.println("removeEntidad");
//        Entidad e = null;
//        Celda instance = null;
//        boolean expResult = false;
//        boolean result = instance.removeEntidad(e);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of arder method, of class Celda.
//     */
//    @Test
//    public void testArder() {
//        System.out.println("arder");
//        Celda instance = null;
//        instance.arder();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of apagar method, of class Celda.
//     */
//    @Test
//    public void testApagar() {
//        System.out.println("apagar");
//        Celda instance = null;
//        instance.apagar();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isArdiendo method, of class Celda.
//     */
//    @Test
//    public void testIsArdiendo() {
//        System.out.println("isArdiendo");
//        Celda instance = null;
//        boolean expResult = false;
//        boolean result = instance.isArdiendo();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of conecta method, of class Celda.
//     */
//    @Test
//    public void testConecta() {
//        System.out.println("conecta");
//        Orientacion o = null;
//        Celda c = null;
//        Celda instance = null;
//        boolean expResult = false;
//        boolean result = instance.conecta(o, c);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of arrastrar method, of class Celda.
//     */
//    @Test
//    public void testArrastrar() {
//        System.out.println("arrastrar");
//        Orientacion o = null;
//        int desplazamiento = 0;
//        Celda instance = null;
//        instance.arrastrar(o, desplazamiento);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of empujar method, of class Celda.
//     */
//    @Test
//    public void testEmpujar() {
//        System.out.println("empujar");
//        Celda c = null;
//        Orientacion o = null;
//        int desplazamiento = 0;
//        Celda instance = null;
//        instance.empujar(c, o, desplazamiento);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of colocar method, of class Celda.
//     */
//    @Test
//    public void testColocar() {
//        System.out.println("colocar");
//        int x = 0;
//        int y = 0;
//        Celda instance = null;
//        instance.colocar(x, y);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of expandirFuego method, of class Celda.
//     */
//    @Test
//    public void testExpandirFuego() {
//        System.out.println("expandirFuego");
//        Celda instance = null;
//        instance.expandirFuego();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
