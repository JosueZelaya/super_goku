package superGoku;

import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Criatura extends Sprite{

	private String nombre;
	private int numeroVidas;
	private boolean estoyVivo;		
	private boolean atacando;	
	private boolean tocandoSuelo;
	private String horientacion;	
	
	public Criatura(ArrayList<Animacion> animaciones) {
		super(animaciones);
		estoyVivo = true;
		numeroVidas = 1;
	}
	
	public void actualizar(long tiempoTranscurrido,ArrayList<Criatura> criaturas) throws Exception{
		super.actualizar(tiempoTranscurrido);		
	}
	
	public abstract void atacar();
	
	public abstract void moverseDerecha();
	
	public abstract void moverseIzquierda();
	
	public abstract void detenerse() throws Exception;
	
	public abstract void morir();
	
	public abstract void generarComportamiento() throws Exception;
	
	public boolean hayColision(Criatura criatura) throws Exception{
		Rectangle yo = this.getRectangulo();
		Rectangle criaturaR = criatura.getRectangulo();		
		if(yo.intersects(criaturaR)){
			return true;
		}
		return false;
	}
	
	public void saltar() {
		// TODO Auto-generated method stub
		if(estaVivo()){
			if(estoyTocandoSuelo()){
				setVelocidadY(-2);
			}
			setTocandoSuelo(false);
		}		
	}
	
	public void setEstoyVivo(boolean bool){
		this.estoyVivo = bool;
	}
	
	public boolean estaVivo(){
		return estoyVivo;
	}
	
	public boolean estaCayendo(){
		if(this.getVelocidadY()>0){
			return true;
		}else{
			return false;
		}
	}
	
	public void setHorientacion(String horientacion){
		this.horientacion = horientacion;
	}
	
	public String getHorientacion(){
		return horientacion;
	}
	
	public boolean estaAtacando(){
		return atacando;
	}
	
	public void setAtacando(boolean atacando) {
		this.atacando = atacando;
	}
	
	public void setTocandoSuelo(boolean bool){
		tocandoSuelo = bool;
	}
	
	public boolean estoyTocandoSuelo(){
		return tocandoSuelo;
	}
	
	//GETTERS AND SETTERS
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public int getNumeroVidas(){
		return numeroVidas;
	}
	
	public void setNumeroVidas(int numeroVidas){
		this.numeroVidas = numeroVidas;
	}

}
