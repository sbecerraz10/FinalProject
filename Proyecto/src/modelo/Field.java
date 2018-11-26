package modelo;

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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Field getNext() {
		return next;
	}

	public void setNext(Field next) {
		this.next = next;
	}

	public Field getPrevious() {
		return previous;
	}

	public void setPrevious(Field previous) {
		this.previous = previous;
	}

	public Gemma getRootGemma() {
		return rootGemma;
	}

	public void setRootGemma(Gemma rootGemma) {
		this.rootGemma = rootGemma;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public Chronometer getChronometer() {
		return chronometer;
	}

	public void setChronometer(Chronometer chronometer) {
		this.chronometer = chronometer;
	}

	public ArrayList<Trap> getTraps() {
		return traps;
	}

	public void setTraps(ArrayList<Trap> traps) {
		this.traps = traps;
	}

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
	
	public void addGemma(int n){
		
		Gemma nueva = new Gemma(n);
		if(rootGemma!=null) {
			rootGemma.add(nueva);
		}else {
			rootGemma = nueva;
		}
		
	}
	
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
	
	
	public ArrayList<Gemma> showList( ){
		
		ArrayList<Gemma> out = new ArrayList<>();
        if(rootGemma!=null)
		rootGemma.showInList(out);    
            return out;  
    }
	
	public Gemma searchGemma(int power) {
		if(this.rootGemma == null) {
			return null;
		}else {
			return rootGemma.searchGemma(power);
		}
	}
	
	public Field searchField(String name) {
		Field toReturn = null;
		if(this.name.equals(name)) {
			toReturn =  this;
		}else {
			toReturn = this.next.searchField(name);
		}
		return toReturn;
	}
}
