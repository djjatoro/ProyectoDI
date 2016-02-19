package modelo.entidades;

import modelo.escenario.RedTuberias;

/**
 * <p><b>Título:</b> Clase KyloRen
 <p><b>Descripción:</b> Entidad enemiga. Se moverá buscando la fuerza a menos que se cruce
 * el lado oscuro en su camino, en cuyo caso se quedará inmovil.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 *
 */
public class KyloRen extends Enemigo {

	// CONSTRUCTORES
	
	/**
	 * Constructor de la clase, no extiende ninguna funcionalidad al constructor de su clase
	 * padre, enemigo
	 * @param red de tuberías en la que se encuentra la entidad.
	 * @see Enemigo#Enemigo(RedTuberias)
	 */
	public KyloRen(RedTuberias red){
		super(red);
	}

	// MÉTODOS
	
	/**
	 * Método que define el comportamiento de la entidad.
	 * Es obligatorio para toda entidad animada.
	 * Calculará la ruta más cercana a una comida y se moverá hacia ella excepto
	 * si existe una roca en la celda a desplazarse, en cuyo caso permanecerá inmovil.
	 */
	public void actuar() {
            ;
	}

	/**
	 * Método que indica el nombre de la imagen con la que se representará la entidad.
	 * Es obligatorio para implementar la interfaz dibujable.
	 * @return String, nombre de la imagen.
	 */
	public String getImagen(){
		return "kyloren";
	}
}
