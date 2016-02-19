package modelo.escenario;

import java.util.LinkedList;

/**
 * <p><b>Título:</b> Clase Codo
 * <p><b>Descripción:</b> Tipo de tramo caracterizado por tener forma de "L".
 * Este tipo de tramo está formado por 4 celdas, formando un dibujo de "L", aunque puede
 * tener el mismo sentido que la letra o sentido contrario.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 *
 */
public class Codo extends Tramo {

	//CONSTANTES
	public static final int MAXCELDAS = 4;
	
	//ATRIBUTOS
	
	private LinkedList<Celda> celdasTramo;
	private boolean esL;
	
	//CONSTRUCTORES
	
	/**
	 * Constructor de la clase Codo.
	 * Creará un codo a partir de la posición de su celda negativa y la una orientación
	 * que será igualmente, la negativa de este tramo.
	 * Para la construcción del resto de celdas del tramo, será necesario saber si éste es
	 * con forma de "L" o no (la forma opuesta a ésta). Este dato será utilizado para 
	 * determinar la posición de la última de las celdas del codo (celda positiva).
	 * @param pos, Posición de la celda negativa del codo.
	 * @param orientacion, Orientación negativa del nuevo codo.
	 * @esL, booleano que indica si el codo tendrá forma de "L" o su opuesta.
	 */
	public Codo(Posicion pos, Orientacion orientacion, boolean esL){
		
		super(orientacion);
		this.esL = esL;
		celdasTramo = new LinkedList<Celda>();
		
		construirCodo(this, pos, orientacion);
	}

	/**
	 * Método get para la obtención de la celda negativa de un codo.
	 * @return celdaNegativa, será la primera celda de las que componen el codo.
	 */
	public Celda getCeldaNegativa(){
		return null;
	}
	
	
	/**
	 * Método de obtención de la longitud de un codo.
	 * @return entero con el número de celdas que conforman el codo.
	 * Su valor será siempre 4, la longitud fija de un codo.
	 */
	public int getLongitud(){
		return 0;
	}
	
	
	/**
	 * Método get para la obtención de la celda positiva de un tramo.
	 * @return celdaPositiva, última celda de las que componen el codo.
	 */
	public Celda getCeldaPositiva(){
		return null;
	}
	
	/**
	 * Método get que devuelve la lista de celdas que conforma el codo.
	 * @return celdasTramo, lista (linkedList) de celdas contenidas en el codo.
	 */
	public LinkedList<Celda> getCeldasTramo() {
		return null;	
	}
	
	/**
	 * Método get para la obtención de la orientación positiva del codo.
	 * Dependerá del atributo "esL" del codo. 
	 * @return orientacion positiva.
	 */
	protected Orientacion getOrientacionPositiva() {
		return null;
	}

	/**
	 * Devuelve un nuevo codo que tendrá como orientación negativa la nueva dada.
	 * Conservará la posición de la celda negativa del tramo que invoca al método,
	 * cambiando la posición del resto.
	 * @param orientacion, Nueva orientación negativa del tramo.
	 * @return Nuevo tramo creado con la misma posición de la celda negativa y con la
	 * orientación pasada como parámetro.
	 */
	protected void girar(Orientacion orientacion) {
		Posicion pos = getCeldaNegativa().getPosicion();
		
		celdasTramo.clear();
		setOrientacionNegativa(orientacion);
		
		construirCodo(this,pos,orientacion);
	}

	/**
	 * Redefinición del método toString de la clase tramo para añadirle el atributo "esL".
	 * @return Cadena con las características de un codo.
	 */
	public String toString(){
		return super.toString() + ", es L: " + esL;
	}
	
	/**
	 * Método de copia de objetos de tipo Codo.
	 * Se implementará una copia profunda, el clone devolverá un nuevo objeto totalmente
	 * distinto al que realiza la llamada.
	 * @return Nuevo Codo de iguales características al clonado.
	 */
	@SuppressWarnings("unchecked")
	public Codo clone(){
		
		Tramo tramo = super.clone();	
		
		Codo codo = (Codo) tramo;
		codo.celdasTramo = (LinkedList<Celda>) celdasTramo.clone();
		codo.celdasTramo.clear();

		construirCodo(codo,getCeldaNegativa().getPosicion(),getOrientacionNegativa());
		
		return codo;	
	}
	
	/**
	 * Método privado para la construcción de las celdas de un codo.
	 * Será utilizado por el resto de métodos en caso de tener que reconstruir la
	 * estructura de celdas del codo.
	 * @param codo, será el codo sobre el que se aplica la reconstrucción
	 * @param pos, posición de la nueva celda negativa del codo.
	 * @param orientacion, orientación en la que se irán añadiendo las celdas desde la
	 * posición de la celda negativa.
	 */
	private void construirCodo(Codo codo, Posicion pos, Orientacion orientacion){
		
		;
	}
}
