package modelo.entidades;

/**
 * <p><b>Título:</b> Clase LadoOscuro
 <p><b>Descripción:</b> Entidad inerte que restará capacidad respiratoria a las entidades
 * vivas que se crucen con ella.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 *
 */
public class LadoOscuro extends Inerte {

	// CONSTANTES
	private static final int MIEDOIRAODIO = 3;
	
	// ATRIBUTOS
	private int miedoIraOdio;

	// CONSTRUCTORES
	
	/**
	 * Crea el objeto ladoOscuro estableciendo el valor inicial de su miedoIraOdio.
	 */
	public LadoOscuro(){
		miedoIraOdio = MIEDOIRAODIO;
	}
	
	// MÉTODOS
	
	/**
	 * Método get para obtener la cantidad de miedoIraOdio del lado oscuro.
	 * @return entero, cantidad de veneno.
	 */
	public int getMiedoIraOdio() {
		return miedoIraOdio;
	}

	/**
	 * Método que establece el conjunto de acciones que se realizarán sobre cualquier
	 * entidad viva que interactue con un objeto del lado oscuro.
	 * En el caso del lado oscuro, restará capacidad respiratoria a la entidad.
	 * Este método es obligatorio para toda entidad inerte.
	 * @param Viva, entidad sobre la que actuará el lado oscuro.
	 */
	public void reaccionar(Viva viva){
			;
	}
	
	/**
	 * Método que indica el nombre de la imagen con la que se representará la entidad
	 * ladoOscuro.
	 * Es obligatorio para implementar la interfaz dibujable.
	 * @return String, nombre de la imagen.
	 */
	public String getImagen(){
		return "ladooscuro";
	}
	
	/**
	 * Devuelve una cadena con la descripción del objeto LadoOscuro.
	 * @return Cadena con las características del lado oscuro.
	 */
	public String toString(){
		return super.toString() + " MiedoIraOdio: " + getMiedoIraOdio();
	}
}
