import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        //Create scraper which contains functions needed to interact with GUI
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Layout.fxml"));
        primaryStage.setTitle("League of Legends stats retrieve 1.0");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
