package modelo.entidades;

/**
 * <p><b>Título:</b> Clase abstracta Viva
 * <p><b>Descripción:</b> Clase que engloba todas las entidades del juego que tienen
 * como característica común tener un límite de aire y poder ahogarse desapareciendo 
 * del juego.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 *
 */

public abstract class Viva extends Entidad implements Animada {

	// CONSTANTES
	
	private static final int LIMITE_AIRE = 10;
	
	// ATRIBUTOS
	
	private int limiteAire;
	private int aireGastado;


	
	// CONSTRUCTORES
	
	/**
	 * Constructor de las entidades vivas que establece el aire gastado a 0 y el límite
	 * de aire a una constante de clase.
	 */
	public Viva(){
		aireGastado = 0;
		limiteAire = LIMITE_AIRE;
	}
	
	
	// MÉTODOS
	
	
	/**
	 * Método que engloba las acciones que toda entidad viva realizará al recibir el turno
	 * por parte del temporizador del juego.
	 * Primero actualizará su nivel de aire, si sigue con vida, realizará su movimiento
	 * e interactuará con las entidades inertes que encuentre.
	 * En caso contrario, desaparecerá del juego.
	 */
	public void turno(){
		
		if (this.getCelda().isArdiendo()) aireGastado+=1;
		else setAireGastado(0);
		
		if (suficienteAire()){
			actuar();
			interactuar();	
		}
		else desaparecer();

	}

	/**
	 * Método get que devuelve el aire que ha gastado cada entidad en un momento determinado.
	 * @return entero con el aire que ha perdido.
	 */
	public int getAireGastado() {
		return aireGastado;
	}
	
	/**
	 * Devuelve el límite de aire que puede alcanzar una entidad en cada momento sin ahogarse.
	 * @return entero, número máximo de turnos sin respirar.
	 */
	public int getLimiteAire() {
		return limiteAire;
	}
	
	/**
	 * Establece el límite turnos que podrá aguantar la entidad sin respirar
	 * @param aire, entero, número de turnos.
	 */
	public void setLimiteAire(int aire){
		limiteAire+=aire;
	}

	/**
	 * Comprueba si el aire gastado es inferior al límite de la entidad.
	 * @return booleano, verdadero en caso de que le siga quedando aire, falso en el contrario.
	 */
	public boolean suficienteAire(){
		
		if (aireGastado > limiteAire){
			return false;
		}
		return true;
	}
	
	/**
	 * Método que se encarga de comprobar si la entidad se encuentra en la misma celda
	 * que una entidad inerte y de reaccionar convenientemente ante esta.
	 *
	 */
	public void interactuar(){
		Inerte inerte;
		for (Entidad entidad : this.getCelda().getEntidades()){
			if (entidad instanceof Inerte){
				inerte = (Inerte) entidad;
				inerte.reaccionar(this);
			}	
		}	
	}

	/**
	 * Método que establece el aire gastado por la entidad viva a un valor 
	 * determinado
	 * @param aireGastado, entero que representa el aire gastado por la entidad.
	 */
	public void setAireGastado(int aireGastado) {
		this.aireGastado = aireGastado;
	}
	
	/**
	 * Devuelve una cadena con la descripción del objeto Viva.
	 * @return Cadena con las características de la entidad viva.
	 */
	public String toString(){
		return super.toString() + " aire gastado: " + getAireGastado() +
		" límite de aire: " + getLimiteAire();
	}
        
}
