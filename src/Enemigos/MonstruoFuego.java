package Enemigos;

import java.util.ArrayList;

import superGoku.Animacion;
import superGoku.Criatura;

public class MonstruoFuego extends Enemigo{
	
	private int cont;
	private int contAtacar;

	public MonstruoFuego(ArrayList<Animacion> animaciones) {
		super(animaciones);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actualizar(long tiempoTranscurrido, Criatura criatura)
			throws Exception {
		// TODO Auto-generated method stub
		super.actualizar(tiempoTranscurrido, criatura);
		generarComportamiento(tiempoTranscurrido);
	}
	
	@Override
	public void verificarColision(Criatura criatura) throws Exception {
		// TODO Auto-generated method stub
		if(this.hayColision(criatura)){
			if(estaAtacando()){
				criatura.morir();
			}
		}
	}
	
	@Override
	public void atacar() {
		// TODO Auto-generated method stub		
		setIndiceAnimacion(1);
		setAtacando(true);
	}

	@Override
	public void moverseDerecha() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moverseIzquierda() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detenerse() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void morir() {
		// TODO Auto-generated method stub
		
	}

	public void generarComportamiento(long tiempoTranscurrido) throws Exception {
		// TODO Auto-generated method stub
		cont+=tiempoTranscurrido;
		if(cont>3500){
			atacar();
			contAtacar+=tiempoTranscurrido;
			if(contAtacar>1500){
				cont = 0;
				contAtacar = 0;
				setIndiceAnimacion(0);
				setAtacando(false);
			}											
		}
	}

	@Override
	public void generarComportamiento() throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
