package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Field class
 * @author Sebastian Becerra, Cristian Sierra, Juan Camilo Vargas
 * @version Nov-23-2018
 */

public class Field implements Comparable<Field>{
	
	
	//Atributos
	private String name;
	
	private Field next;
	
	private Field previous;
	
	private Gemma rootGemma;
	
	private String image;
	
	private ArrayList<Trap> traps;
		
	private Chronometer chronometer;

	
	/**
	 * Field Constructor
	 * @param name : name of the field
	 * @param image: route of the image 
	 */
	public Field(String name,String image) {
		this.name = name;
		this.image = image;
		traps = new ArrayList<Trap>();
		generateTraps();
		sortTrapsByDamage();
		chronometer = new Chronometer();
	}
	
	/**
     * Retorna el atributo name  
     * @return name el atributo name  
     */
	public String getName() {
		return name;
	}

	/**
     * Modifica el atributo name 
     * @param name el nuevo atributo name  
     */ 
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Retorna el siguiente field de la lista enlazada de fields 
     * @return next el siguiente field de la lista enlazada de fields 
     */
	public Field getNext() {
		return next;
	}

	/**
     * Modifica el siguiente campo de la lista enlazada 
     * @param next el nuevo siguiente field  
     */
	public void setNext(Field next) {
		this.next = next;
	}

	/**
     * Retorna el anteior field de la lista enlazada de fields 
     * @return previous el anterior field de la lista enlazada de fields 
     */
	public Field getPrevious() {
		return previous;
	}

	/**
     * Modifica el anterior campo de la lista enlazada 
     * @param previous el nuevo anterior field  
     */
	public void setPrevious(Field previous) {
		this.previous = previous;
	}

	/**
     * Retorna el valor de la roootGemma  
     * @return rootGemma la gemma cabeza del arbol de gemmas  
     */
	public Gemma getRootGemma() {
		return rootGemma;
	}

	/**
     * Modifica la raiz del arbol de gemmas
     * @param rootGemma la nueva raiz del arbol   
     */
	public void setRootGemma(Gemma rootGemma) {
		this.rootGemma = rootGemma;
	}

	/**
     * Retorna la imagen del field
     * @return image la imagen del field
     */
	public String getImage() {
		return image;
	}
	
	/**
     * Modifica la imagen del field 
     * @param image la nueva imagen del field  
     */
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
     * Retorna el cronometro del field
     * @return chronometer el chronometro del field  
     */
	public Chronometer getChronometer() {
		return chronometer;
	}

	/**
     * Modifica el cronometro del field
     * @param chronometer el nuevo chronometer del field  
     */
	public void setChronometer(Chronometer chronometer) {
		this.chronometer = chronometer;
	}

	/**
     * Retorna una lista de Trampas 
     * @return traps la lista de trampas del field  
     */
	public ArrayList<Trap> getTraps() {
		return traps;
	}

	/**
     * Modifica la lista de trampas del field
     * @param traps la nueva lista de trampas
     */
	public void setTraps(ArrayList<Trap> traps) {
		this.traps = traps;
	}

	/**
     * Genera un numero aleatorio de trampas para el field y agrega trampas a la lista de trampas
     */
	public void generateTraps() {
		int numberOfBombs = (int)(Math.random()*7) + 2;
		
		for(int i=0; i<numberOfBombs; i++) {
			int damage = (int)(Math.random() * 200 )+ 30;
			int x = (int)( Math.random() * 500) + 3;
			int y = (int)( Math.random() * -200) - 10;
			int radius = (int) (Math.random() * 20) +1;
			Bomb bomb = new Bomb(damage, x, y, radius);
			traps.add(bomb);
		}
		
		
		int numberOfElectricityTraps = (int)(Math.random()*7) + 2 ;
		
		for(int i=0; i<numberOfElectricityTraps; i++) {
			int damage = (int)(Math.random() * 200 )+ 30;
			int x = (int)( Math.random() * 500) + 3;
			int y = (int)( Math.random() * -200) - 10;
			int numGemma = (int) (Math.random() * 3) +1;
			Electricity electricity = new Electricity(damage, x, y, numGemma);
			traps.add(electricity);
		}
		
	}
	
	/**
	 * Se comparan dos fields
	 * @param field el campo con el que se va comparar
	 */
	@Override
	public int compareTo(Field field) {
		int toReturn = 0;
		if(this.name.compareToIgnoreCase(field.getName())<0) {
			toReturn = -1;
		}else if(this.name.compareToIgnoreCase(field.getName())>0) {
			toReturn = 1;
		}
		return toReturn;
	}
	
	/**
     * Agrega una gemma al arbol de gemmas
     * @param n el dato n que tendra la nueva gemma  
     */
	public void addGemma(int n){
		
		Gemma nueva = new Gemma(n);
		if(rootGemma!=null) {
			rootGemma.add(nueva);
		}else {
			rootGemma = nueva;
		}
		
	}
	
	/**
     * Organiza la lista de trampas por su daño 
     */
	public void sortTrapsByDamage() {
		for(int i= 0; i<traps.size();i++) {
			int cual = i;
			Trap greater = traps.get(i);
			for(int j=i+1;j<traps.size();j++) {
				if(greater.compareTo(traps.get(j)) < 0) {
					greater = traps.get(j);
					cual = j;
				}
			}
			
			Trap temp = traps.get(i);
			traps.set(i, greater);
			traps.set(cual, temp);
		}
		
	}
	
	/**
     * Retorna el arbol binario de gemma en forma de lista
     * @return la lista de gemmas  
     */
	public ArrayList<Gemma> showList( ){
		
		ArrayList<Gemma> out = new ArrayList<>();
        if(rootGemma!=null)
		rootGemma.showInList(out);    
            return out;  
    }
	
	/**
     * Busca una gemma de acuerdo a su dato power 
     * @param power el dato de la Gemma buscada
     * @return la gemma encontrada con el dato power ingresado 
     */
	public Gemma searchGemma(int power) {
		if(this.rootGemma == null) {
			return null;
		}else {
			return rootGemma.searchGemma(power);
		}
	}
	
	/**
     * Busca una gemma de acuerdo a nombre 
     * @param nombre el nombre del field
     * @return toReturn el campo con el nombre ingresado
     */
	public Field searchField(String name) {
		Field toReturn = null;
		if(this.name.equals(name)) {
			toReturn =  this;
		}else {
			toReturn = this.next.searchField(name);
		}
		return toReturn;
	}
	
	
	public void loadGems() {
		FileWriter fileout;
		try {
			fileout = new FileWriter("files/gem.txt");
			BufferedWriter buff =  new BufferedWriter(fileout);
			int i = 0;
			while(i<showList().size()){
				buff.write("Gemma "+i +" "+ showList().get(i).getPower());
				buff.newLine();	
				i++;
			}
			
			buff.flush();
			fileout.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
