package superGoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import nucleo.ManejadorPantalla;
import nucleo.Principal;

import elementosEscenario.Escenario;

public class Jugador extends Criatura{

	private boolean detenido;
	private int cont;
	private boolean reiniciar = false;
	private Animacion foto;
	
	public Jugador(ArrayList<Animacion> animaciones) {
		super(animaciones);
		// TODO Auto-generated constructor stub
		
	}
	
	public void actualizar(long tiempoTranscurrido,ManejadorPantalla pantalla,Principal juego) throws Throwable {
		// TODO Auto-generated method stub
		super.actualizar(tiempoTranscurrido);
		if(estaVivo()){
			if(detenido){
				detenerse();
				detenido = false;
			}
		}else if(!estaVivo()){
			//Explota
			cont+=tiempoTranscurrido;
			if(cont>1000){
				cont = 0;	
				setNumeroVidas(getNumeroVidas()-1);
				if(getNumeroVidas()>0){					
					setEstoyVivo(true);
					reiniciar = true;
				}								
			}			
		}
		
		//Si el jugador se cae por debajo de la pantalla, entonces muere
		if(getY()>pantalla.getAltoPantalla()-getAlto() && getX()<400){
			setVelocidadY(0);
			setY((float)(pantalla.getAltoPantalla()-getAlto()));
		}if(getY()>pantalla.getAltoPantalla()+100 && getX()>400){
			morir();
		}
		
	}	

	public void setReiniciar(boolean bool){
		reiniciar = bool;
	}
	
	public boolean seDebeReiniciar(){
		return reiniciar;
	}
	
	public void soltar(){
		if(estaVivo()){
			detenido = true;
		}		
	}
	
	@Override
	public void atacar() {
		// TODO Auto-generated method stub
		if(estaVivo()){
			setAtacando(true);
		}		
	}
	
	public void puyaBaculo(){
		if(estaVivo()){
			if(this.getHorientacion()=="derecha"){
				setIndiceAnimacion(6);
			}else if(this.getHorientacion()=="izquierda"){
				setIndiceAnimacion(7);
			}
			atacar();
		}		
	}
		
	public void baculogira(){
		if(estaVivo()){
			if(this.getHorientacion()=="derecha"){
			    setIndiceAnimacion(5);
		   }else if(this.getHorientacion()=="izquierda"){
			   	setIndiceAnimacion(8);
		   }
			atacar();
		}		
	}
	
	public void trompon(){
		if(estaVivo()){
			if(this.getHorientacion()=="derecha"){
				this.setIndiceAnimacion(9);
			}else if(this.getHorientacion()=="izquierda"){
				this.setIndiceAnimacion(10);
			}
			atacar();
		}		
	}
	
	public void patada(){
		if(estaVivo()){
			if (this.getHorientacion()=="derecha"){
				this.setIndiceAnimacion(11);
			}else if(this.getHorientacion()=="izquierda"){
				this.setIndiceAnimacion(12);
			}atacar();
		}		
	}
	
	/*@Override
	public void saltar() {
		// TODO Auto-generated method stub
		if(estaVivo()){
			if(estoyTocandoSuelo()){
				setVelocidadY(-2);
			}
			setTocandoSuelo(false);
		}		
	}*/

	@Override
	public void moverseDerecha() {
		// TODO Auto-generated method stub
		if(estaVivo()){
			setIndiceAnimacion(3);
			setVelocidadX(0.2f);
			setHorientacion("derecha");
		}		
	}

	@Override
	public void moverseIzquierda() {
		// TODO Auto-generated method stub
		if(estaVivo()){
			setIndiceAnimacion(4);
			setVelocidadX(-0.2f);
			setHorientacion("izquierda");
		}		
	}

	@Override
	public void detenerse() throws Exception {
		// TODO Auto-generated method stub
		if(estaVivo()){
			if(this.getHorientacion()=="derecha"){
				setIndiceAnimacion(1);
			}else if(this.getHorientacion()=="izquierda"){
				setIndiceAnimacion(2);			
			}		
			setVelocidadX(0);
			setAtacando(false);
		}				
	}	

	@Override
	public void morir() {
		// TODO Auto-generated method stub
		if(estaVivo()){
			setVelocidadX(0);
			setVelocidadY(0);
			setAceleracionX(0);
			setAceleracionY(0);
			setIndiceAnimacion(0);
			setEstoyVivo(false);
		}		
	}

	@Override
	public void generarComportamiento() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void dibujar(Graphics2D g) throws Exception {
		// TODO Auto-generated method stub
		
		
		g.drawImage(this.getImagen(),(int)(getX()),(int)getY(),(int)getAncho(),(int)getAlto(),null);
		/*if(estaAtacando()){
			if(getHorientacion()=="derecha"){
				g.drawImage(this.getImagen(),(int)(getX()),(int)getY(),(int)getAncho(),(int)getAlto(),null);
			}else if(getHorientacion()=="izquierda"){
				float xDibujo = (float) (this.getX()-this.getAncho());
				g.drawImage(this.getImagen(),(int)(xDibujo),(int)getY(),(int)getAncho(),(int)getAlto(),null);
				
			}
		}else{
			super.dibujar(g);
		}*/
	}
	
	public void dibujarVidas(Graphics2D g,Escenario escenario) throws Exception{
		
		if(foto!=null){
			g.drawImage(foto.getImagen(),(int)(-1*escenario.getX()),0,60,60,null);
		}
		
		String s = ""+getNumeroVidas();
		Font fuente = new Font("Serif",Font.BOLD,50);
		g.setFont(fuente);
		if(getNumeroVidas()<=1){
			g.setColor(Color.RED);
		}else{
			g.setColor(Color.BLUE);
		}				
		g.drawString(s, -1*escenario.getX()+59, 49);
		
		if(getNumeroVidas()<=1){
			g.setColor(Color.RED);
		}else{
			g.setColor(new Color(198,226,255));
		}
		
		fuente = new Font("Serif",Font.BOLD,50);
		g.setFont(fuente);
		g.drawString(s,-1*escenario.getX()+60, 50);
		
		fuente = new Font("Serif",Font.BOLD,15);
		g.setFont(fuente);
		if(getNumeroVidas()==1){
			s = "vida";
		}else{
			s = "vidas";
		}
		
		g.drawString(s,-1*escenario.getX()+95, 50);
	}
	
	
	//Se agrega una animacion para la foto
	public void setAnimacionFoto(Animacion foto){
		this.foto = foto;
	}	
	
	
	//EVENTOS DE TECLADO
	public void teclaPresionada(KeyEvent e){
		/*
		 * El objeto keyEvent contiene la informacion del codigo de la tecla
		 * que se presiono. Es por ello que obtenemos de el el codigo de la tecla.
		 */
		int codigoTecla = e.getKeyCode();
		/*
		 * luego comparamos ese codigo, con el codigo de las teclas que queremos
		 * que tengan acciones, en este caso, las teclas: escape,space,c,d,x, y las flechas
		 * cursoras izquierda y derecha, en caso de que coincida con alguna ejecutamos
		 * una accion.
		 */
		if(codigoTecla == KeyEvent.VK_SPACE){
			//Si se presiona la barra espaciadora, saltamos
			saltar();
						
		}else if(codigoTecla == KeyEvent.VK_D){
			//Si se presiona la d, lanzamos un golpe
			baculogira();
						
		}else if(codigoTecla == KeyEvent.VK_X){
			//Si se presiona la x, damos un bastonazo
			puyaBaculo();
										
		}else if(codigoTecla == KeyEvent.VK_LEFT){
			//si se presiona la flecha cursora izquierda, nos movemos a la izquerda
			moverseIzquierda();
									
		}else if(codigoTecla == KeyEvent.VK_RIGHT){
			//si se presiona la flecha cursora derecha, nos movemos a la derecha
			moverseDerecha();
								
		}else if (codigoTecla == KeyEvent.VK_UP){
			
		}else if (codigoTecla == KeyEvent.VK_Z){
			
			patada();
						
		}else if(codigoTecla == KeyEvent.VK_C){
			//Si se presiona la c, lanzamos un golpe
			
			trompon();
						
		}
	}
	
	public void teclaSoltada(KeyEvent e) throws Exception{
		int codigoTecla = e.getKeyCode();
		if(codigoTecla == KeyEvent.VK_LEFT){
			detenerse();													
		}else if(codigoTecla == KeyEvent.VK_X){
			detenerse();	
		}else if(codigoTecla == KeyEvent.VK_RIGHT){
			detenerse();					
		}else if(codigoTecla == KeyEvent.VK_C){
			detenerse();	
		}else if(codigoTecla == KeyEvent.VK_D){
			detenerse();	
		}else if (codigoTecla == KeyEvent.VK_UP){
			detenerse();
		}else if (codigoTecla == KeyEvent.VK_Z){
			detenerse();
		}
	}

}
