import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HTMLScanner implements Iterator<String> {

  protected String url;

  protected Document doc;

  protected Scanner scanner;

  private Iterator<Element> linkIterator;

 
  public HTMLScanner(String url) throws IOException {
    // save this for students use later
    this.url = url;

    // Open up a connection to the web page or file
    if (url.toLowerCase().startsWith("http://")
      || url.toLowerCase().startsWith("https://")) {
      this.doc = Jsoup.connect(url).get();
    } else {
      String tempUrl = url;
      if (url.toLowerCase().startsWith("file:")) {
        tempUrl = url.substring(5);
      }
      this.doc = Jsoup.parse(new File(tempUrl), null, url);
      this.doc.setBaseUri("file://"+tempUrl);
    }
    // Read the document
    String body = doc.body().text();
    // Create a scanner to parse the document
    this.scanner = new Scanner(body);
    // But we only care about alphanumeric things
    this.scanner.useDelimiter(Pattern.compile("[^a-zA-Z0-9]+"));

    // If we want to use the links
    Elements links = doc.select("a[href]");
    linkIterator = links.iterator();
  }

  public boolean hasNext() {
    return this.scanner.hasNext();
  }

  public String next() {
    return this.scanner.next();
  }

  public void remove() {
    throw new UnsupportedOperationException("Cannot remove from HTMLScanner");
  }

  public boolean hasNextLink() {
    return linkIterator.hasNext();
  }

  public String nextLink() {
    return linkIterator.next().attr("abs:href");
  }

  public static void main(String[] args) {
    for (String s: args) {
      try {
        HTMLScanner url = new HTMLScanner(s);
        System.out.println(url.url);
        System.out.println(url.url.replaceAll(".", "-"));
        while (url.hasNext()) {
          System.out.println(url.next());
        }
        System.out.println("\nLinks\n-----");
        while (url.hasNextLink()) {
          System.out.println(url.nextLink());
        }
        System.out.println(url.doc.baseUri());
      } catch (IOException e) {
        System.out.println("Error reading URL: "+s+" ("+e.getMessage()+")");
      }
      System.out.println();
    }
  }
}
