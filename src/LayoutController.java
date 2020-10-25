import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LayoutController {
    @FXML
    protected TextField region;
    @FXML
    protected TextField summonerName;
    @FXML
    protected Button winrateLookupButton;
    @FXML
    protected TextArea outputDisplayArea;


    public LayoutController() {
        //empty for now
    }

    @FXML
    public void handleSubmission() {
        //this is the method actuated by pressing a button. this is called by
        //placing the onAction method as "#handleSubmission" in the layout.fxml

        //now create an instance of the Winrate_scraper
        Winrate_Scraper scrapie = new Winrate_Scraper(summonerName.getText());
        //call the generateReport method from scrapie and set the text in the textArea
        outputDisplayArea.setText(scrapie.generateReport());

    }

}
