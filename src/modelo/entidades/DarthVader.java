package modelo.entidades;

import modelo.escenario.RedTuberias;

/**
 * <p><b>Título:</b> Clase DarthVader
 <p><b>Descripción:</b> Entidad enemiga. Se moverá buscando la fuerza, abriendo todas las
 * puertas que se crucen por su paso.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 *
 */
public class DarthVader extends Enemigo {

	// CONSTRUCTORES
	
	/**
	 * Constructor de la clase, no extiende ninguna funcionalidad al constructor de su clase
	 * padre, enemigo
	 * @param red de tuberías en la que se encuentra la entidad.
	 * @see Enemigo#Enemigo(RedTuberias)
	 */
	public DarthVader(RedTuberias red){
		super(red);
	}

	// MÉTODOS
	
	/**
	 * Método que define el comportamiento de la entidad darth vader.
	 * Es obligatorio para toda entidad animada.
	 * Calculará la ruta más cercana a una comida y se moverá hacia ella.
	 * En caso de pasar por una puerta, la abrirá.
	 */
	public void actuar() {
		;

	}

	/**
	 * Método que indica el nombre de la imagen con la que se representará la entidad
	 * darth vader.
	 * Es obligatorio para implementar la interfaz dibujable.
	 * @return String, nombre de la imagen.
	 */
	public String getImagen(){
		return "darthvader";
	}
}
