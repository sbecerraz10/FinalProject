package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exception.NicknameNotValid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Index;


public class SearchController implements Initializable{
	
	Index index = new Index();
	
    @FXML
    private Button B_Select;

    @FXML
    private Button B_Home;
    
    @FXML
    private ListView<String> ListView;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			index.registrerUser("Sierra");
		} catch (NicknameNotValid e) {
			e.printStackTrace();
		}
		
		for(int i = 0 ; i<index.getUsers().size();i++) {
    		ListView.getItems().add(index.getUsers().get(i).getName()+"-   Puntaje:  ("+index.getUsers().get(i).getScore()+")");
    		}
		
	}
	
	@FXML
	public void select(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/IndexWindow.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void backToMenu(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/MenuWindow.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
