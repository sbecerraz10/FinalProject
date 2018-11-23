package modelo;

import java.util.ArrayList;

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
		rootGemma = new Gemma(0);
		chronometer = new Chronometer();
		generateTraps();
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
		int numberOfBombs = 0;
		numberOfBombs = (int)(Math.random()*10) + 4;
		
		for(int i=0; i<numberOfBombs; i++) {
			int damage = (int)(Math.random() * 200 )+ 30;
			int x = (int)( Math.random() * 500) + 10;
			int y = (int)( Math.random() * -40) - 10;
			int radius = (int) (Math.random() * 20) +1;
			Bomb bomb = new Bomb(damage, x, y, radius);
			traps.add(bomb);
		}
		
		int numberOfElectricityTraps = 0;
		numberOfElectricityTraps = (int)(Math.random()*10) + 4;
		
		for(int i=0; i<numberOfElectricityTraps; i++) {
			int damage = (int)(Math.random() * 200 )+ 30;
			int x = (int)( Math.random() * 500) + 10;
			int y = (int)( Math.random() * -40) - 10;
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
//	public Field searchField(String id) {
//		Field toReturn = null;
//		if(this.image.equals(id)) {
//			toReturn =  this;
//		}else {
//			toReturn = this.next.searchField(id);
//		}
//		return toReturn;
//	}
}
