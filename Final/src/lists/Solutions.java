package lists;

import java.util.*;

public class Solutions {

    /*
        Question 1
     */
    public static int nestedSum(List<NestedObject> nestedList) {
        int ans = 0;
        int x = 1;
        List<NestedObject> current = nestedList;
        while (!current.isEmpty()) {
            List<NestedObject> thelist = new ArrayList<>();
            for (NestedObject nested : current) {
                if (nested.representsInteger()) {
                    ans += nested.getValue()*x;
                } else {
                    thelist.addAll(nested.getNestedList());
                }
            }
            current = thelist;
            x++;
        }
        return ans;
    }

    /*
        Question 9
     */
    public static int getMaxTreasure(int[][] treasureMap) {
        int rows = treasureMap.length;
        int col = treasureMap[0].length;
        int[][] arr = new int[rows][col];
        arr[0][0] = treasureMap[0][0];
        for (int i = 1; i < col; i++) {
            arr[0][i] = arr[0][i - 1] + treasureMap[0][i];
        }
        for (int i = 1; i < rows; i++) {
            arr[i][0] = arr[i - 1][0] + treasureMap[i][0];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < col; j++) {
                arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]) + treasureMap[i][j];
            }
        }
        return arr[rows - 1][col - 1];
    }
    /*
        Question 11
     */
    public static List<Integer> mergeAll(List<List<Integer>> sortedLists) {
        PriorityQueue<int[]> priority = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < sortedLists.size(); i++) { //heap
            if (!sortedLists.get(i).isEmpty()) {
                priority.offer(new int[]{i, 0, sortedLists.get(i).get(0)});
            }
        }
        while (!priority.isEmpty()) { //implements adding the value
            int[] curr = priority.poll();
            result.add(curr[2]);
            int indexoflist = curr[0];
            int nextindex = curr[1] + 1;
            if (nextindex < sortedLists.get(indexoflist).size()) {
                priority.offer(new int[]{
                        indexoflist,
                        nextindex,
                        sortedLists.get(indexoflist).get(nextindex)
                });
            }
        }
        return result;
    }
}