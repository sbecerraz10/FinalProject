package modelo;

import java.util.ArrayList;


public class Gemma implements InterfaceMovement{

	//Atributos
	
	private Gemma left;
	
	private Gemma rigth;
	
	private int power;
	
	private int posy;
	
	private int posx;
	
	public Gemma(int power) {
		this.power = power;
	}
	/**
	 * Se moficifa el atribuyo y el cual genera el movimiento 
	 */
	@Override
	public void move() {
		if(this.posy > LIMITY) {
			this.posy = (int) (Math.random()*-200)-10;
			int random = (int) (Math.random()*LIMITX)+5;
			this.posx = random;
		}
		this.posy += MOVEMENT_RATING_GEMMA;
	}
	
	/**
     * Retorna la posicion en y de la gemma
     * @return posy la posicion en y de la gemma
     */
	public int getPosy() {
		return posy;
	}

	/**
     * Modifica la posicion en y de la gemma
     * @param posy la nueva posicion en y de la gemma
     */
	public void setPosy(int posy) {
		this.posy = posy;
	}
	
	/**
     * Retorna la posicion en x de la gemma
     * @return posx la posicion en x de la gemma
     */
	public int getPosx() {
		return posx;
	}

	/**
     * Retorna la gemma izquierda
     * @return left la gemma izquierda
     */
	public Gemma getLeft() {
		return left;
	}

	/**
     * Modifica la gemma izquierda
     * @param left la nueva gemma izquierda
     */
	public void setLeft(Gemma left) {
		this.left = left;
	}

	/**
     * Retorna la gemma derecha
     * @return right la gemma derecha
     */
	public Gemma getRigth() {
		return rigth;
	}

	/**
     * Modifica la gemma derecha
     * @param right la nueva gemma derecha
     */
	public void setRigth(Gemma rigth) {
		this.rigth = rigth;
	}

	/**
     * Retorna el poder de la gemma
     * @return power el poder de la gemma
     */
	public int getPower() {
		return power;
	}

	/**
     * Modifica el poder de la gemma
     * @param power el nuevo poder de la gemma
     */
	public void setPower(int power) {
		this.power = power;
	}
	
	
	/**
     * Agrega una gemma al arbol de gemmas de manera ordenada
     * @param nueva la gemma a agregar
     */
    public void add(Gemma nueva){
    	
    	if(this.getPower()<nueva.getPower()) {
    		if(rigth == null) {
    			rigth = nueva;
    		}else {
    			rigth.add(nueva);
    		}
    	}else if(this.power > nueva.power){
    		if(left == null) {
    			left = nueva;
    		}else {
    			left.add(nueva);
    		}
    	}
        
    }
    
	/**
     * Busca una gemma en el arbol por su poder
     * @param power el poder de la gemma a buscar
     * @return la gemma con el poder buscado
     */
    public Gemma searchGemma(int power) {
            if( this.power == power )
                return this;
            else if( this.power > power )
                return ( left == null ) ? null : left.searchGemma( power );
            else
                return ( rigth == null ) ? null : rigth.searchGemma( power );
        
    }
    
	/**
     * Retorna el peso del arbol de gemmas
     * @return el peso del arbol de gemmas
     */
    public int getWeight( )
    {
        int p1 = ( left == null ) ? 0 : left.getWeight( );
        int p2 = ( rigth == null ) ? 0 : rigth.getWeight( );
        return 1 + p1 + p2;
    }
     
	/**
     * Muestra las gemmas en una lista
     * @param list la lista a la que se van a pasar las gemas
     */
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
