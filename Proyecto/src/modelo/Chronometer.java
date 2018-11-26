package modelo;

public class Chronometer {
	//Atributos
	
	private int secondMinute;
	private int firstMinute;
	private int secondSeconnd;
	private int firstSecond;
	
	public Chronometer() {
		secondMinute=0;
		firstMinute=0;
		secondSeconnd=0;
		firstSecond=0;
	}
		
	/**
     * Retorna el segundo numero de los minutos
     * @return secondMinute el segundo numero de los minutos
     */
	public int getSecondMinute() {
		return secondMinute;
	}

	public void setSecondMinute(int secondMinute) {
		this.secondMinute = secondMinute;
	}

	/**
     * Retorna el primer numero de los minutos
     * @return firstdMinute el primer numero de los minutos
     */
	public int getFirstMinute() {
		return firstMinute;
	}

	public void setFirstMinute(int firstMinute) {
		this.firstMinute = firstMinute;
	}
	
	/**
     * Retorna el segundo numero de los segundos
     * @return secondSeconnd el segundo numero de los segundos
     */

	public int getSecondSeconnd() {
		return secondSeconnd;
	}

	public void setSecondSeconnd(int secondSeconnd) {
		this.secondSeconnd = secondSeconnd;
	}

	/**
     * Retorna el primer numero de los segundos
     * @return firstSecond el primer numero de los segundos
     */
	public int getFirstSecond() {
		return firstSecond;
	}

	public void setFirstSecond(int firstSecond) {
		this.firstSecond = firstSecond;
	}

	/**
     * Retorna el tiempo del cronometro
     * @return el tiempo del cronometro
     */
	public String getTime() {
		return secondMinute+""+firstMinute+":"+secondSeconnd+""+firstSecond;
	}
	
	/**
     * Inicializa el cronometro 
     */
	public void initiate() {
		if(firstSecond<=8) {
			firstSecond+=1;
		}else {
			firstSecond=0;
			if(secondSeconnd<5) {
				secondSeconnd+=1;
			}else {
				secondSeconnd=0;
				if(firstMinute<=8) {
					firstMinute+=1;
				}else {
					firstMinute=0;
					if(secondMinute<5) {
						secondMinute+=1;
					}
				}
			}
		}		
	}
}
