package modelo;

public class Bomb extends Trap{

	private int radius;
	
	public Bomb(int damage, int x, int y, int radius) {
		super(damage,x,y);
		this.radius = radius;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
}
