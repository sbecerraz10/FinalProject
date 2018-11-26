package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import application.Main;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.IntegerExpression;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.Bomb;
import modelo.Gemma;
import modelo.Trap;
import threads.ThreadChronometer;

public class FieldController implements Initializable{
	
	private AnimationTimer timer;
	
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
    
    @FXML
    private ArrayList<Rectangle> life;
    
    @FXML
    private Rectangle life6;

    @FXML
    private Rectangle life5;

    @FXML
    private Rectangle life4;

    @FXML
    private Rectangle life3;

    @FXML
    private Rectangle life2;

    @FXML
    private Rectangle life1;

    @FXML
    private Rectangle life0;
    
    private boolean vivo;
    
    private boolean win;
    
    private ThreadChronometer ch;
    
    @FXML
    private ImageView home;

    @FXML
    public void B_home(ActionEvent event) {
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		win = false;
		
		vivo = true;
		
		initializeScene();

		
			thread = new Timeline(new KeyFrame(Duration.ZERO, e-> {		
				if(vivo) {
					catchGemma();
				}else {
					thread.stop();
					ch.stop();
				}
				if(win) {
					win();
				}
			}),new KeyFrame(Duration.millis(30)));
			
			thread.setCycleCount(Animation.INDEFINITE);
			thread.play();
			
			
		
		trapsThread = new Timeline(new KeyFrame(Duration.ZERO, e-> {
			if(vivo) {
				catchTrap();
			}else {
				lose();
				trapsThread.stop();
			}
			if(win) {
				trapsThread.stop();
			}
			
		}),new KeyFrame(Duration.millis(30)));
		
		trapsThread.setCycleCount(Animation.INDEFINITE);
		trapsThread.play();
	}
	
	private void lose() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setContentText("YOU LOSE YOUR SCORE WAS: 0");
		alert.show();
		home.setVisible(true);
	}

	private void win() {
		System.out.println("GANASTE");
		thread.stop();
		ch.stop();
		timer.stop();
		Main.getIndexModel().getUserChoose().bestGame(chronometer.getText());
		Main.getIndexModel().getUserChoose().setLastGame(chronometer.getText());
		Main.getIndexModel().getUserChoose().setFirstGame(chronometer.getText());
		setScore();
		int convert = 0;
		String aux [] = chronometer.getText().split(":");
		int seconnds = Integer.parseInt(aux[1]);
		int minutes = Integer.parseInt(aux[0]);
		convert = (minutes*60)+seconnds;
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setContentText("CONGRATULATIONS YOUR SCORE WAS: "+(100-convert));
		alert.show();
		home.setVisible(true);
	}

	public void setScore() {
		int score = 0;
		int convert = 0;
		String aux [] = chronometer.getText().split(":");
		int seconnds = Integer.parseInt(aux[1]);
		int minutes = Integer.parseInt(aux[0]);
		convert = (minutes*60)+seconnds;
		score = (int)(100 - convert*0.5);
		Main.getIndexModel().getUserChoose().setScore(Main.getIndexModel().getUserChoose().getScore()+score);;
	}
	
	public void receiveScene(Scene scene) {
		onKeyPressed(scene);
		onKeyReleased(scene);
		movement();
	}

	@FXML
	public void onKeyPressed(Scene scene) {
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case LEFT: 
					Main.getIndexModel().getCharacterChoose().setLeft(true);
						break;
				case RIGHT: 
					Main.getIndexModel().getCharacterChoose().setRight(true);
						break;	
				case UP: 
					Main.getIndexModel().getCharacterChoose().setUp(true);
					break;
				case DOWN: 
					Main.getIndexModel().getCharacterChoose().setDown(true);					
						break;
				}			
			}
		});		
	}
	
	private void onKeyReleased(Scene scene) {
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case LEFT: 
					Main.getIndexModel().getCharacterChoose().setLeft(false);
						break;
				case RIGHT: 
					Main.getIndexModel().getCharacterChoose().setRight(false);
						break;	
				case UP: 
					Main.getIndexModel().getCharacterChoose().setUp(false);
					break;
				case DOWN: 
					Main.getIndexModel().getCharacterChoose().setDown(false);					
						break;
				}				
			}
		});
	}
	/**
	 * Method for character movement
	 */
	private void movement() {
		timer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				Main.getIndexModel().getCharacterChoose().move();
				int x = Main.getIndexModel().getCharacterChoose().getPosx();
				int y = Main.getIndexModel().getCharacterChoose().getPosy();
				character.relocate(x, y);
				
			}
		};
		timer.start();
	}
	
	
	public void chronometer() {
		ch = new ThreadChronometer(Main.getIndexModel().getFieldChoose(),chronometer);
		ch.start();
	}
	
	@FXML
	private void saveGame() {
		try {
			Main.getIndexModel().serializarUsers();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void generateTraps() {
		
		URL bomb = getClass().getResource("/images/bomb2.png");
    	Image bomba = new Image(bomb.toString(),50,50,false,true);
    	
    	URL elect = getClass().getResource("/images/electricity2.png");
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
		
    	Main.getIndexModel().getFieldChoose().addGemma(1);
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
	
	public void catchTrap() {
		for(int i = 0; i<traps.size();i++) {
			traps.get(i).move();
			trapsImages.get(i).setLayoutX(traps.get(i).getX());
			trapsImages.get(i).setLayoutY(traps.get(i).getY());
			if(Main.getIndexModel().getCharacterChoose().take((int)trapsImages.get(i).getLayoutX(),(int) trapsImages.get(i).getLayoutY())) {
				if(trapsImages.get(i).isVisible()) {
					if(traps.get(i) instanceof Bomb){
						int contador = 0;
						for(int j = 0; j<life.size()&&contador<2;j++) {
							if(life.get(j).isVisible()) {
								life.get(j).setVisible(false);
								contador++;
							}
						}
					}else {
						int contador = 0;
						for(int j = 0; j<life.size()&&contador<1;j++) {
							if(life.get(j).isVisible()) {
								life.get(j).setVisible(false);
								contador++;
							}
						}
					}
				}
				trapsImages.get(i).setVisible(false);
				
			}
		}
		
		if(!life.get(6).isVisible()) {
			vivo = false;
			System.out.println("PERDISTE");
		}
	}
	
	public void catchGemma() {
		for(int i = 0; i<gemma.size();i++) {
			gemma.get(i).move();
			gemmaImages.get(i).setLayoutX(gemma.get(i).getPosx());
			gemmaImages.get(i).setLayoutY(gemma.get(i).getPosy());
			if(Main.getIndexModel().getCharacterChoose().take((int)gemmaImages.get(i).getLayoutX(),(int) gemmaImages.get(i).getLayoutY())) {
				gemmaImages.get(i).setVisible(false);
				Main.getIndexModel().getCharacterChoose().catchGemma(gemma.get(i));
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
		
		if(gema1.getOpacity() == 1.0 && gema2.getOpacity() == 1.0 && gema3.getOpacity() == 1.0 && gema4.getOpacity() == 1.0 && gema5.getOpacity() == 1.0 && gema6.getOpacity() == 1.0 ) {
			win = true;
		}
	}
	

	public void winGame() {
		boolean win = false;
		for(int i = 0; i<gemmaImages.size() ;i++ ) {
			if(!gemmaImages.get(i).isVisible()) {
				win = true;
			}
		}
		
	}
	
	public void initializeScene() {
		life = new ArrayList<>();
		life.add(life0);
		life.add(life1);
		life.add(life2);
		life.add(life3);
		life.add(life4);
		life.add(life5);
		life.add(life6);
		chronometer();
		traps = new ArrayList<>();
		trapsImages = new ArrayList<>();
		gemma = new ArrayList<>();
		gemmaImages = new ArrayList<>();
		generateGemmas();
		generateTraps();
		Main.getIndexModel().getFieldChoose().loadGems();
		field.setImage(new Image(Main.getIndexModel().getFieldChoose().getImage()));
		gema1.setOpacity(0.50);
		gema2.setOpacity(0.50);
		gema3.setOpacity(0.50);
		gema4.setOpacity(0.50);
		gema5.setOpacity(0.50);
		gema6.setOpacity(0.50);	
		character.setImage(new Image(Main.getIndexModel().getCharacterChoose().getImage()));
	}
	
}