package modelo;

public class Trap {
	
	private int damage;
	private int x;
	private int y;
	
	public Trap(int damage, int x, int y) {
		this.damage = damage;
		this.x = x;
		this.y = y;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
