
package modelo.escenario;

public class Salida extends Celda {
    
    public Salida (Posicion p){
        super(p, 1);
    }

    @Override
    public int getCapacidadLibre() {
        return 1; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
