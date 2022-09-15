/**
 * This Node class defines an obejct with an data item and a variable 
 * containing the frequency of the item. Each node also contains pointer for 
 * two child nodes, one left and one right. This allows for the creation of 
 * a binary tree. The Comparable interface is implemented so that a 
 * PriorityQueue of Nodes can be created, with the priority being based on 
 * the freq variable. 
 * 
 * @author Kamal Abro
 *
 */
public class Node implements Comparable<Node>{

   String item;
   int freq;
   Node left;
   Node right;
   
   public Node(String item, int freq) {
      super();
      this.item = item;
      this.freq = freq;
   }
   public String getItem() {
      return item;
   }
   public void setItem(String item) {
      this.item = item;
   }
   public int getFreq() {
      return freq;
   }
   public void setFreq(int freq) {
      this.freq = freq;
   }
   public Node getLeft() {
      return left;
   }
   public void setLeft(Node left) {
      this.left = left;
   }
   public Node getRight() {
      return right;
   }
   public void setRight(Node right) {
      this.right = right;
   }
   
   /**
    * Overwrites compareTo() method. Allows PriorityQueue class to compare 
    * a Node object by frequency value. A node with lower frequency will have 
    * a higher priority.
    */
   public int compareTo(Node n) {
      if(this.freq < n.freq) {
         return -1;
      }
      if(this.freq > n.freq) {
         return 1;
      }
      return 0;
   }//end compareTo()
   
   /**
    * Overwrites toString() method.
    * @return A concatenated string containing the item and frequency 
    */
   public String toString() {
      String s = this.item + " " + this.freq;
      return s;
   }//end toString()
   
   /**
    * Determine if a node is a leaf. Node is a leaf if both children are null
    * @return True if no children
    */
   public boolean isLeaf() {
      if(this.left == null && this.right == null) {
         return true;
      }
      return false;
   }//end isLeaf()
}//end Node class
