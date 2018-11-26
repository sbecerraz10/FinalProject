package modelo;

public class Trap implements InterfaceMovement,Comparable<Trap>{
	
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

	@Override
	public void move() {
		if(this.y > LIMITY) {
			this.y = (int) (Math.random() *-40 ) - 10;
			this.x = (int)(Math.random() * LIMITX) + 5;
		}
		
		this.y += MOVEMENT_RATING_TRAP;
	}

	@Override
	public int compareTo(Trap trap2) {
		if(this.damage < trap2.damage) {
			return -1;
		}else if(this.damage > trap2.damage) {
			return 1;
		}else {
			return 0;
		}
	}
	
}
