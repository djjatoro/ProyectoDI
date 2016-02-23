
package modelo.escenario;

public class Posicion {
    
    private static final Posicion origen = new Posicion(0,0);
    
    private final int x;
    private final int y;
    
    public Posicion (){
        x=0;
        y=0;
    }
    
    public Posicion (int coordX, int coordY){
        this.x=coordX;
        this.y=coordY;
    }
    
    public Posicion (Posicion p){
        this.x=p.getX();
        this.y=p.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Posicion desplaza(int desplazamiento, Orientacion o) {
        int x=this.x;
        int y=this.y;
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
    
    public Posicion desplaza(Orientacion o) {
        int x=this.x;
        int y=this.y;
        switch (o){
            case DERECHA:
                x=x+1;
                break;
            case IZQUIERDA:
                x=x-1;
                break;
            case ARRIBA:
                y=y+1;
                break;
            case ABAJO:
                y=y-1;                
                break;
        }
        Posicion p = new Posicion (x,y);
        return p;
    }

    public Boolean adyacente(int x, int y, Orientacion o) {
        switch (o){
            case ARRIBA:
                return (x==this.x && y-this.y==1);
            case ABAJO:    
                return (x==this.x && y-this.y==-1);
            case DERECHA:
                return (y==this.y && x-this.x==1);
            case IZQUIERDA:
                return (y==this.y && x-this.x==-1);
        }
        return false;
    }
    
    public Orientacion adyacencia(Posicion pos){
        if(this.adyacente(pos.getX(),pos.getY(),Orientacion.ARRIBA))return Orientacion.ARRIBA;
        else if(this.adyacente(pos.getX(),pos.getY(),Orientacion.ABAJO))return Orientacion.ABAJO;
        else if(this.adyacente(pos.getX(),pos.getY(),Orientacion.DERECHA))return Orientacion.DERECHA;
        else if(this.adyacente(pos.getX(),pos.getY(),Orientacion.IZQUIERDA))return Orientacion.IZQUIERDA;
        return null;
    }
    
    public Posicion origen (){
        return origen;
    }
    
    public Double distancia(Posicion p){
        return Math.sqrt(Math.pow((p.getX()-x),2)+Math.pow((p.getY()-y),2));
    }
    
    // este va en posicion
    public Posicion clone(){

		Object obj = null;
		
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException e){
			assert false: "El objeto no puede ser clonado";
		}
		
		return (Posicion) obj;
	}
}
