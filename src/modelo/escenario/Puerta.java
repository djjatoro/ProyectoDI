
package modelo.escenario;

public class Puerta extends Celda {
    
    private boolean abierta;
    
    public Puerta (Posicion p, int capacidad){
        super(p, capacidad);
        this.abierta=true;
    }

    public boolean isAbierta() {
        return abierta;
    }

    public void setAbierta(boolean abierta) {
        this.abierta = abierta;
    }

    @Override
    public void expandirFuego() {
        if (abierta)
            super.expandirFuego(); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean conmutar(){
        return !abierta;
    }
    
    
}
