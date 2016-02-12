
package modelo.escenario;

import java.util.LinkedList;

public class Celda {
    private Posicion posicion;
    private int capacidad;
    private final int CAPACMAX=5;
    private boolean ardiendo=false;
    private LinkedList<Celda> celdas;
    private LinkedList<Entidad> entidades;

    //CONSTRUCTOR
    public Celda(Posicion posicion, int capacidad) {
        this.posicion = posicion;
        this.capacidad = capacidad;
        celdas = new LinkedList<>();
        entidades = new LinkedList<>();
    }
    
    //CONSTRUCTOR, CAPACIDAD CONSTANTE
    public Celda(Posicion posicion) {
        this.posicion = posicion;
        this.capacidad=CAPACMAX;
    }
    
    //GETTER posición
    public Posicion getPosicion() { 
        return posicion;
    }
    
    //GETER celda vecina
    public Celda getCeldaVecina(Orientacion o){
        for (Celda c : celdas)
            if (posicion.adyacente(c.getPosicion().getCoordX(), c.getPosicion().getCoordY(), o))
                return c;
        return null;        
    }
    
    public LinkedList<Celda> getCeldasVecinas(){
        return celdas;
    }
    
    public int getCapacidadLibre(){
        return capacidad-entidades.size();
    }
    
    public boolean isLlena(){
        if (capacidad==entidades.size())
            return false;
        return true;
    }
    
    public boolean addEntidad(Entidad e){
        if (capacidad-entidades.size()>0){
            entidades.add(e);
            return true;
        }else
            return false;
    }
    
    public boolean removeEntidad (Entidad e){
        return entidades.remove(e);
    }
    
    public void arder(){
        ardiendo=true;
    }
    
    public void apagar(){
        ardiendo=false;
    }
    
    public boolean isArdiendo(){
        return ardiendo;
    }
    
    public boolean conecta(Orientacion o, Celda c){
        if (posicion.adyacente(c.getPosicion().getCoordX(), c.getPosicion().getCoordY(), o)){
            celdas.add(c);
            c.getCeldasVecinas().add(this);
            return true;
        } else
            return false;
    }
    
    public void arrastrar(Orientacion o, int desplazamiento){
        
    }
    
    public void empujar(Celda c, Orientacion o, int desplazamiento){
        
    }
    
    public void colocar (){
        
    }
}
