package nucleo;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Window;

import javax.swing.JOptionPane;



public abstract class Nucleo {
	
	public static final DisplayMode modos[]= {
		new DisplayMode (1368,768,32,0),
		new DisplayMode (1368,768,24,0),
		new DisplayMode (1368,768,16,0),
		new DisplayMode (1024,768,32,0),
		new DisplayMode (1024,768,24,0),
		new DisplayMode (1024,768,16,0),
		new DisplayMode (800,600,32,0),
		new DisplayMode (800,600,24,0),
		new DisplayMode (800,600,16,0),
		new DisplayMode (640,480,32,0),
		new DisplayMode (640,480,24,0),
		new DisplayMode (640,480,16,0),
	};
	
	private boolean ejecutandose;	
	protected ManejadorPantalla manejadorPantalla;
	
	
	//Sirve para detener el juego
	public void detener(){
		 ejecutandose = false;
	}
	
	//Sirve para inicializar y reproducir el juego
	public void ejecutar() throws Throwable{
		try{
			inicializar();
			reproducirJuego();
		}finally{
			manejadorPantalla.RestaurarPantalla();
		}
	}
	
	
	//Inicializa el modo de pantalla completa
	public void inicializar() throws Exception{
		manejadorPantalla = new ManejadorPantalla();
		
		try {
			DisplayMode modo = manejadorPantalla.encontrarPrimerModoCompatible(modos);
			manejadorPantalla.establecerPantallaCompleta(modo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo establecer la pantalla completa \n"+e.getMessage());
		}
		
		Window ventana = manejadorPantalla.getPantallaCompleta();
		ventana.setFont(new Font("Arial",Font.PLAIN,20));
		ventana.setBackground(Color.BLACK);
		ventana.setForeground(Color.WHITE);
		ejecutandose = true;
		
	}
	
	
	//Es el encargado de reproducir todas las animaciones del juego
	public void reproducirJuego() throws Throwable{
		long tiempoInicial = System.currentTimeMillis();
		long tiempoAcumulado = tiempoInicial;
		
		while(ejecutandose){			
			
			long tiempoTranscurrido = System.currentTimeMillis()-tiempoAcumulado;
			tiempoAcumulado += tiempoTranscurrido;
			
			actualizar(tiempoTranscurrido);
			
			Graphics2D g = manejadorPantalla.getGraphics();
			dibujar(g);
			g.dispose();
			manejadorPantalla.actualizar();
			
			try{
				Thread.sleep(20);
			}catch(Exception ex){
				throw new Exception("Error al dormir el hilo en reproducirJuego()");
			}
			
		}		
	}
	
	/*
	 * Los metodos dibujar() Y actualizar() van a depender de lo que se quiera realizar
	 * en cada juego, por ello deberan ser sobreEscritos(override) cada vez que se use esta clase
	 * por tal motivo solo se declararan las cabeceras y sin escribir nada dentro de ellas.
	 */

	//Se utilizara para dibujar en la pantalla completa
	public abstract void dibujar(Graphics2D g) throws Exception;

	//Actualizara constantemente el juego
	public abstract void actualizar(long tiempoTranscurrido) throws Exception, Throwable;

}
