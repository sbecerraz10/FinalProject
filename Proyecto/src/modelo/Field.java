package modelo;

import java.util.ArrayList;

public class Field implements Comparable<Field>{
	
	private String name;
	
	private Field next;
	
	private Field previous;
	
	private Gemma rootGemma;
	
	private String image;
	
	private ArrayList<Bomb> bombs;
	
	private ArrayList<Electricity> electricity;

	public Field(String name,String image) {
		this.name = name;
		this.image = image;
		bombs = new ArrayList<Bomb>();
		electricity = new ArrayList<Electricity>();
		rootGemma = new Gemma(0); 
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

	public ArrayList<Bomb> getBombs() {
		return bombs;
	}
	
	public ArrayList<Electricity> getElectricity() {
		return electricity;
	}

	public void setBombs(ArrayList<Bomb> bombs) {
		this.bombs = bombs;
	}

	public void setTraps(ArrayList<Electricity> electricity) {
		this.electricity = electricity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public void generateTraps() {
		
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
