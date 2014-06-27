package superGoku;

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import Enemigos.Chucho;
import Enemigos.EscupeFuego;
import Enemigos.Fantasma;
import Enemigos.Fuego;
import Enemigos.MonstruoFuego;
import Enemigos.Tortuga;
import Enemigos.TortugaSaltarina;



public class Personaje {
	
	java.net.URL url;
	BufferedImage spriteExplosion;
	BufferedImage spriteGoku;
	BufferedImage spriteEnemigosMario;
	BufferedImage spriteRaquel;
	BufferedImage spriteJosue;
	BufferedImage SpriteLaura1;
	BufferedImage SpriteManga;
	
	public Personaje() throws IOException{
		url = this.getClass().getResource("explosion.png");
		spriteExplosion = ImageIO.read(url);
		url = this.getClass().getResource("goku2.png");
		spriteGoku = ImageIO.read(url);
		url = this.getClass().getResource("marioEnemigos.png");
		spriteEnemigosMario = ImageIO.read(url);
		url = this.getClass().getResource("spriteRaquel.png");
		spriteRaquel = ImageIO.read(url);
		url = this.getClass().getResource("spriteJosue.png");
		spriteJosue = ImageIO.read(url);
		
		url = this.getClass().getResource("SpriteManga.png");
		SpriteManga = ImageIO.read(url);
		url = this.getClass().getResource("SpriteLaura1.png");
		SpriteLaura1 = ImageIO.read(url);
	}
	
	public Laura getLaura() throws IOException{
		//Se prepara la explosion
		Animacion explosion = getExplosion();
		
		//laura parada hacia la derecha
		Animacion paradaD = new Animacion(SpriteLaura1);
		paradaD.agregarFrame(new FrameInfo(37,421,26,75), 50);
		paradaD.agregarFrame(new FrameInfo(8,351,24,66), 50);
		
		//laura caminando hacia la derecha
		Animacion caminaD = new Animacion(SpriteLaura1);
		caminaD.agregarFrame(new FrameInfo(37,421,26,75), 50);
		caminaD.agregarFrame(new FrameInfo(85,423,25,68), 50);
		caminaD.agregarFrame(new FrameInfo(127,418,26,69), 50);
		caminaD.agregarFrame(new FrameInfo(179,420,26,72), 50);
		caminaD.agregarFrame(new FrameInfo(229,424,21,66), 50);
		caminaD.agregarFrame(new FrameInfo(265,421,19,67), 50);
		caminaD.agregarFrame(new FrameInfo(299,423,24,66), 50);
		
		
		//laura tira trompon hacia la derecha
		Animacion tromponD = new Animacion(SpriteLaura1);
		tromponD.agregarFrame(new FrameInfo(14,273,28,71), 50);
		tromponD.agregarFrame(new FrameInfo(53,275,27,68), 50);
		tromponD.agregarFrame(new FrameInfo(89,273,25,71), 50);
		tromponD.agregarFrame(new FrameInfo(134,276,45,70), 50);
		
		
		//laura tira patada hacia la derecha
		Animacion patadaD = new Animacion(SpriteLaura1);
		patadaD.agregarFrame(new FrameInfo(8,350,23,67), 50);
		patadaD.agregarFrame(new FrameInfo(42,348,30,65), 50);
		patadaD.agregarFrame(new FrameInfo(84,346,30,62), 50);
		patadaD.agregarFrame(new FrameInfo(132,354,54,60), 50);
		
		
		
		//laura parado a la izquierda
		Animacion paradaI = new Animacion(SpriteLaura1);
		paradaI.agregarFrame(new FrameInfo(292,16,30,77), 50);
		paradaI.agregarFrame(new FrameInfo(296,99,27,77), 50);
		
		//Yonatan caminando hacia la izquierda
		Animacion caminaI = new Animacion(SpriteLaura1);
		caminaI.agregarFrame(new FrameInfo(298,192,25,75), 50);
		caminaI.agregarFrame(new FrameInfo(249,193,27,68), 50);
		caminaI.agregarFrame(new FrameInfo(207,190,26,67), 50);
		caminaI.agregarFrame(new FrameInfo(156,191,25,72), 50);
		caminaI.agregarFrame(new FrameInfo(111,194,21,67), 50);
		caminaI.agregarFrame(new FrameInfo(74,193,21,66), 50);
		caminaI.agregarFrame(new FrameInfo(39,193,22,67), 50);
		
		//laura tira trompon hacia la izquierda
		Animacion tromponI = new Animacion(SpriteLaura1);
		tromponI.agregarFrame(new FrameInfo(292,16,31,78), 50);
		tromponI.agregarFrame(new FrameInfo(249,15,30,76), 50);
		tromponI.agregarFrame(new FrameInfo(205,17,25,77), 50);
		tromponI.agregarFrame(new FrameInfo(141,16,47,74), 50);
		
		
		//laura tira patada hacia la izquierda
		Animacion patadaI = new Animacion(SpriteLaura1);
		patadaI.agregarFrame(new FrameInfo(297,98,26,79), 50);
		patadaI.agregarFrame(new FrameInfo(253,100,36,75), 50);
		patadaI.agregarFrame(new FrameInfo(205,105,38,72), 50);
		patadaI.agregarFrame(new FrameInfo(135,104,67,71), 50);
		patadaI.agregarFrame(new FrameInfo(416,785,155,254), 50);
		
		
		ArrayList<Animacion> animaciones = new ArrayList<Animacion>();
		animaciones.add(explosion);
		animaciones.add(paradaD);
		animaciones.add(caminaD);
		animaciones.add(tromponD);
		animaciones.add(patadaD);
		animaciones.add(paradaI);
		animaciones.add(caminaI);
		animaciones.add(tromponI);
		animaciones.add(patadaI);
		
		Laura laura = new Laura(animaciones);
		laura.setIndiceAnimacion(1);
		
		
		//Se carga la foto para mostrar a la par de las vidas
		url = this.getClass().getResource("laura.png");
		BufferedImage ra = ImageIO.read(url);
		Animacion foto = new Animacion(ra);
		foto.agregarFrame(new FrameInfo(0,0,ra.getWidth(),ra.getHeight()), 1000);
		laura.setAnimacionFoto(foto);
		
		return laura;
	
	}
	
	public Yonatan getYonatan() throws IOException{
		//Se prepara la explosion
		Animacion explosion = getExplosion();
		
		//yonatan parado hacia la derecha
		Animacion paradoD = new Animacion(SpriteManga);
		paradoD.agregarFrame(new FrameInfo(25,19,92,242), 50);
		paradoD.agregarFrame(new FrameInfo(173,21,55,246), 50);
		
		//Yonatan caminando hacia la derecha
		Animacion caminaD = new Animacion(SpriteManga);
		caminaD.agregarFrame(new FrameInfo(25,19,92,242), 50);
		caminaD.agregarFrame(new FrameInfo(173,21,55,246), 50);
		caminaD.agregarFrame(new FrameInfo(301,21,93,244), 50);
		caminaD.agregarFrame(new FrameInfo(457,21,90,241), 50);
		caminaD.agregarFrame(new FrameInfo(598,19,75,237), 50);
		caminaD.agregarFrame(new FrameInfo(736,12,64,247), 50);
		
		
		//yonatan tira trompon hacia la derecha
		Animacion tromponD = new Animacion(SpriteManga);
		tromponD.agregarFrame(new FrameInfo(530,295,94,230), 50);
		tromponD.agregarFrame(new FrameInfo(693,290,102,232), 50);
		tromponD.agregarFrame(new FrameInfo(29,564,89,228), 50);
		tromponD.agregarFrame(new FrameInfo(165,569,119,227), 50);
		tromponD.agregarFrame(new FrameInfo(328,562,131,225), 50);
		
		//Yonatan tira patada hacia la derecha
		Animacion patadaD = new Animacion(SpriteManga);
		patadaD.agregarFrame(new FrameInfo(736,12,64,247), 50);
		patadaD.agregarFrame(new FrameInfo(598,19,75,237), 50);
		patadaD.agregarFrame(new FrameInfo(19,285,102,248), 50);
		patadaD.agregarFrame(new FrameInfo(158,291,104,247), 50);
		patadaD.agregarFrame(new FrameInfo(291,287,193,250), 50);
		
		
		//Yonatan parado a la izquierda
		Animacion paradoI = new Animacion(SpriteManga);
		paradoI.agregarFrame(new FrameInfo(782,1074,89,248), 50);
		paradoI.agregarFrame(new FrameInfo(656,1067,55,246), 50);
		
		//Yonatan caminando hacia la izquierda
		Animacion caminaI = new Animacion(SpriteManga);
		caminaI.agregarFrame(new FrameInfo(782,1074,89,248), 50);
		caminaI.agregarFrame(new FrameInfo(656,1067,55,246), 50);
		caminaI.agregarFrame(new FrameInfo(511,176,67,240), 50);
		caminaI.agregarFrame(new FrameInfo(366,1071,93,239), 50);
		caminaI.agregarFrame(new FrameInfo(255,1069,77,239), 50);
		caminaI.agregarFrame(new FrameInfo(133,1067,64,245), 50);
			
		//Yonatan tira trompon hacia la izquierda
		Animacion tromponI = new Animacion(SpriteManga);
		tromponI.agregarFrame(new FrameInfo(309,802,95,232), 50);
		tromponI.agregarFrame(new FrameInfo(143,805,105,226), 50);
		tromponI.agregarFrame(new FrameInfo(780,546,89,227), 50);
		tromponI.agregarFrame(new FrameInfo(644,546,118,222), 50);
		tromponI.agregarFrame(new FrameInfo(516,338,129,223), 50);
		
		//Yonatan tira patada hacia la izquierda
		Animacion patadaI = new Animacion(SpriteManga);
		patadaI.agregarFrame(new FrameInfo(656,1067,55,246), 50);
		patadaI.agregarFrame(new FrameInfo(782,1074,89,248), 50);
		patadaI.agregarFrame(new FrameInfo(744,794,102,250), 50);
		patadaI.agregarFrame(new FrameInfo(599,787,108,250), 50);
		patadaI.agregarFrame(new FrameInfo(416,785,155,254), 50);
		
		
		ArrayList<Animacion> animaciones = new ArrayList<Animacion>();
		animaciones.add(explosion);
		animaciones.add(paradoD);
		animaciones.add(caminaD);
		animaciones.add(tromponD);
		animaciones.add(patadaD);
		animaciones.add(paradoI);
		animaciones.add(caminaI);
		animaciones.add(tromponI);
		animaciones.add(patadaI);
		
		Yonatan yonatan = new Yonatan(animaciones);
		yonatan.setIndiceAnimacion(1);
		
		
		//Se carga la foto para mostrar a la par de las vidas
		url = this.getClass().getResource("manga.png");
		BufferedImage ra = ImageIO.read(url);
		Animacion foto = new Animacion(ra);
		foto.agregarFrame(new FrameInfo(0,0,ra.getWidth(),ra.getHeight()), 1000);
		yonatan.setAnimacionFoto(foto);
		//yonatan.setEscala(0.5f, 0.25f);
		
		return yonatan;
	}
	
	public EscupeFuego getEscupeFuego(){
		Animacion fuego = new Animacion(spriteEnemigosMario);
		fuego.agregarFrame(new FrameInfo(297,762,16,23), 200);
		fuego.agregarFrame(new FrameInfo(333,762,16,24), 300);
		fuego.agregarFrame(new FrameInfo(377,716,16,35), 100);
		fuego.agregarFrame(new FrameInfo(377,760,16,47), 100);
		fuego.agregarFrame(new FrameInfo(376,816,17,55), 100);
		
		ArrayList<Animacion> animaciones = new ArrayList<Animacion>();
		animaciones.add(fuego);
		EscupeFuego efuego = new EscupeFuego(animaciones);
		efuego.setEscala(2f, 2.5f);
		efuego.setIndiceAnimacion(0);
		return efuego;
	}
	
	public MonstruoFuego getMonstruoFuego(){
		Animacion quieto = new Animacion(spriteEnemigosMario);
		quieto.agregarFrame(new FrameInfo(417,1166,16,15), 50);
		quieto.agregarFrame(new FrameInfo(417,1166,16,15), 50);
		quieto.setNombre("quieto");
		
		Animacion ataque = new Animacion(spriteEnemigosMario);
		ataque.agregarFrame(new FrameInfo(351,1158,47,32), 50);
		ataque.agregarFrame(new FrameInfo(291,1158,47,32), 50);
		
		ArrayList<Animacion> animaciones = new ArrayList<Animacion>();
		animaciones.add(quieto);
		animaciones.add(ataque);
		MonstruoFuego fuego = new MonstruoFuego(animaciones);
		fuego.setEscala(2f, 1.5f);
		fuego.setIndiceAnimacion(0);
		return fuego;
		
	}
	
	public Fuego getFuego(){
		//Se prepara la fuego
		Animacion aFuego = new Animacion(spriteEnemigosMario);
		aFuego.agregarFrame(new FrameInfo(257,1006,16,16), 50);
		aFuego.agregarFrame(new FrameInfo(301,1002,47,24), 50);
		aFuego.agregarFrame(new FrameInfo(381,998,47,32), 50);
		aFuego.agregarFrame(new FrameInfo(5,1038,79,32), 50);
		aFuego.agregarFrame(new FrameInfo(95,1038,80,32), 50);
		aFuego.agregarFrame(new FrameInfo(275,1038,80,32), 50);
		aFuego.agregarFrame(new FrameInfo(365,1038,74,32), 50);
		aFuego.agregarFrame(new FrameInfo(5,1078,80,32), 50);
		aFuego.agregarFrame(new FrameInfo(75,1078,80,32), 50);
		aFuego.agregarFrame(new FrameInfo(185,1078,80,32), 50);
		aFuego.agregarFrame(new FrameInfo(365,1082,74,24), 50);
		aFuego.agregarFrame(new FrameInfo(5,1126,31,16), 50);
		aFuego.agregarFrame(new FrameInfo(53,1126,31,16), 50);
		aFuego.agregarFrame(new FrameInfo(95,1126,15,16), 50);
		
		ArrayList<Animacion> animaciones = new ArrayList<Animacion>();
		animaciones.add(aFuego);
		Fuego fuego = new Fuego(animaciones);
		fuego.setEscala(2f, 1.5f);
		return fuego;
	}
	
	public Josue getJosue() throws IOException{
		//Se prepara la explosion
		Animacion explosion = getExplosion();
		
		//Josue parado hacia la derecha
		Animacion paradoD = new Animacion(spriteJosue);
		paradoD.agregarFrame(new FrameInfo(237,3,28,75), 50);
		paradoD.agregarFrame(new FrameInfo(270,3,29,64), 50);
		
		//Josue caminando hacia la derecha
		Animacion caminaD = new Animacion(spriteJosue);
		caminaD.agregarFrame(new FrameInfo(5,1,31,76), 250);
		caminaD.agregarFrame(new FrameInfo(43,1,26,76), 250);
		caminaD.agregarFrame(new FrameInfo(74,0,32,79), 250);
		caminaD.agregarFrame(new FrameInfo(113,1,27,80), 250);
		caminaD.agregarFrame(new FrameInfo(146,0,24,80), 250);
		caminaD.agregarFrame(new FrameInfo(173,1,28,81), 250);
		caminaD.agregarFrame(new FrameInfo(204,2,29,78), 250);
		
		//Josue tira trompon hacia la derecha
		Animacion tromponD = new Animacion(spriteJosue);
		tromponD.agregarFrame(new FrameInfo(237,3,28,75), 50);
		tromponD.agregarFrame(new FrameInfo(270,3,29,64), 50);
		tromponD.agregarFrame(new FrameInfo(5,80,46,77), 50);
		tromponD.agregarFrame(new FrameInfo(57,81,55,75), 50);
		
		//Josue tira patada hacia la derecha
		Animacion patadaD = new Animacion(spriteJosue);
		patadaD.agregarFrame(new FrameInfo(116,83,28,77), 50);
		patadaD.agregarFrame(new FrameInfo(152,84,30,74), 50);
		patadaD.agregarFrame(new FrameInfo(191,85,37,74), 50);
		patadaD.agregarFrame(new FrameInfo(237,85,51,74), 50);
		patadaD.agregarFrame(new FrameInfo(6,164,55,69), 50);
		patadaD.agregarFrame(new FrameInfo(68,160,33,77), 50);
		patadaD.agregarFrame(new FrameInfo(110,163,32,72), 50);
		patadaD.agregarFrame(new FrameInfo(153,162,47,72), 50);
		patadaD.agregarFrame(new FrameInfo(207,164,44,71), 50);
		patadaD.agregarFrame(new FrameInfo(256,164,43,71), 50);
		
		//Jossue parado a la izquierda
		Animacion paradoI = new Animacion(spriteJosue);
		paradoI.agregarFrame(new FrameInfo(35,333,28,75), 50);
		paradoI.agregarFrame(new FrameInfo(1,332,27,74), 50);
		
		//Josue caminando hacia la izquierda
		Animacion caminaI = new Animacion(spriteJosue);
		caminaI.agregarFrame(new FrameInfo(265,331,30,76), 250);
		caminaI.agregarFrame(new FrameInfo(232,330,26,76), 250);
		caminaI.agregarFrame(new FrameInfo(194,331,32,78), 250);
		caminaI.agregarFrame(new FrameInfo(160,330,28,81), 250);
		caminaI.agregarFrame(new FrameInfo(130,329,24,81), 250);
		caminaI.agregarFrame(new FrameInfo(99,330,27,81), 250);
		caminaI.agregarFrame(new FrameInfo(65,332,32,78), 250);
		
		//Josue tira trompon hacia la izquierda
		Animacion tromponI = new Animacion(spriteJosue);
		tromponI.agregarFrame(new FrameInfo(35,333,28,75), 50);
		tromponI.agregarFrame(new FrameInfo(1,332,27,74), 50);
		tromponI.agregarFrame(new FrameInfo(248,409,48,78), 50);
		tromponI.agregarFrame(new FrameInfo(189,410,54,76), 50);
		
		//Josue tira patada hacia la izquierda
		Animacion patadaI = new Animacion(spriteJosue);
		patadaI.agregarFrame(new FrameInfo(156,413,27,76), 50);
		patadaI.agregarFrame(new FrameInfo(118,414,30,73), 50);
		patadaI.agregarFrame(new FrameInfo(72,414,36,75), 50);
		patadaI.agregarFrame(new FrameInfo(12,413,51,74), 50);
		patadaI.agregarFrame(new FrameInfo(239,493,56,70), 50);
		patadaI.agregarFrame(new FrameInfo(199,489,33,78), 50);
		patadaI.agregarFrame(new FrameInfo(155,492,36,73), 50);
		patadaI.agregarFrame(new FrameInfo(100,492,47,73), 50);
		patadaI.agregarFrame(new FrameInfo(49,493,44,71), 50);
		patadaI.agregarFrame(new FrameInfo(1,493,44,71), 50);
		
		
		ArrayList<Animacion> animaciones = new ArrayList<Animacion>();
		animaciones.add(explosion);
		animaciones.add(paradoD);
		animaciones.add(caminaD);
		animaciones.add(tromponD);
		animaciones.add(patadaD);
		animaciones.add(paradoI);
		animaciones.add(caminaI);
		animaciones.add(tromponI);
		animaciones.add(patadaI);
		
		Josue josue = new Josue(animaciones);
		josue.setIndiceAnimacion(1);
		
		//Se carga la foto para mostrar a la par de las vidas
		url = this.getClass().getResource("josue.png");
		BufferedImage ra = ImageIO.read(url);
		Animacion foto = new Animacion(ra);
		foto.agregarFrame(new FrameInfo(0,0,ra.getWidth(),ra.getHeight()), 1000);
		josue.setAnimacionFoto(foto);
		
		return josue;

	}
	
	public Raquel getRaquel() throws IOException{
		//Se prepara la explosion
		Animacion explosion = getExplosion();
		
		//Raquel esta parada hacia la derecha
		Animacion paradaD = new Animacion(spriteRaquel);
		paradaD.agregarFrame(new FrameInfo(7,464,20,86), 50);
		
		//Raquel camina hacia la derecha
		Animacion caminaD = new Animacion(spriteRaquel);
		caminaD.agregarFrame(new FrameInfo(41,465,22,85), 250);
		caminaD.agregarFrame(new FrameInfo(75,465,24,84), 250);
		caminaD.agregarFrame(new FrameInfo(110,461,37,88), 250);
		caminaD.agregarFrame(new FrameInfo(157,464,27,91), 250);
		caminaD.agregarFrame(new FrameInfo(194,456,25,92), 250);
		caminaD.agregarFrame(new FrameInfo(225,456,40,90), 250);
		
		//Raquel esta parada hacia la izquierda
		Animacion paradaI = new Animacion(spriteRaquel);
		paradaI.agregarFrame(new FrameInfo(0,1,22,87), 50);
		
		//Raquel camina hacia la izquierda
		Animacion caminaI = new Animacion(spriteRaquel);
		caminaI.agregarFrame(new FrameInfo(29,1,21,85), 250);
		caminaI.agregarFrame(new FrameInfo(54,1,24,84), 250);
		caminaI.agregarFrame(new FrameInfo(81,2,37,88), 250);
		caminaI.agregarFrame(new FrameInfo(122,2,26,91), 250);
		caminaI.agregarFrame(new FrameInfo(151,1,25,92), 250);
		caminaI.agregarFrame(new FrameInfo(180,4,40,90), 250);
		
		//Raquel tira patada derecha
		Animacion patadaD = new Animacion(spriteRaquel);
		patadaD.agregarFrame(new FrameInfo(35,557,32,90), 30);
		patadaD.agregarFrame(new FrameInfo(77,557,31,86), 30);
		patadaD.agregarFrame(new FrameInfo(118,558,28,84), 30);
		patadaD.agregarFrame(new FrameInfo(151,558,38,83), 30);
		patadaD.agregarFrame(new FrameInfo(194,556,40,84), 30);
		patadaD.agregarFrame(new FrameInfo(22,654,62,79), 30);
		patadaD.agregarFrame(new FrameInfo(87,652,65,79), 30);
		patadaD.agregarFrame(new FrameInfo(162,646,69,81), 30);
		
		//Raquel tira patada izquierda
		Animacion patadaI = new Animacion(spriteRaquel);
		patadaI.agregarFrame(new FrameInfo(1,95,31,91), 30);
		patadaI.agregarFrame(new FrameInfo(39,96,30,85), 30);
		patadaI.agregarFrame(new FrameInfo(73,97,28,85), 30);
		patadaI.agregarFrame(new FrameInfo(105,98,36,83), 30);
		patadaI.agregarFrame(new FrameInfo(145,100,39,83), 30);
		patadaI.agregarFrame(new FrameInfo(190,100,61,82), 30);
		patadaI.agregarFrame(new FrameInfo(0,188,69,80), 30);
		patadaI.agregarFrame(new FrameInfo(72,188,68,81), 30);
		
		ArrayList<Animacion> animaciones = new ArrayList<Animacion>();
		animaciones.add(explosion);
		animaciones.add(paradaD);
		animaciones.add(caminaD);
		animaciones.add(paradaI);
		animaciones.add(caminaI);
		animaciones.add(patadaD);
		animaciones.add(patadaI);
		
		Raquel raquel = new Raquel(animaciones);
		raquel.setIndiceAnimacion(1);
		
		//Se carga la foto para mostrar a la par de las vidas
		url = this.getClass().getResource("raton.jpg");
		BufferedImage ra = ImageIO.read(url);
		Animacion foto = new Animacion(ra);
		foto.agregarFrame(new FrameInfo(0,0,ra.getWidth(),ra.getHeight()), 1000);
		raquel.setAnimacionFoto(foto);
		
		return raquel;			
		
		
	}
	
	public Chucho getChucho(){
		//Se prepara la explosion
		Animacion explosion = getExplosion();
		
		//Chucho camina a la izquierda
		Animacion caminaI = new Animacion(spriteEnemigosMario);		
		caminaI.agregarFrame(new FrameInfo(169,638,32,32), 50);
		caminaI.agregarFrame(new FrameInfo(129,638,32,31), 50);
		
		//Chucho camina a la derecha
		Animacion caminaD = new Animacion(spriteEnemigosMario);
		caminaD.agregarFrame(new FrameInfo(244,1986,32,32), 50);
		caminaD.agregarFrame(new FrameInfo(284,1986,32,31), 50);
		
		ArrayList<Animacion> animaciones = new ArrayList<Animacion>();
		animaciones.add(explosion);
		animaciones.add(caminaI);
		animaciones.add(caminaD);
		
		Chucho chucho = new Chucho(animaciones);
		chucho.setIndiceAnimacion(1);
		chucho.setEscalaX(2f);
		chucho.setEscalaY(2f);
		return chucho;		
		
	}
	
	public Fantasma getFantasma(){
		//Fantasma moviendose a la Izquierda
		Animacion moviendoseI = new Animacion(spriteEnemigosMario);
		moviendoseI.agregarFrame(new FrameInfo(371,1242,67,64), 50);
		
		//Fantasma moviendose a la izquierda tapado
		Animacion moviendoseItapado = new Animacion(spriteEnemigosMario);
		moviendoseItapado.agregarFrame(new FrameInfo(293,1242,64,64), 50);
		
		//Fantasma moviendose a la derecha
		Animacion moviendoseD = new Animacion(spriteEnemigosMario);
		moviendoseD.agregarFrame(new FrameInfo(7,2590,67,64), 50);
		
		//Fantasma moviendose a la derecha tapado
		Animacion moviendoseDtapado = new Animacion(spriteEnemigosMario);
		moviendoseDtapado.agregarFrame(new FrameInfo(88,2590,64,64), 50);
		
		ArrayList<Animacion> animaciones = new ArrayList<Animacion>();
		animaciones.add(moviendoseI);
		animaciones.add(moviendoseItapado);
		animaciones.add(moviendoseD);
		animaciones.add(moviendoseDtapado);
		
		Fantasma fantasma = new Fantasma(animaciones);
		fantasma.setIndiceAnimacion(1);
		//fantasma.setEscalaX(2f);
		//fantasma.setEscalaY(2f);
		return fantasma;
	}
	
	
	public TortugaSaltarina getTortugaVerdeSaltarina(){
		//Se prepara la explosion
		Animacion explosion = getExplosion();
		//Tortuga caminando a la izquierda
		Animacion saltandoI = new Animacion(spriteEnemigosMario);
		saltandoI.agregarFrame(new FrameInfo(136,40,17,27), 100);
		saltandoI.agregarFrame(new FrameInfo(94,40,22,28), 100);
		//Tortuga caminando a la derecha
		Animacion saltandoD = new Animacion(spriteEnemigosMario);
		saltandoD.agregarFrame(new FrameInfo(292,1388,17,27), 100);
		saltandoD.agregarFrame(new FrameInfo(329,1388,22,28), 100);
		
		ArrayList<Animacion> animaciones = new ArrayList<Animacion>();
		animaciones.add(explosion);
		animaciones.add(saltandoI);
		animaciones.add(saltandoD);
		
		TortugaSaltarina tortuga = new TortugaSaltarina(animaciones);
		tortuga.setIndiceAnimacion(1);
		tortuga.setEscalaX(2f);
		tortuga.setEscalaY(2f);
		return tortuga;
	}
	
	public Tortuga getTortugaRojaCaminado()throws IOException{
		//Se prepara la explosion
		Animacion explosion = getExplosion();
		//Tortuga caminando a la izquierda
		Animacion caminandoI = new Animacion(spriteEnemigosMario);
		caminandoI.agregarFrame(new FrameInfo(297,0,16,27), 100);
		caminandoI.agregarFrame(new FrameInfo(257,1,16,26), 100);
		//Tortuga caminando a la derecha
		Animacion caminandoD = new Animacion(spriteEnemigosMario);
		caminandoD.agregarFrame(new FrameInfo(132,1348,16,27), 100);
		caminandoD.agregarFrame(new FrameInfo(172,1349,16,27), 100);
		
		ArrayList<Animacion> animaciones = new ArrayList<Animacion>();
		animaciones.add(explosion);
		animaciones.add(caminandoI);
		animaciones.add(caminandoD);
		
		Tortuga tortuga = new Tortuga(animaciones);
		tortuga.setIndiceAnimacion(1);
		tortuga.setEscalaX(2f);
		tortuga.setEscalaY(2f);
		return tortuga;		
	}
	
	public Jugador getGoku() throws IOException{		
		
		//Se prepara la animacion EXPLOSION
		Animacion explosion = getExplosion();	
		
		//goku esta parado hacia la derecha
		Animacion paradoD = new Animacion(spriteGoku);
		paradoD.agregarFrame(new FrameInfo(12,10,26,37), 100);
		paradoD.agregarFrame(new FrameInfo(48,11,28,36), 100);
		paradoD.agregarFrame(new FrameInfo(85,9,28,38), 100);
		paradoD.agregarFrame(new FrameInfo(122,9,27,38), 100);
		paradoD.agregarFrame(new FrameInfo(159,10,27,37), 100);
		paradoD.agregarFrame(new FrameInfo(195,11,27,36), 100);
		
		//Goku esta parado hacia la izquierda
		Animacion paradoI = new Animacion(spriteGoku);
		paradoI.agregarFrame(new FrameInfo(491,750,26,37), 100);
		paradoI.agregarFrame(new FrameInfo(454,751,28,36), 100);
		paradoI.agregarFrame(new FrameInfo(417,749,28,38), 100);
		paradoI.agregarFrame(new FrameInfo(381,749,27,38), 100);
		paradoI.agregarFrame(new FrameInfo(344,750,27,37), 100);
		paradoI.agregarFrame(new FrameInfo(308,751,27,36), 100);
		
		//Goku esta caminando a la derecha
		Animacion caminandoD = new Animacion(spriteGoku);
		caminandoD.agregarFrame(new FrameInfo(8,298,28,38), 50);
		caminandoD.agregarFrame(new FrameInfo(45,300,27,37), 50);
		caminandoD.agregarFrame(new FrameInfo(80,296,28,40), 50);
		caminandoD.agregarFrame(new FrameInfo(119,297,27,38), 50);
		caminandoD.agregarFrame(new FrameInfo(155,296,28,37), 50);
		caminandoD.agregarFrame(new FrameInfo(194,295,27,37), 50);
		caminandoD.agregarFrame(new FrameInfo(230,293,27,40), 50);
		caminandoD.agregarFrame(new FrameInfo(268,294,27,38), 50);
		
		//Goku esta caminando a la izquierda
		Animacion caminandoI = new Animacion(spriteGoku);
		caminandoI.agregarFrame(new FrameInfo(494,1038,28,38), 50);
		caminandoI.agregarFrame(new FrameInfo(457,1040,27,37), 50);
		caminandoI.agregarFrame(new FrameInfo(422,1036,28,40), 50);
		caminandoI.agregarFrame(new FrameInfo(384,1037,27,38), 50);
		caminandoI.agregarFrame(new FrameInfo(347,1036,28,37), 50);
		caminandoI.agregarFrame(new FrameInfo(309,1035,27,37), 50);
		caminandoI.agregarFrame(new FrameInfo(273,1033,27,40), 50);
		caminandoI.agregarFrame(new FrameInfo(235,1034,27,38), 50);
		
		//Goku girando el baculo hacia la derecha
		Animacion baculoGiraD = new Animacion(spriteGoku);
		baculoGiraD.agregarFrame(new FrameInfo(5,150,28,37), 50);
		baculoGiraD.agregarFrame(new FrameInfo(45,151,30,37), 50);
		baculoGiraD.agregarFrame(new FrameInfo(86,151,31,39), 50);
		baculoGiraD.agregarFrame(new FrameInfo(127,152,35,39),50);
		baculoGiraD.agregarFrame(new FrameInfo(172,152,35,36), 50);
		baculoGiraD.agregarFrame(new FrameInfo(125,153,35,38), 50);
		baculoGiraD.agregarFrame(new FrameInfo(257,151,27,37), 50);
		baculoGiraD.agregarFrame(new FrameInfo(293,150,28,37), 50);
		
		//Goku puyando con el baculo hacia la derecha
		Animacion baculoPuyaD = new Animacion(spriteGoku);
		baculoPuyaD.agregarFrame(new FrameInfo(17,254,32,33), 100);
		baculoPuyaD.agregarFrame(new FrameInfo(58,255,36,33), 100);
		baculoPuyaD.agregarFrame(new FrameInfo(102,251,72,37), 100);
		baculoPuyaD.agregarFrame(new FrameInfo(181,252,70,36), 100);
		
		//Goku puyando con el baculo hacia la izquierda
		Animacion baculoPuyaI = new Animacion(spriteGoku);
		baculoPuyaI.agregarFrame(new FrameInfo(481,994,32,33), 100);
		baculoPuyaI.agregarFrame(new FrameInfo(436,995,36,33), 100);
		baculoPuyaI.agregarFrame(new FrameInfo(356,991,72,37), 100);
		baculoPuyaI.agregarFrame(new FrameInfo(279,992,70,36), 100);
		
		//Goku girando el baculo hacia la izquierda
		Animacion baculoGiraI = new Animacion(spriteGoku);
		baculoGiraI.agregarFrame(new FrameInfo(497,890,28,37), 50);
		baculoGiraI.agregarFrame(new FrameInfo(455,891,30,36), 50);
		baculoGiraI.agregarFrame(new FrameInfo(413,891,31,39), 50);
		baculoGiraI.agregarFrame(new FrameInfo(368,892,35,39), 50);
		baculoGiraI.agregarFrame(new FrameInfo(323,892,35,36), 50);
		baculoGiraI.agregarFrame(new FrameInfo(280,892,35,39), 50);
		baculoGiraI.agregarFrame(new FrameInfo(246,891,27,37), 50);
		baculoGiraI.agregarFrame(new FrameInfo(209,890,28,37), 50);
		
		//Goku tirando trompon hacia la derecha
		Animacion tromponD = new Animacion(spriteGoku);
		tromponD.agregarFrame(new FrameInfo(5,56,27,35), 50);
		tromponD.agregarFrame(new FrameInfo(42,55,34,36), 50);
		tromponD.agregarFrame(new FrameInfo(84,56,33,35), 50);
		tromponD.agregarFrame(new FrameInfo(125,55,34,36), 50);
		tromponD.agregarFrame(new FrameInfo(165,55,33,36), 50);
		tromponD.agregarFrame(new FrameInfo(206,55,29,36), 50);
		tromponD.agregarFrame(new FrameInfo(243,53,30,36), 50);
		tromponD.agregarFrame(new FrameInfo(282,52,29,37), 50);
		tromponD.agregarFrame(new FrameInfo(320,53,28,37), 50);
		tromponD.agregarFrame(new FrameInfo(356,55,42,35), 50);
				
		//Goku tirando trompon hacia la izquierda
		Animacion tromponI = new Animacion(spriteGoku);
		tromponI.agregarFrame(new FrameInfo(498,795,27,36), 50);
		tromponI.agregarFrame(new FrameInfo(454,795,34,36), 50);
		tromponI.agregarFrame(new FrameInfo(413,795,33,36), 50);
		tromponI.agregarFrame(new FrameInfo(371,795,34,36), 50);
		tromponI.agregarFrame(new FrameInfo(332,795,33,36), 50);
		tromponI.agregarFrame(new FrameInfo(295,795,29,36), 50);
		tromponI.agregarFrame(new FrameInfo(257,793,30,36), 50);
		tromponI.agregarFrame(new FrameInfo(219,792,29,37), 50);
		tromponI.agregarFrame(new FrameInfo(182,792,28,38), 50);
		tromponI.agregarFrame(new FrameInfo(132,795,42,35), 50);
				
		//Goku tira patada derecha
		Animacion patadaD = new Animacion(spriteGoku);
		patadaD.agregarFrame(new FrameInfo(12,482,27,37), 50);
		patadaD.agregarFrame(new FrameInfo(45,479,30,39), 50);
		patadaD.agregarFrame(new FrameInfo(86,476,45,42), 50);
		patadaD.agregarFrame(new FrameInfo(137,477,38,41), 50);
				
		//Goku tira patada derecha
		Animacion patadaI = new Animacion(spriteGoku);
		patadaI.agregarFrame(new FrameInfo(491,1222,27,37), 50);
		patadaI.agregarFrame(new FrameInfo(455,1219,30,39), 50);
		patadaI.agregarFrame(new FrameInfo(399,1216,45,42), 50);
		patadaI.agregarFrame(new FrameInfo(355,1217,38,41), 50);
				
		//Se crea el array con las animaciones y se crea el personaje goku
		ArrayList<Animacion> animaciones = new ArrayList<Animacion>();
		animaciones.add(explosion);
		animaciones.add(paradoD);
		animaciones.add(paradoI);
		animaciones.add(caminandoD);
		animaciones.add(caminandoI);
		animaciones.add(baculoGiraD);
		animaciones.add(baculoPuyaD);
		animaciones.add(baculoPuyaI);
		animaciones.add(baculoGiraI);
		animaciones.add(tromponD);
		animaciones.add(tromponI);
		animaciones.add(patadaD);
		animaciones.add(patadaI);
		Jugador goku = new Jugador(animaciones);
		goku.setIndiceAnimacion(1);
		
		Animacion foto = new Animacion(spriteGoku);
		foto.agregarFrame(new FrameInfo(75,680,30,30), 1000);
		goku.setAnimacionFoto(foto);
		return goku;
		
	}
	
	private Animacion getExplosion(){
		//Se prepara la animacion EXPLOSION
		Animacion explosion = new Animacion(spriteExplosion);
		explosion.agregarFrame(new FrameInfo(14,17,36,33), 25);
		explosion.agregarFrame(new FrameInfo(76,15,41,38), 25);
		explosion.agregarFrame(new FrameInfo(137,14,46,41), 25);
		explosion.agregarFrame(new FrameInfo(200,13,49,44), 25);
		explosion.agregarFrame(new FrameInfo(266,13,52,45), 25);
		//*******************************************************
		explosion.agregarFrame(new FrameInfo(6,76,54,47), 25);
		explosion.agregarFrame(new FrameInfo(70,76,55,48), 25);
		explosion.agregarFrame(new FrameInfo(133,76,56,48), 25);
		explosion.agregarFrame(new FrameInfo(197,75,57,50), 25);
		explosion.agregarFrame(new FrameInfo(260,75,58,50), 25);
		//*******************************************************
		explosion.agregarFrame(new FrameInfo(4,139,58,50), 25);
		explosion.agregarFrame(new FrameInfo(68,139,58,50), 25);
		explosion.agregarFrame(new FrameInfo(131,139,59,50), 25);
		explosion.agregarFrame(new FrameInfo(195,139,59,50), 25);
		explosion.agregarFrame(new FrameInfo(259,139,59,50), 25);
		//*******************************************************
		explosion.agregarFrame(new FrameInfo(4,204,58,48), 50);
		explosion.agregarFrame(new FrameInfo(68,204,58,48), 50);
		explosion.agregarFrame(new FrameInfo(132,204,58,48), 50);
		explosion.agregarFrame(new FrameInfo(196,205,58,47), 50);
		explosion.agregarFrame(new FrameInfo(260,205,58,47), 50);
		//*******************************************************
		explosion.agregarFrame(new FrameInfo(5,269,57,46), 65);
		explosion.agregarFrame(new FrameInfo(70,271,54,45), 75);
		explosion.agregarFrame(new FrameInfo(138,271,50,38), 85);
		explosion.agregarFrame(new FrameInfo(205,272,45,34), 95);
		explosion.agregarFrame(new FrameInfo(289,279,16,13), 110);
		return explosion;
	}

}
