package Enemigos;

import java.util.ArrayList;
import superGoku.Animacion;
import superGoku.Criatura;

public class Chucho extends Enemigo{
	
	private int cont;
	private boolean actuar=false;

	public Chucho(ArrayList<Animacion> animaciones) {
		super(animaciones);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actualizar(long tiempoTranscurrido, Criatura criatura)
			throws Exception {
		// TODO Auto-generated method stub
		super.actualizar(tiempoTranscurrido, criatura);
		if(estaVivo()){
			generarComportamiento(criatura);
		}else if(!estaVivo()){
			//Explota
			cont+=tiempoTranscurrido;
			if(cont>1000){
				cont = 0;				
				setX(-500);				
				setIndiceAnimacion(1);				
			}			
		}	
	}

	@Override
	public void atacar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moverseDerecha() {
		// TODO Auto-generated method stub
		if(estaVivo()){
			setVelocidadX(0.1f);
			setIndiceAnimacion(2);
		}
	}

	@Override
	public void moverseIzquierda() {
		// TODO Auto-generated method stub
		if(estaVivo()){
			this.setVelocidadX(-0.1f);
			this.setIndiceAnimacion(1);
			
		}
	}

	@Override
	public void detenerse() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void morir() {
		// TODO Auto-generated method stub
		this.setVelocidadX(0);
		this.setIndiceAnimacion(0);
		this.setEstoyVivo(false);
	}
	
	
	public void saltar(float vy) {
		// TODO Auto-generated method stub
		if(estaVivo()){
			setVelocidadY(vy);
		}
	}

	
	public void generarComportamiento(Criatura goku) throws Exception {
		// TODO Auto-generated method stub
		
		int diferencia = getxMax() - getxMin();
		int puntoClave = getxMin() + diferencia/2 -200;
		
		if(goku.getX()+goku.getAncho()>=puntoClave){
			setAceleracionY(0.01f);
			actuar = true;
		}
		
		if(actuar){
			if(goku.getX()-getX()>0){
				moverseDerecha();
			}else{
				moverseIzquierda();
			}
		}
				
	}

	@Override
	public void generarComportamiento() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
