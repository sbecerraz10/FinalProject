package modelo;

import java.util.ArrayList;

public class Gemma implements InterfaceMovement{

	private Gemma left;
	
	private Gemma rigth;
	
	private int power;
	
	private int posy;
	
	private int posx;
	
	
	@Override
	public void move() {
		if(this.posy > LIMITY) {
			this.posy = 0;
			int random = (int) (Math.random()*LIMITX)+5;
			this.posx = random;
		}
		
		this.posy += MOVEMENT_RATING_GEMMA;
	}
	
	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}
	
	public int getPosx() {
		return posx;
	}

	public Gemma(int power) {
		this.power = power;
	}



	public Gemma getLeft() {
		return left;
	}



	public void setLeft(Gemma left) {
		this.left = left;
	}



	public Gemma getRigth() {
		return rigth;
	}



	public void setRigth(Gemma rigth) {
		this.rigth = rigth;
	}



	public int getPower() {
		return power;
	}



	public void setPower(int power) {
		this.power = power;
	}
	
	
    public void add(Gemma nueva){
    	
    	if(this.getPower()<nueva.getPower()) {
    		if(rigth == null) {
    			rigth = nueva;
    		}else {
    			rigth.add(nueva);
    		}
    	}else {
    		if(left == null) {
    			left = nueva;
    		}else {
    			left.add(nueva);
    		}
    	}
        
    }
    
    public void showInList(ArrayList<Gemma> list) {
    	if( left != null ) {
            left.showInList(list);
    	}
    	
        list.add( this );
        
        if( rigth != null ) {
            rigth.showInList(list);
        }
    }

}
