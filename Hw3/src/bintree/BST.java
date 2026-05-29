package bintree;
import java.util.*;
public class BST<T extends Comparable> {
    public static class BSTNode<T extends Comparable>{
        BSTNode left;
        BSTNode right;
        BSTNode parent;
        T item;
        int height;

        public BSTNode(T value){
            item = value;
            height = 0;
        }

        // Get first node in the in-order traversal of the subBST
        public BSTNode getFirst(){
            BSTNode leftRunner = this;
            while(leftRunner.left != null){
                leftRunner = leftRunner.left;
            }
            return leftRunner;
        }

        // Get the successor node in the in-order traversal of the subBST
        public BSTNode getSuccessor(){
            BSTNode traverser = this;
            if(this.right != null){
                return this.right.getFirst();
            }

            while(traverser != traverser.parent.left){
                traverser = traverser.parent;
            }
            return traverser.parent;
        }

        // Check if a node is a leaf
        public boolean isLeaf(){
            return this.left == null && this.right == null;
        }

        public String toString(){
            return item.toString();
        }
    }

    BSTNode root;
    public BST(T rootValue){
        root = new BSTNode(rootValue);
    }

    public BST(){
        root = null;
    }

    // Insert a value into the BST
    public void insert(T newValue){
        root = insert(root, newValue);
    }

    // Helper function to insert a value into the BST
    private BSTNode insert(BSTNode node, T newValue){
        if(node == null){
            return new BSTNode(newValue);
        } else if(node.item.compareTo(newValue) > 0){
            node.left = insert(node.left, newValue);
            node.left.parent = node;
        } else if(node.item.compareTo(newValue) < 0){
            node.right = insert(node.right, newValue);
            node.right.parent = node;
        }
        return node;
    }

    public boolean contains(T value){
        return contains(root, value);
    }

    private boolean contains(BSTNode node, T value){
        if(node == null){
            return false;
        } else if(node.item.compareTo(value) == 0){
            return true;
        } else if(node.item.compareTo(value) > 0){
            return contains(node.left, value);
        }
        return contains(node.right, value);
    }

    protected BSTNode getNodeWithValue(BSTNode node, T value){
        if(node.item.compareTo(value) == 0){
            return node;
        } else if(node.item.compareTo(value) > 0){
            return getNodeWithValue(node.left, value);
        }
        return getNodeWithValue(node.right, value);
    }

    public int height() {
        return height(root);
    }

    private int height(BSTNode node) {
        if(node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /*
        Get all the values that are between the lower and upper bounds (inclusive)
        in the BST
     */
    public List<T> rangeQuery(T lowerBound, T upperBound){
        List<T> result = new ArrayList<>();
        rangeQuery(root, lowerBound, upperBound, result);
        return result;
    }
    private void rangeQuery(BSTNode<T> node, T lowerBound, T upperBound, List<T> result) {
        if (node == null) {
            return;
        }
        if (node.item.compareTo(upperBound) < 0) { //lower than range
            rangeQuery(node.right, lowerBound, upperBound, result);
        }
        if (node.item.compareTo(lowerBound) > 0) { //greater than range
            rangeQuery(node.left, lowerBound, upperBound, result);
        }
        if (node.item.compareTo(lowerBound) >= 0 && node.item.compareTo(upperBound) <= 0) {
            result.add(node.item); //in range
        }
    }


    /*
       Code used from https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/
       Do not delete or modify any the functions below.
     */

    public static int getcol(int h) {
        if (h == 1)
            return 1;
        return getcol(h - 1) + getcol(h - 1) + 1;
    }

    public static void printBST(String[][] M, BSTNode root, int col, int row, int height) {
        if (root == null)
            return;
        if(root.item == null){
            M[row][col] = null;
        } else {
            M[row][col] = root.item.toString();
        }
        printBST(M, root.left, col - (int)Math.pow(2, height - 2), row + 1, height - 1);
        printBST(M, root.right, col + (int)Math.pow(2, height - 2), row + 1, height - 1);
    }

    public String toString() {
        int h = height(root);
        int col = getcol(h);
        String[][] M = new String[h][col];
        StringBuilder result = new StringBuilder();
        printBST(M, root, col / 2, 0, h);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < col; j++) {
                if (M[i][j] == null){
                    result.append("  ");
                } else{
                    result.append(M[i][j] + " ");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }

    public boolean equals(Object o) {
        if(o == null || !(o instanceof BST<?>)) {
            return false;
        }
        return equals(this.root, ((BST)o).root);
    }

    private boolean equals(BSTNode n1, BSTNode n2) {
        if(n1 == null && n2 == null) {
            return true;
        } else if(n1 == null || n2 == null) {
            return false;
        } else if(n1.item.compareTo(n2.item) != 0) {
            return false;
        }
        return equals(n1.left, n2.left) && equals(n1.right, n2.right);
    }
}
