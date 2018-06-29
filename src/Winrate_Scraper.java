import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Winrate_Scraper {
    private String userName;//the players own userName

    //constructor
    public Winrate_Scraper(String _userName) {
        //establish credentials (in this case only a username)
        userName = _userName;
        String url = Credentials.getURL();
        //Connection status
        String connection = callAPI(url);
        Document response = Jsoup.parse(connection, url, Parser.xmlParser());//returns sane html as Document
        //Set Current Values
        updateCurrentValues(response);

    }

    private void updateCurrentValues(Document response) {
        //this parses the document response (sane html) and assigns local variables the value caught with a css selector
        userName = response.select("username").text();
        //add more fields here, catch them using css selectors
        generateReport();//print out all the fields in a coherent sentence
    }

    public String generateReport() {
        //this will return all the fields caught in updateCurrentValues in a sentence
        String s = userName;
        return s;
    }

    public String callAPI(String urlString) {
        //catches exception
        StringBuilder data = new StringBuilder();
        try {
            URL url = new URL(urlString);
            BufferedReader input = new BufferedReader(
                    //this is called an anonymous inner class
                    new InputStreamReader(url.openStream())
            );
            String s;
            while ((s = input.readLine()) != null) {
                data.append(s);
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data.toString();
    }

}
