import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class LayoutController {
    @FXML
    protected TextField region;
    @FXML
    protected TextField summonerName;
    @FXML
    protected Button winrateLookupButton;
    @FXML
    protected TextArea outputDisplayArea;
    @FXML
    protected ChoiceBox regionBox;


    public void handleSubmission() {
        //this is the method actuated by pressing a button. this is called by
        //placing the onAction method as "#handleSubmission" in the layout.fxml
        //String region = (String) regionBox.getValue();
        ArrayList<String> inputs = new ArrayList<>();



        inputs.add(summonerName.getText());
        inputs.add(translatedRegionChoice());

        for (String input:inputs) {
            System.out.println(input);
        }

        //now create an instance of the Winrate_scraper
        StatsRetrieve scrapie = new StatsRetrieve(inputs);
        //call the generateReport method from scrapie and set the text in the textArea
        outputDisplayArea.setText(scrapie.generateReport());
    }

    public String translatedRegionChoice(){
        String region = (String)regionBox.getValue();

        switch (region){
            case "NA":
                region = "na1";
                break;
            case "RU":
                region = "ru";
                break;
            case "BR":
                region = "br1";
                break;
            case "KR":
                region = "kr";
                break;
            case "JP":
                region = "jp1";
                break;
            case "EUN":
                region = "eun1";
            case "EUW":
                region = "euw1";
                break;
        }
        return region;
    }



}
