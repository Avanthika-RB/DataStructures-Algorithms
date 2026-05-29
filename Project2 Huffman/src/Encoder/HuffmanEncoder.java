package Encoder;
import Utils.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HuffmanEncoder {
    // Define any helper methods up here.
    private static void theCodes(HuffmanNode node, String code, Map<Character, String> codes) {
        if (node.isLeaf()) { //makes new huffman codes using tree
            codes.put(node.letter, code);
        } else {
            theCodes(node.left, code + "0", codes);
            theCodes(node.right, code + "1", codes);
        }
    }
    public static void encodeFile(String inputFilePath, String outputFilePath){
        try {
            TextReader textReader = new TextReader(inputFilePath);
            Map<Character, Integer> mapFreq = new HashMap<>();
            int charValue;
            while ((charValue = textReader.readNextChar()) != -1) { //read file
                char character = (char) charValue;
                mapFreq.put(character, mapFreq.getOrDefault(character, 0) + 1);
            } //use frequency
            textReader.close();
            PQ<HuffmanNode> tree = new PQ<>(); //use tree
            for (Map.Entry<Character, Integer> entry : mapFreq.entrySet()) {
                tree.insert(new HuffmanNode(entry.getKey(), entry.getValue()));
            }
            while (tree.size() > 1) {
                HuffmanNode left = tree.remove();
                HuffmanNode right = tree.remove();
                HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
                parent.left = left;
                parent.right = right;
                tree.insert(parent);
            }
            HuffmanNode root = tree.remove();

            Map<Character, String> codes = new HashMap<>();
            theCodes(root, "", codes); //use helper method to get new codes

            BufferedBitWriter bitWriter = new BufferedBitWriter(outputFilePath);
            TreeSerializer.writeHeader(bitWriter, root);//write into file
            textReader = new TextReader(inputFilePath); //read file
            while ((charValue = textReader.readNextChar()) != -1) {
                char character = (char)charValue;
                String code = codes.get(character);
                for (char bit : code.toCharArray()) {
                    bitWriter.writeBit(bit == '1' ? 1 : 0); //write into file
                }
            }
            textReader.close(); //close reader and writer
            bitWriter.close();
        } catch (IOException e) { //catch for try statement
            e.printStackTrace();
        }
    }

}
