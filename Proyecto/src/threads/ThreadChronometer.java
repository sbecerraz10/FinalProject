package threads;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modelo.Field;

public class ThreadChronometer extends Thread{
		
	private Field field;
	private Text text;
	
	public ThreadChronometer(Field field,Text text) {
		this.field= field;
		this.text = text;
	}
	
	@Override
	public void run() {
		while (true) {		
			field.getChronometer().initiate();
			System.out.println(field.getChronometer().getTime());
			String time = field.getChronometer().getTime();
			text.setText(time);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}									
		}					
	}			
}