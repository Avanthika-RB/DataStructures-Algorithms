package lists;

public class MaxStack<T extends Comparable<T>> {
    private Node<T> top; //top element in stack
    private Node<T> max;

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }


    public MaxStack() {
        top = null; //empty stack
        max = null;
    }

    public T pop() {
        T data = top.data;
        top = top.next;
        if (data.equals(max.data)) {
            max = max.next;
        }
        return data; //return top stack
    }
    public void push(T value) {
        Node<T> node = new Node<>(value);
        node.next = top;
        top = node;
        //add value to top
        if (max == null || value.compareTo(max.data) >= 0) {
            Node<T> newMaxNode = new Node<>(value);
            newMaxNode.next = max;
            max = newMaxNode; //update max
        }
    }
}
