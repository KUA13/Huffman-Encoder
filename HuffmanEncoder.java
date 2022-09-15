/**
 * This program is able to encode or decode a string via Huffman encoding. 
 * The user enters the input file path, frequency table file path, and the 
 * output file path. The user also enters whether they want the input file 
 * to be encoded or decoded. The program stores all output in a single string 
 * variable which will be written to the output file. In addition to the main 
 * method, this class contains 4 additional methods needed for creating a 
 * Huffman Tree and Huffman codes.
 * 
 * This program requires 4 additional class files in order to function. Those 
 * additional classes are the Node class, HuffmanTree class, CodeNode class, 
 * and HuffEncoder class.
 * 
 * @author Kamal Abro
 */

import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanEncoder {

   public static void main(String[] args) {

      //Gets necessary file paths
      Scanner scan = new Scanner(System.in);
      System.out.println("Enter the file path for the file being read: ");
      String inFile = scan.nextLine();
      System.out.println("Enter the file path for the frequency table: ");
      String freqTabFile = scan.nextLine();
      System.out.println("Enter the file path for the output file: ");
      String outFile = scan.nextLine();

      //Allows user to select whether to encode the input or decode it
      System.out.println("Enter 1 for encode or 2 for decode: ");
      int choice = scan.nextInt();
      while(choice != 1 && choice != 2) {
         System.out.println("INVALID SELECTION! Enter 1 for encode or "
               + "2 for decode");
         choice = scan.nextInt();
      }
      //Echoes inputs in the output
      String output = "FILE BEING READ: " + inFile + "\n";
      output += "FREQUENCY TABLE LOCATION: " + freqTabFile + "\n";
      output += "OUTPUT FILE LOCATION: " + outFile + "\n" + "\n";
      //Header for the Huffman Tree
      output += "HUFFMAN TREE (PREORDER TRAVERSAL)" + "\n";
      
      //Stores info from frequency table into an array of nodes
      Node[] arr = makeFreqTabArr(freqTabFile);
      //Creates priority queue of nodes than can be used to generate the 
      //Huffman Tree
      PriorityQueue<Node> nodes = makePrioQ(arr);
      //Creates the Huffman Tree, which can be accessed by the root node
      Node root = makeHuffTree(nodes);
      //Initializes HuffmanTree object
      HuffmanTree ht = new HuffmanTree(root);
      //Initializes the variable used to store the Huffman Tree as a String
      String[] treeOutput = {""};
      //Stores Huffman Tree as String via preorder traversal
      String treeAsStr = ht.printPreOrder(root, treeOutput);

      //Initializes array that will hold the codes for every letter
      CodeNode[] codes = new CodeNode[26];
      //Counter used to recursively get Huffman codes for each letter
      int[] counter = {0}; 
      //Recursively generates Huffman codes and stores them in array of 
      //CodeNode objects, which hold the letter and the code
      codes = getHuffCodes(root, "", codes, counter);
      //Initializes string that will hold all codes to be output
      String codesAsStr = "CODES" + "\n";
      //Stores all Huffman codes in string
      for(int i = 0; i < codes.length; i++) {
         codesAsStr += codes[i] + "\n";
      }
      
      try {
         BufferedReader br = new BufferedReader(new FileReader(inFile));
         BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
         String curLine; //Var used to iterate through each line in input

         //Initialize HuffEncoder object used to encode and decode files
         HuffEncoder encoder = new HuffEncoder();
         
         //Stores tree and codes in output string
         output += treeAsStr + "\n";
         output += codesAsStr + "\n" + "\n";
         
         if(choice == 1) {
            //Encode text to binary and store in output
            while((curLine = br.readLine()) != null) {
               curLine = curLine.toUpperCase();
               output += "INPUT: ";
               output += curLine + "\n";
               output += "OUTPUT: ";
               output += encoder.encode(curLine, codes) + "\n";
               output += "\n";
            }
            System.out.println("FILE ENCODED!");
         }
         else {
            //Decode encoded text and store in output
            while((curLine = br.readLine()) != null) {
               output += "INPUT: ";
               output += curLine + "\n";
               output += "OUTPUT: ";
               output += encoder.decode(curLine, root) + "\n";
               output += "\n";
            }
            System.out.println("FILE DECODED!");
         }
         bw.write(output); //Writes output string to outFile
         
         br.close();
         bw.close();
      } 
      catch (IOException e) {
         e.printStackTrace();
      }
      scan.close();
   }//end main()
   
   /**
    * Takes a file location and stores frequency table in an array of Nodes
    * @param freqTabFile String containing frequency table file path
    * @return frequency table as an array of Nodes
    */
   public static Node[] makeFreqTabArr(String freqTabFile) {
      Node[] freqTabArr = new Node[26];
      try {
         //Reads the file containing the frequency table
         BufferedReader freqTabReader = new BufferedReader(
               new FileReader(freqTabFile));
         //Variable used to store each line of the frequency table file
         String curLine = null;
         //Variable used to iterate through output array
         int i = 0;
         //Reads each line of the file
         while((curLine = freqTabReader.readLine()) != null) {
            //Array used to hold a read line in 3 separate parts
            String[] lineAsArr = new String[3];
            //Splits the read line into 3 parts, the character, the hyphen, 
            //and the frequency
            lineAsArr = curLine.split(" ");
            //Converts the frequency value from a String to an int
            int freq = Integer.parseInt(lineAsArr[2]);
            //Creates a node with the character and the frequency located in 
            //the current line
            Node newNode = new Node(lineAsArr[0], freq);
            //Stores node in an array used to represent the frequency table
            freqTabArr[i] = newNode;
            i++;
         }
         freqTabReader.close();
      }//end try
      catch (IOException e) {
         e.printStackTrace();
      }
      return freqTabArr; //Return the frequency table as an array
   }//end makeFreqTabArr()
   
   /**
    * Creates a Huffman Tree from a priority queue of Nodes
    * @param nodes The frequency table as a priority queue
    * @return The root of the Huffman Tree
    */
   public static Node makeHuffTree(PriorityQueue<Node> nodes) {
      while(nodes.size() > 1) {
         //Removes the two highest priority nodes and stores them as left 
         //and right
         Node left = nodes.remove();
         Node right = nodes.remove();
         //Generates variable for the parent node of left and right
         int freqSum = left.freq + right.freq;
         String parItem = left.item + right.item;
         //Creates the parent node
         Node parent = new Node(parItem, freqSum);
         parent.left = left;
         parent.right = right;
         //Parent node added into the priority queue
         nodes.add(parent);
      }
      //Returns the root of the Huffman Tree
      return nodes.peek();
   }//end makeHuffTree()

   /**
    * Generates a priority queue of Nodes from an array that represents the 
    * frequency table
    * @param freqTab Array representing the frequency table
    * @return The frequency table as a priority queue with the highest 
    * priority belonging to the Node with the lowest frequency
    */
   public static PriorityQueue<Node> makePrioQ(Node[] freqTab) {
      PriorityQueue<Node> pq = new PriorityQueue<Node>();
      for(int i = 0; i < freqTab.length; i++) {
         pq.add(freqTab[i]);
      }
      return pq; //Returns frequency table as priority queue
   }//end makePrioQ()
   /**
    * Generates the Huffman codes from a Huffman Tree and stores. Each 
    * character and code is stored in a CodeNode object. Each CodeNode 
    * object is stored in an array that will be output. Each recursive call 
    * add either a "0" or a "1" to the prefix until a leaf is found.
    * @param n The root of the Huffman Tree
    * @param prefix The prefix of each code being generated
    * @param codes The array that each CodeNode will be output into
    * @param counter Array containing a single integer to recursively traverse 
    * the Huffman Tree
    * @return An array of CodeNode objects containing the codes for each character
    */
   public static CodeNode[] getHuffCodes(Node n, String prefix, 
         CodeNode[] codes, int[] counter) {

      //Stores the character of a leaf lode in CodeNode object with the 
      //generated Huffman code and stores the CodeNode in the output array
      if(n.isLeaf() == true) {
         CodeNode cn = new CodeNode(n.item, prefix);
         codes[counter[0]] = cn;
         counter[0]++;
      }
      else {
         //Recursively traverses through the left subtree
         getHuffCodes(n.left, prefix + "0", codes, counter);
         //Recursively traverse through the right subtree
         getHuffCodes(n.right, prefix + "1", codes, counter);
      }
      return codes; //Returns the out array of Codes
   }//end getHuffCodes()
}//end HuffmanEncoder class
