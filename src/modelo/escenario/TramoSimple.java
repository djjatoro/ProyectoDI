package modelo.escenario;

import java.util.LinkedList;

/**
 * <p><b>Título:</b> Clase Tramo Simple
 * <p><b>Descripción:</b> Tipo de tramo compuesto únicamente por una celda.
 * Esta clase, que hereda de tramo, servirá para alojar celdas especiales como fuentes o llaves.
 * Su única celda será tanto la negativa como la positiva, teniendo ambas orientaciones 
 * que serán opuestas.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 *
 */
public class TramoSimple extends Tramo {

	//CONSTANTES
	public static final int MAXCELDAS = 1;
	
	private Celda celdaTramo;
	
	//CONSTRUCTORES
	
	/**
	 * Creará un TramoSimple a partir de una única celda.
	 * Para ello, invoca al método de construcción de la clase tramo y establece la única
	 * celda de su estructura como la celda positiva.
	 * @param celda, única celda del tramo, se establecerá como su celda tanto positiva 
	 * como negativa.
	 * @param orientacion, orientacion negativa del tramo, la positiva será la opuesta a esta.
	 */
	public TramoSimple(Celda celda, Orientacion orientacion){
		
		super(orientacion);
		
		celdaTramo = celda;
	}
	
	//MÉTODOS
	
	/**
	 * Método get para la obtención de la celda negativa de un tramo.
	 * @return celdaNegativa, atributo con la celda negativa del tramo.
	 */
	public Celda getCeldaNegativa(){
		return null;
	}
	
	
	/**
	 * Método get para la obtención de la celda positiva de un tramo.
	 * @return celdaPositiva, atributo con la celda positiva del tramo.
	 */
	public Celda getCeldaPositiva(){
		return null;
	}
	
	/**
	 * Método get que devuelve la lista de celdas que conforma el plano
	 * @return celdasTramo, lista (linkedList) de celdas contenidas en el tramo.
	 */
	public LinkedList<Celda> getCeldasTramo() {
		
		return null;
	}
	
	/**
	 * Método de obtención de la longitud de un tramo.
	 * @return entero con el número de celdas que conforman el tramo.
	 */
	public int getLongitud(){
		return 0;
	}
	
	/**
	 * Método get para la obtención de la orientación positiva del tramo.
	 * En este caso, será la opuesta a la negativa.
	 * @return orientacion positiva.
	 */
	public Orientacion getOrientacionPositiva() {
		return null;
	}

	/**
	 * Método para girar un tramoSimple.
	 * Al ser una única celda, no tendrá que desplazarse, solamente establecer su nueva
	 * orientación negativa.
	 */
	public void girar(Orientacion orientacion) {
		setOrientacionNegativa(orientacion);
		getCeldaNegativa().getCeldasVecinas().clear();
	}

	/**
	 * Método de copia de objetos de tipo TramoSimple.
	 * Se implementará una copia profunda, el clone devolverá un nuevo objeto totalmente
	 * distinto al que realiza la llamada.
	 * @return Nuevo TramoSimple de iguales características al clonado.
	 */
	public TramoSimple clone(){
		
		Tramo tramo = super.clone();	
		
		TramoSimple tramoSimple = (TramoSimple) tramo;
		tramoSimple.celdaTramo = celdaTramo.clone();
		
		return tramoSimple;	
	}
}
