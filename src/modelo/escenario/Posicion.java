
package modelo.escenario;

public class Posicion {
    private final int coordX;
    private final int coordY;
    
    public Posicion (){
        coordX=0;
        coordY=0;
    }
    
    public Posicion (int coordX, int coordY){
        this.coordX=coordX;
        this.coordY=coordY;
    }
    
    public Posicion ( Posicion p){
        this.coordX=p.getCoordX();
        this.coordY=p.getCoordY();
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }
    
    public Posicion desplaza(int desplazamiento, Orientacion o) {
        int x=coordX;
        int y=coordY;
        switch (o){
            case DERECHA:
                x=x+desplazamiento;
                break;
            case IZQUIERDA:
                x=x-desplazamiento;
                break;
            case ARRIBA:
                y=y+desplazamiento;
                break;
            case ABAJO:
                y=y-desplazamiento;                
                break;
        }
        Posicion p = new Posicion (x,y);
        return p;
    }

    public Boolean adyacente(int x, int y, Orientacion o) {
        switch (o){
            case ARRIBA:
                return (x==coordX && y-coordY==1);
            case ABAJO:    
                return (x==coordX && y-coordY==-1);
            case DERECHA:
                return (y==coordY && x-coordX==1);
            case IZQUIERDA:
                return (y==coordY && x-coordX==-1);
        }
        return false;
    }
    
    public Orientacion adyacencia(Posicion pos){
        if(this.adyacente(pos.getCoordX(),pos.getCoordY(),Orientacion.ARRIBA))return Orientacion.ARRIBA;
        else if(this.adyacente(pos.getCoordX(),pos.getCoordY(),Orientacion.ABAJO))return Orientacion.ABAJO;
        else if(this.adyacente(pos.getCoordX(),pos.getCoordY(),Orientacion.DERECHA))return Orientacion.DERECHA;
        else if(this.adyacente(pos.getCoordX(),pos.getCoordY(),Orientacion.IZQUIERDA))return Orientacion.IZQUIERDA;
        return null;
    }
    
    public Posicion origen (){
        Posicion o = new Posicion();
        return o;
    }
    
    public Double distancia(int x, int y){
        return Math.sqrt(Math.pow((x-coordX),2)+Math.pow((y-coordY),2));
    }
}
