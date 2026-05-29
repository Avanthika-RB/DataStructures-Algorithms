package hw0;

import static org.junit.Assert.*;

public class MainTest {

    @org.junit.Test
    public void testFactorialIterative() {
        assertTrue(Main.factorialIterative(1) == 1);
        assertTrue(Main.factorialIterative(6) == 720);
    }

    @org.junit.Test
    public void testFactorialRecursive() {
        assertTrue(Main.factorialRecursive(1) == 1);
        assertTrue(Main.factorialRecursive(6) == 720);
    }

    @org.junit.Test
    public void reverseString() {
        assertTrue(Main.reverseString("abcd").equals("dcba"));
    }
}