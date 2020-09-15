package com.company;

import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//Jsoup document other one doesn't work even after type casting
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static final String def = "http://pec.ac.in";

    public static void main(String[] args) throws IOException {
        // write your code here
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter\n1: to use http://pec.ac.in\n2:To use Some other Link");
        int input = 1;// scan.nextInt();
        String use;
        if (input == 1) {
            use = def;
        } else {
            use = scan.nextLine();
        }

        // It sends the request (http request) for the URL supplied.
        // Get the page and extract all the text and html tags present in the page.
        Document doc = Jsoup.connect(use).get();
        // Getting Text
        // part3(doc);
        // Getting Url
        // part4_A(doc);
        // Focused Crawler
        part4_BANDC(doc, use);
        System.out.println("FINISHED");
    }

    // TEXT
    public static void part3(Document doc) {
        String fileName = "Tag_Text.csv";
        File file = new File(fileName);
        try {
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            // HEADER
            String[] currStr = { "Tag", "Text" };
            writer.writeNext(currStr);
            currStr[0] = "title";
            currStr[1] = doc.title();
            writer.writeNext(currStr);
            String[] tag = { "p", "h1", "h2", "h3", "h4", "h5", "h6" };

            for (int i = 0; i < tag.length; i++) {
                Elements links = doc.select(tag[i]);
                for (Element link : links) {
                    currStr[0] = tag[i];
                    currStr[1] = link.text();
                    if (currStr[1].length() == 0) {
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

    // URL
    public static void part4_A(Document doc) {
        String title = doc.title();
        // System.out.println("title : " +title);
        // System.out.println(doc);//HTML page
        String fileName = "Text_Urls.csv";
        File file = new File(fileName);
        try {
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            // HEADER
            String[] currStr = { "Text", "Link" };
            writer.writeNext(currStr);
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                currStr[1] = link.attr("href");
                currStr[0] = link.text();
                if (currStr[0].length() == 0) {
                    continue;
                }
                if (currStr[1].startsWith("/")) {
                    currStr[1] = def + currStr[1];
                }
                writer.writeNext(currStr);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // FOCUSED CRAWLER
    public static void part4_BANDC(Document doc, String use) {
        String focusedUrlFileName = "Focused_Text_Urls.csv";
        String focusedTagFileName = "Focused_Tag_Text.csv";
        File TagFile = new File(focusedTagFileName);
        File UrlFile = new File(focusedUrlFileName);

        ArrayList<String> currLink = new ArrayList<>();
        HashSet<String> visitedLink = new HashSet<>();

        try {
            FileWriter tagOutputFile = new FileWriter(TagFile);
            CSVWriter tagWriter = new CSVWriter(tagOutputFile);
            FileWriter urlOutputFile = new FileWriter(UrlFile);
            CSVWriter urlWriter = new CSVWriter(urlOutputFile);

            // HEADER
            String[] currStrTag = { "Tag", "Text" };
            String[] currStrUrl = { "Text", "Link" };
            tagWriter.writeNext(currStrTag);
            urlWriter.writeNext(currStrUrl);
            // title tag
            currStrTag[0] = "title";
            currStrTag[1] = doc.title();
            tagWriter.writeNext(currStrTag);
            // Defaults
            String[] tag = { "p", "h1", "h2", "h3", "h4", "h5", "h6" };
            String currUse = use;
            Document currDoc = doc;
            currLink.add(currUse);
            for (int qn = 0; qn < currLink.size() && qn < 157; qn++) {
                System.out.print(qn + " ");
                use = currLink.get(qn);
                currDoc = Jsoup.connect(currUse).get();
                // visited
                visitedLink.add(currStrUrl[1]);
                Elements links = currDoc.select("a[href]");
                // URL
                for (Element link : links) {
                    currStrUrl[1] = link.attr("href");
                    currStrUrl[0] = link.text();
                    if (link.text().length() == 0) {
                        continue;
                    }
                    if (currStrUrl[1].startsWith("/")) {
                        currStrUrl[1] = currUse + currStrUrl[1];
                    }
                    if ((!(visitedLink.contains(currStrUrl[1])))) {
                        if (!currLink.contains(currStrUrl[1])) {
                            currLink.add(currStrUrl[1]);
                        }
                        if ((currStrUrl[1].toLowerCase().contains("faculty"))) {
                            urlWriter.writeNext(currStrUrl);
                        }
                    }
                }
                // TEXT
                for (int i = 0; i < tag.length; i++) {
                    Elements textLinks = currDoc.select(tag[i]);
                    // URL
                    for (Element link : textLinks) {
                        currStrTag[1] = link.text();
                        currStrTag[0] = tag[i];
                        if (currStrTag[1].length() == 0) {
                            continue;
                        }
                        tagWriter.writeNext(currStrTag);
                    }
                }
            }
            // Writer Close
            tagWriter.close();
            urlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
