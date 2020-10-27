import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class StatsRetrieve {
    //statistics to retrieve
    private String summonerName, wins, losses, tier, rank;
    private String encryptedSummonerID;


    //constructor
    public StatsRetrieve(ArrayList<String> _inputs) {
        //establish credentials
        Credentials creds = new Credentials(_inputs);
        //fetch url to get encrypted summoner ID
        String urlSumID = creds.getUrlSumID();
        //initialize connection and created doc with response results
        String connection1 = callAPI(urlSumID);
        Document response = Jsoup.parse(connection1, urlSumID, Parser.xmlParser());//returns sane html as Document
        //create url to get summoner stats
        encryptedSummonerID = getEncryptedSummonerID(response);
        //System.out.println(encryptedSummonerID);
        String urlStats = creds.getUrlStats() + encryptedSummonerID;
        String connection2 = callAPI(urlStats);
        Document response2 = Jsoup.parse(connection2, urlStats, Parser.xmlParser()); //xml parser might need to be looked into

    }

    private String getEncryptedSummonerID(Document response) {
        //this parses the document response (sane html) and assigns local variables the value caught with a css selector
        //StringBuilder e = new StringBuilder();
        String encryptedSummonerID = "Default encrypted summoner ID";
        String[] responseValues = response.text().substring(1, response.text().length() - 1).split(",");
//        for(String value : responseValues){
//            System.out.println(value);
//        }
        for(int i = 0; i < responseValues.length; i ++){
            String [] tempStringArray = responseValues[i].replaceAll("\"", "").split(":");
//            for(String x : tempStringArray){
//                System.out.println(x);
//                //System.out.println("-------");
//            }
            if(tempStringArray[0].equals("id")){
                encryptedSummonerID = tempStringArray[1];
            }
        }
        return encryptedSummonerID;
    }

    private void statParse(Document response) {
        //this parses the document response (sane html) and assigns local variables the value caught with a css selector
        summonerName = response.select("username").text();
        wins = response.text();
    }

    public String generateReport() {
        //this will return all the fields caught in updateCurrentValues in a sentence
        String wins2 = this.wins;
        return wins2;
    }

    public String callAPI(String urlString) {
        //Stringbuilder is just a mutable string
        //thus this method only appends to 'data' everything in 'input'
        StringBuilder data = new StringBuilder();
        System.out.println(urlString);//debugging
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
