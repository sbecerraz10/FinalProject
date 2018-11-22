package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import modelo.Character;
import application.Main;
import exception.CharacterNotChoosen;
import exception.FieldNotChoosen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Field;

public class IndexController implements Initializable{
	
	@FXML
    private ImageView field1;
    @FXML
    private ImageView character1;
    
    private Character actualCharacter;
    
    private Field actualField;

    @FXML
    private ImageView leftArrowCharacter, rightArrowCharacter;
    @FXML
    private ImageView leftArrowField, rightArrowField;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		actualCharacter = Main.getIndexModel().getHeadCharacter();
		actualField = Main.getIndexModel().getHeadField();
		character1.setImage(new Image(Main.getIndexModel().getHeadCharacter().getImage()));
		field1.setImage(new Image(Main.getIndexModel().getHeadField().getImage()));
	}
	
	@FXML
	public void showNextCharacter(MouseEvent event) {
		Main.getIndexModel().showNextCharacter();
		character1.setImage(new Image(Main.getIndexModel().getCharacterChoose().getImage()));
	}
	@FXML
	public void showPreviousCharacter(MouseEvent event) {
		Main.getIndexModel().showPreviousCharacter();
		character1.setImage(new Image(Main.getIndexModel().getCharacterChoose().getImage()));
	}
	
	@FXML
	public void showNextField(MouseEvent t) {
		Main.getIndexModel().showNextField();
		field1.setImage(new Image(Main.getIndexModel().getFieldChoose().getImage()));
	}
	
	@FXML
	public void showPreviousField(MouseEvent t) {
		Main.getIndexModel().showPreviousField();
		field1.setImage(new Image(Main.getIndexModel().getFieldChoose().getImage()));
	}
	
	@FXML
	public void selectCharacter(MouseEvent event) {
		character1.setOpacity(0.65);
		leftArrowCharacter.setVisible(false);
		rightArrowCharacter.setVisible(false);		
	}
	
	@FXML
	public void selectField(MouseEvent event) {
		field1.setOpacity(0.65);
		leftArrowField.setVisible(false);
		rightArrowField.setVisible(false);
	}
	
	@FXML
	public void play(ActionEvent event) {
		try {
			if(field1.getOpacity() != 0.65) {
				Main.getIndexModel().electedField(false);
			}if(character1.getOpacity() != 0.65) {
				Main.getIndexModel().electedCharacter(false);
			}
			Main.getIndexModel().electedCharacter(true);
			Main.getIndexModel().electedField(true);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/FieldWindow.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FieldController fdc = (FieldController) loader.getController();
			fdc.receiveScene(scene);
			stage.setScene(scene);
			stage.show();
		}catch(IOException e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(e.getMessage());
			a.show();
		} catch (FieldNotChoosen e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(e.getMessage());
			a.show();
		} catch (CharacterNotChoosen e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(e.getMessage());
			a.show();
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
