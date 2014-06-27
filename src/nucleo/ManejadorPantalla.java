package nucleo;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;


public class ManejadorPantalla {
	
	private GraphicsDevice tarjetaGrafica;
	private JFrame ventana;
	
	//Permite a tarjetaGrafica tener acceso a la pantalla del monitor
	public ManejadorPantalla(){
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		tarjetaGrafica = e.getDefaultScreenDevice();
		ventana = new JFrame();
	}
	
	//permite establecer un color de fondo a la ventana
	public void setColorFondo(Color color){
		ventana.setBackground(color);
	}
	
	//permite obtener el color de fondo de la ventana
	public Color getColorFondo(){
		return ventana.getBackground();
	}
	
	
	/*
	 *Dentro de la tarjeta grafica de una computadora, se encuentran predefinidos
	 *diferentes modos de visualizacion, el problema es que nuestro programa
	 *no sabe en que maquina se ejecutara, ni que tipo de tarjeta grafica tendra.
	 *Por tanto, no conoce cual modo de visualizacion es compatible con el.
	 *Para resolver este problema, vamos a obtener todos los modos de visualizacion
	 *de la tarjeta grafica, y los compararemos con modos de visualizacion que nosotros
	 *hemos establecido previamente hasta encontrar uno compatible.
	*/
	
	//obteniendo los modos de visualizacion de la tarjeta de video
	public DisplayMode[] getModosVisualizacionCompatibles(){
		return tarjetaGrafica.getDisplayModes();
	}
	
	//Comparar los modos de visualizacion de la tarjeta grafica, contra los modos
	//de visualizacion pre-definidos y encontrar el primero que coincida.
	public DisplayMode encontrarPrimerModoCompatible(DisplayMode modos[]) throws Exception{
		DisplayMode modosTarjeta[] = tarjetaGrafica.getDisplayModes();
		for(int x=0;x<modos.length;x++){
			for(int y=0;y<modosTarjeta.length;y++){
				if(coincidenModosVisualizacion(modos[x],modosTarjeta[y])){
					return modos[x];
				}
			}
		}
		throw new Exception("No se ha encontrado un modo de visualizacion compatible con tu tarjeta de video.");
	}
	
	//Obtener modo de visualizacion actual
	public DisplayMode getModoVisualizacionActual(){
		return tarjetaGrafica.getDisplayMode();
	}
	
	//Verificar si dos modos de visualizacion son compatibles
	public boolean coincidenModosVisualizacion(DisplayMode m1,DisplayMode m2){
		if(m1.getWidth() != m2.getWidth() || m1.getHeight() != m2.getHeight()){
			return false;
		}
		if(m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m1.getBitDepth() != m2.getBitDepth()){
			return false;
		}
		if(m1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m1.getRefreshRate() != m2.getRefreshRate()){
			return false;
		}
		return true;
	}
	
	//Crear pantalla completa
	public void establecerPantallaCompleta(DisplayMode modo) throws Exception{
		ventana.setUndecorated(true);
		ventana.setIgnoreRepaint(true);
		ventana.setResizable(false);
		tarjetaGrafica.setFullScreenWindow(ventana);
		
			
		if(modo != null && tarjetaGrafica.isDisplayChangeSupported() && esPosibleCambiarResolucion(modo.getWidth(),modo.getHeight(), modo.getBitDepth())){
			//isDisplayChangeSupported se emplea para saber si podemos
			//cambiar los modos de visualizacion.
			DisplayMode modoAnterior = getModoVisualizacionActual();
			try{
				tarjetaGrafica.setDisplayMode(modo);
			}catch(Exception e){
				throw new Exception("No se pudo establecer el modo de visualizacion.\n"+e.getMessage());				
			}finally{
				tarjetaGrafica.setDisplayMode(modoAnterior);
			}
		}
		/*
		 * Cuando creamos una animacion y la mostramos en pantalla esta parpadea mucho,
		 * lo cual se debe a que lo que mostramos se esta creando y mostrando
		 * al mismo tiempo. Para evitar eso utilizaremos una tecnica de doble buffer
		 * y lo que esto significa es que antes de mostrar algo en pantalla vamos a crear
		 * una imagen y guardarla en memoria, para que cuando venga el momento de mostrarse
		 * ya este creada y no tenga que crearse y mostrarse al mismo tiempo. En nuestro caso
		 * usaremos un buffer doble, lo que hara que sean dos imagenes las que se creen
		 * y se guarden en memoria, cuando una este lista esta se muestra mientras se prepara la otra. 
		 */
		ventana.createBufferStrategy(2);		
	}
	
	
	//Verifica si es posible cambiar la resolucion.
	private boolean esPosibleCambiarResolucion(int width, int height,int bitDepth){
		DisplayMode[] modes = tarjetaGrafica.getDisplayModes();		
		  for(int i = 0; i < modes.length; i++) {	
		    if (width == modes[i].getWidth() && height == modes[i].getHeight() && bitDepth == modes[i].getBitDepth())
		    	return true;	
		  }	
		  return false;
	}


	/*En este metodo estableceremos las configuraciones para el objeto Graphics
	*Cuando este objeto sea utilizado en otra clase llevara las configuraciones
	*establecidas en este metodo.
	*/
	public Graphics2D getGraphics() throws Exception{
		//guardamos todo lo que este en la pantalla completa dentro de la variable ventana
		Window ventana = tarjetaGrafica.getFullScreenWindow();
		if(ventana != null){
			BufferStrategy s = ventana.getBufferStrategy();
			return (Graphics2D)s.getDrawGraphics();
		}else{
			throw new Exception("Error en getGraphics(): No hay nada que se pueda mostrar");
		}
	}

	//Actualiza la pantalla
	public void actualizar(){
		Window ventana = tarjetaGrafica.getFullScreenWindow();
		if(ventana != null){
			BufferStrategy s = ventana.getBufferStrategy();
			/*
			 * contentsLost verifica si el buffer no se ha perdido
			 * debido a que las imagenes en los buffer pueden perderse
			 * al residir en la memoria volatil.
			 * Si el buffer se pierde, entonces no hay nada que mostrar en pantalla
			 * y si lo mostramos hara que se creen parpadeos o que se muestren por ejemplo:
			 * colores indeseados en la pantalla.
			 */
			if(!s.contentsLost()){
				//Si el buffer no se ha perdido entonces lo mostramos en pantalla
				s.show();
			}
		}
	}
	
	//Devuelve la pantalla completa
	public Window getPantallaCompleta(){
		return tarjetaGrafica.getFullScreenWindow();
	}
	
	//Devuelve el ancho de la pantalla
	public float getAnchoPantalla(){
		Window v = tarjetaGrafica.getFullScreenWindow();
		if(v != null){
			return v.getWidth();
		}else{
			return 0;
		}
	}
	
	//Devuelve la altura de la pantalla
	public float getAltoPantalla(){
		Window v = tarjetaGrafica.getFullScreenWindow();
		if(v != null){
			return v.getHeight();
		}else{
			return 0;
		}	
	}
	
	//Salir de la pantalla completa
	public void RestaurarPantalla(){
		Window v = tarjetaGrafica.getFullScreenWindow();
		if(v != null){
			v.dispose();
		}
		tarjetaGrafica.setFullScreenWindow(null);
	}
	
	//crear imagenes compatibles con el monitor
	public BufferedImage crearImagenCompatible(int ancho,int alto,int transparencia){
		Window v = tarjetaGrafica.getFullScreenWindow();
		if(v != null){
			/*
			 * Obtenemos las caracteristicas del monitor y las guardamos en GraphicsConfiguration.
			 * Una vez conocidas las configuraciones del monitor se puede crear una imagen compatible
			 * con dicho monitor por medio del metodo createCompatibleImage del objeto configuracionGrafica  
			 */
			GraphicsConfiguration configuracionGrafica = v.getGraphicsConfiguration();
			return configuracionGrafica.createCompatibleImage(ancho, alto,transparencia);
		}
		return null;
	}
	
}
