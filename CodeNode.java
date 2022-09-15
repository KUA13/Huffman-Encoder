/**
 * This class defines a CodeNode object, which contains a string and a 
 * Huffman code. These objects are used to create an array of CodeNode 
 * objects in order to access Huffman codes.
 * 
 * @author Kamal Abro
 *
 */
public class CodeNode {

   String s;
   String huffCode;
   
   public CodeNode(String s, String huffCode) {
      super();
      this.s = s;
      this.huffCode = huffCode;
   }
   public String getS() {
      return s;
   }
   public void setS(String s) {
      this.s = s;
   }
   public String getHuffCode() {
      return huffCode;
   }
   public void setHuffCode(String huffCode) {
      this.huffCode = huffCode;
   }
   /**
    * Overwrites the toString() method. 
    * @return A concatenated string containing the character and the Huffman 
    * code
    */
   public String toString() {
      String s = this.s + " = " + this.huffCode;
      return s;
   }//end toString()
}//end CodeNode
