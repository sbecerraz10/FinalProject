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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import modelo.Bomb;
import modelo.Electricity;
import modelo.Gemma;
import modelo.Trap;
import threads.ThreadChronometer;

public class FieldController implements Initializable{
	
	private ArrayList<Trap> traps;
	
	private ArrayList<ImageView> trapsImages;
	
	private ArrayList<ImageView> gemmaImages;
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
		traps = new ArrayList<>();
		trapsImages = new ArrayList<>();
		generateTraps();
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
			
		
		trapsThread = new Timeline(new KeyFrame(Duration.ZERO, e-> {
			
			for(int i = 0; i<traps.size();i++) {
				traps.get(i).move();
				trapsImages.get(i).setLayoutX(traps.get(i).getX());
				trapsImages.get(i).setLayoutY(traps.get(i).getY());
			}
		}),new KeyFrame(Duration.millis(30)));
		
		trapsThread.setCycleCount(Animation.INDEFINITE);
		trapsThread.play();
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
	
	@FXML
	private void saveGame() {
		Main.getIndexModel().serializarUsers();
		System.out.println("Mi mama me mima");
	}
	
	private void generateTraps() {
		
		URL bomb = getClass().getResource("/images/pene.png");
    	Image bomba = new Image(bomb.toString(),50,50,false,true);
    	
    	URL elect = getClass().getResource("/images/pene.png");
    	Image electri = new Image(elect.toString(),50,50,false,true);
 
		traps = Main.getIndexModel().getFieldChoose().getTraps();
		System.out.println(traps.size());
		trapsImages = new ArrayList<>();
		chronometer();
		for(int i=0; i<traps.size();i++) {
			trapsImages.add(new ImageView());
			System.out.println("Entro");
			if(traps.get(i) instanceof Bomb){
				trapsImages.get(i).setImage(bomba);
			}else {
				trapsImages.get(i).setImage(electri);
			}
			
		}
		pane.getChildren().addAll(trapsImages);
		
	}
	
	private void generateGemmas() {
		
		Main.getIndexModel().getFieldChoose().addGemma(2);
		Main.getIndexModel().getFieldChoose().addGemma(3);
		Main.getIndexModel().getFieldChoose().addGemma(4);
		Main.getIndexModel().getFieldChoose().addGemma(5);
		Main.getIndexModel().getFieldChoose().addGemma(6);
		
	}
	
}