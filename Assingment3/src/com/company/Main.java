package com.company;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//Jsoup document other one doesn't work even after type casting
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String def = "http://pec.ac.in";

    public static void main(String[] args) throws IOException {
	// write your code here
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter\n1: to use http://pec.ac.in\n2:To use Some other Link");
        int input = scan.nextInt();
        String use;
        if(input==1){
            use = def;
        } else{
            use = scan.nextLine();
        }

        //It sends the request (http request) for the URL supplied.
        //Get the page and extract all the text and html tags present in the page.
        Document doc = Jsoup.connect(use).get();
        //Getting Text
        part3(doc);
        System.out.println("FINISHED");
    }
    //TEXT
    public static void part3(Document doc){
        String fileName = "Tag_Text.csv";
        File file = new File(fileName);
        try{
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            //HEADER
            String[] currStr = {"Tag", "Text"};
            writer.writeNext(currStr);
            currStr[0] = "title";
            currStr[1] = doc.title();
            writer.writeNext(currStr);
            String[] tag = {"p", "h1", "h2", "h3", "h4", "h5", "h6"};

            for(int i=0;i< tag.length;i++) {
                Elements links = doc.select(tag[i]);
                for (Element link : links) {
                    currStr[0] = tag[i];
                    currStr[1] = link.text();
                    if(currStr[1].length()==0){
                        continue;
                    }
                    writer.writeNext(currStr);
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
