package trie;
import java.util.*;

public class Trie {
    private static final int CHARACTER_SET_SIZE = 128;
    public TrieNode root; // Acts as a sentinel node. Made public for autograder testing purposes

    /*
        Really should be a private nested class, but made public for autograder testing.
     */
    public static class TrieNode {
        private char letter;
        private boolean isKey;
        public TrieNode[] children;

        private TrieNode parent;

        private TrieNode(TrieNode parent, char k) {
            this.letter = k;
            this.isKey = false;
            children = new TrieNode[CHARACTER_SET_SIZE];
            this.parent = parent;
        }

        private boolean isLeaf() {
            for (int i = 0; i < CHARACTER_SET_SIZE; i++) {
                if (children[i] != null) {
                    return false;
                }
            }
            return true;
        }
    }

    public Trie() {
        // Initialize root to take on any value.
        root = new TrieNode(null, 'a');
    }

    // Iteratively add String s to the trie
    public void insert(String s) {
        if (s == null) {
            return;
        }
        int stringLength = s.length();
        TrieNode traverser = root;

        for (int idx = 0; idx < stringLength; idx++) {
            char currentLetter = s.charAt(idx);
            if (traverser.children[currentLetter] == null) {
                TrieNode childNode = new TrieNode(traverser, currentLetter);
                traverser.children[currentLetter] = childNode;
            }
            traverser = traverser.children[currentLetter];
        }
        traverser.isKey = true;
    }

    // Iterative implementation to check if trie contains s
    public boolean contains(String s) {
        if (s == null) {
            return false;
        }
        int stringLength = s.length();
        TrieNode parent = root;

        for (int idx = 0; idx < stringLength; idx++) {
            char currentLetter = s.charAt(idx);
            if (parent.children[currentLetter] == null) {
                return false;
            }
            parent = parent.children[currentLetter];
        }
        return parent.isKey;
    }

    public void delete(String s) {
        if (s == null || !contains(s)) {
            return;
        }
        TrieNode node = root;
        Stack<TrieNode> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            stack.push(node);
            node = node.children[c];
        }
        node.isKey = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            TrieNode child = node;
            node = stack.pop();
            if (child.isLeaf() && !child.isKey) {
                node.children[s.charAt(i)] = null;
            } else {
                break;
            }
        }
    }

    public List<String> collectKeys() {
        List<String> result = new ArrayList<>();
        collectKeys(root, "", result);
        return result;
    }

    private void collectKeys(TrieNode node, String prefix, List<String> result) {
        if (node == null) {
            return;
        }
        if (node.isKey) {
            result.add(prefix);
        }
        for (int i = 0; i < CHARACTER_SET_SIZE; i++) {
            if (node.children[i] != null) {
                collectKeys(node.children[i], prefix +(char) i, result);
            }
        }
    }

    public List<String> keysWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c] == null) {
                return result;
            }
            node = node.children[c];
        }
        collectKeys(node, prefix, result);
        return result;
    }
}
