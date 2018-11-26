package modelo;

public class Trap implements InterfaceMovement,Comparable<Trap>{
	
	//Atributos
	
	private int damage;
	private int x;
	private int y;
	 
	public Trap(int damage, int x, int y) {
		this.damage = damage;
		this.x = x;
		this.y = y;
	}

	/**
     * Retorna el daño que genera una trampa
     * @return damage el daño que genera la trampa
     */
	public int getDamage() {
		return damage;
	}

	/**
     *	Modifica el daño que genera la trampa 
     * @param damage el nuevo daño que genera la trampa
     */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
     * Retorna la posicion en x de la trampa
     * @return x la posicion en x de la trampa
     */
	public int getX() {
		return x;
	}

	/**
     * Modifica la posicion en x de la trampa
     * @param x la nueva posicion en x de la trampa
     */
	public void setX(int x) {
		this.x = x;
	}

	/**
     * Retorna la posicion en y de la trampa
     * @return y la posicion en y de la trampa
     */
	public int getY() {
		return y;
	}

	/**
     * Modifica la posicion en y de la trampa
     * @param y la nueva posicion en y de la trampa
     */
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
