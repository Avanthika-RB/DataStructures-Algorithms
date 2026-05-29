package Utils;

public class TreeDeSerializer {

    public int bitsRead;
    public int bitsToRead;

    public TreeDeSerializer() {
        bitsToRead = 0;
        bitsRead = 0;
    }

    /*
        Get the number of bits that is used to represent the serialized tree.
     */
    public int getSerializedSize(BufferedBitReader br) {
        int size = 0;
        for (int i = 0; i < 32; i++) {
            int bit = IO(br);
            size = (size << 1) | bit;
        }
        return size;
    }

    /*
        Recreate the Huffman tree using a pre-order traversal.
     */
    public HuffmanNode deserializeTree(BufferedBitReader br) {
        if (bitsRead >= bitsToRead) {
            return null;
        }
        int bit = IO(br);
        bitsRead++;
        if (bit == 1) { // Leaf node
            int charCode = 0;
            for (int i = 0; i < 8; i++) {
                charCode = (charCode << 1) | IO(br);
                bitsRead++;
            }
            return new HuffmanNode((char) charCode, 0);
        } else { // Internal node
            HuffmanNode node = new HuffmanNode('\0', 0);
            node.left = deserializeTree(br);
            node.right = deserializeTree(br);
            return node;
        }
    }

    public HuffmanNode readHeader(BufferedBitReader br) {
        this.bitsToRead = getSerializedSize(br);
        HuffmanNode root = deserializeTree(br);
        return root;
    }

    private int IO(BufferedBitReader br) {
        //helper to be able to not change readHeader in any way
        try {
            return br.readBit();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
