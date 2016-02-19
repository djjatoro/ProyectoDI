package modelo.entidades;

/**
 * <p><b>Título:</b> Clase abstracta Inerte
 * <p><b>Descripción:</b> Clase que engloba todas las entidades del juego que no realizan
 * movimiento alguno y no reciben el turno del temporizador.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 *
 */
public abstract class Inerte extends Entidad {

	/**
	 * Método abstracto que deben implementar todas las entidades inertes.
	 * Englobará el conjunto de acciones que se realizarán con las entidades vivas
	 * en caso de que interactúen con alguna entidad inerte
	 * @param viva, entidad viva sobre la que actuar.
	 */
	public abstract void reaccionar(Viva viva);
	
}
