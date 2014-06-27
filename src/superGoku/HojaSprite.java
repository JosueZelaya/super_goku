package superGoku;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class HojaSprite {
	
	private BufferedImage hojaSprite;
	
	public HojaSprite(Image hoja){
		hojaSprite = (BufferedImage) hoja;
	}
	
	
	public BufferedImage getSectorImagen(int x,int y,int ancho,int alto){
		return hojaSprite.getSubimage(x, y, ancho, alto);
	}	

}
