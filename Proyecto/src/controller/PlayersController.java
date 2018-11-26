package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PlayersController implements Initializable{
	
	@FXML
    private ListView<String> listUsers;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		if(Main.getIndexModel().getUsers().size()!=0){
//			String[] users = Main.getIndexModel().writeUsers().split(",");
//			for(int i = 0; i<users.length;i++) {
//				listUsers.getItems().add(users[i]);
//			}
//		}
	}
	
	@FXML
	public void ordenar1() {
		listUsers.getItems().clear();
		if(Main.getIndexModel().getUsers().size()!=0){
			String[] users = Main.getIndexModel().writeUsers().split(",");
			for(int i = 0; i<users.length;i++) {
				listUsers.getItems().add(users[i]);
			}
		}
	}
	@FXML
	public void ordenar2() {
		listUsers.getItems().clear();
		if(Main.getIndexModel().getUsers().size()!=0){
			String[] users = Main.getIndexModel().writeUsersS().split(",");
			for(int i = 0; i<users.length;i++) {
				listUsers.getItems().add(users[i]);
			}
		}
	}
	@FXML
	public void ordenar3() {
		listUsers.getItems().clear();
		if(Main.getIndexModel().getUsers().size()!=0){
			String[] users = Main.getIndexModel().writeUsersB().split(",");
			for(int i = 0; i<users.length;i++) {
				listUsers.getItems().add(users[i]);
			}
		}
	}
	@FXML
	public void ordenar4() {
		listUsers.getItems().clear();
		if(Main.getIndexModel().getUsers().size()!=0){
			String[] users = Main.getIndexModel().writeUsersL().split(",");
			for(int i = 0; i<users.length;i++) {
				listUsers.getItems().add(users[i]);
			}
		}
	}
	@FXML
	public void ordenar5() {
		listUsers.getItems().clear();
		if(Main.getIndexModel().getUsers().size()!=0){
			String[] users = Main.getIndexModel().writeUsersF().split(",");
			for(int i = 0; i<users.length;i++) {
				listUsers.getItems().add(users[i]);
			}
		}
	}
	
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