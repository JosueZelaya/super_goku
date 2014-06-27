package elementosEscenario;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import nucleo.ManejadorPantalla;

import superGoku.Jugador;
import superGoku.Matriz;
import superGoku.MatrizFormatException;

public class Esfera {

	private int numero;
	double ancho;
	double alto;
	float x,y,xPivote,yPivote;
	private int angulo;
	Rectangle esfera;	
	BufferedImage imagen;
	private boolean estaAgarrada;
	private boolean girando=true;
	
	public Esfera(BufferedImage imagen,float x,float y){
		this.x=xPivote=x;
		this.y=yPivote=y;
		
		ancho = imagen.getWidth();
		alto = imagen.getHeight();
		this.imagen = imagen;
		estaAgarrada = false;
	}
	
	public void actualizar(Jugador goku,Escenario Es1,ManejadorPantalla pantalla) throws Exception{
				
		Rectangle rGoku = goku.getRectangulo();
		esfera = getRectangulo();
		if(esfera.intersects(rGoku)){
			estaAgarrada = true;
			if(numero==7){
				Es1.setE7(true);
			}else if(numero==6){
				Es1.setE6(true);
			}else if(numero==5){
				Es1.setE5(true);
			}else if(numero==4){
				Es1.setE4(true);
			}else if(numero==3){
				Es1.setE3(true);
			}else if(numero==2){
				Es1.setE2(true);
			}else if(numero==1){
				Es1.setE1(true);
			}			
		}if(!estaAgarrada){
			x = 1*Es1.getX()+xPivote;
		}else if(estaAgarrada){
			if(numero==7){
				setY(0);
				setX((float) (-1*Es1.getX()+pantalla.getAnchoPantalla()-ancho));
			}else if(numero==6){
				setY(0);
				setX((float) (-1*Es1.getX()+pantalla.getAnchoPantalla()-2*ancho));
			}else if(numero==5){
				setY(0);
				setX((float) (-1*Es1.getX()+pantalla.getAnchoPantalla()-3*ancho));
			}else if(numero==4){
				setY(0);
				setX((float) (-1*Es1.getX()+pantalla.getAnchoPantalla()-4*ancho));
			}else if(numero==3){
				setY(0);
				setX((float) (-1*Es1.getX()+pantalla.getAnchoPantalla()-5*ancho));
			}else if(numero==2){
				setY(0);
				setX((float) (-1*Es1.getX()+pantalla.getAnchoPantalla()-6*ancho+1));
			}else if(numero==1){
				setY(0);
				setX((float) (-1*Es1.getX()+pantalla.getAnchoPantalla()-7*ancho));
			}						
		}
		angulo +=1;
				
	}

	
	public void dibujar(Graphics2D g,Escenario Es1){		
		if(!estaAgarrada && girando){
			AffineTransform xform = new AffineTransform();
			xform.rotate(angulo,x+ancho/2,y+alto/2);
			g.setTransform(xform);
			g.drawImage(imagen,(int)x,(int)y,(int)ancho,(int)alto,null);
		}else{
			//g.translate(-1*Es1.getX(), 0);
			g.drawImage(imagen,(int)x,(int)y,(int)ancho,(int)alto,null);
		}
					
	}
	
	public void setGirando(boolean bool){
		girando = bool;
	}
	
	public void setEscala(float x, float y) throws MatrizFormatException{
		//Se obtiene la matriz escalar de 2x2
		Matriz escalar = Matriz.getMatrizEscalar(x, y);
		//Se crea una matriz con las dimensiones de la imagen actual
		Matriz dimensiones = new Matriz(1,2);
		dimensiones.setPosicion((double)imagen.getWidth(), 0, 0);
		dimensiones.setPosicion((double)imagen.getHeight(), 0, 1);
		/*
		 * Se multiplica la matriz de dimensiones por la matriz escalar y se guarda el resultado
		 * de la multiplicacion en la matriz de dimensiones, que ahora contiene las dimensiones escaladas.
		 */
		dimensiones = Matriz.Multiplicar(dimensiones, escalar);
		//Se devuelve el ancho
		ancho = dimensiones.getPosicion(0, 0);
		alto = dimensiones.getPosicion(0, 1);
	}

	public void setAgarrada(boolean bool){
		estaAgarrada = bool;
	}
	
	public boolean estaAgarrada(){
		return estaAgarrada;
	}
	
	public Rectangle getRectangulo(){
		return new Rectangle((int)xPivote,(int)yPivote,(int)ancho,(int)alto);
	}
	
	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public double getAlto() {
		return alto;
	}

	public void setAlto(double alto) {
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

	public BufferedImage getImagen() {
		return imagen;
	}

	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}	
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
}
