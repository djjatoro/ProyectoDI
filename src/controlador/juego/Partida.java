package controlador.juego;

import vista.IPantalla;
import vista.Dibujable;
import vista.IControlador;
import modelo.escenario.Orientacion;
import modelo.escenario.Tuberia;
import modelo.escenario.Codo;
import modelo.escenario.RedTuberias;
import modelo.escenario.Tramo;
import modelo.escenario.Salida;
import modelo.escenario.Tubo;
import modelo.escenario.TramoSimple;
import modelo.escenario.Puerta;
import modelo.escenario.Explosion;
import modelo.escenario.Posicion;
import modelo.entidades.LadoOscuro;
import modelo.entidades.StormTrooper;
import modelo.entidades.KyloRen;
import modelo.entidades.Entidad;
import modelo.entidades.R2d2;
import modelo.entidades.Jugador;
import modelo.entidades.DarthVader;
import modelo.entidades.Fuerza;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

import javax.swing.Timer;

import controlador.configuracion.ConfiguracionIncorrecta;
import vista.Pantalla;

/**
 * <p><b>Título:</b> Clase Partida
 * <p><b>Descripción:</b> Clase encargada del control del juego.
 * La clase partida realizará tareas de visualización e interacción con el usuario.
 * Será también la encargada del escenario de juego
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 *
 */

public class Partida implements IControlador, ActionListener {
	
	// CONSTANTES
	
	private static final int VELOCIDAD = 500;

	// ATRIBUTOS
	
	private RedTuberias redActual;
	private IPantalla pantalla;
	private Jugador jugador;
	private Timer temporizador;
	
	// CONSTRUCTORES
	
	/**
	 * Constructor de la clase partida.
	 * Únicamente tendrá como tarea inicializar el atributo que contiene la pantalla
	 * gráfica que ofrece la visualización del juego y el temporizador de la clase para
	 * tareas de comprobación del juego.
	 * El resto de atributos se cargarán con la creación de la red de tuberías. 
	 *
	 */
	public Partida(){
            pantalla = new Pantalla(this);		
	}
	
	// MÉTODOS
	
	/**
	 * Método de creación de la red de tuberías a partir del nombre de un fichero.
	 * Obligatorio para implementar la interfaz IControlador.
	 * @param fichero, cadena con la ruta completa del fichero de configuración para 
	 * la creación de la red de tuberías.
	 * @exception ConfiguracionIncorrecta, excepción que saltará en caso de que el fichero
	 * no se ajuste a los normas sintácticas que deben cumplirse.
	 * @exception IOException, excepción para el caso de que haya algún problema en la
	 * lectura del fichero.
	 */
	public void abrir(String fichero){

	}
	
	/**
	 * Método de creación de la red de tuberías.
	 * A partir del mapa recibido del procesamiento de la clase "ProcesadorConfiguración"
	 * crearemos el escenario y las entidades que pertenezcan al mismo.
	 * @param red, estructura de datos dividida en 3 partes (tuberias, conexiones y entidades)
	 * de la cual obtendremos toda la información para la creación de la red. 
	 * @return RedTuberias, nueva red de tuberías creada a partir de los datos provenientes
	 * del procesamiento del fichero de configuración.
	 */
	public RedTuberias crearRed(Map<String,List<List<String>>> red){
		List<List<String>> listaTuberias;
		List<List<String>> listaConexiones;
		List<List<String>> listaEntidades;
		
		RedTuberias redTuberias = new RedTuberias();
		
		listaTuberias = red.get("tuberias");
		
		
		for(List<String> listaTramos: listaTuberias){
			LinkedList<Tramo> lista = obtenerTramos(listaTramos);
			Tuberia tuberia = new Tuberia(lista.getFirst());
			for (int i = 1;i<lista.size();i++){
				tuberia.addTramo(lista.get(i));
			}
		redTuberias.addTuberia(tuberia);	
		}
		
		listaConexiones = red.get("conexiones");
		
		for(List<String> listaParametros: listaConexiones){
			try {
			redTuberias.enlazarTuberias(Integer.parseInt(listaParametros.get(0)), Integer.parseInt(listaParametros.get(1)),
					Integer.parseInt(listaParametros.get(2)), obtenerOrientacion(listaParametros.get(3)));
			}
			catch (Exception e) {
				pantalla.abrirDialogo("Error en la conexión de las tuberías " + 
						listaParametros.get(0) + " y " + listaParametros.get(1) + 
						" " + e.getMessage(), "ERROR");
				e.printStackTrace();
			}
		}
		
		listaEntidades = red.get("entidades");
		
		for (List<String> entidades: listaEntidades){
			try {
			redTuberias.addEntidad(Integer.parseInt(entidades.get(1)), Integer.parseInt(entidades.get(2)), obtenerEntidad(entidades.get(0),redTuberias));
			}
			catch (Exception e) {
				pantalla.abrirDialogo("Error al insertar la entidad " +
						entidades.get(0) + " " + e.getMessage(), "ERROR");
			}
		}
		
		jugador = new Jugador();
		redTuberias.addEntidad(1, 1, jugador);
		
		redTuberias.finalizaConstruccion();
		
		return redTuberias;
	}
	
	
	/**
	 * Método auxiliar para el procesamiento de cada uno de los tramos que formarán
	 * cada tubería de la red.
	 * @param listaTramos, lista de String, cada elemento corresponderá al nombre de un
	 * tipo de tramo, el cual procesaremos para su creación. 
	 * @return LinkedList de tramos, cada elemento de la lista será, en este caso un
	 * tramo real ya creado.
	 */
	private LinkedList<Tramo> obtenerTramos(List<String> listaTramos){
		LinkedList<Tramo> resultado = new LinkedList<Tramo>();
		for (String tramo: listaTramos){
			
			if (tramo.contains("tubo")){
				Tubo tubo = new Tubo(Posicion.getOrigen(),Orientacion.IZQUIERDA,Integer.parseInt(tramo.substring(4)));
				resultado.add(tubo);
			}
			else if (tramo.contains("codo")){
				boolean esL;
				if (tramo.endsWith("nol")) esL = false;
				else esL = true;
				
				Codo codo = new Codo(Posicion.getOrigen(),Orientacion.IZQUIERDA, esL);
				resultado.add(codo);
			}
			else if (tramo.equals("salida")){
				TramoSimple ts = new TramoSimple(new Salida(Posicion.getOrigen()),Orientacion.IZQUIERDA);
				resultado.add(ts);
			}
			else if (tramo.equals("puerta")){
				TramoSimple ts = new TramoSimple(new Puerta(Posicion.getOrigen()),Orientacion.IZQUIERDA);
				resultado.add(ts);
			}
			else {
				TramoSimple ts = new TramoSimple(new Explosion(Posicion.getOrigen()),Orientacion.IZQUIERDA);
				resultado.add(ts);
			}
		}
		return resultado;
		
	}
	
	/**
	 * Método auxiliar para la obtención de las diferentes orientaciones que pueden
	 * darse en la conexión de tuberías.
	 * @param orientacion, String con el nombre de una de las orientaciones posibes.
	 * @return Orientacion, un nuevo objeto Orientacion que será utilizado para la conexión.
	 */
	private Orientacion obtenerOrientacion(String orientacion){
		if (orientacion.equals("arriba")) return Orientacion.ARRIBA;
		else if (orientacion.equals("abajo")) return Orientacion.ABAJO;
		else if (orientacion.equals("izquierda")) return Orientacion.IZQUIERDA;
		else return Orientacion.DERECHA;
	}
	
	/**
	 * Método auxiliar para la obtención de las entidades que contendrá la red de tuberías.
	 * @param entidad, String con el nombre de la entidad a crear.
	 * @param redTuberias, red que estamos creando, necesaria para la creación de los 
	 * enemigos.
	 * @return Entidad creada.
	 */
	private Entidad obtenerEntidad(String entidad, RedTuberias redTuberias){
		if (entidad.equals("fuerza")) return new Fuerza();
		else if (entidad.equals("ladooscuro")) return new LadoOscuro();
		else if (entidad.equals("r2d2")) return new R2d2();
		else if (entidad.equals("kyloren")) return new KyloRen(redTuberias);
		else if (entidad.equals("darthvader")) return new DarthVader(redTuberias);
		else  return new StormTrooper(redTuberias);
		
	}
	
	/**
	 * Método que devuelve una lista con todas las celdas de la red de tuberías.
	 * Obligatorio para implementar la interfaz IControlador.
	 * @return LinkedList de objetos dibujables, en este caso, celdas.
	 */
	public LinkedList<Dibujable> getCeldasDibujables(){
             return null;
	}
	
	/**
	 * Método que devuelve una lista con todas las entidades de la red de tuberías.
	 * Obligatorio para implementar la interfaz IControlador.
	 * @return LinkedList de objetos dibujables, en este caso, entidades.
	 */
	public LinkedList<Dibujable> getEntidadesDibujables(){
            return null;
	}
	
	/**
	 * Método para el establecimiento de la pantalla del juego.
	 * Obligatorio para implementar la interfaz IControlador.
	 * @param iPantalla, pantalla del juego.
	 */
	public void setPantalla(IPantalla iPantalla) {
	    ;
	}   
	
	/**
	 * Método que comprobará si se cumplen las condiciones necesarias para que pueda
	 * dibujarse la pantalla de juego.
	 * @return booleano, true en caso de cumplirse las condiciones, falso si es al contrario.
	 */
	public boolean dibujarEscenario(){
            return false;
	}
	
	/**
	 * Método de incio de una nueva partida.
	 * Comprobará si se cumplen las condiciones para comenzar y en caso afirmativo
	 * iniciará los temporizadores para animar las entidades y el de la propia partida.
	 * Obligatorio para implementar la interfaz IControlador.
	 */
	public void nueva(){
            ;
	}
	
	/**
	 * Método que indica al jugador que el usuario ha pulsado la tecla para
	 * realizar el movimiento de desplazamiento hacia abajo.
	 * Obligatorio para implementar la interfaz IControlador.
	 */
	public void mueveAbajo(){
            ;
	}
	
	/**
	 * Método que indica al jugador que el usuario ha pulsado la tecla para
	 * realizar el movimiento de desplazamiento hacia arriba.
	 * Obligatorio para implementar la interfaz IControlador.
	 */
	public void mueveArriba(){
            ;
	}
	
	/**
	 * Método que indica al jugador que el usuario ha pulsado la tecla para
	 * realizar el movimiento de desplazamiento hacia la derecha.
	 * Obligatorio para implementar la interfaz IControlador.
	 */
	public void mueveDerecha(){
            ;
	}
	
	/**
	 * Método que indica al jugador que el usuario ha pulsado la tecla para
	 * realizar el movimiento de desplazamiento hacia la izquierda.
	 * Obligatorio para implementar la interfaz IControlador.
	 */
	public void mueveIzquierda(){
            ;
	}
	
	/**
	 * 	Método que indica al jugador que el usuario ha pulsado la tecla para
	 * realizar el una acción (conmutar puerta).
	 * Obligatorio para implementar la interfaz IControlador.
	 */
	public void accion(){
            ;
	}
	
	/**
	 * Método que devuelve la posición en el eje X en cada momento del jugador
	 * Obligatorio para implementar la interfaz IControlador.
	 * @return entero con el valor de X en la posición del jugador.
	 */
	public int getPosicionXJugador(){
            return 0;
	}
	
	/**
	 * Método que devuelve la posición en el eje Y en cada momento del jugador
	 * Obligatorio para implementar la interfaz IControlador.
	 * @return entero con el valor de Y en la posición del jugador.
	 */
	public int getPosicionYJugador(){
            return 0;
	}
	
	/**
	 * Devuelve la longitud vertical de la pantalla para que esta pueda ser dibujada
	 * Obligatorio para implementar la interfaz IControlador.
	 * @return entero, valor del alto de la pantalla de juego.
	 */
	public int getAltoEscenario(){
		return 10;
	}
	
	/**
	 * Devuelve la longitud horizontal de la pantalla para que esta pueda ser dibujada
	 * Obligatorio para implementar la interfaz IControlador.
	 * @return entero, valor del ancho de la pantalla de juego.
	 */
	public int getAnchoEscenario(){
		return 25;
	}
	
	/**
	 * Método con el conjunto de acciones a realizar en caso de fin de juego.
	 * El juego terminará en caso de llegar el jugador a una salida o de quedarse sin aire.
	 * Este procedimiento se encargará de parar los temporizadores y mostrar un mensaje
	 * según se haya ganado el juego o no.
	 *
	 */
	public void finalizar(){

	}

	/**
	 * Método get que devuelve el jugador de la partida.
	 * @return jugador, entidad jugador que será controlada por el usuario.
	 */
	public Jugador getJugador() {
		return jugador;
	}

	/**
	 * Método get para la obtención de la pantalla de juego.
	 * @return pantalla de juego.
	 */
	public IPantalla getPantalla() {
		return pantalla;
	}

	/**
	 * Método get para la obtención de la red que se esté jugando en ese momento.
	 * @return redActual, red de tuberías en la que se disputa la partida.
	 */
	public RedTuberias getRedActual() {
		return redActual;
	}
	
	/**
	 * Método que ejecuta periódicamente el temporizador.
	 * Se encargará de tareas de comprobación para ver si se ha llegado al fin del juego
	 * y de informar al jugador acerca del estado de su personaje.
	 */
	public void actionPerformed(ActionEvent e){

	}
	
}
