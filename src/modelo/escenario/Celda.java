
package modelo.escenario;

import java.util.LinkedList;

public class Celda {
    private Posicion posicion;
    private int capacidad;
    private LinkedList<Celda> celdas;
    private LinkedList<Entidad> entidades;

    //CONSTRUCTOR ja
    public Celda(Posicion posicion, int capacidad) {
        this.posicion = posicion;
        this.capacidad = capacidad;
    }
    //CONSTRUCTOR, CAPACIDAD CONSTANTE
    public Celda(Posicion posicion) {
        this.posicion = posicion;
        capacidad=5; 
    }
    
    //GETTER posición
    public Posicion getPosicion() { 
        return posicion;
    }
    
    //GETER celda vecina
    public Celda getCeldaVecina(Orientacion o){
        
    }
    
    public LinkeList<Celda> getCeldasVecinas(){
        
    }
    
}
