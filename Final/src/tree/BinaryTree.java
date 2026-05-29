package tree;

import java.util.*;

public class BinaryTree {

    // Should be made private/protected but public for grading purposes
    public  BinaryNode root;

    public BinaryTree(int value) {
        this.root = new BinaryNode(value);
    }

    public BinaryTree(BinaryNode node) {
        this.root = node;
    }
    public static class BinaryNode {
        public int value;
        public BinaryNode left;
        public BinaryNode right;

        public BinaryNode(int value){
            this.value = value;
        }

        public BinaryNode(int value, BinaryNode left, BinaryNode right){
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return (this.left == null) && (this.right == null);
        }
    }

    /*
        Question 3
     */
    public int pathSum(){
        return helper(root);
    }

    private int helper(BinaryNode node) {
        if (node == null) {
            return 0;
        }
        if (node.isLeaf()) {
            return node.value;
        }
        int leftSum = helper(node.left);
        int rightSum = helper(node.right);
        return node.value + Math.max(leftSum, rightSum);
    }


    /*
        Question 4
     */
    public static BinaryTree reconstructTree(List<Integer> preOrder, List<Integer> inOrder) {
        if (preOrder.isEmpty() || inOrder.isEmpty()) {
            return new BinaryTree(null); //if list empty, return null tree
        }
        if (preOrder.size() == 1) {
            return new BinaryTree(new BinaryNode(preOrder.get(0)));
        }
        int val = preOrder.get(0);
        BinaryNode root = new BinaryNode(val);
        int rootorder = inOrder.indexOf(val);

        List<Integer> left = inOrder.subList(0, rootorder);
        List<Integer> right = inOrder.subList(rootorder + 1, inOrder.size());
        List<Integer> leftbefore = new ArrayList<>();
        List<Integer> rightbefore = new ArrayList<>();
        for (int i = 1; i < preOrder.size(); i++) {
            if (left.contains(preOrder.get(i))) {
                leftbefore.add(preOrder.get(i));
            } else {
                rightbefore.add(preOrder.get(i));
            }
        }
        root.left = reconstructTree(leftbefore, left).root; //recursive
        root.right = reconstructTree(rightbefore, right).root;
        return new BinaryTree(root);
    }


    /*
        Do not modify these methods. Used for testing purposes
     */
    public List<Integer> getPreOrderRepr(){
        List<Integer> preOrder = new ArrayList<>();
        getPreOrderHelper(root, preOrder);
        return preOrder;
    }

    private void getPreOrderHelper(BinaryNode node, List<Integer> preOrder) {
        if(node == null) {
            return;
        }
        preOrder.add(node.value);
        getPreOrderHelper(node.left, preOrder);
        getPreOrderHelper(node.right, preOrder);
    }

    public List<Integer> getInOrderRepr(){
        List<Integer> inOrder = new ArrayList();
        getInOrderHelper(root, inOrder);
        return inOrder;
    }
    private void getInOrderHelper(BinaryNode node, List<Integer> inOrder) {
        if(node == null) {
            return;
        }
        getInOrderHelper(node.left, inOrder);
        inOrder.add(node.value);
        getInOrderHelper(node.right, inOrder);
    }
}
