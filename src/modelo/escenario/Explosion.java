
package modelo.escenario;

public class Explosion extends Celda {
    
    public Explosion(Posicion p, int capacidad){
        super(p, capacidad);
        this.arder();
    }

    @Override
    public void apagar() {
        
    }
    
    
}
