import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    /*
        ToDo:
        - make a .exe for this
        - get riot key that wont expire in 24 hours lol
        - find better color scheme or better font color
        - make info button functional to provide information on project
        - clear textbox after submission to chain queries easier
        - make default region NA
     */
    public static void main(String[] args) {
        //Create scraper which contains functions needed to interact with GUI
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //get GUI from fxml file "Layout"
        Parent root = FXMLLoader.load(getClass().getResource("Layout.fxml"));
        //set title of window on application
        primaryStage.setTitle("League of Legends stats retrieve 1.0");
        //create scene on stage 'root' with width x height
        primaryStage.setScene(new Scene(root, 600, 400));
        //make scene 'root' reference stylesheet 'style.css'
        root.getStylesheets().add(String.valueOf(Main.class.getResource("style.css")));
        //show the stage
        primaryStage.show();
    }
}
