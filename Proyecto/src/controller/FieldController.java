package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import application.Main;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import modelo.Bomb;
import modelo.Electricity;
import modelo.Gemma;
import modelo.Trap;
import threads.ThreadChronometer;

public class FieldController implements Initializable{
	//
	private Gemma gemma;
	
	private Timeline thread;
	
	private Timeline trapsThread;

    @FXML
    private ImageView field;

    @FXML
    private AnchorPane pane;
    
    @FXML
    private ImageView gema1;

    @FXML
    private ImageView gema2;

    @FXML
    private ImageView gema3;

    @FXML
    private ImageView gema4;

    @FXML
    private ImageView gema5;

    @FXML
    private ImageView gema6;
    
    @FXML
    private ImageView character;
    
    @FXML
    private ImageView g;
    
    @FXML
    private Text chronometer;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chronometer();
		field.setImage(new Image(Main.getIndexModel().getFieldChoose().getImage()));
		gema1.setOpacity(0.50);
		gema2.setOpacity(0.50);
		gema3.setOpacity(0.50);
		gema4.setOpacity(0.50);
		gema5.setOpacity(0.50);
		gema6.setOpacity(0.50);	
		character.setImage(new Image(Main.getIndexModel().getCharacterChoose().getImage()));

			thread = new Timeline(new KeyFrame(Duration.ZERO, e-> {
				g.setImage(new Image("/images/Gema_Alma.png"));
				gemma = Main.getIndexModel().getFieldChoose().getRootGemma();
				gemma.move();
				g.setLayoutY(gemma.getPosy());
				g.setLayoutX(gemma.getPosx());
				
			}),new KeyFrame(Duration.millis(30)));
			
			thread.setCycleCount(Animation.INDEFINITE);
			thread.play();
			
		generateTraps();
	}
	
	public void receiveScene(Scene scene) {
		onKeyPressed(scene);
	}

	@FXML
	public void onKeyPressed(Scene scene) {
		scene.setOnKeyPressed(e->{
			switch(e.getCode()) {
			case LEFT: 
				disableRight();
				disableUp();
				disableDown();
				moveLeft();
					break;
				case RIGHT: 
				disableLeft();
				disableUp();
				disableDown();
				moveRight();
					break;	
				case UP: 
				disableDown();
				disableRight();
				disableLeft();
				moveUp();
					break;
				case DOWN: 
				disableUp();
				disableRight();
				disableLeft();
				moveDown();
					break;
				default:
					break;
				}
			
		});
	}
	
	private void moveDown() {
		Main.getIndexModel().getCharacterChoose().setDown(true);
		Main.getIndexModel().getCharacterChoose().move();
		character.setLayoutX(Main.getIndexModel().getCharacterChoose().getPosx());
		character.setLayoutY(Main.getIndexModel().getCharacterChoose().getPosy());
		
	}

	private void moveUp() {
		Main.getIndexModel().getCharacterChoose().setUp(true);
		Main.getIndexModel().getCharacterChoose().move();
		character.setLayoutX(Main.getIndexModel().getCharacterChoose().getPosx());
		character.setLayoutY(Main.getIndexModel().getCharacterChoose().getPosy());
		
	}

	public void moveRight() {
		Main.getIndexModel().getCharacterChoose().setRight(true);
		Main.getIndexModel().getCharacterChoose().move();
		character.setLayoutX(Main.getIndexModel().getCharacterChoose().getPosx());
		character.setLayoutY(Main.getIndexModel().getCharacterChoose().getPosy());
	}
	
	public void moveLeft() {
		Main.getIndexModel().getCharacterChoose().setLeft(true);
		Main.getIndexModel().getCharacterChoose().move();
		character.setLayoutX(Main.getIndexModel().getCharacterChoose().getPosx());
		character.setLayoutY(Main.getIndexModel().getCharacterChoose().getPosy());
	}
	
	public void disableRight() {
		Main.getIndexModel().getCharacterChoose().setRight(false);
	}
	
	public void disableLeft() {
		Main.getIndexModel().getCharacterChoose().setLeft(false);
	}
	
	public void disableUp() {
		Main.getIndexModel().getCharacterChoose().setUp(false);
	}
	
	public void disableDown() {
		Main.getIndexModel().getCharacterChoose().setDown(false);
	}
	
	public void chronometer() {
		ThreadChronometer ch = new ThreadChronometer(Main.getIndexModel().getFieldChoose(),chronometer);
		ch.start();
	}
	
	private void generateTraps() {
		ArrayList<Trap> traps = Main.getIndexModel().getFieldChoose().getTraps();
//		
//		for(int i=0;i<traps.size();i++) {
//			if(traps.get(i) instanceof Bomb ) {
//				Bomb temp = (Bomb) traps.get(i);
//				ImageView bomb = new ImageView(new Image("/images/bomb.png"));
//				pane.getChildren().add(bomb);
//				bomb.setLayoutX(temp.getX());
//				bomb.setLayoutY(temp.getY());
//				trapsThread = new Timeline(new KeyFrame(Duration.ZERO, e-> {
//					temp.move();
//					bomb.setLayoutX(temp.getX());
//					bomb.setLayoutY(temp.getY());
//				}),new KeyFrame(Duration.millis(30)));
//				
//				trapsThread.setCycleCount(Animation.INDEFINITE);
//				trapsThread.play();
//			}
//			if(traps.get(i) instanceof Electricity) {
//				Electricity temp = (Electricity) traps.get(i);
//				ImageView electricity = new ImageView(new Image("/images/bomb.png"));
//				pane.getChildren().add(electricity);
//				electricity.setLayoutX(temp.getX());
//				electricity.setLayoutY(temp.getY());
//			}			
//		}
		
	}
	
}