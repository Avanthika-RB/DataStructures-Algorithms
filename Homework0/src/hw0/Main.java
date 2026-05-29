// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
package hw0;
import java.util.*;

public class Main {
    /*
        1. Write a function that computes n!. Write this function without using recursion.
        Example: 4! = 4 * 3 * 2 * 1 = 24
     */
    public static int factorialIterative(int n){
        /*
            Your code here
         */
        return -1;
    }

    /*
        2. Write a recursive function that computes n!.
        Example: 4! = 4 * 3 * 2 * 1 = 24
     */
    public static int factorialRecursive(int n){
        /*
            Your code here
         */
        return -1;
    }

    /*
        3. Reverse the string that is passed as input to the function
        Example: "abCD" --> "DCab"
     */
    public static String reverseString(String s){
        /*
            Your code here
         */
        return "";
    }

    /*
        4. Assume a and b are positive integers. What does
        the function myFunction return in terms of a and b?
        What is the runtime complexity of the function below?
     */
    public static int myFunction(int a, int b){
        if (b == 0){
            return 1;
        } else if(b % 2 == 0){
            int partialRes = myFunction(a, b / 2);
            return partialRes * partialRes;
        }
        int partialRes = myFunction(a, b / 2);
        return a * partialRes * partialRes;
    }
}