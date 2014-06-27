package elementosEscenario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import superGoku.Criatura;
import nucleo.ManejadorPantalla;

public class Suelo{
	
	float ancho,alto,x,y;
	Rectangle rSuelo;
	Color color;
	BufferedImage imagen;

	
	public Suelo(ManejadorPantalla pantalla){
		ancho = pantalla.getAnchoPantalla();
		alto = 50;
		x = 0;
		y = pantalla.getAltoPantalla()-alto;
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
				

				/*if(criaturas.get(i).getX()+criaturas.get(i).getAncho()>this.getX()){
					if(criaturas.get(i).getY()>this.getY() && criaturas.get(i).getY()<this.getY()+this.getAlto()){
						criaturas.get(i).setX((float)(this.getX()-criaturas.get(i).getAncho()));
					}
				}*/
				
				if(criaturas.get(i).getY()<getY()){
					criaturas.get(i).setTocandoSuelo(true);
					criaturas.get(i).setVelocidadY(0);
					criaturas.get(i).setY((float)(y-criaturas.get(i).getAlto()));
				}else if(criaturas.get(i).getY()>=getY()){
					criaturas.get(i).setVelocidadY(0);
					criaturas.get(i).setY((float)(y+getAlto()));
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
