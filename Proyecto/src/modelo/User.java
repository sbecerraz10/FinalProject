package modelo;

import java.io.Serializable;

public class User implements Comparable<User>, Serializable{
	
	private String name;
	private int score;
	private String bestGame;
	private String firstGame;
	private String lastGame;
	
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

	public User(String name, int score) {
		this.name = name;
		this.score = score;
		bestGame = "";
		lastGame = "";
		firstGame = "";
	}

	public String getBestGame() {
		return bestGame;
	}

	public void setBestGame(String bestGame) {
		this.bestGame = bestGame;
	}
	
	public String getFirstGame() {
		return firstGame;
	}

	public void setFirstGame(String firstGame) {
		this.firstGame = firstGame;
	}

	public String getLastGame() {
		return lastGame;
	}

	public void setLastGame(String lastGame) {
		this.lastGame = lastGame;
	}

	public void bestGame(String time) {
		if(bestGame.equals("")){
			bestGame = time;
		}else {
			String[] ntime = time.split("");
			int fSecond = Integer.parseInt(ntime[4]);
			int sSecond = Integer.parseInt(ntime[3]);
			int fMinute = Integer.parseInt(ntime[1]);
			int sMinute = Integer.parseInt(ntime[0]);
			String[] vtime = bestGame.split("");
			int vfSecond = Integer.parseInt(vtime[4]);
			int vsSecond = Integer.parseInt(vtime[3]);
			int vfMinute = Integer.parseInt(vtime[1]);
			int vsMinute = Integer.parseInt(vtime[0]);
			if(vsMinute>sMinute) {
				bestGame = time;
			}else if((vsMinute<=sMinute)&&(vfMinute>fMinute)) {
				bestGame = time;
			}else if((vsMinute<=sMinute)&&(vfMinute<=fMinute)&&(vsSecond>sSecond)) {
				bestGame = time;
			}else if((vsMinute<=sMinute)&&(vfMinute<=fMinute)&&(vsSecond<=sSecond)&&(vfSecond>fSecond)) {
				bestGame = time;
			}
		}
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
	
	@Override
	public String toString() {
		return "User [name=" + name + "- score=" + score + "]";
	}
	

}
