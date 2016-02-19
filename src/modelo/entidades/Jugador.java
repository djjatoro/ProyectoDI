package modelo.entidades;

import modelo.escenario.Orientacion;
import modelo.escenario.Puerta;

/**
 * <p><b>Título:</b> Clase Jugador
 * <p><b>Descripción:</b> Define la entidad jugador, que será la manejada por el usuario
 * durante el transcurso de la partida.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 *
 */
public class Jugador extends Viva {
	
	// ATRIBUTOS
	private Accion siguienteAccion;
	
	// No se ha definido constructor
	
	// MÉTODOS
	
	/**
	 * Este método realizará la acción del jugador cuando obtenga el turno del temporizador.
	 * Dependiendo de la tecla pulsada por el usuario, el atributo siguienteAccion tendrá
	 * un valor u otro.
	 * Según este valor, realizaremos una de las diferentes acciones posibles.
	 */
	public void actuar() {
		
		if (getSiguienteAccion()==Accion.ARRIBA) mover(Orientacion.ARRIBA);
		else if (getSiguienteAccion()==Accion.ABAJO) mover(Orientacion.ABAJO);
		else if (getSiguienteAccion()==Accion.DERECHA) mover(Orientacion.DERECHA);
		else if (getSiguienteAccion()==Accion.IZQUIERDA) mover(Orientacion.IZQUIERDA);
		else if (getSiguienteAccion()==Accion.CONMUTA) conmutar();
		
		siguienteAccion = null;
		
	}

	/**
	 * Método que indica el nombre de la imagen con la que se representará la entidad
	 * jugador.
	 * Es obligatorio para implementar la interfaz dibujable.
	 * @return String, nombre de la imagen.
	 */
	public String getImagen(){
		return "jugador";
	}
	
	/**
	 * Método para conmutar el estado de una puerta.
	 *
	 */
	public void conmutar(){
		if (this.getCelda() instanceof Puerta){
			Puerta puerta = (Puerta) this.getCelda();
			puerta.conmutar();
		}
	}

	/**
	 * Método get que devuelve cual será la siguiente accion a realizar.
	 * @return Accion a realizar.
	 */
	public Accion getSiguienteAccion() {
		return siguienteAccion;
	}

	/**
	 * Método que establece la siguiente acción para que el jugador realice el movimiento
	 * hacia arriba.
	 *
	 */
	public void solicitudMueveArriba() {
		this.siguienteAccion = Accion.ARRIBA;
	}
	
	/**
	 * Método que establece la siguiente acción para que el jugador realice el movimiento
	 * hacia abajo.
	 *
	 */
	public void solicitudMueveAbajo() {
		this.siguienteAccion = Accion.ABAJO;
	}
	
	/**
	 * Método que establece la siguiente acción para que el jugador realice el movimiento
	 * hacia la derecha.
	 *
	 */
	public void solicitudMueveDerecha() {
		this.siguienteAccion = Accion.DERECHA;
	}
	
	/**
	 * Método que establece la siguiente acción para que el jugador realice el movimiento
	 * hacia la izquierda.
	 *
	 */
	public void solicitudMueveIzquierda() {
		this.siguienteAccion = Accion.IZQUIERDA;
	}
	
	/**
	 * Método que establece la siguiente acción para que el jugador realice la acción de 
	 * conmutar una llave.
	 *
	 */
	public void solicitudConmuta() {
		this.siguienteAccion = Accion.CONMUTA;
	}
}
