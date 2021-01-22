import java.util.*;
import java.io.*;

public class WebPageIndex {
  private MyTreeMap map = new MyTreeMap();
  private MyTreeSet set = new MyTreeSet();
  private int count = 0;
  private String URL;

  public WebPageIndex(String url){
    this.URL = url;
    try{
      HTMLScanner scanner = new HTMLScanner(URL);
      while(scanner.hasNext()){
        String token = scanner.next();
        map.put(token.toLowerCase(),count);
        count ++;
      }
      while (scanner. hasNextLink()){
        set.add(scanner.nextLink());
      }
    }catch (FileNotFoundException e){
      System.out.println(e);
    } catch (IOException e){
      System.out.println(e);
    }
  }

  public static void main(String[] args){
    String fileName = "testscannerfile";
    WebPageIndex index = new WebPageIndex(fileName);
    Iterator<String> keys = index.map.keys();
    while(keys.hasNext()){
      String word = keys.next();
      LinkedList<Integer> locations = index.map.get(word);
      System.out.println(word+"\t"+locations.size() * 1.0/index.getWordCount() + "\t" + locations);
    }
    
    System.out.println("---------------------------");

    System.out.println("expect:\t9");
    System.out.println("got:\t"+index.getWordCount());

    System.out.println("expect:\ttrue");
    System.out.println("got:\t"+index.contains("is"));

    System.out.println("expect:\t3");
    System.out.println("got:\t"+index.getCount("is"));

    System.out.println("expect:\t[6,9]");
    System.out.println("got:\t"+index.getLocations("it"));

    System.out.println("expect:\t[happening,hi,if,important,is,it,tagged,there,what]");
    String result = "";
    Iterator<String> it = index.words();
    while(it.hasNext()){
      result += it.next() +",";
    }
    System.out.println("got:\t["+result+"]");

    System.out.println("expect:\ttrue");
    System.out.println("got:\t"+index.containsPhrase("it is"));

    System.out.println("expect:\tfalse");
    System.out.println("got:\t"+index.containsPhrase("is important"));
  }

  public int getWordCount() {
    Iterator<String> it = map.keys();
    int size = 0;
    while(it.hasNext()){
      it.next();
      size++;
    }
    return size;
  }
  
  public String getUrl() {
    return URL;
  }
  
  public boolean contains(String s) {
    Object result = map.get(s);
    return result != null;
  }
  
  public int getCount(String s) {
    LinkedList<Integer> locations = map.get(s);
    return locations.size();
  }
  
  public double getFrequency(String s) {
    return getCount(s)*1.0/getWordCount();
  }

  public List<Integer> getLocations(String s) {
    return map.get(s);
  }
  
  public Iterator<String> words() {
    return map.keys();
  }
  
  public String toString() {
    return "TODO";
  }
  
  public boolean containsPhrase(String s) {
    String words[] = s.split(" ");
    String word1 = words[0];
    String word2 = words[1];
    boolean result = false;
    if(contains(word1) && contains(word2)){
      LinkedList<Integer> word1Locations = map.get(word1);
      LinkedList<Integer> word2Locations = map.get(word2);
      Iterator<Integer> it1 = word1Locations.iterator();
      while (it1.hasNext());{
        Integer location1 = it1.next();
        Iterator<Integer> it2 = word2Locations.iterator();
        while(it2.hasNext()){
          Integer location2 = it2.next();
          if(location2.equals(location1+1)){
            return true;
          }
        }
      }
    }
    return result;
  }
  
  public int getPhraseCount(String s) {
    return -1;
  }
  
  public double getPhraseFrequency(String s) {
    return -1.0;
  }

  public List<Integer> getPhraseLocations(String s) {
    return null;
  }
}
