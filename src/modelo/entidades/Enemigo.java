package modelo.entidades;

import modelo.escenario.Orientacion;
import modelo.escenario.Celda;
import modelo.escenario.RedTuberias;
import java.util.*;

/**
 * <p><b>Título:</b> Clase abstracta Enemigo
 * <p><b>Descripción:</b> Clase que engloba todas las entidades del juego que son vivas
 * y no son controladas por el usuario.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 *
 */
public abstract class Enemigo extends Viva {

	// ATRIBUTOS
	private RedTuberias red;
	
	// CONSTRUCTORES
	
	/**
	 * Constructor de la clase, establece en que red se encuentra la entidad.
	 * @param red, red de tuberías en la que se coloca el enemigo.
	 */
	public Enemigo(RedTuberias red){
		this.red = red;
	}
	
	/**
	 * Método para calcular el movimiento que debe realizar el enemigo.
	 * El método buscará las entidades "fuerza" que haya en la red. En caso de encontrar alguna,
	 * calculará cual es la más cercana para moverse hacia ella.
	 * Antes de realizar el movimiento, calculará de nuevo cual es la celda más cercana
	 * entre las posibles.
	 * @return orientacion del movimiento a realizar o null si no ha de moverse.
	 */
	public Orientacion calcularRuta(){
		LinkedList<Entidad> comidas = new LinkedList<Entidad>();
		
		// Sacamos las comidas que haya en la red
		for(Entidad entidad: red.getEntidades()){
			if (entidad instanceof Fuerza){
				comidas.add(entidad);
			}
		}
		// Si hay alguna, buscamos la más cercana.
		if (!comidas.isEmpty()){
			double distancia = 0;
			double maxDistancia = 99999;
			Entidad entidadElegida = null;
			for(Entidad entidad: comidas){
				distancia = this.getPosicion().distancia(entidad.getPosicion());
				if (distancia < maxDistancia) {
					entidadElegida = entidad;
					maxDistancia = distancia;
				}
			}
			// Una vez encontrada, buscamos la celda adyacente más próxima a ella.
			distancia = 0;
			maxDistancia = 99999;
			Orientacion orientacionElegida = null;
			for(Celda celda: this.getCelda().getCeldasVecinas()){
				distancia = celda.getPosicion().distancia(entidadElegida.getPosicion());
				if (distancia < maxDistancia) {
					orientacionElegida = this.getPosicion().adyacencia(celda.getPosicion());
					maxDistancia = distancia;
				}
			}
			
			return orientacionElegida;
		}
		return null;
	}

	/**
	 * Método get para obtener la red en la que se encuentra el enemigo.
	 * @return red, red de tuberías.
	 */
	public RedTuberias getRed() {
		return red;
	}

}
