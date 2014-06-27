package Enemigos;

import java.util.ArrayList;
import superGoku.Animacion;

public class TortugaSaltarina extends Tortuga{

	public TortugaSaltarina(ArrayList<Animacion> animaciones) {
		super(animaciones);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void generarComportamiento() throws Exception {
		// TODO Auto-generated method stub
		saltar();
		if(getX()<=getxMin()){
			setX(getxMin());
			moverseDerecha();			
		}else if(getX()+getAncho()>=getxMax()){
			setxMax(getxMax());
			moverseIzquierda();			
		}
	}	

}
