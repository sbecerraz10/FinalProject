package modelo;


/**
 * Character class
 * @author Sebastian Becerra, Juan Camilo Vargas, Cristian Sierra
 *  @version Nov-23-2018
 */
public class Character implements Comparable<Character>, InterfaceMovement {
	//Atributos
	
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
	

	/**
	 * Character Constructor
	 * @param life:life of the character
	 * @param power: power of the character
	 * @param nickname: nickname of the character
	 * @param image: route of the image of the character
	 */
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
		this.rootGemma = null;
	}
	
	/**
     * Retorna el atributo up del character
     * @return up el valor del atributo up
     */
	public boolean isUp() {
		return up;
	}
	
	/**
     * Modifica el valor up del character
     * @param up el nuevo valor del atributo up
     */
	public void setUp(boolean up) {
		this.up = up;
	}
	
	/**
     * Retorna el atributo down del character
     * @return down el valor del atributo down
     */
	public boolean isDown() {
		return down;
	}
	
	/**
     * Modifica el valor down del character
     * @param down el nuevo valor del atributo down
     */
	public void setDown(boolean down) {
		this.down = down;
	}
	
	/**
	 * Method move
	 * It is responsible for the movement of the character
	 */
	@Override
	public void move() {
		if(left) {
			if(posx>0) {
				this.posx -= MOVEMENT_RATING;				
			}else {
				this.posx = LIMITX;
			}
		}if(right) {
			if(posx<LIMITX) {
				this.posx += MOVEMENT_RATING;
			}else {
				this.posx = 0;
			}
		}if(up) {
			if(posy>0) {
				this.posy -= MOVEMENT_RATING;
			}
		}if(down) {
			if(posy+110<LIMITY) {
				this.posy += MOVEMENT_RATING;
			}
		}
		
	}

	/**
     * Retorna el atributo left del character
     * @return left el valor del atributo left
     */
	public boolean isLeft() {
		return left;
	}


	/**
     * Modifica el valor left del character
     * @param left el nuevo valor del atributo left
     */
	public void setLeft(boolean left) {
		this.left = left;
	}

	/**
     * Retorna el atributo right del character
     * @return right el valor del atributo right
     */
	public boolean isRight() {
		return right;
	}


	/**
     * Modifica el valor right del character
     * @param right el nuevo valor del atributo right
     */
	public void setRight(boolean right) {
		this.right = right;
	}

	/**
     * Retorna el atributo rootGemma del character
     * @return rootGemma la raiz del arbol de gemas
     */
	public Gemma getRootGemma() {
		return rootGemma;
	}

	/**
     * Modifica la raiz del arbol de gemas
     * @param rootGemma la nueva raiz deseada
     */
	public void setRootGemma(Gemma rootGemma) {
		this.rootGemma = rootGemma;
	}
	
	/**
     * Retorna la posicion en x del character
     * @return posx la posicion en x de la bomba
     */
	public int getPosx() {
		return posx;
	}

	/**
     * Modifica la posicion en x del character
     * @param posx la nueva posicion en x del character
     */
	public void setPosx(int posx) {
		this.posx = posx;
	}

	/**
     * Retorna la posicion en y del character
     * @return posy la posicion en y de la bomba
     */
	public int getPosy() {
		return posy;
	}

	/**
     * Modifica la posicion en y del character
     * @param posy la nueva posicion en y del character
     */
	public void setPosy(int posy) {
		this.posy = posy;
	}

	/**
     * Retorna el atributo life del character
     * @return life el valor del atributo life
     */
	public int getLife() {
		return life;
	}

	/**
     * Modifica el valor del atributo life del character
     * @param life el nuevo valor para el atributo life
     */
	public void setLife(int life) {
		this.life = life;
	}

	/**
     * Retorna el atributo nickname del character
     * @return nickname el nickname del character
     */
	public String getNickname() {
		return nickname;
	}

	/**
     * Modifica el valor del atributo nickname 
     * @param nickname nuevo nickname del character
     */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
     * Retorna el atributo image del character
     * @return image la imagen del charcter
     */
	public String getImage() {
		return image;
	}

	/**
     * Modifica la imagen del character
     * @param image la nueva imagen del character
     */
	public void setImage(String image) {
		this.image = image;
	}

	/**
     * Retorna el siguiente character de la lista enlazada
     * @return next el siguiente character de la lista enlazada
     */
	public Character getNext() {
		return next;
	}

	/**
     * Modifica el siguiente character de la lista enlazada
     * @param next el nuevo siguiente character
     */
	public void setNext(Character next) {
		this.next = next;
	}

	/**
     * Retorna el anterior character de la lista enlazada
     * @return previous el anterior character de la lista enlazada
     */
	public Character getPrevious() {
		return previous;
	}

	/**
     * Modifica el anterior character de la lista enlazada
     * @param previous el nuevo anterior character
     */
	public void setPrevious(Character previous) {
		this.previous = previous;
	}

	/**
     * Retorna el atributo power del character
     * @return power el poder del character
     */
	public int getPower() {
		return power;
	}

	/**
     * Modifica el valor del poder del character
     * @param power nuevo poder del character
     */
	public void setPower(int power) {
		this.power = power;
	}

	
	
	@Override
	public int compareTo(Character character2) {
		return this.getNickname().compareToIgnoreCase(character2.getNickname());
	
	}
	
	/**
     * Verifica que la trampa o la gema este en el rango del character y sea posible cogerla
     * @param a posicion en x de la gemma o trampa 
     * @param b posicion en y de la gemma o trampa 
     * @return cth valor de verdad que indica si esta en rango o no 
     */
	public boolean take(int a,int b){
		boolean cth = false;
		
		if(a<posx+100 && a>posx && b>posy && b<posy+110){
			cth = true;
		}
		return cth;
	}
	
	public void catchGemma(Gemma g) {
		if(rootGemma == null) {
			rootGemma = g;
		}else{		
			rootGemma.add(g);
		}
			
	}
	
	/**
     * Retorna el peso del arbol de gemas
     * @return el peso del arbol de gemas
     */
	public int getWeight() {
		if(rootGemma == null) {
			return 0;
		}else {
			return this.rootGemma.getWeight();
		}
	}
	

	/**
	 * El metodo busca un character por su nombre
	 * @param nombre nombre del Character buscado
	 * @return toReturn El Character con el nombre buscado
	 */
	public Character searchCharacter(String nombre) {
		Character toReturn = null;
		
		if(this.nickname.equalsIgnoreCase(nombre)) {
			toReturn =  this;
		}else {
			if(this.next!=null)
			toReturn = this.next.searchCharacter(nombre);
		}
		return toReturn;
	}
	
	@Override
	public String toString() {
		return "Character [life=" + life + ", power=" + power + ", nickname=" + nickname + "]";
	}
}