package superGoku;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite {
	
	private ArrayList<Animacion> animaciones;
	private float x,y;
	private float escalaX,escalaY;
	private float velocidadX;
	private float velocidadY;
	private float aceleracionX;
	private float aceleracionY;
	private int indiceAnimacion;	
	
	//CONSTRUCTOR
	/*
	 * Se construye el objeto pasando por parametro las animaciones que deberá
	 * contener, dentro de un ArrayList
	 */
	public Sprite(ArrayList<Animacion> animaciones){
		escalaX = 1;
		escalaY = 1;
		this.animaciones = animaciones;
		x= 0;
		y = 0;
		velocidadX = 0;
		velocidadY = 0;
		aceleracionX = 0;
		aceleracionY = 0;
		indiceAnimacion = 0;		
	}	
	
	/*
	 * Metodo encargado de actualizar la posicion del Sprite, en funcion del
	 * tiempo transcurrido. Usando Física de movimiento de partículas.
	 * Tambien se encarga de actualizar la imagen que debe mostrar.
	 */
	public void actualizar(long tiempoTranscurrido) throws Exception{
		animaciones.get(indiceAnimacion).actualizar(tiempoTranscurrido);
		
		//Actualiza la velocidad en X, y su posicion en X
		velocidadX = Cinematica.getVelocidadX(velocidadX, aceleracionX, tiempoTranscurrido);
		x += Cinematica.getDesplazamientoX(velocidadX, aceleracionX, tiempoTranscurrido);
		
		//Actualiza la velocidad en Y, y su posicion en Y
		tiempoTranscurrido *=0.3;
		velocidadY = Cinematica.getVelocidadY(velocidadY,aceleracionY, tiempoTranscurrido);
		y += Cinematica.getDesplazamientoY(velocidadY,aceleracionY,tiempoTranscurrido);		
		
	}
	
	//Se encarga de agregar animaciones al Sprite
	public synchronized void agregarAnimacion(Animacion animacion){
		animaciones.add(animacion);
	}
	
	//Reinicia la animacion
	public void reiniciarAnimacion(int indiceAnimacion){
		animaciones.get(indiceAnimacion).comenzar();
	}
		
	/*
	 * Debemos recordar que el sprite contiene una imagen en la clase
	 * Animacion que es la que se muestra, por ello utilizamos la clase
	 * animacion para obtener la imagen y luego su ancho. Lo mismo sucede
	 * en el caso de querer obtener la altura.
	 */
	
	//Sirve para obtener el ancho del sprite
	public double getAncho() throws Exception{		
		//Se obtiene la informacion del frame actual
		FrameInfo i = animaciones.get(indiceAnimacion).getFrame().getFrameInfo();
		//Se obtiene la matriz escalar de 2x2
		Matriz escalar = Matriz.getMatrizEscalar(escalaX, escalaY);
		//Se crea una matriz con las dimensiones del frame actual que corresponde al ancho y alto del sprite
		Matriz dimensiones = new Matriz(1,2);
		dimensiones.setPosicion((double) i.ancho, 0, 0);
		dimensiones.setPosicion((double)i.alto, 0, 1);
		/*
		 * Se multiplica la matriz de dimensiones por la matriz escalar y se guarda el resultado
		 * de la multiplicacion en la matriz de dimensiones, que ahora contiene las dimensiones escaladas.
		 */
		dimensiones = Matriz.Multiplicar(dimensiones, escalar);
		//Se devuelve el ancho
		return dimensiones.getPosicion(0, 0);
	}	

	//Sirve para obtener la altura del sprite
	public double getAlto() throws Exception{
		//Se obtiene la informacion del frame actual
		FrameInfo i = animaciones.get(indiceAnimacion).getFrame().getFrameInfo();
		//Se obtiene la matriz escalar de 2x2
		Matriz escalar = Matriz.getMatrizEscalar(escalaX, escalaY);
		//Se crea una matriz con las dimensiones del frame actual que corresponde al ancho y alto del sprite
		Matriz dimensiones = new Matriz(1,2);
		dimensiones.setPosicion((double) i.ancho, 0, 0);
		dimensiones.setPosicion((double)i.alto, 0, 1);
		/*
		 * Se multiplica la matriz de dimensiones por la matriz escalar y se guarda el resultado
		 * de la multiplicacion en la matriz de dimensiones, que ahora contiene las dimensiones escaladas.
		 */
		dimensiones = Matriz.Multiplicar(dimensiones, escalar);
		//Se devuelve el ancho
		return dimensiones.getPosicion(0, 1);	
	}
	
	//Devuelve la imagen actual del sprite
	public BufferedImage getImagen() throws Exception{
		return animaciones.get(indiceAnimacion).getImagen();			
	}
	
	//Dibuja el Sprite
	public void dibujar(Graphics2D g) throws Exception{
	
		g.drawImage(getImagen(),(int)x,(int)y,(int) getAncho(),(int) getAlto(), null);
		//BufferedImage i = getImagen();		
		//g.drawImage(i,(int)x+200,(int)y-100,i.getWidth(),i.getHeight(),0,0,100,100,null);
		//g.drawImage(i,(int)(x+200),(int)(y-100),i.getWidth(),-100,0,0,100,100,null);
		//AffineTransform xform = new AffineTransform();
		//xform.rotate(Math.toRadians(95),i.getWidth()/2,i.getHeight()/2);
		//g.translate(this.getX(), this.getY());
		//g.drawImage(i,(int)x,(int)y,getAlto(),getAncho());		
	}	
	
	//Devuelve un objeto Rectangle con las dimensiones del Sprite
	public Rectangle getRectangulo() throws Exception{
		return new Rectangle((int)x,(int) y, (int) getAncho(), (int) getAlto());
	}
	
	//EL RESTO DE GETTERS Y SETTERS	
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(float velocidadX) {
		this.velocidadX = velocidadX;
	}

	public float getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(float velocidadY) {
		this.velocidadY = velocidadY;
	}

	public int getIndiceAnimacion() {
		return indiceAnimacion;
	}

	public void setIndiceAnimacion(int indiceAnimacion) {
		this.indiceAnimacion = indiceAnimacion;
	}	

	public float getAceleracionY() {
		return aceleracionY;
	}

	public void setAceleracionY(float aceleracionY) {
		this.aceleracionY = aceleracionY;		
	}
	
	public float getAceleracionX() {
		return aceleracionX;
	}

	public void setAceleracionX(float aceleracionX) {
		this.aceleracionX = aceleracionX;
	}
	
	public float getEscalaX() {
		return escalaX;
	}

	public void setEscalaX(float escalaX) {
		this.escalaX = escalaX;
	}

	public float getEscalaY() {
		return escalaY;
	}

	public void setEscalaY(float escalaY) {
		this.escalaY = escalaY;
	}

	public void setEscala(float x, float y){
		escalaX = x;
		escalaY = y;
	}

}
