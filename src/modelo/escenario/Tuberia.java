package modelo.escenario;

import java.util.LinkedList;

/**
 * <p><b>Título:</b> Clase Tubería
 * <p><b>Descripción:</b> Tipo de tramo compuesto a su vez por tramos.
 * Esta clase, que hereda de tramo, tendrá un conjunto número indefinido
 * (aunque finito) de tramos.
 * Al poder contener cualquier tipo de tramo, una tubería puede estar contenida dentro
 * de otra.
 * @author Miguel Ángel Marín Fernández
 * @version 1.0
 *
 */
public class Tuberia extends Tramo {

	//ATRIBUTOS
	private LinkedList<Tramo> tramosTuberia;
	
	//CONSTRUCTORES
	/**
	 * Constructor de la clase tubería.
	 * Se creará a partir de un tramo, su tramo inicial, de este cogerá su celda y orientación
	 * negativas, que pasarán a ser las suyas propias.
	 * @param tramo, Tramo inicial que se incluirá en la tubería.
	 */
	public Tuberia(Tramo tramo) {
		super(tramo.getOrientacionNegativa());
		
		tramosTuberia = new LinkedList<Tramo>();
		tramosTuberia.add(tramo);
	}

	/**
	 * Método get para devolver una lista con los tramos contenidos en la tubería.
	 * @return Lista de tramos de la tubería.
	 */
	public LinkedList<Tramo> getTramosTuberia() {
		
		return new LinkedList<Tramo>(tramosTuberia);
	}

	/**
	 * Método para añadir un tramo a la tubería.
	 * El método se encargará de colocar la celda negativa del nuevo tramo en el conector
	 * positivo de la tubería, lo girará para establecer su nueva orientación negativa 
	 * (debe ser la contraria a la positiva de la tubería) y conectará la celda negativa
	 * del nuevo tramo con la positiva de la tubería.
	 * @param tramo. Nuevo tramo a añadir a la tubería.
	 */
	public void addTramo(Tramo tramo){
		tramo.getCeldaNegativa().colocar(this.conectorPositivo());
		tramo.girar(this.getOrientacionPositiva().opuesta());
		tramo.getCeldaNegativa().conecta(this.getCeldaPositiva(), tramo.getOrientacionNegativa());
		tramosTuberia.add(tramo);
	}

	/**
	 * Método get para la obtención del listado de celdas que componen la tubería.
	 * Se encargará de recorrer cada uno de los tramos que la componen y extraer sus celdas
	 * para devoverlas en un solo listado
	 * @return Listado completo de celdas.
	 */
	public LinkedList<Celda> getCeldasTramo() {
		LinkedList<Celda> celdasTramo = new LinkedList<Celda>();
		
		for (Tramo tramo: tramosTuberia){
			celdasTramo.addAll(tramo.getCeldasTramo());
		}
		
		return celdasTramo;
	}
	
	/**
	 * Método para la obtención de la celda negativa de una tubería.
	 * Coincidirá con la celda negativa del primer tramo que forme la tubería.
	 * @return celda negativa, primera celda de la tubería.
	 */
	public Celda getCeldaNegativa(){
		return tramosTuberia.getFirst().getCeldaNegativa();
	}
	
	/**
	 * Método para la obtención de la celda positiva de una tubería.
	 * Coincidirá con la celda positiva del último tramo que forme la tubería.
	 * @return celda positiva, última celda de la tubería. 
	 */
	public Celda getCeldaPositiva(){
		return tramosTuberia.getLast().getCeldaPositiva();
	}
	
	/**
	 * Método get para la obtención del número de celdas que componen la tubería.
	 * Será la suma de las longitudes de los tramos que la componen.
	 * @return longitud, número de celdas que forman la tubería.
	 */
	public int getLongitud(){
		return getCeldasTramo().size();
	}
	
	/**
	 * Método get para la obtención de la orientación positiva de la tubería.
	 * En este caso, será la orientación positiva del último de los tramos que contiene.
	 * @return orientacion positiva.
	 */
	public Orientacion getOrientacionPositiva() {
		return tramosTuberia.getLast().getOrientacionPositiva();

	}

	/**
	 * Método para girar una tubería.
	 * El método devuelve una nueva tubería con la orientación negativa indicada.
	 * Para ello, gira el primer tramo que compone la tubería que recibe la llamada y a partir
	 * de él la compone uniendo el resto de tramos a este primero, ya girado.
	 * @param orientacion, Nuevo orientación negativa de la tubería.
	 * @return Nuevo tramo con la orientación negativa indicada.
	 */
	public void girar(Orientacion orientacion) {
		LinkedList<Tramo> tramos = new LinkedList<Tramo>();
		
		// se usa una lista auxiliar de tramos, copias de los de la tubería
		for (Tramo tramo: tramosTuberia){
			tramos.add(tramo.clone());
		}
		
		// giramos el primero y cambiamos el atributo de orientación
		tramos.getFirst().girar(orientacion);
		this.setOrientacionNegativa(orientacion);
		tramosTuberia.clear();
		tramosTuberia.add(tramos.getFirst());
		
		// Reconstruímos la tubería añadiendo los tramos.
		
		for (int i=1; i<tramos.size();i++){
			addTramo(tramos.get(i));
		}
		
	}
	
	/**
	 * Método de acceso a las celdas por orden numérico.
	 * El acceso se realizará contando desde la celda negativa, que será el número 1.
	 * @param orden, entero que contiene la posición ordenada de la celda que buscamos.
	 * @return celda.
	 */
	public Celda getCelda(int orden){
		return getCeldasTramo().get(orden-1);
	}

	/**
	 * Redefinición del método toString para añadir a las características de la tubería
	 * la lista de tramos que contiene.
	 * @return Cadena con las características de la tubería.
	 */
	public String toString(){
		String tramos = null;
		
		for(Tramo tramo: getTramosTuberia()){
			tramos += tramo.toString() + ' ';
		}
		
		return super.toString() + ", Tramos en la tubería: " + tramos;
	}
	
	/**
	 * Método de copia de objetos de tipo Tubería.
	 * Se implementará una copia profunda, el clone devolverá un nuevo objeto totalmente
	 * distinto al que realiza la llamada.
	 * Para ello se realizará una copia profunda de cada uno de los tramos que componen la
	 * tubería.
	 * @return Nueva Tubería de iguales características a la clonada.
	 */
	@SuppressWarnings("unchecked")
	public Tuberia clone(){
		
		Tramo tramo = super.clone();	
		
		Tuberia tuberia = (Tuberia) tramo;
		
		tuberia.tramosTuberia = new LinkedList<Tramo>();
		
		for (int i=0; i<tramosTuberia.size();i++){
			tuberia.tramosTuberia.add(tramosTuberia.get(i).clone());
			if (i>0){
				tuberia.tramosTuberia.get(i).getCeldaNegativa().conecta(tuberia.tramosTuberia.get(i-1).getCeldaPositiva(), tuberia.tramosTuberia.get(i).getOrientacionNegativa());
			}	
		}
		
		return tuberia;
	}
}
