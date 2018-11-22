package modelo;

import java.io.Serializable;

public class User implements Comparable<User>, Serializable{
	
	private String name;
	private int score;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public User(String name) {
		this.name = name;
		score = 0;
	}

	@Override
	public int compareTo(User user) {
		int result = 0;
		if(this.name.compareToIgnoreCase(user.getName())==0) {
			result = 0;
		}else if(this.name.compareToIgnoreCase(user.getName())<0) {
			result = -1;
		}else if(this.name.compareToIgnoreCase(user.getName())>0) {
			result = 1;
		}
		
		return result;
	}

}
