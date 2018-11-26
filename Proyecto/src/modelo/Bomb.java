package modelo;

public class Bomb extends Trap{

	private int radius;
	
	/**
     * Crea una bomba 
     * @param damage daño que hace la bomba
     * @param x posicion x de la bomba
     * @param y posicion y de la bomba
     * @param radius radio de la bomba
     */
	
	public Bomb(int damage, int x, int y, int radius) {
		super(damage,x,y);
		this.radius = radius;
	}
	
	/**
     * Retorna el atributo radio de la bomba
     * @return radius el radio de la bomba
     */
	
	public int getRadius() {
		return radius;
	}
	
	/**
     * Modifica el valor del radio de la bomba
     * @param radius radio de la bomba
     */
	public void setRadius(int radius) {
		this.radius = radius;
	}
}
