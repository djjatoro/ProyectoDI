package modelo.escenario;

import java.util.LinkedList;


/**
 * <p><b>Título:</b> Clase Tubo
 * <p><b>Descripción:</b> Tipo de tramo compuesto por varias celdas alineadas.
 * Esta clase, que hereda de tramo, tendrá un conjunto número indefinido
 * (aunque finito) de celdas. Su orientación positiva será la opuesta a su negativa.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 *
 */
public class Tubo extends Tramo {

	//ATRIBUTOS
	
	private LinkedList<Celda> celdasTramo;
	
	
	//CONSTRUCTORES
	
	/**
	 * Constructor de la clase tubo. Crea un tubo de longitud especificada partiendo de 
	 * una posición pasada igualmente como parámetro.
	 * @param pos, Posición en la que se ubicará la celda negativa del tubo.
	 * @param orientacion, se establecerá como orientación negativa del tubo.
	 * @param longitud, entero con la longitud (número de celdas) del nuevo tubo.
	 */
	public Tubo(Posicion pos, Orientacion orientacion, int longitud){
		
		super(orientacion);
		celdasTramo = new LinkedList<Celda>();

		construirTubo(this, pos, orientacion, longitud);
		
	}
	
	/**
	 * Método get para la obtención de la celda negativa de un tubo.
	 * @return celdaNegativa, primera celda del tubo.
	 */
	public Celda getCeldaNegativa(){
		return null;
	}
	
	/**
	 * Método get para la obtención de la orientación positiva del tubo.
	 * En este caso, será la opuesta a la negativa.
	 * @return orientacion positiva.
	 */
	public Orientacion getOrientacionPositiva() {
		return null;
	}
	
	/**
	 * Método de obtención de la longitud de un tubo
	 * @return entero con el número de celdas que conforman el tubo.
	 */
	public int getLongitud(){
		return 0;
	}
	
	/**
	 * Método get para la obtención de la celda positiva de un tubo.
	 * @return celdaPositiva, última celda del tubo.
	 */
	public Celda getCeldaPositiva(){
		return null;
	}

	/**
	 * Método get que devuelve la lista de celdas que conforma el tubo.
	 * @return celdasTramo, lista (linkedList) de celdas contenidas en el tubo.
	 */
	public LinkedList<Celda> getCeldasTramo() {
		return null;
	}
	
	/**
	 * Método para girar un tubo.
	 * Devolvemos un nuevo tubo, con la nueva orientación tras el giro, que mantendrá
	 * la celda negativa del anterior y que será creado con el resto de celdas en su
	 * posición correcta tras haber girado el tramo.
	 * @return Nuevo tubo creado a partir del anterior y con la nueva orientación.
	 */
	public void girar(Orientacion orientacion) {
		int longitud = getLongitud();
		Posicion pos = getCeldaNegativa().getPosicion();
		
		// Empiezo cambiando la orientación y guardo la posición de la celda negativa
		
		setOrientacionNegativa(orientacion);
		getCeldaNegativa().getCeldasVecinas().clear();
		celdasTramo.clear();
		
		construirTubo(this, pos, orientacion,longitud);
	}

	/**
	 * Método de copia de objetos de tipo Tubo.
	 * Se implementará una copia profunda, el clone devolverá un nuevo objeto totalmente
	 * distinto al que realiza la llamada.
	 * @return Nuevo Tubo de iguales características al clonado.
	 */
	@SuppressWarnings("unchecked")
	public Tubo clone(){
		
		Tramo tramo = super.clone();	
		
		Tubo tubo = (Tubo) tramo;
		tubo.celdasTramo = (LinkedList<Celda>) celdasTramo.clone();
		tubo.celdasTramo.clear();

		construirTubo(tubo, getCeldaNegativa().getPosicion(),getOrientacionNegativa(),getLongitud());

		return tubo;	
		
	}
	
	/**
	 * Método privado que se utilizará para la construcción del listado de celdas
	 * que conforman el tubo.
	 * @param tubo, tubo que será reconstruído.
	 * @param pos, posición de la nueva celda negativa.
	 * @param orientacion, orientación en la que añadiremos celdas al tubo.
	 * @param longitud, número de celdas a añadir.
	 */
	private void construirTubo(Tubo tubo, Posicion pos, Orientacion orientacion, int longitud){
		;
		
	}
}
