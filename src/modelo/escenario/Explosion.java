
package modelo.escenario;

public class Explosion extends Celda {
    
    public Explosion(Posicion p, int capacidad){
        super(p, capacidad);
        this.arder();
    }
    
    public Explosion (Posicion p){
        super(p);
    }

    @Override
    public void apagar() {
        
    }
    
    
}
