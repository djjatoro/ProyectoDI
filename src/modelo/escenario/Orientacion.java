
package modelo.escenario;

public enum Orientacion {
    ARRIBA, ABAJO, IZQUIERDA, DERECHA;
    
    public Orientacion opuesta(){
        switch (this){
            case ARRIBA:
                return Orientacion.ABAJO;
            case ABAJO:
                return Orientacion.ARRIBA;
            case IZQUIERDA:
                return Orientacion.DERECHA;
            case DERECHA:
                return Orientacion.IZQUIERDA;
        }
        return null;
    }
}
