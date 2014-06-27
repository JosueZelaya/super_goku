package elementosEscenario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import superGoku.Criatura;

public class Obstaculo {

	float ancho,alto,x,y;
	Rectangle rSuelo;
	Color color;
	BufferedImage imagen;
	private boolean activo=false;
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Obstaculo(){
		ancho = 50;
		alto = 50;
		x = 0;
		y = 0;
		rSuelo = new Rectangle((int)x,(int)y,(int)ancho,(int)alto);
		color = Color.GRAY;
	}
	
	public void actualizar(long tiempoTranscurrido,ArrayList<Criatura> criaturas)throws Exception{		
		reconociendoCriaturas(criaturas);
	}
	
	public void reconociendoCriaturas(ArrayList<Criatura> criaturas) throws Exception{
		int numeroCriaturas = criaturas.size();
		rSuelo = this.getRectangulo();
		for(int i = 0; i<numeroCriaturas;i++){
			Rectangle rCriatura = criaturas.get(i).getRectangulo();			
			if(rSuelo.intersects(rCriatura)){
				activo = true;
				if(criaturas.get(i).getY()<getY()){
					criaturas.get(i).setTocandoSuelo(true);
					criaturas.get(i).setVelocidadY(0);
					criaturas.get(i).setY((float)(y-criaturas.get(i).getAlto()));
				}			
				
			}
		}				
	}
	
	public void setImagen(BufferedImage imagen){
		this.imagen = imagen;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
		
	public void dibujar(Graphics2D g){
		if(imagen!=null){
			g.drawImage(imagen,(int)x,(int)y,(int)ancho,(int)alto,null);
		}else{
			g.setColor(color);
			g.fillRect((int)x,(int)y,(int)ancho,(int)alto);
		}	
		
	}

	public float getAncho() {
		return ancho;
	}
	
	public Rectangle getRectangulo(){
		return new Rectangle((int)x,(int)y,(int)ancho,(int)alto);
	}

	public void setAncho(float ancho) {
		this.ancho = ancho;
	}

	public float getAlto() {
		return alto;		
	}

	public void setAlto(float alto) {
		this.alto = alto;		
	}

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

	public Color getColor() {
		return color;
	}

	public BufferedImage getImagen() {
		return imagen;
	}
	
	
}
