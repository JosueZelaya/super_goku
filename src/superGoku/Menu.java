package superGoku;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import elementosEscenario.Escenario;

import nucleo.ManejadorPantalla;
import nucleo.Principal;

public class Menu
{
	int alto,ancho;
	Graphics2D gra;
	int xc=0;
	int yc=0;
    Boton btnGoku, btnRaquel,btnLaura,btnJosue,btnYonatan;
    boolean activo;
        
    public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

		private String jugadorSelecionado="raquel";
        
	public Menu(ManejadorPantalla pantalla)
    {
		alto = (int) pantalla.getAltoPantalla();
		ancho = (int) pantalla.getAnchoPantalla();
		btnGoku=new Boton(xc, yc, 200, 170);                
        btnRaquel=new Boton(xc, yc, 200, 170);
        btnLaura=new Boton(xc, yc, 200, 170);
        btnJosue=new Boton(xc, yc, 200, 170);
        btnYonatan=new Boton(xc, yc, 200, 170);
        activo = true;
    }
	
	public void dibujar(Graphics2D g,Escenario e)
        {
		xc=(int) e.getXCentral();
                yc=(int) e.getYCentral();
                gra=g; 
                prepararFondo();
                //propiedades y ubicacion del boton Goku
                btnGoku.x = 15;
                btnGoku.y = yc/2-100;
                //propiedades y ubicacion del boton Raquel
                btnRaquel.x = 250;
                btnRaquel.y = yc/2-100;
                //propiedades y ubicacion del boton Laura
                btnLaura.x = 500;
                btnLaura.y = yc/2-100;
                //propiedades y ubicacion del boton Josue
                btnJosue.x=15;
                btnJosue.y=yc-25;
                //propiedades y ubicacion del boton Yonatan
                btnYonatan.x=250;
                btnYonatan.y=yc-25;
                //se dibujan los diferentes botones
                Image i=cargarImagen("imagenes/goku2.png");
                btnGoku.dibujar("Goku",i);
                i=cargarImagen("imagenes/rakl.png");
                btnRaquel.dibujar("Raquel",i);
                i=cargarImagen("imagenes/laura.png");
                btnLaura.dibujar("Laura", i);
                i=cargarImagen("imagenes/josue.png");
                btnJosue.dibujar("Josue", i);
                i=cargarImagen("imagenes/manga.png");
                btnYonatan.dibujar("Yonatan", i);
                
                
        }
        /*
         * Este metodo pone una imagen al fondo del menu
         */
	public void prepararFondo()
        {
                Image immm=null; 
               immm=cargarImagen("imagenes/dragon1.jpg");
                gra.drawImage(immm,0,0, 2*xc, 2*yc, null);
	
         }
	/**
         * Este metodo recibe la posicion del mause
         * si codigo=1 se dibuja un cuadro al boton
         * si codigo=0 se seleciona al jugador
         * @param x
         * @param y
         * @param codigo 
	 * @throws Exception 
         * 
         */
	 public void verificar(int x,int y, int codigo,Principal juego) throws Exception{
             
             if (codigo==0) {
                 if (btnRaquel.esBoton(x, y)) {
                     setJugadorSelecionado("raquel");
                     activo = false;
                     juego.prepararJuego();
                 }
                 if (btnGoku.esBoton(x, y)) {
                   setJugadorSelecionado("goku");
                   activo = false;
                   juego.prepararJuego();
                 }   
                 if (btnLaura.esBoton(x, y)) {
                   setJugadorSelecionado("laura");
                   activo = false;
                   juego.prepararJuego();
                 }  
                 if (btnJosue.esBoton(x, y)) {
                   setJugadorSelecionado("josue");
                   activo = false;
                   juego.prepararJuego();
                 }  
                 if (btnYonatan.esBoton(x, y)) {
                   setJugadorSelecionado("yonatan");
                   activo = false;
                   juego.prepararJuego();
                 }  
             }
             if (codigo==1) {
                if (btnGoku.esBoton(x, y)) {
                    btnGoku.desc = true;
                 }else if (btnRaquel.esBoton(x, y)) {
                    btnRaquel.desc = true;
                 }else if (btnLaura.esBoton(x, y)) {
                    btnLaura.desc = true;
                 }else if (btnJosue.esBoton(x, y)) {
                    btnJosue.desc = true;
                 }else if (btnYonatan.esBoton(x, y)) {
                    btnYonatan.desc = true;
                 }else{
                    btnGoku.desc = false;
                    btnRaquel.desc = false;
                    btnLaura.desc=false;
                    btnJosue.desc=false;
                    btnYonatan.desc=false; 
                 }  
             }
             
         }
       /**
          * Con este metodo se carga una imagen
          * cadena debe de ser la ruta del archivo con su extension
          * @param cadena
          * @return 
          */
        public Image cargarImagen(String cadena){
            java.net.URL url=null;
            Image imagen=null;
        try {
            url = this.getClass().getResource(cadena);
            imagen = ImageIO.read(url);            
            } 
        catch (IOException ex) 
            {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        return imagen;
        }

    /**
     * Este metodo devuelve el jugador selecionado
     * @return the jugadorSelecionado
     */
    public String getJugadorSelecionado() {
        return jugadorSelecionado;
    }

    /**
     * @param jugadorSelecionado the jugadorSelecionado to set
     */
    public void setJugadorSelecionado(String jugadorSelecionado) {
        this.jugadorSelecionado = jugadorSelecionado;
    }
        
    /**
     * Este es una clase interna que permitira crear objetos Botones para poder animar 
     * la selecion del jugador que se desea ejecutar en el juego
     */        
private class Boton{
            public int x,y;
            public int ancho;
            public int alto;
            String adentro="";
            boolean desc=false;
            
    public Boton(int x,int y, int ancho,int alto){
                this.x=x;
                this.y=y;
                this.ancho=ancho;
                this.alto=alto;
                                     }
       
    public  void dibujar(String nombre, Image imagen) {
        if (desc) {
            
            gra.setColor(Color.decode("7634512"));            
            gra.draw3DRect(x-2, y-2, ancho+4,alto+4,true);
            gra.fill3DRect(800, 50,85, 30, desc);
            gra.setColor(Color.decode("1110405"));
            
            gra.drawString(nombre, 805, 70);
        gra.setColor(Color.decode("7777777"));
          //gra.setColor(Color.CYAN);  
        gra.fillRect(x, y, ancho, alto);
        }
       gra.setColor(Color.decode("556645"));
       gra.drawString(nombre, x+10, y-10); 
       gra.drawImage(imagen, x, y, ancho, alto,null);
       gra.setColor(Color.red);
       gra.drawString(adentro, 100, 100);   
        }    
    
    public boolean esBoton(int x,int y){     
        if (x>=this.x && x<=this.x+this.ancho && y>=this.y && y<=this.y+this.alto) {
            return true;
        } else {   
            return false;
        }     
    }
        }


        }



