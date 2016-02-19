
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


public Orientacion getOrientacionL(boolean esL){
    switch (this){
        case ABAJO:
            if (esL) return Orientacion.IZQUIERDA;
            else return Orientacion.DERECHA;
        case ARRIBA:
            if (esL) return Orientacion.DERECHA;
            else return Orientacion.IZQUIERDA;            
        case IZQUIERDA:
            if (esL) return Orientacion.ARRIBA;
            else return Orientacion.ABAJO;
        case DERECHA:
            if (esL) return Orientacion.ABAJO;
            else return Orientacion.ARRIBA;                        
    }
    return null;
}
}
