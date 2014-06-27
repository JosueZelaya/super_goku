package Enemigos;


import java.util.ArrayList;
import superGoku.Animacion;
import superGoku.Criatura;

public class Fantasma extends Enemigo  {				
	

	public Fantasma(ArrayList<Animacion> animaciones) {
		super(animaciones);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actualizar(long tiempoTranscurrido, Criatura criatura)
			throws Exception {
		// TODO Auto-generated method stub
		super.actualizar(tiempoTranscurrido, criatura);
		generarComportamiento(criatura);
	}

	@Override
	public void atacar() {
		// TODO Auto-generated method stub
		
	}
	
	public void moverArriba(){
		setVelocidadY(-0.1f);		
	}
	
	public void moverAbajo(){
		setVelocidadY(0.1f);		
	}

	@Override
	public void moverseDerecha() {
		// TODO Auto-generated method stub	
		setVelocidadX(0.1f);
		setIndiceAnimacion(2);		
	}

	@Override
	public void moverseIzquierda() {
		// TODO Auto-generated method stub
		setVelocidadX(-0.1f);
		setIndiceAnimacion(0);
	}

	@Override
	public void detenerse() throws Exception {
		// TODO Auto-generated method stub
		setVelocidadX(0);
		setVelocidadY(0);
	}

	@Override
	public void morir() {
		// TODO Auto-generated method stub
		
	}
	
	public void generarComportamiento(Criatura goku) throws Exception{
				
		if(goku.getX()+goku.getAncho()>getxMin() && goku.getX()+goku.getAncho()<getxMax()){			
			//Movimiento en x
			if(goku.getX()+goku.getAncho()<getX()){
				moverseIzquierda();
			}else{
				moverseDerecha();
			}
			//Movimiento en y
			if(goku.getY()<getY()){
				moverArriba();
			}else{
				moverAbajo();
			}												
		}else{
			detenerse();
		}	
		
	}

	@Override
	public void generarComportamiento() throws Exception {
		// TODO Auto-generated method stub
		
	}



	
}
