package superGoku;

/*
 * Esta es una clase abstracta con metodos estaticos creada con el fin
 * de manejar la física del juego, por el momento solo maneja desplazamiento
 * horizontal con aceleracion.
 * Y desplazamiento vertical con aceleracion gravitatoria.
 */

public abstract class Cinematica {	

	public static float getDesplazamientoX(float vx,float t){
		return vx*t;
	}
	
	public static float getVelocidadX(float v0,float aceleracion,float tiempo){
		return v0 + aceleracion*tiempo;
	}
	
	public static float getDesplazamientoX(float vx, float aceleracion, float tiempo){
		return (vx*tiempo + .5f*aceleracion*tiempo*tiempo);
	}
	
	public static float getDesplazamientoY(float velocidadY,float aceleracionGravitatoria,float t){
		return (velocidadY*t + .5f*aceleracionGravitatoria*t*t);		
	}
	
	public static float getVelocidadY(float v0,float aceleracionGravitatoria,float tiempo){
		return v0 + aceleracionGravitatoria*tiempo;
	}
	
}
