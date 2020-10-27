import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import javax.print.Doc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class StatsRetrieve {
    //statistics to retrieve
    private static String summonerName, wins, losses, tier, rank, encryptedSummonerID;

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
        String urlStats = creds.getUrlStats() + encryptedSummonerID + "?api_key=" + creds.getApiKey();
        String connection2 = callAPI(urlStats);
        Document response2 = Jsoup.parse(connection2, urlStats, Parser.xmlParser()); //xml parser might need to be looked into
        getValues(response2);
    }

    private String getEncryptedSummonerID(Document response) {
        //this parses the document response (sane html) and assigns local variables the value caught with a css selector
        //StringBuilder e = new StringBuilder();
        String encryptedSummonerID = "Default";
        String[] responseValues = response.text().substring(1, response.text().length() - 1).split(",");

        for(int i = 0; i < responseValues.length; i ++){
            String [] tempStringArray = responseValues[i].replaceAll("\"", "").split(":");
            if(tempStringArray[0].equals("id")){
                encryptedSummonerID = tempStringArray[1];
            }
        }
        return encryptedSummonerID;
    }

    public void getValues(Document response){
        //    private static String summonerName, wins, losses, tier, rank
        //this method will grab all of the values we want to fill in the report on the app
        this.summonerName = "Default";
        this.wins = "Default";
        this.losses = "Default";
        this.tier = "Default";
        this.rank = "Default";
        String[] responseValues = response.text().substring(2, response.text().length() - 2).split(",");

        for(int i = 0; i < responseValues.length; i++){
            String[] tempStringArray = responseValues[i].replaceAll("\"", "").split(":");
            if (tempStringArray[0].equals("tier")){
                tier = tempStringArray[1];
            }else if(tempStringArray[0].equals("rank")){
                rank = tempStringArray[1];
            }else if(tempStringArray[0].equals("summonerName")){
                summonerName = tempStringArray[1];
            }else if(tempStringArray[0].equals("wins")){
                wins = tempStringArray[1];
            }else if(tempStringArray[0].equals("losses")){
                losses = tempStringArray[1];
            }

            System.out.println(summonerName + ", wins:" + wins + ", losses:" + losses + ", " + tier + " " + rank);
        }
    }

    public String generateReport() {
        //this will return all the fields caught in updateCurrentValues in a sentence
        return summonerName + ", wins:" + wins + ", losses:" + losses + ", " + tier + " " + rank;
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
