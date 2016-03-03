package modelo.entidades;

import modelo.escenario.RedTuberias;
import java.util.*;
import modelo.escenario.Celda;
import modelo.escenario.Explosion;
import modelo.escenario.Orientacion;


/**
 * <p><b>Título:</b> Clase StormTrooper
 <p><b>Descripción:</b> Entidad enemiga. Se moverá buscando la fuerza, en caso de cruzarse
 * con otro stormtrooper, dependiendo del estado de la celda, se moverá aleatoriamente o se quedará
 * inmóvil.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 *
 */
public class StormTrooper extends Enemigo {
	
	// CONSTRUCTORES
	
	/**
	 * Constructor de la clase, no extiende ninguna funcionalidad al constructor de su clase
	 * padre, enemigo
	 * @param red de tuberías en la que se encuentra la entidad.
	 * @see Enemigo#Enemigo(RedTuberias)
	 */
	public StormTrooper(RedTuberias red){
		super(red);
	}

	// MÉTODOS
	
	/**
	 * Método que define el comportamiento de la entidad stormtrooper.
	 * Es obligatorio para toda entidad animada.
	 * Calculará la ruta más cercana a la fuerza y se moverá hacia ella.
	 * En caso de pasar caer en la misma celda que otro soldado, se quedará inmóvil si 
	 * ésta está apagada, y se moverá aleatoriamente a una celda adyacente, si está ardiendo.
	 */
	public void actuar() {
	    Orientacion o = super.calcularRuta();
            super.mover(o);
            if (this.getCelda() instanceof Explosion) {
                boolean ardiendo = ((Explosion) this.getCelda()).isArdiendo();
                for (Entidad e : this.getCelda().getEntidades()) {
                    if (e instanceof StormTrooper && e != this) {
                        if (ardiendo) {
                            Random r = new Random();
                            Celda c = getCelda().getCeldasVecinas().get(r.nextInt(4));
                            mover(c.getPosicion().adyacencia(c.getPosicion()));
                        } else {
                            super.mover(null);
                        }
                    }

                }
            }
	}

	/**
	 * Método que indica el nombre de la imagen con la que se representará la entidad
	 * stormtrooper.
	 * Es obligatorio para implementar la interfaz dibujable.
	 * @return String, nombre de la imagen.
	 */
	public String getImagen(){
		return "stormtrooper";
	}
}
