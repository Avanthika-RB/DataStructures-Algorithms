package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T>{
    private class ListNode<T> {
        T item;
        ListNode<T> prev;
        ListNode<T> next;

        ListNode(T item, ListNode<T> prev, ListNode<T> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    private ListNode<T> sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new ListNode<>(null, null, null); //dummy values
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public class LinkedListDequeIterator<T> implements Iterator<T>{
        private LinkedListDeque<T>.ListNode<T> current = (LinkedListDeque<T>.ListNode<T>) sentinel.next;

        public boolean hasNext(){
            return current != sentinel;
        }

        public T next(){
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = current.item;
            current = current.next;
            return item;
        }
    }


    @Override
    public void addFirst(T value){
        ListNode<T> newNode = new ListNode<>(value, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T value){
        ListNode<T> newNode = new ListNode<>(value, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;

    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public T removeFirst(){
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return item;
    }
    @Override
    public T removeLast(){
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return item;

    }

    @Override
    public T get(int index){
        if (index < 0 || index >= size) {
            return null;
        }
        ListNode<T> current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    @Override
    public void printDeque(){
    }

    public LinkedListDequeIterator<T> iterator(){
        return new LinkedListDequeIterator();
    }
}
