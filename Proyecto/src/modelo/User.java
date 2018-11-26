package modelo;

import java.io.Serializable;

public class User implements Comparable<User>, Serializable{
	
	//Atributos
	
	private String name;
	private int score;
	private String bestGame;
	private String firstGame;
	private String lastGame;
	
	/**
     * Retorna el nombre del usuario
     * @return name el nombre del usuario
     */
	public String getName() {
		return name;
	}

	/**
     * Modifica el nombre del usuario
     * @param name el nuevo nombre del usuario
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * retorna el puntaje del usuario
     * @return score el puntaje del usuario
     */
	public int getScore() {
		return score;
	}

	/**
     * Modifica el puntaje del usuario
     * @param score el nuevo puntaje del usuario
     */
	public void setScore(int score) {
		this.score = score;
	}

	/**
     * Crea un objeto de tipo User
     */
	public User(String name, int score) {
		this.name = name;
		this.score = score;
		bestGame = "";
		lastGame = "";
		firstGame = "";
	}

	/**
     * Retorna el mejor juego de un Usuario
     * @return bestGame el mejor juego de un usuario
     */
	public String getBestGame() {
		return bestGame;
	}

	/**
     * Modifica el mejor juego de un usuario
     * @param bestGame el nuevo mejor juego del usuario
     */
	public void setBestGame(String bestGame) {
		this.bestGame = bestGame;
	}
	
	/**
     * Retorna el primer juego de un usuario
     * @return firstGame el primer juego de un usuario
     */
	public String getFirstGame() {
		return firstGame;
	}

	/**
     * Modifica el primer juego de un usuario
     * @param firstGame el nuevo primer juego del usuario
     */
	public void setFirstGame(String firstGame) {
		if(this.firstGame.equals("")) {
		this.firstGame = firstGame;
		}
	}

	/**
     * Retorna el ultimo juego de un usuario
     * @return lastGame el ultimo juego del usuario
     */
	public String getLastGame() {
		return lastGame;
	}

	/**
     * Modifica el ultimo juego de un usuario
     * @param lastGame el nuevo ultimo juego del usuario
     */
	public void setLastGame(String lastGame) {
		this.lastGame = lastGame;
	}

	/**
     * Calcula el mejor juego de un usuario con base el el tiempo
     * @param tiempo el tiempo del juego 
     */
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
