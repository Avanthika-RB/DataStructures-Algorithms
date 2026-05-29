package Decoder;
import Utils.*;
import java.io.IOException;

public class HuffmanDecoder {
    // Define any helper methods up here.
    public static void decodeFile(String compressedFilePath, String outputFilePath){
        try {
            BufferedBitReader bitReader = new BufferedBitReader(compressedFilePath);
            TreeDeSerializer treeDeserializer = new TreeDeSerializer();//deserialize tree
            HuffmanNode root = treeDeserializer.readHeader(bitReader);
            TextWriter textWriter = new TextWriter(outputFilePath);
            HuffmanNode current = root;
            int bit;
            while ((bit = bitReader.readBit()) != -1) { //read bits, decode file, -1 when all bits have been read
                if (bit == 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
                if (current.isLeaf()) {
                    textWriter.writeChar(current.letter);
                    current = root;
                }
            }
            bitReader.close(); //close reader and writer
            textWriter.close();
        } catch (IOException e) { //needs catch for try statement
            e.printStackTrace();
        }
    }
}
