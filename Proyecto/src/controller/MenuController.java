package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import exception.CharacterDoesNotExist;
import exception.NicknameNotValid;
import exception.UserDoesNotExist;
import exception.UserAlreadyExists;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MenuController implements Initializable{
	
	//In case you need indexModel, call it using Main.getIndexModel()
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	@FXML
	public void play(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("�BIENVENIDO!");
		dialog.setContentText("Por favor ingeresa tu nickname: ");
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()) {
			try {
				String nickName = result.get();
				Main.getIndexModel().registrerUser(nickName);
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/application/IndexWindow.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();				
				stage.setTitle("INDEX");
				stage.setScene(scene);
				stage.show();
			} catch (NicknameNotValid e) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText(e.getMessage());
				a.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UserAlreadyExists e) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText(e.getMessage());
				a.show();
			}
			
		}
	}
	
	@FXML
	public void ranking(ActionEvent event) {
		try {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/PlayersWindow.fxml"));
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
	public void resumeGame(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/SearchWindow.fxml"));
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
	public void searchPlayer() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setContentText("Ingrese el nombre del usuario que sea buscar: ");
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()){
			String criterio = result.get();
			try {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText(Main.getIndexModel().searchUser(criterio).toString());
				alert.show();
			} catch (UserDoesNotExist e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(e.getMessage());
				alert.show();
			}
		}
	}
	
	@FXML
	public void searchPlayerScore() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setContentText("Ingrese el puntaje del usuario que sea buscar: ");
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()){
			try {
				String criterio = result.get();
				int score = Integer.parseInt(criterio);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText(Main.getIndexModel().searchUserScore(score).toString());
				alert.show();
			} catch (UserDoesNotExist e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(e.getMessage());
				alert.show();
			}catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("�Por favor ingrese numeros, no letras!");
				alert.show();
			}
		}
	}
		
		@FXML
		public void searchCharacter(ActionEvent event) {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setContentText("Ingrese el nombre del personaje que desea buscar: ");
			Optional<String> result = dialog.showAndWait();
			if(result.isPresent()) {
				String criterio = result.get();
				try {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setContentText(Main.getIndexModel().searchCharacter(criterio).toString());
					alert.show();
				} catch (CharacterDoesNotExist e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText(e.getMessage());
					alert.show();
				}
			}
		}

}
