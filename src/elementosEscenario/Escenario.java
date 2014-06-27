package elementosEscenario;

import java.awt.Graphics2D;
import java.util.ArrayList;

import superGoku.Cinematica;
import superGoku.Jugador;
import nucleo.ManejadorPantalla;


public class Escenario{
	
	private float x,y,ancho,alto;
	private float xCentral,yCentral;
	private float velocidadX;
	private float aceleracionX;
	private boolean e1,e2,e3,e4,e5,e6,e7;
	private boolean completado = false;
	private int cont;
	
	private ArrayList<FrameEscenario> frames;
	
	public Escenario(ManejadorPantalla pantalla){
		xCentral = pantalla.getAnchoPantalla()/2;
		yCentral = pantalla.getAltoPantalla()/2;
		frames = new ArrayList<FrameEscenario>();
		x = 0;
		y = 0;
		ancho = 0;
		alto = pantalla.getAltoPantalla();
		velocidadX = 0;
		aceleracionX = 0;
	}
	
	public float getAlto() {
		return alto;
	}

	public void actualizar(long tiempoTranscurrido,ManejadorPantalla pantalla,Jugador criatura) throws Exception{
		float anchoPantalla = pantalla.getAnchoPantalla();
		if(criatura.getX()>anchoPantalla/3 && criatura.getX()<ancho-2*anchoPantalla/3){
			if(x<=0){
				velocidadX = criatura.getVelocidadX();
				aceleracionX = criatura.getAceleracionX();
				x -= Cinematica.getDesplazamientoX(velocidadX, aceleracionX, tiempoTranscurrido);
				xCentral = -1*x +pantalla.getAnchoPantalla()/2;
			}else{
				x=0;
			}			
		}else if(criatura.getX()<0){
			criatura.setX(0);
			x =0;
		}else if(criatura.getX()+criatura.getAncho()>ancho){
			criatura.setX((float)(ancho-criatura.getAncho()+1));
		}
		
		if(isE1() && isE2() && isE3() && isE4() && isE5() && isE6() && isE7()){
			cont+=tiempoTranscurrido;
			if(cont>1000){
				cont = 0;	
				completado = true;								
			}
			
		}		
	}
	
	public boolean estaCompletado(){
		return completado;
	}
	
	public float getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(float velocidadX) {
		this.velocidadX = velocidadX;
	}

	public float getAceleracionX() {
		return aceleracionX;
	}

	public void setAceleracionX(float aceleracionX) {
		this.aceleracionX = aceleracionX;
	}

	public void agregarFrame(FrameEscenario frame){		
		frame.setX(x+ancho);
		ancho += frame.getAncho();
		frames.add(frame);
	}
	
	public void actualizarG(Graphics2D g){
		g.translate(x, y);		
	}
	
	public void dibujar(Graphics2D g){
		actualizarG(g);
		int nFrames = frames.size();
		for(int i=0;i<nFrames;i++){
			frames.get(i).dibujar(g);
		}		
	}
	
	public float getXCentral(){		
		return xCentral;
	}
	
	public float getYCentral(){
		return yCentral;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public float getX(){
		return this.x;
	}

	public boolean isE1() {
		return e1;
	}

	public void setE1(boolean e1) {
		this.e1 = e1;
	}

	public boolean isE2() {
		return e2;
	}

	public void setE2(boolean e2) {
		this.e2 = e2;
	}

	public boolean isE3() {
		return e3;
	}

	public void setE3(boolean e3) {
		this.e3 = e3;
	}

	public boolean isE4() {
		return e4;
	}

	public void setE4(boolean e4) {
		this.e4 = e4;
	}

	public boolean isE5() {
		return e5;
	}

	public void setE5(boolean e5) {
		this.e5 = e5;
	}

	public boolean isE6() {
		return e6;
	}

	public void setE6(boolean e6) {
		this.e6 = e6;
	}

	public boolean isE7() {
		return e7;
	}

	public void setE7(boolean e7) {
		this.e7 = e7;
	}

	
	
}
