import java.io.InputStream;

import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApllicationEntryPoint extends Application
{
	public static void main(String [] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundaries/MainController.fxml"));
			Parent root = (Parent) loader.load();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/boundaries/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setWidth(700);
			primaryStage.setHeight(500);
			primaryStage.setTitle("Managment");
			primaryStage.setResizable(false);
			primaryStage.setMaximized(true);
			primaryStage.show();
		}
		catch (Exception e) {
			System.out.println( "Start failed!   "+ e);
			System.exit(1);
		}
		
	}
}
