/*
 *
 * @author Alexander Zelaya
 *
 *
 * Algoritmos Graficos
 * 
 * Este es un ejemplo de demostración de cómo utilizar el núcleo del juego presentado
 * en el laboratorio de AG, para que no tenga que programar cada quién su propio 
 * núcleo, si no que simplemtente utilicen este. Y dejen al núcleo el trabajo de rutina
 * y que todos nos podamos enfocar directamente en darle vida a nuestro juego.
 * Se invita a aquellas personas que quieran modificarlo y mejorarlo a que lo hagan y
 * puedan compartir con nosotros dichas modificaciones, de manera que todos nos beneficiemos.
 * También si alguien lo quiere migrar a C# o a otra plataforma de desarrollo, es libre de hacerlo,
 * y le invitamos a compartirlo con el resto de los grupos, para aquellos que van a programar
 * en .Net.
 * 
 * *****************************************************************************
 * 
 * Para utilizar el nucleo del juego, su clase Principal o Main, debe heredar de
 * la clase Nucleo, e implementar los métodos abstractos de la clase Nucleo.
 * Los métodos que se implementan son solo dos: dibujar y actualizar.
 * El método dibujar se encarga solo de dibujar los personajes y escenarios y demás
 * objetos del juego, es decir, que no se debería hacer ningun cálculo adisional en
 * dicho método porque lo que se pretende es que el dibujado sea rápido. Este métdo
 * es invocado constantemente por el núcleo, así que lo que pongamos dentro de él
 * se estará dibujando constantemente sin que hagamos una invocación explícita
 * a dicho método.
 * El método actualizar es el que se encarga de actualizar el juego; este método
 * es invocado constantemente por el núcleo, es por ello que en ese método es donde
 * debemos actualizar todos los personajes y demás objetos, e invocar todos aquellos
 * métodos que contengan cálculos que queremos que se estén ejecutando constantemente.
 * 
 */

package nucleo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import superGoku.Criatura;
import superGoku.Jugador;
import superGoku.MatrizFormatException;
import superGoku.Menu;
import superGoku.Personaje;

import elementosEscenario.Escenario;
import elementosEscenario.Esfera;
import elementosEscenario.FrameEscenario;
import elementosEscenario.Obstaculo;
import elementosEscenario.Suelo;

import Enemigos.Chucho;
import Enemigos.EscupeFuego;
import Enemigos.Fantasma;
import Enemigos.Fuego;
import Enemigos.MonstruoFuego;
import Enemigos.Tortuga;
import Enemigos.TortugaSaltarina;


/*
 * Implementamos la clase KeyListener, la cual se encarga de manejar los eventos
 * del teclado. Cuando alguien presiona una tecla, deja de presionar una tecla,
 * o digita una tecla.
 */
public class Principal extends Nucleo implements KeyListener,MouseListener,MouseMotionListener{
	
	private boolean pausado;
	
	
	/* Declaramos los objetos que vamos a utilizar
	 * lo ideal es que se usara un objeto escenario que contenga al fondo
	 * en lugar de simplemente colocar un fondo a la pantalla, pero por
	 * razones practicas lo haré así para simplificar el ejemplo
	 */	
	private boolean isGameOver = false;
	Personaje personaje;
	private Escenario Es1;	
	private Tortuga tortuga1,tortuga2,tortuga3;
	private Tortuga tortuga4,tortuga5,tortuga6;
	private Tortuga tortuga7,tortuga8,tortuga9;
	private Chucho chucho1,chucho2,chucho3,chucho4;
	private Fantasma fantasma1,fantasma2,fantasma3;	
	private TortugaSaltarina tortugaS1,tortugaS2,tortugaS3;	
	private Suelo suelo,suelo2,suelo3,suelo4;	
	private Suelo plataforma1, plataforma2, plataforma3;
	private Suelo plataforma4, plataforma5;
	private Suelo plataforma6, plataforma7,cubo;
	private Obstaculo obstaculo1,obstaculo2;
	private Esfera e1,e2,e3,e4,e5,e6,e7;
	private Fuego fuego1,fuego2,fuego3,fuego4,fuego5;
	private MonstruoFuego monstruoFuego1;
	private EscupeFuego escupeFuego1,escupeFuego2,escupeFuego3;
	
	private Jugador jugador;
	
	//MENU
	private Menu menu;

	/*
	 * El objeto jugador es el que ocupo para el personaje principal y este
	 * hereda de la clase Sprite.
	 */
	

	public static void main(String[] e){
		try {
			/*
			 * Instanciamos esta clase, y como hereda del nucleo usamos el
			 * metodo ejecutar() del nucleo.
			 * Esto es todo lo que debemos hacer para correr el juego
			 */			
			new Principal().ejecutar();
			
		} catch (Throwable e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
		}
	}
	
	private void pausar(){
		pausado = true;
	}
	
	private void quitarPausa(){
		pausado = false;
	}

	/*
	 * (non-Javadoc)
	 * @see nucleoAG.Nucleo#inicializar()
	 * Al ejecutar el juego, lo primero que hace el nucleo es ejecutar el metodo
	 * inicializar() y lo hace solo una vez. Es por ello que lo sobreescribimos
	 * para inicializar tambien aqui nuestros personajes y demas objetos.
	 */
	@Override
	public void inicializar() throws Exception {
		/*
		 * super.inicializar() es una llamada al metodo inicializar() de la clase padre
		 * Esto lo hacemos porque es ahi donde se establece la pantalla completa
		 */
		super.inicializar();
		
		//preparar Menu
		prepararMenu();
		
		//Procedemos a inicializar nuestros objetos
		prepararJuego();
		
				
		/*
		 * Le decimos a la clase KeyListener, que este pendiente de cuando se 
		 * presione alguna tecla cuando estemos en el modo de pantalla completa.
		 */		
		Window ventana = manejadorPantalla.getPantallaCompleta();
		ventana.addKeyListener(this);
		ventana.addMouseListener(this);
		ventana.addMouseMotionListener(this);
	}	
	
	public void crearCriaturas() throws Exception{
		//se prepara a las primeras 3 tortugas
		tortuga1 = personaje.getTortugaRojaCaminado();
		tortuga2 = personaje.getTortugaRojaCaminado();
		tortuga3 = personaje.getTortugaRojaCaminado();
		tortuga4 = personaje.getTortugaRojaCaminado();
		tortuga5 = personaje.getTortugaRojaCaminado();
		tortuga6 = personaje.getTortugaRojaCaminado();
		tortuga7 = personaje.getTortugaRojaCaminado();
		tortuga8 = personaje.getTortugaRojaCaminado();
		tortuga9 = personaje.getTortugaRojaCaminado();
				
		//Se preparan a las tortugas saltarinas
		tortugaS1 = personaje.getTortugaVerdeSaltarina();
		tortugaS2 = personaje.getTortugaVerdeSaltarina();
		tortugaS3 = personaje.getTortugaVerdeSaltarina();
		
		//Se instancia los chuchos
		chucho1 = personaje.getChucho();
		chucho2 = personaje.getChucho();
		chucho3 = personaje.getChucho();
		chucho4 = personaje.getChucho();
		
		//Se instacia los fantasmas
		fantasma1 = personaje.getFantasma();
		fantasma2 = personaje.getFantasma();
		fantasma3 = personaje.getFantasma();
		
		//Se instancia el fuego
		fuego1 = personaje.getFuego();
		fuego2 = personaje.getFuego();
		fuego3 = personaje.getFuego();
		fuego4 = personaje.getFuego();
		fuego5 = personaje.getFuego();
		
		//Se instancian los escupe fuego
		escupeFuego1 = personaje.getEscupeFuego();
		escupeFuego2 = personaje.getEscupeFuego();
		escupeFuego3 = personaje.getEscupeFuego();
		
		//Se instancia el monstruo de fuego
		monstruoFuego1 = personaje.getMonstruoFuego();
		
		iniciarCriaturas();
		
	}
	
	public void prepararMenu(){
		//preparar menu
		menu = new Menu(manejadorPantalla);
	}
	
	public void prepararJuego() throws Exception {		
		
		//se prepara el escenario
		prepararEscenario();
		
		//se inicializa la clase personaje
		personaje = new Personaje();
		
		
		//Se prepara al personaje
		if(menu.getJugadorSelecionado()=="goku"){
			//Se prepara a goku
			jugador = personaje.getGoku();
			jugador.setNumeroVidas(5);
		}else if(menu.getJugadorSelecionado()=="raquel"){
			jugador = personaje.getRaquel();
			jugador.setNumeroVidas(5);			
		}else if(menu.getJugadorSelecionado()=="josue"){
			jugador = personaje.getJosue();
			jugador.setNumeroVidas(5);			
		}else if(menu.getJugadorSelecionado()=="yonatan"){
			jugador = personaje.getYonatan();
			jugador.setNumeroVidas(5);			
		}else if(menu.getJugadorSelecionado()=="laura"){
			jugador = personaje.getLaura();
			jugador.setNumeroVidas(5);			
		}
		
		
		//se prepara a los enemigos
		crearCriaturas();
		
		isGameOver=false;		
		
	}
	
	public void prepararEscenario() throws IOException, MatrizFormatException{
		//Se cargan las imagenes		
		java.net.URL url = this.getClass().getResource("fondos/pantano.jpg");
		BufferedImage fondo5 = ImageIO.read(url);
		
		//Se crean los frames
		FrameEscenario frame1 = new FrameEscenario(fondo5);		
		FrameEscenario frame2 = new FrameEscenario(fondo5);				
		FrameEscenario frame3 = new FrameEscenario(fondo5);
		FrameEscenario frame4 = new FrameEscenario(fondo5);
		FrameEscenario frame5 = new FrameEscenario(fondo5);
		
		//Se establecen las dimensiones para los frames
		//alto
		frame1.setAlto(manejadorPantalla.getAltoPantalla());
		frame2.setAlto(manejadorPantalla.getAltoPantalla());
		frame3.setAlto(manejadorPantalla.getAltoPantalla());
		frame4.setAlto(manejadorPantalla.getAltoPantalla());
		frame5.setAlto(manejadorPantalla.getAltoPantalla());
		//ancho
		/*frame1.setAncho(manejadorPantalla.getAnchoPantalla());
		frame2.setAncho(manejadorPantalla.getAnchoPantalla());
		frame3.setAncho(manejadorPantalla.getAnchoPantalla());
		frame4.setAncho(manejadorPantalla.getAnchoPantalla());
		*/
		
		//Se crea el escenario principal y se agregan los frames
		Es1 = new Escenario(manejadorPantalla);		
		Es1.agregarFrame(frame1);
		Es1.agregarFrame(frame2);
		Es1.agregarFrame(frame3);
		Es1.agregarFrame(frame4);
		Es1.agregarFrame(frame5);
		
		//se prepara el suelo
		url = this.getClass().getResource("elementos/suelo.png");
		BufferedImage imagen = ImageIO.read(url);
		suelo = new Suelo(manejadorPantalla);
		suelo.setImagen(imagen);
		suelo.setAncho((float) (1500));		
				
		//Se prepara el suelo2
		suelo2 = new Suelo(manejadorPantalla);
		suelo2.setImagen(imagen);
		suelo2.setAncho(500);
		suelo2.setX(suelo.getAncho()+100);
				
		//Se prepara el suelo3
		suelo3 = new Suelo(manejadorPantalla);
		suelo3.setImagen(imagen);
		suelo3.setAncho(2500);
		suelo3.setX(suelo2.getX()+suelo2.getAncho()+150);
			
		//se prepara el suelo4
		suelo4 = new Suelo(manejadorPantalla);
		suelo4.setImagen(imagen);
		suelo4.setAncho((float) (2*manejadorPantalla.getAnchoPantalla()));
		suelo4.setX(suelo3.getX()+suelo3.getAncho()+200);
		
		//Se prepara plataforma1
		url = this.getClass().getResource("elementos/flotante.png");
		imagen = ImageIO.read(url);
		plataforma1= new Suelo(manejadorPantalla);
		plataforma1.setImagen(imagen);		
		plataforma1.setX(suelo.getX()+500);
		plataforma1.setY(manejadorPantalla.getAltoPantalla()-250);
		plataforma1.setAncho(imagen.getWidth());
		
		//Se prepara plataforma2
		plataforma2= new Suelo(manejadorPantalla);
		plataforma2.setImagen(imagen);		
		plataforma2.setX(suelo.getX()+700);
		plataforma2.setY(manejadorPantalla.getAltoPantalla()-450);
		plataforma2.setAncho(imagen.getWidth());
		
		//Se prepara plataforma3
		plataforma3= new Suelo(manejadorPantalla);
		plataforma3.setImagen(imagen);		
		plataforma3.setX(suelo.getX()+900);
		plataforma3.setY(manejadorPantalla.getAltoPantalla()-250);
		plataforma3.setAncho(imagen.getWidth());
		
		//Se prepara plataforma4
		plataforma4= new Suelo(manejadorPantalla);
		plataforma4.setImagen(imagen);
		plataforma4.setAncho(imagen.getWidth());
		plataforma4.setX(suelo3.getX()+suelo3.getAncho()/2-2*plataforma4.getAncho());
		plataforma4.setY(manejadorPantalla.getAltoPantalla()-400);
		
		
		//Se prepara plataforma5
		plataforma5= new Suelo(manejadorPantalla);
		plataforma5.setImagen(imagen);
		plataforma5.setAncho(imagen.getWidth());
		plataforma5.setX(suelo3.getX()+suelo3.getAncho()/2-3*plataforma4.getAncho()-100);
		plataforma5.setY(plataforma4.getY()-200);
		
		
		//Se prepara obstaculo1
		url = this.getClass().getResource("elementos/obstaculo1.png");
		imagen = ImageIO.read(url);
		obstaculo1 = new Obstaculo();
		obstaculo1.setImagen(imagen);
		obstaculo1.setAncho(imagen.getWidth());
		obstaculo1.setAlto(imagen.getHeight()-100);
		obstaculo1.setX(suelo3.getX()+suelo3.getAncho()/2-obstaculo1.getAncho()/2);
		obstaculo1.setY(suelo3.getY()-obstaculo1.getAlto());
		
		//Se prepara obstaculo1
		url = this.getClass().getResource("elementos/obstaculo1.png");
		imagen = ImageIO.read(url);		
		obstaculo2 = new Obstaculo();
		obstaculo2.setImagen(imagen);
		obstaculo2.setAncho(imagen.getWidth());
		obstaculo2.setAlto(imagen.getHeight()-100);
		obstaculo2.setX(suelo4.getX()+1000);
		obstaculo2.setY(suelo3.getY()-obstaculo1.getAlto());
		
		
		url = this.getClass().getResource("elementos/cubo.png");
		imagen = ImageIO.read(url);
		//Se prepara plataforma6
		plataforma6= new Suelo(manejadorPantalla);
		plataforma6.setImagen(imagen);
		plataforma6.setAncho(imagen.getWidth());
		plataforma6.setX(obstaculo2.getX()+obstaculo2.getAncho()+150);
		plataforma6.setY(obstaculo2.getY()-180);
		plataforma6.setAncho(suelo4.getX()+suelo4.getAncho()-plataforma6.getX()+100);
		plataforma6.setAlto(50);
		
		//Se prepara plataforma5
		plataforma7= new Suelo(manejadorPantalla);
		plataforma7.setImagen(imagen);
		plataforma7.setAncho(imagen.getWidth());
		plataforma7.setX(plataforma6.getX()+plataforma6.getAncho()+150);
		plataforma7.setY(plataforma6.getY()-190);
		plataforma7.setAlto(50);
		
		//Se prepara el cubo
		url = this.getClass().getResource("elementos/cubo2.png");
		imagen = ImageIO.read(url);
		cubo= new Suelo(manejadorPantalla);
		cubo.setImagen(imagen);
		cubo.setAncho(imagen.getWidth());
		//cubo.setAlto(imagen.getHeight());
		cubo.setX(obstaculo1.getX()+obstaculo1.getAncho()+550);
		cubo.setY(suelo3.getY()-200);
		cubo.setAlto(50);
		
		
		//Se preparan las esferas del dragon
		url = this.getClass().getResource("elementos/esferas2.png");
		imagen = ImageIO.read(url);
		imagen = imagen.getSubimage(82, 5, 139, 139);
		e1 = new Esfera(imagen, plataforma2.getX()+plataforma2.getAncho()/2, 100);
		e1.setEscala(0.3f, 0.3f);
		e1.setNumero(1);
		e1.setAgarrada(false);
		imagen = ImageIO.read(url);
		imagen = imagen.getSubimage(282, 9, 142, 140);
		e2 = new Esfera(imagen, plataforma5.getX()-200, 200);
		e2.setEscala(0.3f, 0.3f);
		e2.setNumero(2);
		e2.setAgarrada(false);
		imagen = ImageIO.read(url);
		imagen = imagen.getSubimage(280,322,142,141);		
		e3 = new Esfera(imagen,suelo4.getX()+400,500);
		e3.setEscala(0.3f, 0.3f);
		e3.setNumero(4);
		e3.setAgarrada(false);
		imagen = ImageIO.read(url);
		imagen = imagen.getSubimage(348,161,140,141);
		e4 = new Esfera(imagen,obstaculo1.getX()+obstaculo1.getAncho()+300,400);
		e4.setEscala(0.3f, 0.3f);
		e4.setNumero(3);
		e4.setAgarrada(false);
		imagen = ImageIO.read(url);
		imagen = imagen.getSubimage(348,161,140,141);
		e5 = new Esfera(imagen,plataforma6.getX()+plataforma6.getAncho()/2,200);
		e5.setEscala(0.3f, 0.3f);
		e5.setNumero(5);
		e5.setAgarrada(false);
		imagen = ImageIO.read(url);
		imagen = imagen.getSubimage(348,161,140,141);
		e6 = new Esfera(imagen,plataforma6.getX()+plataforma6.getAncho()-200,550);
		e6.setEscala(0.3f, 0.3f);
		e6.setNumero(6);
		e6.setAgarrada(false);
		imagen = ImageIO.read(url);
		imagen = imagen.getSubimage(348,161,140,141);
		e7 = new Esfera(imagen,plataforma7.getX()+plataforma7.getAncho()-50,150);
		e7.setEscala(0.3f, 0.3f);
		e7.setNumero(7);
		e7.setAgarrada(false);
		//e7.setGirando(false);
	}
	
public void iniciarCriaturas() throws Exception{
		
		//PRIMERAS 3 TORTUGAS
		tortuga1.setEstoyVivo(true);
		tortuga2.setEstoyVivo(true);
		tortuga3.setEstoyVivo(true);
		tortuga1.setX(500);
		tortuga2.setX(540);
		tortuga3.setX(580);
		tortuga1.setY((float)(manejadorPantalla.getAltoPantalla()-tortuga1.getAlto()));
		tortuga2.setY((float)(manejadorPantalla.getAltoPantalla()-tortuga2.getAlto()));
		tortuga3.setY((float)(manejadorPantalla.getAltoPantalla()-tortuga3.getAlto()));
		tortuga1.setxMin(300);
		tortuga2.setxMin(300);
		tortuga3.setxMin(300);
		tortuga1.setxMax(600);
		tortuga2.setxMax(600);
		tortuga3.setxMax(600);
		tortuga1.moverseIzquierda();
		tortuga2.moverseIzquierda();
		tortuga3.moverseIzquierda();
		
		//SEGUNDAS 3 TORTUGAS
		tortuga4.setEstoyVivo(true);
		tortuga5.setEstoyVivo(true);
		tortuga6.setEstoyVivo(true);
		tortuga4.setX(suelo2.getX()+suelo2.getAncho()/2);
		tortuga5.setX(suelo2.getX()+suelo2.getAncho()/2+40);
		tortuga6.setX(suelo2.getX()+suelo2.getAncho()/2+80);
		tortuga4.setY((float)(manejadorPantalla.getAltoPantalla()-tortuga4.getAlto()));
		tortuga5.setY((float)(manejadorPantalla.getAltoPantalla()-tortuga5.getAlto()));
		tortuga6.setY((float)(manejadorPantalla.getAltoPantalla()-tortuga6.getAlto()));
		tortuga4.setxMin((int) suelo2.getX());
		tortuga5.setxMin((int) suelo2.getX());
		tortuga6.setxMin((int) suelo2.getX());
		tortuga4.setxMax((int)(suelo2.getX()+suelo2.getAncho()));
		tortuga5.setxMax((int)(suelo2.getX()+suelo2.getAncho()));
		tortuga6.setxMax((int)(suelo2.getX()+suelo2.getAncho()));
		tortuga4.moverseIzquierda();
		tortuga5.moverseIzquierda();
		tortuga6.moverseIzquierda();
		
		//TERCERAS 3 TORTUGAS
		tortuga7.setEstoyVivo(true);
		tortuga8.setEstoyVivo(true);
		tortuga9.setEstoyVivo(true);
		tortuga7.setX(plataforma1.getX()+plataforma1.getAncho()/2);
		tortuga8.setX(plataforma2.getX()+plataforma2.getAncho()/2);
		tortuga9.setX(plataforma3.getX()+plataforma3.getAncho()/2);
		tortuga7.setY((float)(manejadorPantalla.getAltoPantalla()-plataforma2.getY()+10));
		tortuga8.setY((float)(manejadorPantalla.getAltoPantalla()-plataforma1.getY()+10));
		tortuga9.setY((float)(manejadorPantalla.getAltoPantalla()-plataforma2.getY()+10));
		tortuga7.setxMin((int) plataforma1.getX());
		tortuga8.setxMin((int) plataforma2.getX());
		tortuga9.setxMin((int) plataforma3.getX());
		tortuga7.setxMax((int)(plataforma1.getX()+plataforma1.getAncho()));
		tortuga8.setxMax((int)(plataforma2.getX()+plataforma2.getAncho()));
		tortuga9.setxMax((int)(plataforma3.getX()+plataforma3.getAncho()));
		tortuga7.setAceleracionY(0.01f);
		tortuga8.setAceleracionY(0.01f);
		tortuga9.setAceleracionY(0.01f);
		tortuga7.moverseIzquierda();
		tortuga8.moverseIzquierda();
		tortuga9.moverseIzquierda();
		
		//PRIMERAS 3 TORTUGAS SALTARINAS
		tortugaS1.setEstoyVivo(true);
		tortugaS2.setEstoyVivo(true);
		tortugaS3.setEstoyVivo(true);
		tortugaS1.setAceleracionY(0.0f);
		tortugaS2.setAceleracionY(0.0f);
		tortugaS3.setAceleracionY(0.0f);		
		tortugaS1.setX(suelo3.getX()+suelo3.getAncho()/2);
		tortugaS2.setX(suelo3.getX()+suelo3.getAncho()/2+40);
		tortugaS3.setX(suelo3.getX()+suelo3.getAncho()/2+80);
		tortugaS1.setY((float)(0));
		tortugaS2.setY((float)(0));
		tortugaS3.setY((float)(0));
		tortugaS1.setxMin((int) suelo3.getX());
		tortugaS2.setxMin((int) suelo3.getX());
		tortugaS3.setxMin((int) suelo3.getX());
		tortugaS1.setxMax((int)(obstaculo1.getX()));
		tortugaS2.setxMax((int)(obstaculo1.getX()));
		tortugaS3.setxMax((int)(obstaculo1.getX()));
		tortugaS1.moverseIzquierda();
		tortugaS2.moverseIzquierda();
		tortugaS3.moverseIzquierda();
		tortugaS1.setAceleracionY(0.01f);
		tortugaS2.setAceleracionY(0.01f);
		tortugaS3.setAceleracionY(0.01f);
		
		//Primeros chuchos
		chucho1.setX(obstaculo1.getX()+obstaculo1.getAncho());
		chucho1.setY((float) -chucho1.getAlto());
		chucho1.setxMin((int)(obstaculo1.getX()+obstaculo1.getAncho()));
		chucho1.setxMax((int)(suelo3.getX()+suelo3.getAncho()));
		//chucho1.moverseIzquierda();
		//chucho1.setAceleracionY(0.01f);
		chucho2.setX((float)(obstaculo1.getX()+obstaculo1.getAncho()+chucho1.getAncho()));
		chucho2.setY((float) -chucho1.getAlto());
		chucho2.setxMin((int)(obstaculo1.getX()+obstaculo1.getAncho()));
		chucho2.setxMax((int)(suelo3.getX()+suelo3.getAncho()));
		//chucho2.moverseIzquierda();
		//chucho2.setAceleracionY(0.01f);
		chucho3.setX((float)(suelo3.getX()+suelo3.getAncho()));
		chucho3.setY((float) -chucho1.getAlto());
		chucho3.setxMin((int)(obstaculo1.getX()+obstaculo1.getAncho()));
		chucho3.setxMax((int)(suelo3.getX()+suelo3.getAncho()));
		//chucho3.setAceleracionY(0.01f);
		//chucho3.moverseDerecha();
		chucho4.setX((float)(suelo3.getX()+suelo3.getAncho()-chucho4.getAncho()));
		chucho4.setY((float) -chucho1.getAlto());
		chucho4.setxMin((int)(obstaculo1.getX()+obstaculo1.getAncho()));
		chucho4.setxMax((int)(suelo3.getX()+suelo3.getAncho()));
		//chucho4.setAceleracionY(0.01f);
		//chucho4.moverseDerecha();
		
		//Fantasmas primeros
		fantasma1.setX(suelo3.getX()+suelo3.getAncho());
		fantasma1.setY(400);
		fantasma1.setxMin((int)(obstaculo1.getX()+obstaculo1.getAncho()));
		fantasma1.setxMax((int)(suelo3.getX()+suelo3.getAncho()));
		fantasma2.setX(suelo3.getX()+suelo3.getAncho()/2-500);
		fantasma2.setY(400);
		fantasma2.setxMin((int) (suelo3.getX()));
		fantasma2.setxMax((int)(suelo3.getX()+suelo3.getAncho()));
		fantasma3.setX(suelo3.getX()+suelo3.getAncho()/2+100);
		fantasma3.setY(400);
		fantasma3.setxMin((int) (suelo3.getX()));
		fantasma3.setxMax((int)(suelo3.getX()+suelo3.getAncho()));
		
		//Se prepara el fuego
		fuego1.setX(suelo4.getX()+200);
		fuego1.setY((float) (suelo4.getY()-fuego1.getAlto()));
		fuego1.setAceleracionY(0.01f);
		fuego2.setX(suelo4.getX()+550);
		fuego2.setY((float) (suelo4.getY()-fuego1.getAlto()));		
		fuego2.setAceleracionY(0.01f);
		
		fuego3.setX(plataforma6.getX()+100);
		fuego3.setY((float) (suelo4.getY()-fuego3.getAlto()));		
		fuego3.setAceleracionY(0.01f);
		
		fuego4.setX((float) (fuego3.getX()+fuego3.getAncho()+300));
		fuego4.setY((float) (suelo4.getY()-fuego4.getAlto()));		
		fuego4.setAceleracionY(0.01f);
		
		fuego5.setX((float) (fuego4.getX()+fuego4.getAncho()+300));
		fuego5.setY((float) (suelo4.getY()-fuego5.getAlto()));		
		fuego5.setAceleracionY(0.01f);
		
		//Se prepara el monstruo de fuego
		monstruoFuego1.setX((float) (fuego1.getX()+fuego1.getAncho()+175));
		monstruoFuego1.setY((float) (suelo4.getY()-fuego1.getAlto()));
		monstruoFuego1.setAceleracionY(0.01f);
		
		//Se preparan los escupe fuego
		escupeFuego1.setX(plataforma6.getX()+plataforma6.getAncho()/4);
		escupeFuego1.setY((float) (plataforma6.getY()-escupeFuego1.getAlto()));
		escupeFuego1.setAceleracionY(0.01f);
		escupeFuego2.setX(plataforma6.getX()+2*plataforma6.getAncho()/4);
		escupeFuego2.setY((float) (plataforma6.getY()-escupeFuego1.getAlto()));
		escupeFuego2.setAceleracionY(0.01f);
		escupeFuego3.setX(plataforma6.getX()+3*plataforma6.getAncho()/4);
		escupeFuego3.setY((float) (plataforma6.getY()-escupeFuego1.getAlto()));
		escupeFuego3.setAceleracionY(0.01f);
		
		
		//se establecen las configuraciones iniciales para goku
		jugador.setAceleracionY(0.0f);
		jugador.setEstoyVivo(true);				
		jugador.setX(50);
		jugador.setY(0);
		jugador.setAceleracionY(0.01f);
		jugador.setIndiceAnimacion(1);
		jugador.setReiniciar(false);
		if(menu.getJugadorSelecionado()=="goku"){
			jugador.setEscalaX(1.5f);
			jugador.setEscalaY(1.5f);
		}else if(menu.getJugadorSelecionado()=="raquel"){
			jugador.setEscalaX(1f);
			jugador.setEscalaY(0.7f);
		}else if(menu.getJugadorSelecionado()=="josue"){
			jugador.setEscalaX(1f);
			jugador.setEscalaY(0.7f);
		}else if(menu.getJugadorSelecionado()=="yonatan"){
			jugador.setEscalaX(0.5f);
			jugador.setEscalaY(0.25f);
		}else if(menu.getJugadorSelecionado()=="laura"){
			jugador.setEscalaX(1f);
			jugador.setEscalaY(0.7f);
		}
	}

	@Override
	public void dibujar(Graphics2D g) throws Exception {
		/*
		 * Obtenemos la configuracion del objeto grafics de la clase manejadorPantalla
		 * Al hacer esto, g ahora implementa una estrategia de doble buffer.
		 * La clase manejadorPantalla se instancia en el nucleo. 
		 */
		g = manejadorPantalla.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		/*
		 * Procedemos a dibujar nuestros objetos.
		 * En este caso mi objeto Jugador tiene un metodo propio que lo dibuja
		 * y solo le paso el objeto grafics que implementa la estrategia de doble buffer
		 * para que lo dibuje.
		 */
		//dibujo el fondo
		//g.drawImage(fondo,0,0,manejadorPantalla.getAnchoPantalla(),manejadorPantalla.getAltoPantalla(),null);
		//dibujo a goku
		
		if(menu.isActivo()){
			menu.dibujar(g, Es1);
		}else{
			if(!pausado && isGameOver==false && !Es1.estaCompletado()){
				//Se dibujan el suelo, las plataformas y los obstaculos
				Es1.dibujar(g);
				suelo.dibujar(g);
				suelo2.dibujar(g);
				suelo3.dibujar(g);
				suelo4.dibujar(g);
				plataforma1.dibujar(g);
				plataforma2.dibujar(g);
				plataforma3.dibujar(g);
				plataforma4.dibujar(g);
				plataforma5.dibujar(g);
				plataforma6.dibujar(g);
				plataforma7.dibujar(g);
				cubo.dibujar(g);
				obstaculo1.dibujar(g);
				obstaculo2.dibujar(g);	
				
				//Se dibujan los enemigos
				tortuga1.dibujar(g);
				tortuga2.dibujar(g);
				tortuga3.dibujar(g);
				tortuga4.dibujar(g);
				tortuga5.dibujar(g);
				tortuga6.dibujar(g);
				tortuga7.dibujar(g);
				tortuga8.dibujar(g);
				tortuga9.dibujar(g);
				tortugaS1.dibujar(g);
				tortugaS2.dibujar(g);
				tortugaS3.dibujar(g);
				chucho1.dibujar(g);
				chucho2.dibujar(g);
				chucho3.dibujar(g);
				chucho4.dibujar(g);
				fantasma1.dibujar(g);
				fantasma2.dibujar(g);
				fantasma3.dibujar(g);
				fuego1.dibujar(g);
				fuego2.dibujar(g);
				fuego3.dibujar(g);
				fuego4.dibujar(g);
				fuego5.dibujar(g);
				escupeFuego1.dibujar(g);
				escupeFuego2.dibujar(g);
				escupeFuego3.dibujar(g);
				monstruoFuego1.dibujar(g);
				
				jugador.dibujar(g);
				
				//Se dibujan las vidas de goku
				jugador.dibujarVidas(g, Es1);
				
				if(e1.estaAgarrada()){
					e1.dibujar(g, Es1);
				}if(e2.estaAgarrada()){
					e2.dibujar(g, Es1);
				}if(e3.estaAgarrada()){
					e3.dibujar(g, Es1);
				}if(e4.estaAgarrada()){
					e4.dibujar(g, Es1);
				}if(e5.estaAgarrada()){
					e5.dibujar(g, Es1);
				}if(e6.estaAgarrada()){
					e6.dibujar(g, Es1);
				}if(e7.estaAgarrada()){
					e7.dibujar(g, Es1);
				}
				
				//Se dibujan las esferas
				if(!e1.estaAgarrada()){
					e1.dibujar(g, Es1);
				}if(!e2.estaAgarrada()){
					e2.dibujar(g, Es1);
				}if(!e3.estaAgarrada()){
					e3.dibujar(g, Es1);
				}if(!e4.estaAgarrada()){
					e4.dibujar(g, Es1);
				}if(!e5.estaAgarrada()){
					e5.dibujar(g, Es1);
				}if(!e6.estaAgarrada()){
					e6.dibujar(g, Es1);
				}if(!e7.estaAgarrada()){
					e7.dibujar(g, Es1);
				}
				
			}else if(pausado){
				g.translate(0, 0);
				//Font fuente = new Font("Verdana",Font.CENTER_BASELINE,24);
				//g.setFont(fuente);
				g.setColor(Color.GRAY);
				g.drawString("¡Juego Pausado!", manejadorPantalla.getAnchoPantalla()/2-100, manejadorPantalla.getAltoPantalla()/2);
				g.setColor(Color.WHITE);
				g.drawString("¡Juego Pausado!", manejadorPantalla.getAnchoPantalla()/2-98, manejadorPantalla.getAltoPantalla()/2-2);			
			}else if(isGameOver){
				g.setColor(Color.BLACK);
				g.translate(0, 0);
				g.fillRect(0, 0,(int)(manejadorPantalla.getAnchoPantalla()),(int)(manejadorPantalla.getAltoPantalla()));
				
				String s = "GAME OVER...";
				Font fuente = new Font("Serif",Font.BOLD,52);
				g.setFont(fuente);
				g.setColor(Color.BLUE);
				g.drawString(s, manejadorPantalla.getAnchoPantalla()/2-200, manejadorPantalla.getAltoPantalla()/2);
				g.setColor(new Color(198,226,255));
				g.drawString(s, manejadorPantalla.getAnchoPantalla()/2-198, manejadorPantalla.getAltoPantalla()/2-2);
				fuente = new Font("Serif",Font.BOLD,15);
				g.setFont(fuente);
				g.drawString("Presione Enter", manejadorPantalla.getAnchoPantalla()/2-50, manejadorPantalla.getAltoPantalla()/2+25);			
			}else if(Es1.estaCompletado()){
				g.setColor(Color.BLACK);
				g.translate(0, 0);
				g.fillRect(0, 0,(int)(manejadorPantalla.getAnchoPantalla()),(int)(manejadorPantalla.getAltoPantalla()));
				java.net.URL url = this.getClass().getResource("elementos/shenlong.jpg");
				BufferedImage imagen = ImageIO.read(url);
				g.drawImage(imagen,0,0,(int)manejadorPantalla.getAnchoPantalla(),(int)manejadorPantalla.getAltoPantalla(),null);
				String s = "¡¡¡¡¡FELICIDADES!!!!!";
				Font fuente = new Font("Serif",Font.BOLD,52);
				g.setFont(fuente);
				g.setColor(Color.BLUE);
				g.drawString(s, manejadorPantalla.getAnchoPantalla()/2-325, manejadorPantalla.getAltoPantalla()/2);
				g.setColor(new Color(198,226,255));
				g.drawString(s, manejadorPantalla.getAnchoPantalla()/2-323, manejadorPantalla.getAltoPantalla()/2-2);
				fuente = new Font("Serif",Font.BOLD,15);
				g.setFont(fuente);
				g.drawString("Presione Enter", manejadorPantalla.getAnchoPantalla()/2-50, manejadorPantalla.getAltoPantalla()/2+25);			
			}
		}
		
		
	}	

	@Override
	public void actualizar(long tiempoTranscurrido) throws Throwable {
		/*
		 * Procedo a actualizar mis objetos.
		 * En este caso mi objeto jugador tiene un metodo propio llamado
		 * actualizar que se encarga de actualizarlo, lo unico que hago
		 * aqui es invocar ese metodo.
		 * Esto depende del estilo de cada programador.
		 */	
		
		if(menu.isActivo()){
			
		}else{
			if(!pausado && jugador.seDebeReiniciar()==false && jugador.getNumeroVidas()>0 && !Es1.estaCompletado()){
				
				Es1.actualizar(tiempoTranscurrido, manejadorPantalla, jugador);
				jugador.actualizar(tiempoTranscurrido,manejadorPantalla,this);
				tortuga1.actualizar(tiempoTranscurrido,jugador);
				tortuga2.actualizar(tiempoTranscurrido,jugador);
				tortuga3.actualizar(tiempoTranscurrido,jugador);
				tortuga4.actualizar(tiempoTranscurrido,jugador);
				tortuga5.actualizar(tiempoTranscurrido,jugador);
				tortuga6.actualizar(tiempoTranscurrido,jugador);
				tortuga7.actualizar(tiempoTranscurrido,jugador);
				tortuga8.actualizar(tiempoTranscurrido,jugador);
				tortuga9.actualizar(tiempoTranscurrido,jugador);
				tortugaS1.actualizar(tiempoTranscurrido, jugador);
				tortugaS2.actualizar(tiempoTranscurrido, jugador);
				tortugaS3.actualizar(tiempoTranscurrido, jugador);
				chucho1.actualizar(tiempoTranscurrido, jugador);
				chucho2.actualizar(tiempoTranscurrido, jugador);
				chucho3.actualizar(tiempoTranscurrido, jugador);
				chucho4.actualizar(tiempoTranscurrido, jugador);
			    fantasma1.actualizar(tiempoTranscurrido, jugador);
			    fuego1.actualizar(tiempoTranscurrido, jugador);
				fuego2.actualizar(tiempoTranscurrido, jugador);
				fuego3.actualizar(tiempoTranscurrido, jugador);
				fuego4.actualizar(tiempoTranscurrido, jugador);
				fuego5.actualizar(tiempoTranscurrido, jugador);
				monstruoFuego1.actualizar(tiempoTranscurrido,jugador);
				escupeFuego1.actualizar(tiempoTranscurrido, jugador);
				escupeFuego2.actualizar(tiempoTranscurrido, jugador);
				escupeFuego3.actualizar(tiempoTranscurrido, jugador);
			    if(obstaculo1.isActivo()){
			    	fantasma2.actualizar(tiempoTranscurrido, jugador);
			    	fantasma3.actualizar(tiempoTranscurrido, jugador);
			    }
				
				ArrayList<Criatura> criaturas = new ArrayList<Criatura>();
				criaturas.add(jugador);			
				criaturas.add(tortuga1);
				criaturas.add(tortuga2);
				criaturas.add(tortuga3);	
				suelo.reconociendoCriaturas(criaturas);
				criaturas.add(tortuga7);
				criaturas.add(tortuga8);
				criaturas.add(tortuga9);
				plataforma1.actualizar(tiempoTranscurrido, criaturas);
				plataforma2.actualizar(tiempoTranscurrido, criaturas);
				plataforma3.actualizar(tiempoTranscurrido, criaturas);
				criaturas.add(tortuga4);
				criaturas.add(tortuga5);
				criaturas.add(tortuga6);
				suelo2.reconociendoCriaturas(criaturas);
				criaturas.add(tortugaS1);
				criaturas.add(tortugaS2);
				criaturas.add(tortugaS3);
				criaturas.add(chucho1);
				criaturas.add(chucho2);
				criaturas.add(chucho3);
				criaturas.add(chucho4);
				suelo3.reconociendoCriaturas(criaturas);
				criaturas.add(fuego1);
				criaturas.add(fuego2);
				criaturas.add(fuego3);
				criaturas.add(fuego4);
				criaturas.add(fuego5);
				criaturas.add(escupeFuego1);
				criaturas.add(escupeFuego2);
				criaturas.add(escupeFuego3);
				criaturas.add(monstruoFuego1);
				suelo4.reconociendoCriaturas(criaturas);
				plataforma4.actualizar(tiempoTranscurrido, criaturas);
				plataforma5.actualizar(tiempoTranscurrido, criaturas);
				plataforma6.actualizar(tiempoTranscurrido, criaturas);
				plataforma7.actualizar(tiempoTranscurrido, criaturas);
				cubo.reconociendoCriaturas(criaturas);
							
				//obstaculo1
				obstaculo1.reconociendoCriaturas(criaturas);
				obstaculo2.reconociendoCriaturas(criaturas);
				
				//Se actualizan las esferas
				e1.actualizar(jugador, Es1, manejadorPantalla);
				e2.actualizar(jugador, Es1, manejadorPantalla);
				e3.actualizar(jugador, Es1, manejadorPantalla);
				e4.actualizar(jugador, Es1, manejadorPantalla);
				e5.actualizar(jugador, Es1, manejadorPantalla);
				e6.actualizar(jugador, Es1, manejadorPantalla);
				e7.actualizar(jugador, Es1, manejadorPantalla);
			}else if(jugador.getNumeroVidas()==0){
				isGameOver = true;			
			}else if(jugador.seDebeReiniciar()==true){				
				Es1.setX(0);
				crearCriaturas();															
			}
		}
				
		
	}

	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 * Estos son los metodos que hemos implementado de la clase KeyListener
	 * Sirven para escuchar eventos del teclado.
	 * Cuando alguien presiona una tecla, se ejecuta el metodo keyPressed,
	 * cuando se suelta una tecla se ejecuta el metodo keyReleased, cuando
	 * se presiona y se suelta una tecla, se ejecuta keyTyped.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int codigoTecla = e.getKeyCode();
	
		if (codigoTecla == KeyEvent.VK_ENTER){
			if(!isGameOver && !Es1.estaCompletado()){
				if(pausado){
					quitarPausa();
				}else{
					pausar();
				}
			}else{
				try {
					prepararMenu();
					prepararJuego();
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		}else if(codigoTecla == KeyEvent.VK_ESCAPE){
			/*
			 * si se presiona la tecla Esc, detenemos el juego. El metodo detener()
			 * pertenece al nucleo.
			 */
			detener();
		}else{
			if(!pausado){
				jugador.teclaPresionada(e);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/*
		 * Cuando alguien suelta una tecla, a veces necesitamos efectuar una accion.
		 * Por ejemplo, cuando alguien lanza un golpe presionando la tecla x, el jugador
		 * continuara lanzando el golpe indefinidamente, a menos que al momento de soltar
		 * la tecla le digamos que deje de golpear.		
		 */
		
		try{
			if(!pausado){
				jugador.teclaSoltada(e);
			}else{
				jugador.soltar();
			}				
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			menu.verificar(e.getX(), e.getY(),1, this);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		 try {
			menu.verificar(e.getX(), e.getY(),0,this);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
