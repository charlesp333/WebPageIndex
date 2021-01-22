import java.util.LinkedList;
import java.util.Iterator;

public class MyTreeSet{
  MyTreeMap map = new MyTreeMap();

  public void add(String key){
    map.put(key,null);
  }

  public Iterator<String> keys(){
    return map.keys();
  }
}
