package modelo.entidades;

import modelo.escenario.Orientacion;
import modelo.escenario.Celda;
import modelo.escenario.Posicion;
import tuberias.vista.*;

/**
 * <p><b>Ttulo:</b> Clase entidad
 * <p><b>Descripcin:</b> Define una entidad dentro del juego.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 */
public abstract class Entidad implements Dibujable {

	// ATRIBUTOS
	
	private Celda celda;

	
	/**
	 * Método get para la obtención de la celda en la que se encuentra situada la entidad
	 * @return celda en la que se encuentra la entidad.
	 */
	public Celda getCelda() {
		return celda;
	}

	/**
	 * Método get para la obtención de la posición de la entidad
	 * @return posicion de la celda en la que se encuentra la entidad.
	 */
	public Posicion getPosicion(){
		if (celda == null){
			throw new NullPointerException("La celda es null, no hay posición");
		}
		return celda.getPosicion();

	}
	/**
	 * Método que establece la celda en la que se situará una entidad.
	 * Comprobará los casos de que la celda actual sea null (aún no ha sido posicionada)
	 * o de que la celda que pasemos como parámetro lo sea (lo que estamos es haciendola
	 * desaparecer del juego).
	 * @param celda, celda que ocupará la entidad.
	 */
	public void setCelda(Celda celda) {
		if (this.celda!=null){
			this.celda.removeEntidad(this);
		}
		
		this.celda = celda;
		
		if (celda != null){
			this.celda.addEntidad(this);
		}	
	}
	
	/**
	 * Establecemos la celda de la entidad a null para que desaparezca del juego.
	 *
	 */
	public void desaparecer(){
		this.setCelda(null);
	}
	
	/**
	 * Método que desplaza una entidad a una celda adyacente en una orientación determinada.
	 * Comprueba que exista una celda en la orientación indicada y que no esté llena, en caso
	 * positivo, establece esta nueva celda como la de la entidad y la saca de su celda 
	 * anterior.
	 * @param orientacion, orientación en la que se desplazará la entidad.
	 */
	public void mover(Orientacion orientacion){
		if (orientacion == null)
			throw new NullPointerException("Orientacion nula");
		
		if ((this.celda.getCeldaVecina(orientacion)!=null) && (!this.celda.getCeldaVecina(orientacion).isLlena())){
			this.setCelda(this.celda.getCeldaVecina(orientacion));
		}
	}
	
	/**
	 * Posición en el eje X de la entidad
	 * En caso de que la celda sea null, que se dará en celdas desaparecidas,
	 * devolverá una posición por defecto, asegurándonos así de que dicha
	 * entidad desaparecida no se mostrará en pantalla.
	 * @return entero, valor de la posición en el eje X.
	 */
	public int getPosicionX(){
		if (getCelda() != null){
			return this.getPosicion().getX();
		}
		else return 1000;
	}
	
	/**
	 * Posición en el eje Y de la entidad.
	 * En caso de que la celda sea null, que se dará en celdas desaparecidas,
	 * devolverá una posición por defecto, asegurándonos así de que dicha
	 * entidad desaparecida no se mostrará en pantalla.
	 * @return entero, valor de la posición en el eje Y.
	 */
	public int getPosicionY(){
		if (getCelda() != null){
			return this.getPosicion().getY();
		}
		else return 1000;
	}
	
	/**
	 * Devuelve una cadena con la descripción del objeto Entidad.
	 * @return Cadena con las características de la entidad.
	 */
	public String toString(){
		return getClass().getName() + " Celda:" + getCelda().toString();
	}
}
