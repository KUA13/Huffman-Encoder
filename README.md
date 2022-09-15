# Huffman-Encoder
A program that is able to encode and decode strings using Huffman Encoding.

The main program is the HuffmanEncoder file. This program will prompt the 
user for the file path for the input file, which will either be encoded or 
decoded. It will then ask for the file path of the frequency table and the  
file path for the output file, where the encoded or decoded string will be 
written. If the input file is to be encoded, the file must have only English 
letters, no numbers or special characters. Spaces are fine. If the input file 
will be decoded, the file must contain only 1's and 0's. The user must specify 
whether the input file will be encoded or decoded by entering either "1" or 
"2". Incorrectly selecting encode for encoded text or decode for clear text 
will result in errors. The user must provide a frequency table by 
specifying the file path of a .txt file containing the table. The format 
of each line in the frequency table file must be 'char' - "frequency".

Credit: Kamal Abro
