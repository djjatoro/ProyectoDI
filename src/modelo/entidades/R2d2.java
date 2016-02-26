package modelo.entidades;

import modelo.escenario.Orientacion;
import java.util.*;
import modelo.escenario.Celda;


/**
 * <p><b>Título:</b> Clase Roca
 * <p><b>Descripción:</b> Entidad inerte y animada que es generada periodicamente por las
 * celdas explosión.
 * Apagará la celda que ocupe en cada momento y dará vida a las entidad vivas con las que 
 * interactúe.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 *
 */
public class R2d2 extends Inerte implements Animada {

	// ATRIBUTOS
	private Orientacion anterior;
	
	// No se han definido constructores para esta clase.
	
	// MÉTODOS
	/**
	 * Método que define las acciones de R2D2 cuando obtenga el turno por parte
	 * del temporizador.
	 * En este caso, no ha de interactuar con las entidades inertes ni necesita respirar,
	 * por lo tanto, se reduce a realizar su acción.
	 */
	public void turno() {
		actuar();
	}

	/**
	 * Método que define las acciones a realizar al coincidir con una entidad viva.
	 * Es obligatorio para toda entidad inerte.
	 * En el caso de R2D2, establece a 0 la vida gastada por la entidad
	 * que coincida con ella.
	 */
	public void reaccionar(Viva viva) {
		;
		/* Este método no debería ser necesario, pero puede darse el caso de que
		 R2 obtenga el temporizador antes que la otra entidad y salga de la celda
		 antes de que la entidad se de cuenta de que la ha drenado, por lo que esta no
		 podría volver su aire gastado a 0 */
	}

	/**
	 * Método que define el comportamiento de la entidad R2.
	 * Elegirá aleatoriamente hacia donde moverse entre sus celdas vecinas, sin volver
	 * nunca en la dirección que llegó a su celda actual.
	 * Incendiará la celda que deje y apagará a la que llegue.
	 * En caso de encontrarse con otro R2 o llegar a una celda apagada o al final
	 * de una tubería, desaparecerá.
	 */
	public void actuar() {
		LinkedList <Celda> candidatas = new LinkedList<>();
	}

	/**
	 * Método heredado de la clase entidad, además de realizar las comprobaciones y 
	 * establecer la celda del R2, almacenará la orientación desde la cual
	 * llegamos a nuestra nueva celda, que se usará para no volver a esta.
	 * @param orientacion opuesta al movimiento.
	 * @see Entidad#mover(Orientacion)
	 */
	public void mover(Orientacion orientacion){
		anterior = orientacion.opuesta();
		super.mover(orientacion);
	}

	/**
	 * Devuelve la orientación opuesta al último movimiento realizado, es decir, la orientación
	 * desde la que llegamos a nuestra celda actual
	 * @return orientacion para llegar a la celda anterior.
	 */
	public Orientacion getAnterior() {
		return anterior;
	}
	
	/**
	 * Método que indica el nombre de la imagen con la que se representará la entidad
	 * R2.
	 * Es obligatorio para implementar la interfaz dibujable.
	 * @return String, nombre de la imagen.
	 */
	public String getImagen(){
		return "r2d2";
	}
	
}
