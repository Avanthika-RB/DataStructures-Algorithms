package Utils;

import java.io.IOException;
import java.util.Map;

public class TreeSerializer {
    /*
        Helper function to write the beginning bits of the compressed file. The
        first 32 bits represents an integer K. The next K bits store the serialization
        of the tree. You don't need to modify this function in any way.
     */
    public static void writeHeader(BufferedBitWriter bw, HuffmanNode root){
        int numLeaves = getNumLeaves(root);
        int numNonLeaves = getNumInternalNodes(root);

        int totalbits = numLeaves * 9 + numNonLeaves;
        String bitString = Integer.toBinaryString(totalbits);
        bitString = String.format("%32s", bitString).replaceAll(" ", "0");  // 32-bit Integer
        try {
            for(int i = 0; i < bitString.length(); i ++){
                if (bitString.charAt(i) == '1'){
                    bw.writeBit(1);
                } else {
                    bw.writeBit(0);
                }
            }
            serializeTree(bw, root);
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /*
        Generate the serialized version of the Huffman tree using a pre-order traversal.
        For every internal (non-leaf) node, write a bit value of 0. For leaf nodes, write the bit
        value of 1 followed by the 8-bit representation of the character. Since we are dealing
        with the ASCII character set, we can represent each character using 8 bits.
     */
    public static void serializeTree(BufferedBitWriter bw, HuffmanNode node) throws IOException{
        if (node.isLeaf()) {
            bw.writeBit(1);
            String charBits = String.format("%8s", Integer.toBinaryString(node.letter)).replace(' ', '0');
            for (char bit : charBits.toCharArray()) {
                bw.writeBit(bit == '1' ? 1 : 0);
            }
        } else {
            bw.writeBit(0);
            if (node.left != null) {
                serializeTree(bw, node.left);
            }
            if (node.right != null) {
                serializeTree(bw, node.right);
            }
        }
    }

    /*
        Get the number of leaf nodes in the Huffman tree.
     */
    public static int getNumLeaves(HuffmanNode node){
        if (node == null) {
            return 0;
        }
        if (node.isLeaf()) {
            return 1;
        }
        return getNumLeaves(node.left) + getNumLeaves(node.right);
    }

    /*
        Get the number of non-leaf nodes in the Huffman tree.
     */
    public static int getNumInternalNodes(HuffmanNode node){
        if (node == null || node.isLeaf()) {
            return 0;
        }
        return 1 + getNumInternalNodes(node.left) + getNumInternalNodes(node.right);
    }
}
