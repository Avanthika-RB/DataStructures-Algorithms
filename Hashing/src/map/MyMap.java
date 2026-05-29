package map;

public class MyMap<K, V> {
    // Define any attributes here
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node<K, V>[] bucket;
    private int size;
    private static final int initial = 16;
    private static final double LoadFactor = 0.75;

    public MyMap(){
        // Fill in constructor
        bucket = (Node<K, V>[]) new Node[initial];
        size = 0;
    }

    public void put(K key, V value){
        int index = Math.abs(key.hashCode()) % bucket.length;
        Node<K, V> current = bucket[index];
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        Node<K, V> node = new Node<>(key, value, bucket[index]);
        bucket[index] = node;
        size++;
        if ((double)size/bucket.length > LoadFactor) {
            resize();
        }
    }

    public boolean contains(K key){
        int index = Math.abs(key.hashCode()) % bucket.length;
        Node<K, V> current = bucket[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public V remove(K key){
        int index = Math.abs(key.hashCode()) % bucket.length;
        Node<K, V> current = bucket[index]; //initialize current and prev
        Node<K, V> prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    bucket[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public V get(K key){
        int index = Math.abs(key.hashCode()) % bucket.length;
        Node<K, V> current = bucket[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void resize(){
        Node<K, V>[] pastbucket = bucket;
        bucket = (Node<K, V>[]) new Node[pastbucket.length * 2];
        size = 0;
        for (Node<K, V> head : pastbucket) {
            while (head != null) {
                put(head.key, head.value);
                head = head.next;
            }
        }
    }
}
