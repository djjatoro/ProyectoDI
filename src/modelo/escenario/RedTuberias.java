package modelo.escenario;

import modelo.entidades.Animada;
import modelo.entidades.Entidad;
import modelo.entidades.R2d2;
import java.util.LinkedList;

import javax.swing.*;
import java.awt.event.*;

/**
 * <p><b>Título:</b> Clase Red de tuberías
 * <p><b>Descripción:</b> Conjunto de tuberías conectadas que componen el escenario de juego.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 * 
 */
public class RedTuberias implements ActionListener {

	// CONSTANTES
	private static final int VELOCIDAD_JUEGO = 500;
	private static final int EXPANSION_AGUA = 10;
	
	//ATRIBUTOS
	private LinkedList<Tuberia> listaTuberias;
	private EstadoRed estado;
	private Timer temporizador;
	private int turnos;
	
	

	//CONSTRUCTORES
	/**
	 * Constructor de la clase red de tuberías.
	 * Inicializa la lista de tuberías contenidas en la red y coloca el estado de la red
	 * como "VACÍA" al no haber aún elementos en ella.
	 *
	 */
	public RedTuberias(){
		
		listaTuberias = new LinkedList<Tuberia>();
		estado = EstadoRed.VACIA;
		turnos = 1;
	}
	
	//MÉTODOS
	
	/**
	 * Método que devuelve la primera celda de la red de tuberías.
	 * Esta celda corresponde con la celda negativa de la primera tubería.
	 * @return Primera celda de la red, celda negativa de la primera tubería.
	 */
	public Celda celdaInicial(){
		return listaTuberias.getFirst().getCeldasTramo().getFirst();
	}
	
	/**
	 * Método que devuelve una lista con todas las celdas de la red.
	 * @return Lista con las celdas de todas las tuberías.
	 */
	public LinkedList<Celda> getCeldas(){
            LinkedList<Celda> listaC = new LinkedList<>();
            for (Tuberia t: listaTuberias)
                for (Tramo tr : t.getTramosTuberia())
                    listaC.addAll(tr.getCeldasTramo());
            return listaC;
	}

	/**
	 * Método que, dada una posición, busca una celda cuya posición sea la pasada como
	 * parámetro.
	 * @param posicion, Posición que debe tener la celda que buscamos.
	 * @return Celda cuya posición sea la indicada o null en caso de no encontrar ninguna
	 * en la red con dicha posición.
	 */
	public Celda getCelda(Posicion posicion){
            for (Celda c : getCeldas())
                if (c.getPosicion()==posicion)
                    return c;
            return null;
	}
	
	/**
	 * Método que, dado el índice de una tubería y el de una celda dentro de la tubería,
	 * devuelve dicha celda.
	 * @param tuberia, entero, índice de la tubería a buscar.
	 * @param celda, entero, índice de la celda dentro de la tubería.
	 * @return Celda con el índice especificado dentro de la tubería de índice "tubería".
	 */
	public Celda getCelda(int tuberia, int celda){
            return listaTuberias.get(tuberia).getCelda(celda);
	}
	
	/**
	 * Método que, dado el índice de una tubería, retorna la longitud de dicha tubería.
	 * @param tuberia, índice de la tubería dentro de la red.
	 * @return entero, longitud (número de celdas) de la tubería de índice especificado.
	 */
	public int getLongitudTuberia(int tuberia){
		return listaTuberias.get(tuberia).getLongitud();
	}
	
	/**
	 * Método que devuelve el número de tuberías de la red
	 * @return entero, número de tuberías que componen la red.
	 */
	public int getNumeroTuberias(){
		return listaTuberias.size();
	}
	
	/**
	 * Método de adición de nuevas tuberías a la red.
	 * En caso de estar en el estado "VACÍA", pasará al estado "PRIMERATUBERIA", si estuviese
	 * en éste, pasará a "CONFIGURACIÓN".
	 * @param tuberia, nueva tubería a añadir a la red.
	 * @return entero, índice de la nueva tubería de la red.
	 */
	public int addTuberia(Tuberia tuberia){
		listaTuberias.add(tuberia);
                return listaTuberias.size()-1;
	}
	
	/**
	 * Método para el enlace de 2 tuberías en la red.
	 * El método comprobará que la red se encuentra en "configuración".
	 * Tras ello, colocará convenientemente las tuberías a enlazar y conectará
	 * las celdas que hayan sido indicadas como parámetro.
	 * @param tuberia1, una de las tuberías que se enlazarán. Esta no será desplazada ni girada,
	 * quedará estática para la conexión de la segunda.
	 * @param tuberia2, tubería a enlazar. Se colocará y girará convenientemente para realizar
	 * la conexión.
	 * @param celdaConexion, celda de la primera tubería que quedará enlazada con la celda
	 * negativa de la 2ª tubería.
	 * @param orientacionConexion, orientación en la que se realizará la conexión, que será
	 * la opuesta a la nueva orientación negativa de la tubería 2.
	 * @return
	 */
	public boolean enlazarTuberias(int tuberia1, int tuberia2,
            int celdaConexion, Orientacion orientacionConexion){
            if (estado == EstadoRed.VACIA) {
                    throw new IllegalStateException("Red vacía. No hay tuberías que enlazar.");
            }
            else if (estado == EstadoRed.PRIMERATUBERIA) {
                    throw new IllegalStateException("Sólo hay una tubería en la red, aún no es posible el enlace.");
            }
            else if (estado == EstadoRed.CONSTRUIDA) {
                    throw new IllegalStateException("Red construída. No se puede enlazar nuevas tuberías");
            }
            if (listaTuberias.get(tuberia1-1)==null || listaTuberias.get(tuberia2-1)==null){
                    throw new IndexOutOfBoundsException("Error en los índices de las tuberías");
            }
            if (listaTuberias.get(tuberia1-1).getCelda(celdaConexion)==null){
                    throw new IndexOutOfBoundsException("Error en el índice de la celda");
            }
            if (listaTuberias.get(tuberia1-1).getCelda(celdaConexion).getCeldaVecina(orientacionConexion)==null){
                    listaTuberias.get(tuberia2-1).desplazar(listaTuberias.get(tuberia1-1).getCelda(celdaConexion).getPosicion().desplaza(orientacionConexion));
                    listaTuberias.get(tuberia2-1).girar(orientacionConexion.opuesta());

                    return listaTuberias.get(tuberia1-1).getCelda(celdaConexion).conecta(listaTuberias.get(tuberia2-1).getCeldaNegativa(), orientacionConexion);	
                    }

            return false;

	}
	
	/**
	 * Método para terminar la construcción de la red, tras aplicarlo, la red pasa a estado
	 * "CONSTRUIDA" y no se podrá aplicar sobre ella ninguna nueva acción.
	 *
	 */
	public void finalizaConstruccion(){
		;
	}
	
	/**
	 * Devuelve una cadena con la descripción de la red.
	 * @return Cadena con las características de la red.
	 * Devuelve una cadena con los datos de cada tubería que la compone
	 * así como su estado.
	 */
	public String toString(){
		String tuberias = null;
		
		for (Tuberia tuberia: listaTuberias){
			tuberias += tuberia.toString() + ' ';
		}
		return getClass().getName() + "[Lista de tuberías: " + tuberias +
		"], Estado: " + estado.toString();
	}
	
	/**
	 * Método que añade una entidad a la red de tuberías.
	 * Para ello, establece cual será la celda que esta entidad ocupe.
	 * @param tuberia, entero que indica el orden de la tubería en la que se insertará la
	 * entidad
	 * @param celda, entero que indica el orden de la celda, dentro de la tubería
	 * en la que estará la entidad
	 * @param entidad, entidad a colocar
	 */
	public void addEntidad(int tuberia, int celda, Entidad entidad){
		entidad.setCelda(this.getCelda(tuberia, celda));
	}
	
	/**
	 * Método sobrecargado para añadir una entdidad a la red de tuberías
	 * @param celda, Celda en la cual se situará la entidad
	 * @param entidad, entidad a colocar en la red.
	 */
	public void addEntidad(Celda celda, Entidad entidad){
		if (this.getCeldas().contains(celda)){
			entidad.setCelda(celda);
		}	
	}
	
	/**
	 * Método que devuelve una lista con todas las entidades que pertenecen a la red
	 * de tuberías en un momento determinado
	 * @return LinkedList<Entidad>, entidades colocadas en alguna
	 * de las celdas de la red.
	 */
	public LinkedList<Entidad> getEntidades(){
		LinkedList<Entidad> resultado = new LinkedList<Entidad>();
		
		for(Celda celda: getCeldas()){
			resultado.addAll(celda.getEntidades());
		}
		return resultado;
	}
	
	/**
	 * Método que servirá para iniciar el temporizador que animará las distintas entidades
	 * del juego.
	 * Únicamente podrá ser ejecutado si la red ha sido construída.
	 * Dejará esta en el estado "ARRANCADA".
	 */
	public void iniciar(){
		if (estado!=EstadoRed.CONSTRUIDA){
			throw new IllegalStateException("Red sin construir, no puede ser iniciarse el juego.");
		}
		temporizador = new Timer(VELOCIDAD_JUEGO,this);
		temporizador.start();
		estado = EstadoRed.ARRANCADA;
	}
	
	/**
	 * Método que detendrá el temporizador y con él todas las entidades animadas del 
	 * juego.
	 * Únicamente podrá ejecutarse si la red está en el estado "ARRANCADA".
	 * La dejará como "FINALIZADA".
	 */
	public void parar(){
		if (estado!=EstadoRed.ARRANCADA){
			throw new IllegalStateException("El juego no se ha iniciado, no puede ser parado.");
		}
		estado = EstadoRed.FINALIZADA;
		temporizador.stop();
	}
	
	/**
	 * Método que se encargará de ejecutar periodicamente el temporizador.
	 * Obligatorio para implementar la interfaz ActionListener.
	 * Se compone de 2 fases, extender el agua en el juego y aplicar los turnos
	 * a las entidades del mismo.
	 */
	public void actionPerformed(ActionEvent e){
		extenderFuego();
		aplicarTurnos();
	}
	
	/**
	 * Método que recorre la lista con todas las entidades del juego.
	 * Le dará el turno a cada una de ellas para que realicen la acción que les
	 * corresponda.
	 */
	public void aplicarTurnos(){
		Animada animada;
		for (Entidad entidad: getEntidades()){
			if (entidad instanceof Animada){
				animada = (Animada) entidad;
				animada.turno();
			}	
		}
	}
	
	/**
	 * Método para extender el agua por la red de tuberías.
	 * Se realizará una extensión cada 3 turnos.
	 * El método también se encarga de generar las burbujas en las celdas fuentes,
	 * lo hará cada 3 extensiones de agua consecutivas.
	 *
	 */
	public void extenderFuego(){
		if (turnos%EXPANSION_AGUA == 0){
			LinkedList<Celda> inundadas = new LinkedList<Celda>();
			for (Celda celda: this.getCeldas()){
				if (celda.isArdiendo()) inundadas.add(celda);
			}	
			for (Celda celda: inundadas){
				celda.expandirFuego();
			}
		}
		if (turnos%(EXPANSION_AGUA*3) == 0){
			for(Celda celda: this.getCeldas()){
				if (celda instanceof Explosion) {
					addEntidad(celda, new R2d2());
				}
			}
		}
		turnos++;
	}
	
}
