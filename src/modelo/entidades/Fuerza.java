package modelo.entidades;

/**
 * <p><b>Título:</b> Clase Fuerza
 <p><b>Descripción:</b> Entidad inerte que aumentará la capacidad respiratoria de 
 * las entidades vivas que se crucen con ella.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 *
 */
public class Fuerza extends Inerte {

	// CONSTANTES
	private static final int FUERZA = 3;
	
	// ATRIBUTOS
	private int fuerza;

	// CONSTRUCTORES
	/**
	 * Constructor que inicializa el valor de la fuerza
	 */
	public Fuerza(){
		fuerza = FUERZA;
	}
	
	/**
	 * Método get con el que obtenemos la cantidad de fuerza del objeto, es decir
	 * la cantidad que aumentará la capacidad respiratoria de la entidad viva que la tome.
	 * @return entero, cantidad de alimento.
	 */
	public int getFuerza() {
		return fuerza;
	}

	/**
	 * Método que establece el conjunto de acciones que se realizarán sobre cualquier
	 * entidad viva que interactue con un objeto fuerza.
	 * En el caso de la fuerza, aumentará la capacidad respiratoria a la entidad y tras
	 * esto desaparecerá del juego.
	 * Este método es obligatorio para toda entidad inerte.
	 * @param Viva, entidad sobre la que actuará el alimento.
	 */
	public void reaccionar(Viva viva){
	    for (Entidad e : this.getCelda().getEntidades()) {
                if (e instanceof Viva) {
                    viva.setAireGastado(fuerza + viva.getAireGastado()); 
                    e.desaparecer();
                }
            }
	}
	
	/**
	 * Método que indica el nombre de la imagen con la que se representará la entidad
	 * fuerza.
	 * Es obligatorio para implementar la interfaz dibujable.
	 * @return String, nombre de la imagen.
	 */
	public String getImagen(){
		return "fuerza";
	}
	
	/**
	 * Devuelve una cadena con la descripción del objeto Fuerza.
	 * @return Cadena con las características de la fuerza.
	 */
	public String toString(){
		return super.toString() + " fuerza: " + getFuerza();
	}
}
