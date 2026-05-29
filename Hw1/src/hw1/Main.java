package hw1;
public class Main {

    /*
        5.
     */
    public static void squishArray(int[] numbers){
        int unique = 0;

        // iterates through array
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] != numbers[unique]) {
                unique++;
                numbers[unique] = numbers[i];
            } else {
                // if not unique, then -1
                numbers[i] = -1;
            }
        }
        // Fill in the tail of the array with -1s to account for the elements that 'removed' from the array
        for (int i = unique + 1; i < numbers.length; i++) {
            numbers[i] = -1;
        }
    }

    /*
        6.
     */
    public static String compressString(String s){
        StringBuilder compressed = new StringBuilder();
        char currentChar = s.charAt(0);
        int count = 1;

        // iterate through the string
        for (int i = 1; i < s.length(); i++) {
            // if curr char equals char at i, increment the count
            if (s.charAt(i) == currentChar) {
                count++;
            } else {
                // if currchar is different, append to compressed string
                compressed.append(count).append(currentChar);
                // update currchar, set count back to 1
                currentChar = s.charAt(i);
                count = 1;
            }
        }
        // append to the compressed string
        compressed.append(count).append(currentChar);
        return compressed.toString();
    }

}

