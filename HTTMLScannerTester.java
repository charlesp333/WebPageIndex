import java.io.*;

public class HTMLScannerTester{
  public static void main (String[] args) {
    if(args.length<1){
      System.out.println("Usage: java TestScanner <url>");
      System.exit(1);
    }

    try {
      String URL=args[0];
      HTMLScanner scanner = new HTMLScanner(URL);
      int count = 0;
      while(scanner.hasNext()) {
        String token = scanner.next();
        System.out.println("the "+count+"th token is #" + token + "#");
        count++;
      }
    } catch (FileNotFoundException e) {
      System.out.println(e);
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
