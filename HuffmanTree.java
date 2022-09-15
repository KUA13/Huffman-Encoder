/**
 * This class defines a HuffmanTree object with a single variable, the root 
 * node of the tree. It has one method, other than getters and setters, to 
 * traverse the entire tree and store each node into a string that can be 
 * printed out.
 * 
 * @author Kamal Abro
 *
 */
public class HuffmanTree {

   Node root;
   
   public HuffmanTree(Node root) {
      super();
      this.root = root;
   }

   public Node getRoot() {
      return root;
   }

   public void setRoot(Node root) {
      this.root = root;
   }
   
   /**
    * Recursively traverses a binary tree via a preorder traversal and adds 
    * each node to a String located in an array containing 1 string.
    * @param n The node currently being visited. Enter the root to traverse 
    * and entire tree
    * @param output The array containing the String that will contain the 
    * output
    * @return The string containing the entire tree 
    */
   public String printPreOrder(Node n, String[] output) {
      if(n == null) {
         return "";
      }
      //Adds current node to output string 
      output[0] += n + "\n";
      //Recursively traverses left subtree
      printPreOrder(n.left, output);
      //Recursively traverses right subtree
      printPreOrder(n.right, output);
      //Returns the tree as a string
      return output[0];
   }//end printPreOrder()
   
}//end HuffmanTree class
