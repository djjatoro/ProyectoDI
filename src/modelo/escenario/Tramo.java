package modelo.escenario;

import java.util.LinkedList;

/**
 * <p><b>Título:</b> Clase abstracta Tramo
 * <p><b>Descripción:</b> Agrupaciones de celdas que a su vez darán lugar a cada una 
 * de las tuberías que conformarán el escenario.
 * Se caracterizarán por las celdas que los componen y su orientación en el plano.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 *
 */
public abstract class Tramo implements Cloneable {

	// ATRIBUTOS
	
	private Orientacion orientacionNegativa;
	
	
	// CONSTRUCTORES
	
	/**
	 * Constructor que crea un tramo a partir de una celda inicial y una orientación para
	 * el tramo.
	 * El tramo constará de orientación positiva y negativa, que servirán para la unión entre
	 * diferentes tramo en busca de formar tuberías.
	 * La orientación que se aporta como parámetro será siempre la negativa.
	 * @param celda, celda inicial a partir de la cual construiremos el tramo
	 * @param orientacion, se establecerá como orientación negativa, a partir de ella calcularemos
	 * la posición del resto de celdas del tramo y la orientación positiva del mismo.
	 */
	public Tramo(Orientacion orientacion){
		
		orientacionNegativa = orientacion;
	}
	
	// MÉTODOS
	
	/**
	 * Método abstracto para la obtención de la longitud de un tramo.
	 * @return entero con el número de celdas que conforman el tramo.
	 */
	public abstract int getLongitud();
	
	/**
	 * Método abstracto para la obtención de la celda negativa de un tramo.
	 * @return celdaNegativa, atributo con la celda negativa del tramo.
	 */
	public abstract Celda getCeldaNegativa();
	
	
	/**
	 * Método abstracto para la obtención de la celda positiva de un tramo.
	 * @return celdaPositiva, atributo con la celda positiva del tramo.
	 */
	public abstract Celda getCeldaPositiva();

	
	/**
	 * Método get para la obtención de la orientación negativa de un tramo
	 * @return orientacionNegativa.
	 */
	public Orientacion getOrientacionNegativa() {
		return orientacionNegativa;
	}
	
	/**
	 * Método set para el establecimiento de la orientación negativa de un tramo.
	 * @param orientacionNegativa, nueva orientación negativa del tramo.
	 */
	public void setOrientacionNegativa(Orientacion orientacionNegativa) {
		this.orientacionNegativa = orientacionNegativa;
	}

	/**
	 * Método get que devuelve la lista de celdas que conforma el plano
	 * @return celdasTramo, lista (linkedList) de celdas contenidas en el tramo.
	 */
	public abstract LinkedList<Celda> getCeldasTramo();
	

	/**
	 * Se define el conector negativo como la posición que tendrá que ocupar la celda
	 * positiva de otro tramo para poder conectarse a la negativa del tramo actual.
	 * @return posición adyacente en la orientación negativa al tramo que realiza la llamada.
	 */
	public Posicion conectorNegativo(){
		return getCeldaNegativa().getPosicion().desplaza(orientacionNegativa);
	}
	
	/**
	 * Se define el conector positivo como la posición que tendrá que ocupar la celda
	 * negativa de otro tramo para poder conectarse a la positiva del tramo actual.
	 * @return posición adyacente en la orientación positiva del tramo que realiza la llamada.
	 */
	public Posicion conectorPositivo(){
		return getCeldaPositiva().getPosicion().desplaza(getOrientacionPositiva());
	}	
	
	/**
	 * Método de desplazamiento de un tramo.
	 * Simplemente se colocará la celda negativa del mismo en la posición que se indique y 
	 * esta arrastrará al resto de celdas conectadas a ella.
	 * @param posicion, nueva posición en la que colocaremos la celda negativa del tramo.
	 * @see Celda#colocar(Posicion)
	 */
	public void desplazar(Posicion posicion){
		getCeldaNegativa().colocar(posicion);
	}
	
	/**
	 * Método abstracto para la obtención de la orientación positiva del plano que deberán
	 * implementar cada una de las clases hijas.
	 * Este método se ha delegado puesto que serán procedimiento distintos para, por ejemplo,
	 * un tubo y un codo.
	 * @return orientación positiva del tramo.
	 */
	protected abstract Orientacion getOrientacionPositiva();
	
	/**
	 * Método para girar un tramo.
	 * La celda negativa quedará fija, cambiando la orientación negativa del tramo y por lo 
	 * tanto, la posición del resto de sus celdas.
	 * @param orientacion, nueva orientación negativa del plano
	 * @return Nuevo tramo creado a partir de la rotación del anterior.
	 */
	protected abstract void girar(Orientacion orientacion);

	/**
	 * Método que devuelve una cadena con la descripción de un objeto tramo
	 * @return Cadena con las características del tramo.
	 */
	public String toString(){
		String celdas = null;
		
		for (Celda celda: getCeldasTramo()){
			celdas += celda.toString() + ' ';
		}
		
		return getClass().getName() + "[Celdas del tramo: " + celdas
		+ ", Celda negativa: " + getCeldaNegativa().toString() + ", Celda positiva: " +
		getCeldaPositiva().toString() + ", Orientacion negativa: " + getOrientacionNegativa().toString() + "]";
	}
	
	/**
	 * Método de copia de objetos de tipo tramo.
	 * Se implementará una copia profunda, el clone devolverá un nuevo objeto totalmente
	 * distinto al que realiza la llamada.
	 * @return Nuevo tramo de iguales características al clonado.
	 */
	public Tramo clone(){
		Object obj = null;
		
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException e){
			assert false: "El objeto no puede ser clonado";
		}
		Tramo tramo = (Tramo) obj;
		tramo.orientacionNegativa = this.orientacionNegativa;
			
		return tramo;
	
	}

	
}
