package superGoku;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animacion {

	private String nombre="Sin nombre";
	private ArrayList<Frame> frames;
	private int indiceFrame;
	private long tiempoTranscurrido;
	private long duracionTotal;
	BufferedImage imagen;
	
	//CONSTRUCTOR
	public Animacion(BufferedImage imagen){
		this.imagen = imagen;
		frames = new ArrayList<Frame>();
		indiceFrame = 0;
		tiempoTranscurrido = 0;
		duracionTotal = 0;
		comenzar();		
	}
	
	/*
	 * Si en un hilo tenemos corriendo varios métodos a la vez
	 * tendrá que esperar a que el método synchronized termine
	 * para poder seguir con la ejecución de los otros métodos que se estén ejecutando.
	 * Aunque este comportamiento pueda parecer contradictorio a la hora de usar hilos
	 * a veces se vuelve necesario porque del resultado de unos metodos dependen otros
	 */

	//Comienza la animacion
	public synchronized void comenzar() {
		tiempoTranscurrido = 0;
		indiceFrame = 0;		
	}
	
	//Sirve para agregar una escena a la animacion
	/*
	 * Cada vez que se agregue una escena, se aumenta la variable duracionTotal
	 * porque el tiempo que dura la animacion es mayor cada vez que se agrega
	 * una escena. El parametro duracion corresponde al tiempo que se desea
	 * que una escena aparezca en pantalla y se expresa en milisegundos.	 
	 */
	public synchronized void agregarFrame(FrameInfo frameInfo,long duracion){
		duracionTotal += duracion;
		frames.add(new Frame(frameInfo,duracionTotal));
	}
	
	//Sirve para cambiar de Escena
	/*
	 * El valor que recibe como parametro corresponde al tiempo
	 * transcurrido desde la última vez que se invoco al metodo actualizar().
	 * Este valor se suma a la variable tiempoTranscurrido para
	 * obtener el tiempo que la animacion lleva ejecutandose.
	 * Si el tiempo que la animacion lleva ejecutandose es mayor o igual a
	 * la duracion total, entonces se debe reiniciar la animacion.
	 * Se cambiará a la siguiente escena siempre y cuando el tiempoTranscurrido
	 * de la animación sea mayor que el tiempo en que debió terminar esa escena.
	 */
	public synchronized void actualizar(long t) throws Exception{
		if(frames.size()>1){
			tiempoTranscurrido += t;
			if(tiempoTranscurrido >= duracionTotal){
				comenzar();
			}
			while(tiempoTranscurrido > getFrame(indiceFrame).tiempoFinal){
				indiceFrame++;
			}
		}
	}
	
	
	/*
	 * Sirve para obtener la imagen actual, es decir, la imagen que se debe
	 * mostrar en pantalla en este momento.
	 */
	public synchronized BufferedImage getImagen() throws Exception{
		if(frames.size()==0){
			throw new Exception("No hay frames agregados en la animacion: "+nombre);
		}else{
			FrameInfo i = getFrame().getFrameInfo();
			return imagen.getSubimage(i.x, i.y, i.ancho, i.alto);
		}
	}
	
	/*
	 * Sirve para obtener el frame actual
	 */	
	public synchronized Frame getFrame() throws Exception{
		if(frames.size()==0){
			throw new Exception("No hay frames agregados en la animacion.");
		}else{
			return (Frame)frames.get(indiceFrame);
		}
	}
	
	/*
	 * Sirve para obtener la imagen que corresponda al indice pasado por parametro
	 */
	
	public BufferedImage getImagen(int indice) throws Exception{
		if(frames.size()<indice){
			throw new Exception("No se puede devolver la imagen "+indice+" porque aun no ha sido agregada");
		}else{
			FrameInfo i = getFrame(indice).getFrameInfo();
			return imagen.getSubimage(i.x, i.y, i.ancho, i.alto);			
		}
	}
	
	//Sirve para obtener una escena de la animacion
	/*
	 * El valor pasado por parametro representa el indice del frame
	 * que se desea sea devuelto.
	 */	
	public Frame getFrame(int indice) throws Exception{
		if(frames.size()<indice){
			throw new Exception("No se puede devolver el frame "+indice+" porque aun no ha sido agregado");
		}else{
			return (Frame)frames.get(indice);
		}
	}
	
	//Sirve para obtener el tiempo que la animacion lleva ejecutandose
	public long getTiempoTranscurrido(){
		return tiempoTranscurrido;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	//***************************************************************************
	//*******************CLASE INTERNA DE TIPO PRIVADA***************************
	
	public class Frame{
		FrameInfo frameInfo;
		long  tiempoFinal;
		
		public Frame(FrameInfo frame,long tiempoFinal){
			this.frameInfo = frame;
			this.tiempoFinal = tiempoFinal;
		}
		
		public FrameInfo getFrameInfo(){
			return frameInfo;
		}
		
	}
	
}
