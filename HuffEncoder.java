/**
 * This class contains three methods used to encode and decode strings. 
 * The methods in the class can be used by other programs to encode or decode 
 * a string via Huffman compression. It requires an already created array of 
 * Huffman codes and a Huffman tree.
 * 
 * @author Kamal Abro
 *
 */
public class HuffEncoder {

   /**
    * Encodes a string of English characters into binary using an array of 
    * Huffman codes
    * @param str The string being encoded
    * @param codes The array of CodeNodes containing Huffman codes
    * @return An encoded string
    */
   public String encode(String str, CodeNode[] codes) {
      //Initializes the output string
      String result = "";
      //Iterates through a string one character at a time and adds the 
      //matching Huffman code to the output string. Adds a space where there 
      //is a space
      for(int i = 0; i < str.length(); i++) {
         if(str.charAt(i) == ' ') {
            result += " ";
            continue;
         }
         String charCode = getCode(str.charAt(i), codes);
         result += charCode;
      }
      return result; //Returns the encoded string
   }//end endcode()
   
   /**
    * Takes an encoded string and decodes it into english text (without 
    * spaces) by using a Huffman Tree.
    * @param str The encoded tree to be decoded
    * @param root The root node of the Huffman Tree
    * @return The decoded string
    */
   public String decode(String str, Node root) {
      Node node = root;
      String result = "";
      //Searches through the tree for the correct character for a given code
      for(int i = 0; i < str.length(); i++) {
         if(str.charAt(i) == '0') {
            node = node.left;
         }
         else {
            node = node.right;
         }
         
         if(node.isLeaf() == true) {
            result += node.item;
            node = root;
         }
      }
      return result;
   }//end decode()
   
   /**
    * Gets the correct Huffman code for a given character via an array of 
    * Huffman codes
    * @param c The character that the method will return a code for
    * @param codes An array of CodeNode objects that contains all Huffman 
    * codes
    * @return The correct Huffman code, as a String, for the given char
    */
   public String getCode(char c, CodeNode[] codes) {
      for(int i = 0; i < codes.length; i++) {
         if(c == codes[i].s.charAt(0)) {
            return codes[i].huffCode;
         }
      }
      return "";
   }//end getCode()
}//endHuffEncoder class
