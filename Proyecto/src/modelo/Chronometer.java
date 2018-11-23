package modelo;

public class Chronometer {

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
		
	public int getSecondMinute() {
		return secondMinute;
	}

	public void setSecondMinute(int secondMinute) {
		this.secondMinute = secondMinute;
	}

	public int getFirstMinute() {
		return firstMinute;
	}

	public void setFirstMinute(int firstMinute) {
		this.firstMinute = firstMinute;
	}

	public int getSecondSeconnd() {
		return secondSeconnd;
	}

	public void setSecondSeconnd(int secondSeconnd) {
		this.secondSeconnd = secondSeconnd;
	}

	public int getFirstSecond() {
		return firstSecond;
	}

	public void setFirstSecond(int firstSecond) {
		this.firstSecond = firstSecond;
	}

	public String getTime() {
		return secondMinute+""+firstMinute+":"+secondSeconnd+""+firstSecond;
	}
	
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
