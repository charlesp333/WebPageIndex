import java.util.LinkedList;
import java.util.Iterator;
import tree.LinkedBinarySearchTree;

public class MyTreeMap{
  LinkedBinarySearchTree<Element> tree =
    new LinkedBinarySearchTree<Element>();

//find the linked list associated with a word
  public LinkedList<Integer> get(String word) {
    Element result = tree.find(new Element(word,null));
    if(result == null){
      return null;
    }
    else{
      return result.getValue();
    }
    
  }

  // insert (word, locations) pair to AVL tree
  public void put(String word, Integer location){
    // TO BE IMPLEMENTED
    // get the old list associated with "word" in the tree
    // if old list is null (doesn't exist), create a new one
    // append new "location" to the list
    // add (word, list) pair to the tree
   Element result = tree.find(new Element(word,null));
   if(result == null){
     LinkedList<Integer> list = new LinkedList<Integer>();
     list.add(location);
     tree.addElement(new Element(word,list));
   }
   else{
     LinkedList<Integer> list = result.getValue();
     list.add(location);
     tree.addElement(new Element(word,list));
   }
 
  }

  public Iterator<String> keys(){
    LinkedList<String> keys = new LinkedList<String>();
    Iterator<Object> it = tree.inOrderIterator();
    while(it.hasNext()){
      Element e = (Element)it.next();
      keys.add(e.getKey());
    }
    return keys.iterator();
  }

  public String toString(){
    String result = "";
    Iterator<Object> it = tree.inOrderIterator();
    while(it.hasNext()){
      Element e = (Element)it.next();
      result += e+"\n";
    }
    return result;
  }
}
