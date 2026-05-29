package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Iterable<T>, Deque<T>{
    // Define any class attributes here
    private T[] items;
    private int head;
    private int tail;
    private int size;

    private static final int initial = 20;

    @SuppressWarnings("unchecked")
    public ArrayDeque(){
        // Fill in constructor
        items = (T[]) new Object[initial];
        head = 0;
        tail = 1;
        size = 0;
    }

    public class ArrayDequeIterator<T> implements Iterator<T> {
        // Define any attributes and constructors for the iterator here
        private int index;
        private int count;

        public ArrayDequeIterator(){
            index = (head + 1) % items.length;
            count = 0;
        }
        @Override
        public boolean hasNext(){
            return count < size;
        }
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T value = (T) items[index];
            index = (index + 1) % items.length;
            count++;
            return value;
        }
    }
    @Override
    public void addFirst(T value){
        if (size == items.length) {
            T[] newArray = (T[]) new Object[items.length * 2];
            int oldIndex = (head + 1) % items.length;
            for (int i = 0; i < size; i++) {
                newArray[i] = items[oldIndex];
                oldIndex = (oldIndex + 1) % items.length;
            }
            items = newArray;
            head = newArray.length - 1;
            tail = size;
        }
        items[head] = value;
        head = (head - 1 + items.length) % items.length; // decrement head index
        size++;
    }
    @Override
    public void addLast(T value){
        if (size == items.length) {
            T[] newArray = (T[]) new Object[items.length * 2];
            int oldIndex = (head + 1) % items.length;
            for (int i = 0; i < size; i++) {
                newArray[i] = items[oldIndex];
                oldIndex = (oldIndex + 1) % items.length;
            }
            items = newArray;
            head = newArray.length - 1;
            tail = size;
        }
        items[tail] = value;
        tail = (tail + 1) % items.length; // increment
        size++;
    }
    @Override
    public T removeFirst(){
        if (isEmpty()) {
            return null;
        }
        head = (head + 1) % items.length; // increment
        T value = items[head];
        items[head] = null;
        size--;
        if (size > 0 && size == items.length / 4) {
            T[] newArray = (T[]) new Object[items.length / 2];
            int oldIndex = (head + 1) % items.length;
            for (int i = 0; i < size; i++) {
                newArray[i] = items[oldIndex];
                oldIndex = (oldIndex + 1) % items.length;
            }
            items = newArray;
            head = newArray.length - 1;
            tail = size;
        }
        return value;
    }
    @Override
    public T removeLast(){
        if (isEmpty()) {
            return null;
        }
        tail = (tail - 1 + items.length) % items.length; //decrement
        T value = items[tail];
        items[tail] = null;
        size--;
        if (size > 0 && size == items.length / 4) {
            T[] newArray = (T[]) new Object[items.length / 2];
            int oldIndex = (head + 1) % items.length;
            for (int i = 0; i < size; i++) {
                newArray[i] = items[oldIndex];
                oldIndex = (oldIndex + 1) % items.length;
            }
            items = newArray;
            head = newArray.length - 1;
            tail = size;
        }
        return value;
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
    public T get(int i){
        int index = (head + 1 + i) % items.length;
        return items[index];
    }

    public void printDeque(){
    }

    public ArrayDequeIterator<T> iterator(){
        return new ArrayDequeIterator();
    }
}