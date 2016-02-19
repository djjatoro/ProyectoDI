
package modelo.escenario;

import java.util.LinkedList;

public class Celda {
    private Posicion posicion;
    private int capacidad;
    private static final int CAPACMAX=5;
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
        this(posicion, CAPACMAX);
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
        return (capacidad==entidades.size());
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
            if (!celdas.contains(c))
                celdas.add(c);
            if (!c.getCeldasVecinas().contains(this))
                    c.getCeldasVecinas().add(this);
            return true;
        } else
            return false;
    }
    
    public void arrastrar(Orientacion o, int desplazamiento){
        this.posicion=posicion.desplaza(desplazamiento, o);
        for(Celda c : celdas)
            c.empujar(this, o, desplazamiento);
    }
    
    public void empujar(Celda c, Orientacion o, int desplazamiento){
        this.posicion=posicion.desplaza(desplazamiento, o);
        for (Celda ce: celdas)
            if (ce!=c)
                ce.empujar(this, o, desplazamiento);
    }
    
    public void colocar (Posicion p){
        if (p.getCoordX()-this.getPosicion().getCoordX()>0)
            this.arrastrar(Orientacion.DERECHA, p.getCoordX()-this.getPosicion().getCoordX());
        else
            this.arrastrar(Orientacion.IZQUIERDA, this.getPosicion().getCoordX()-p.getCoordX());
        if (p.getCoordY()-this.getPosicion().getCoordY()>0)
            this.arrastrar(Orientacion.ARRIBA, p.getCoordY()-this.getPosicion().getCoordX());
        else
            this.arrastrar(Orientacion.ABAJO, this.getPosicion().getCoordX()-p.getCoordY());
    }
    
    public void expandirFuego(){
        for (Celda c : celdas)
            c.arder();
    }
    
    public Celda clone(){
		
        Object obj = null;

        try {
                obj = super.clone();
        } catch (CloneNotSupportedException e){
                assert false: "El objeto no puede ser clonado";
        }
        Celda celda = (Celda) obj;
        celda.posicion = posicion.clone();
        celda.celdas = new LinkedList<Celda>();
        celda.entidades = new LinkedList<Entidad>();
        return celda;
    }

}
