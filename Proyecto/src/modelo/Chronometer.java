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
     * @return firstMinute el primer numero de los minutos
     */
	public int getFirstMinute() {
		return firstMinute;
	}

	/**
     * Modifica el primer numero de los minutos del cronometro
     * @param firstMinute el nuevo primer numero de los minutos
     */
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

	/**
     * Modifica el segundo numero de los segundos del cronometro
     * @param secondSeconnd el nuevo segundo numero de los segundos
     */
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

	/**
     * Modifica el primer numero de los segundos del cronometro
     * @param firstSecond el nuevo primer numero de los segundos
     */
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
	
	public int convertToSeconds() {
		int salida = 0;
		String mins = secondMinute+""+firstMinute;
		int minutes = Integer.parseInt(mins);
		String secs = secondSeconnd+""+firstSecond;
		int seconnds = Integer.parseInt(secs);
		salida = (minutes*60)+seconnds;
		return salida;
	}
}
