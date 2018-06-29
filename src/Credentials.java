import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Credentials {
    private static String url, userName;

    public Credentials(String _userName){
        this.userName = _userName;
    }

    //Primary method for assembling data
    private static void populate(){
        validateXML();
        assembleURL();
    }

    private static void assembleURL(){
        url += userName; //url is first instantiated in Credentials.XML
    }

    private static void validateXML(){
        File inputFile = new File("Credentials.xml");
        StringBuilder xml = new StringBuilder();
        //read credentials
        try {
            Scanner input = new Scanner(inputFile);
            while (input.hasNextLine()) {
                xml.append(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not retrieve credentials.\n" + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        //Read in XML Data
        Document doc = Jsoup.parse(xml.toString(), "Credentials.xml", Parser.xmlParser());
        //Extract URL
        url = doc.select("url").text();
        System.out.println("Connection Successful:\n");
    }

    public static String getURL(){
       populate();
       return url;
    }

}
