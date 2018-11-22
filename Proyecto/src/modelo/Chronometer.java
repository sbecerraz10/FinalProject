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
