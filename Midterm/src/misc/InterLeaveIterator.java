package misc;

import java.util.*;

/*
    Question 3:
 */
public class InterLeaveIterator implements Iterator<java.lang.Character> {
    private List<String> string;
    private int current; //index of current string
    private int index; //index of character

    public InterLeaveIterator(List<String> s) {
        this.string = s;
        this.current = 0;
        this.index = 0; // defines attributes
    }
    public boolean hasNext() {
        for (int i = 0; i < string.size(); i++) {
            if (index < string.get(i).length()) {
                return true; //true if string has char at index
            }
        }
        return false;
    }
    public java.lang.Character next() {
        if (!hasNext()) { //throw exception if theres no next element
            throw new NoSuchElementException();
        }
        //increment curr until no characters left in the end
        while (index >= string.get(current).length()) {
            current = (current + 1) % string.size();
        }
        char next = string.get(current).charAt(index);
        current = (current + 1) % string.size();
        if (current == 0) {
            index++;
        }
        return next;
    }
}
