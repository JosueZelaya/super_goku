package Enemigos;

import java.util.ArrayList;

import superGoku.Animacion;
import superGoku.Criatura;

public abstract class Enemigo extends Criatura {
	
	private int xMin,xMax;

	public int getxMin() {
		return xMin;
	}

	public void setxMin(int xMin) {
		this.xMin = xMin;
	}

	public int getxMax() {
		return xMax;
	}

	public void setxMax(int xMax) {
		this.xMax = xMax;
	}

	public Enemigo(ArrayList<Animacion> animaciones) {
		super(animaciones);		
	}
	
	public void actualizar(long tiempoTranscurrido,Criatura criatura) throws Exception{
		super.actualizar(tiempoTranscurrido);
		if(estaVivo()){
			verificarColision(criatura);
		}		
	}
	
	public void verificarColision(Criatura criatura) throws Exception{
		if(this.hayColision(criatura)){
			if(criatura.getY()+criatura.getAlto()< this.getY()+this.getAlto()/2){
				if(criatura.estaCayendo()){
					this.morir();
				}
			}else if(criatura.estaAtacando()){
				if(criatura.getX() - this.getX() > 0){
					if(criatura.getHorientacion()=="izquierda"){
						this.morir();
					}else{
						criatura.morir();																		
					}
				}else{
					if(criatura.getHorientacion()=="derecha"){
						this.morir();
					}else{						
						criatura.morir();						
					}
				}
			}else{
				criatura.morir();											
			}
		}
	}
	
}
