import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Credentials {
    private static String urlSumID, urlStats, apiKey, summonerName, region;

    //constructor for credentials class
    public Credentials(ArrayList<String> inputs) {
        //initialize a credentials object with the inputs
        this.summonerName = inputs.get(0);
        this.region = inputs.get(1);
    }

    private static void validateXML() {
        //in this, we create stringbuilder 'xml' then
        //create Document 'doc' with method "jsoup.parse(xml, credentials, Parser())"
        //lastly we extract URL from this Document
        File inputFile = new File("/Users/sam/IdeaProjects/OPGG-Winrate-Scraper/src/Credentials.xml");
        StringBuilder xml = new StringBuilder();
        //read credentials
        try {
            Scanner input = new Scanner(inputFile);
            while (input.hasNextLine()) {
                xml.append(input.nextLine());
            }
        } catch (FileNotFoundException e) {//this is where it is failing
            System.err.println("Could not retrieve credentials.\n" + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        //Read in XML Data
        Document doc = Jsoup.parse(xml.toString(), "Credentials.xml", Parser.xmlParser());
        //Extract URL, here we must differentiate between WHICH urls we need. can probably just make two!
        urlSumID = doc.select("urlSumID").text();
        urlStats = doc.select("urlStats").text();
        System.out.println("Connection Successful:\n");
    }

    private static void assembleSummonerIDURL() {
        //assembles the url to get the encrypted summoner ID
        //https://_region_.api.riotgames.com/lol/summoner/v4/summoners/by-name/
        String[] urlArray = urlSumID.split("_region_");
        urlArray[0] += region;
        //apikey dependent on verifyXML method to be assigned a value
        urlSumID = urlArray.toString() + summonerName + "?api_key=" + apiKey;
    }
    private static void assembleStatsURL() {
        //assembles the url to get stats of summoner
        //https://_region_.api.riotgames.com/lol/league/v4/entries/by-summoner/
        String[] urlArray = urlSumID.split("_region_");
        urlArray[0] += region;
        urlStats = urlArray.toString();//add on encrypted ID in the statsretrieve class
    }

    //Primary method for assembling data
    private static void populate() {
        validateXML();
        assembleSummonerIDURL();
        assembleStatsURL();
    }

    public static String getUrlSumID() {
        //invoke populate because otherwise this can be a null
        populate();
        return urlSumID;
    }

    public static String getUrlStats(){
        //invoke populate because otherwise this can be a null
        populate();
        return urlStats;
    }
}
