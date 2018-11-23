package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import modelo.Index;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	
	
	//Static relationship between model and controllers
	//All controllers are going to call it using Main.getIndexModel()
	private static Index indexModel;
	//
	//
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/application/MenuWindow.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Avengers");
			primaryStage.getIcons().add(new Image("/images/logo.png"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		indexModel = new Index();
		launch(args);
	}

	public static Index getIndexModel() {
		return indexModel;
	}

	public static void setIndexModel(Index indexModel) {
		Main.indexModel = indexModel;
	}
	
}
