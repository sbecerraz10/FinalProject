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
import modelo.Gemma;
import modelo.Trap;
import threads.ThreadChronometer;

public class FieldController implements Initializable{
	
	private ArrayList<Trap> traps;
	
	private ArrayList<ImageView> trapsImages;
	
	private ArrayList<ImageView> gemmaImages;
	//
	private ArrayList<Gemma> gemma;
	
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
    private Text chronometer;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chronometer();
		traps = new ArrayList<>();
		trapsImages = new ArrayList<>();
		gemma = new ArrayList<>();
		gemmaImages = new ArrayList<>();
		generateGemmas();
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

				for(int i = 0; i<gemma.size();i++) {
					gemma.get(i).move();
					gemmaImages.get(i).setLayoutX(gemma.get(i).getPosx());
					gemmaImages.get(i).setLayoutY(gemma.get(i).getPosy());
					if(Main.getIndexModel().getCharacterChoose().take((int)gemmaImages.get(i).getLayoutX(),(int) gemmaImages.get(i).getLayoutY())) {
						gemmaImages.get(i).setVisible(false);
						if(gemma.get(i).getPower() == 1) {
							gema1.setOpacity(1);
						}
						if(gemma.get(i).getPower() == 2) {
							gema2.setOpacity(1);
						}
						if(gemma.get(i).getPower() == 3) {
							gema3.setOpacity(1);
						}
						if(gemma.get(i).getPower() == 4) {
							gema4.setOpacity(1);
						}
						if(gemma.get(i).getPower() == 5) {
							gema5.setOpacity(1);
						}
						if(gemma.get(i).getPower() == 6) {
							gema6.setOpacity(1);
						}
						
					}
				}
				
				
			}),new KeyFrame(Duration.millis(30)));
			
			thread.setCycleCount(Animation.INDEFINITE);
			thread.play();
			
		
		trapsThread = new Timeline(new KeyFrame(Duration.ZERO, e-> {
			
			for(int i = 0; i<traps.size();i++) {
				traps.get(i).move();
				trapsImages.get(i).setLayoutX(traps.get(i).getX());
				trapsImages.get(i).setLayoutY(traps.get(i).getY());
				if(Main.getIndexModel().getCharacterChoose().take((int)trapsImages.get(i).getLayoutX(),(int) trapsImages.get(i).getLayoutY())) {
					trapsImages.get(i).setVisible(false);
				}
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
		
		URL bomb = getClass().getResource("/images/bomb.png");
    	Image bomba = new Image(bomb.toString(),50,50,false,true);
    	
    	URL elect = getClass().getResource("/images/electricityTrap.png");
    	Image electri = new Image(elect.toString(),50,50,false,true);
 
		traps = Main.getIndexModel().getFieldChoose().getTraps();
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
		
		URL gg1 = getClass().getResource("/images/Gema_Alma.png");
    	Image g1 = new Image(gg1.toString(),50,50,false,true);
    	
    	URL gg2 = getClass().getResource("/images/Gema_del_tiempo.png");
    	Image g2 = new Image(gg2.toString(),50,50,false,true);
    	
    	URL gg3 = getClass().getResource("/images/Gema_Espacio.png");
    	Image g3 = new Image(gg3.toString(),50,50,false,true);
    	
    	URL gg4 = getClass().getResource("/images/Gema_Mente.png");
    	Image g4 = new Image(gg4.toString(),50,50,false,true);
    	
    	URL gg5 = getClass().getResource("/images/Gema_poder.png");
    	Image g5 = new Image(gg5.toString(),50,50,false,true);
    	
    	URL gg6 = getClass().getResource("/images/Gema_Realidad.png");
    	Image g6 = new Image(gg6.toString(),50,50,false,true);
		
		Main.getIndexModel().getFieldChoose().addGemma(6);
		Main.getIndexModel().getFieldChoose().addGemma(2);
		Main.getIndexModel().getFieldChoose().addGemma(5);
		Main.getIndexModel().getFieldChoose().addGemma(4);
		Main.getIndexModel().getFieldChoose().addGemma(3);
		
		gemma = Main.getIndexModel().getFieldChoose().showList();
		for(int i = 0; i< gemma.size();i++) {
			gemmaImages.add(new ImageView());
		}
		gemmaImages.get(0).setImage(g1);
		gemmaImages.get(1).setImage(g2);
		gemmaImages.get(2).setImage(g3);
		gemmaImages.get(3).setImage(g4);
		gemmaImages.get(4).setImage(g5);
		gemmaImages.get(5).setImage(g6);
		
		pane.getChildren().addAll(gemmaImages);
	}
	
}