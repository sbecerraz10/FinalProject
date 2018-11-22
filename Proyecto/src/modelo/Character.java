package modelo;

public class Character implements Comparable<Character>, InterfaceMovement {
	//
	private int life;
	
	private int power;
	
	private String nickname;
	
	private String image;
	
	private Character next;
	
	private Character previous;
	
	private Gemma rootGemma;
	

	private int posx;
	 
	private int posy;
	
	private boolean left; 
	private boolean right; 
	private boolean up; 
	private boolean down; 
	
	public boolean isUp() {
		return up;
	}


	public void setUp(boolean up) {
		this.up = up;
	}


	public boolean isDown() {
		return down;
	}


	public void setDown(boolean down) {
		this.down = down;
	}


	public Character(int life,int power, String nickname, String image) {
		super();
		this.life = life;
		this.power = power;
		this.nickname = nickname;
		this.image = image;
		this.next = null;
		this.previous = null;
		this.posy = 243;
		this.posx = 346;
	}

	@Override
	public void move() {
		if(this.left) {
			if(posx>0) {
				this.posx -= MOVEMENT_RATING;				
			}else {
				this.posx = LIMITX;
			}
		}if(this.right) {
			if(posx<LIMITX) {
				this.posx += MOVEMENT_RATING;
			}else {
				this.posx = 0;
			}
		}if(this.up) {
			if(posy>0) {
				this.posy -= MOVEMENT_RATING;
			}
		}if(this.down) {
			if(posy+110<LIMITY) {
				this.posy += MOVEMENT_RATING;
			}
		}
		
	}

	
	public boolean isLeft() {
		return left;
	}


	public void setLeft(boolean left) {
		this.left = left;
	}


	public boolean isRight() {
		return right;
	}


	public void setRight(boolean right) {
		this.right = right;
	}


	public Gemma getRootGemma() {
		return rootGemma;
	}

	public void setRootGemma(Gemma rootGemma) {
		this.rootGemma = rootGemma;
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Character getNext() {
		return next;
	}

	public void setNext(Character next) {
		this.next = next;
	}

	public Character getPrevious() {
		return previous;
	}

	public void setPrevious(Character previous) {
		this.previous = previous;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	@Override
	public int compareTo(Character character2) {
		int toReturn = 0;
		
		if(this.power < character2.getPower() ) {
			toReturn = -1;
		}else if(this.power > character2.getPower()) {
			toReturn = 1;
		}
		
		return toReturn;
	
	}
	
	public boolean catchGema(int xG,int yG, int gH,int gW,int cH,int cW) {
		boolean cth = false;
		if((this.posx+(cW/2))== (xG-(gW/2))||(this.posy+(cH/2))== (yG-(gH/2))){
			cth = true;
		}if((this.posx-(cW/2))== (xG+(gW/2))||(this.posy-(cH/2))== (yG+(gH/2))) {
			cth = true;
		}
		return cth;
	}
	

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Character searchCharacter(String id) {
		Character toReturn = null;
		if(this.image.equals(id)) {
			toReturn =  this;
		}else {
			toReturn = this.next.searchCharacter(id);
		}
		return toReturn;
	}
	

}