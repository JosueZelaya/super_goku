package elementosEscenario;

import java.awt.Graphics2D;
import java.awt.Image;

public class FrameEscenario{
	
	private float x,y,ancho,alto;		
	private Image fondo;
	
	public FrameEscenario(Image fondo){
		this.fondo = fondo;
		alto = fondo.getHeight(null);
		ancho = fondo.getWidth(null);
	}
	
	public synchronized void dibujar(Graphics2D g){
		g.drawImage(fondo,(int)x,(int)y,(int)ancho,(int)alto,null);
	}
	
	//getters and setters
	public void setX(float x){
		this.x = x;
	}
	
	public float getX(){
		return x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public float getY(){
		return y;
	}
	
	public void setAncho(float ancho){
		this.ancho = ancho;
	}
	
	public void setAlto(float alto){
		this.alto = alto;
	}
	
	public float getAncho(){
		return ancho;
	}
	
	public float getAlto(){
		return alto;
	}
	
}
