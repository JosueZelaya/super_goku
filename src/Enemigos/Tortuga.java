package Enemigos;

import java.util.ArrayList;
import superGoku.Animacion;
import superGoku.Criatura;

public class Tortuga extends Enemigo{
	
	private int cont;

	public Tortuga(ArrayList<Animacion> animaciones) {
		super(animaciones);
		// TODO Auto-generated constructor stub		
	}
	
	@Override
	public void actualizar(long tiempoTranscurrido, Criatura criatura)
			throws Exception {
		// TODO Auto-generated method stub
		super.actualizar(tiempoTranscurrido, criatura);
		if(estaVivo()){
			generarComportamiento();
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

	/*@Override
	public void saltar() {
		// TODO Auto-generated method stub
		if(estoyTocandoSuelo()){
			setVelocidadY(-2);
		}
		setTocandoSuelo(false);
	}*/

	@Override
	public void moverseDerecha() {
		// TODO Auto-generated method stub
		setIndiceAnimacion(2);
		setVelocidadX(0.1f);
	}

	@Override
	public void moverseIzquierda() {
		// TODO Auto-generated method stub
		setIndiceAnimacion(1);
		setVelocidadX(-0.1f);
	}

	@Override
	public void detenerse() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void morir() {
		// TODO Auto-generated method stub
		if(estaVivo()){
			setEstoyVivo(false);
			setAceleracionY(0);
			setAceleracionX(0);
			setVelocidadX(0);
			setVelocidadY(0);
			setIndiceAnimacion(0);			
		}
	}

	@Override
	public void generarComportamiento() throws Exception {
		// TODO Auto-generated method stub		
		if(getX()<=getxMin()){
			setX(getxMin());
			moverseDerecha();
		}else if(getX()+getAncho()>=getxMax()){
			setxMax(getxMax());
			moverseIzquierda();			
		}
	}	
	

}
