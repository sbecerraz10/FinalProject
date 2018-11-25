package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FinController implements Initializable{
	
	private String end;
	
	private String score;

    @FXML
    private Label Fin;

    @FXML
    private Label Puntaje;
    
    public void receiveData(String end, String score) {
    	this.end = end;
    	this.score = score;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Fin.setText(end);
		Puntaje.setText(score);
		
	}
    
    

}


