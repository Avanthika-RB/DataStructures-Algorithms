package bintree;

public class AVL<T extends Comparable> extends BST {
    public AVL(T rootValue){
        super(rootValue);
    }

    public AVL(){
        super();
    }

    @Override
    public void insert(Comparable newValue){
        int leftSkew = 0;
        int rightSkew = 0;

        super.insert(newValue);
        BSTNode nodeInserted = super.getNodeWithValue(root, newValue);
        updateAugmentation(nodeInserted);
        BSTNode unbalancedNode = findLowestUnBalancedNode(nodeInserted);

        if(unbalancedNode != null){
            if(unbalancedNode.left != null){
                leftSkew = skew(unbalancedNode.left);
            }
            if(unbalancedNode.right != null){
                rightSkew = skew(unbalancedNode.right);
            }

            if(rightSkew == 1){
                rotateLeft(unbalancedNode);
            } else if(leftSkew == -1){
                rotateRight(unbalancedNode);
            } else if(rightSkew == -1){
                rotateRight(unbalancedNode.right);
                rotateLeft(unbalancedNode);
            } else if(leftSkew == 1){
                rotateLeft(unbalancedNode.left);
                rotateRight(unbalancedNode);
            }
        }
    }

    /*
        Perform left rotation around the node
     */
    protected void rotateLeft(BSTNode node){
        BSTNode theroot = node.right;
        node.right = theroot.left;
        if (theroot.left != null) {
            theroot.left.parent = node;
        }
        theroot.parent = node.parent;
        if (node.parent == null) {
            root = theroot;
        } else if (node == node.parent.left) {
            node.parent.left = theroot;
        } else {
            node.parent.right = theroot;
        }
        theroot.left = node;
        node.parent = theroot;
        updateAugmentation(node);
        updateAugmentation(theroot);
    }

    /*
        Perform right rotation around the node
     */
    protected void rotateRight(BSTNode node){
        BSTNode theroot = node.left;
        node.left = theroot.right;
        if (theroot.right != null) {
            theroot.right.parent = node;
        }
        theroot.parent = node.parent;
        if (node.parent == null) {
            root = theroot;
        } else if (node == node.parent.right) {
            node.parent.right = theroot;
        } else {
            node.parent.left = theroot;
        }
        theroot.right = node;
        node.parent = theroot;
        updateAugmentation(node);
        updateAugmentation(theroot);
    }

    /*
        Find the lowest ancestor node of the input node that is unbalanced.
     */
    private BSTNode findLowestUnBalancedNode(BSTNode insertedNode){
        BSTNode currentNode = insertedNode;
        while (currentNode != null) {
            if (Math.abs(skew(currentNode)) > 1) {
                return currentNode;
            }
            currentNode = currentNode.parent;
        }
        return null;
    }

    private void updateAugmentation(BSTNode startingNode){
        if(startingNode == null){
            return;
        }
        startingNode.height = height(startingNode);
        updateAugmentation(startingNode.parent);
    }

    private int skew(BSTNode node){
        int rightHeight = 0;
        int leftHeight = 0;

        if(node.right != null){
            rightHeight = 1 + height(node.right);
        }
        if(node.left != null){
            leftHeight = 1 + height(node.left);
        }
        return rightHeight - leftHeight;
    }

    public int height() {
        return height(root);
    }
    private int height(BSTNode node){
        if(node == null){
            return 0;
        }else if(node.isLeaf()){
            return 0;
        } else if(node.left == null){
            return 1 + node.right.height;
        } else if(node.right == null){
            return 1 + node.left.height;
        }
        return 1 + Math.max(node.left.height, node.right.height);
    }
}
