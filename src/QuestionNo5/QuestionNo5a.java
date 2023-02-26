package QuestionNo5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionNo5a {
    public static int[][] getBorderLine(int[][] height) {
        // Create a list of x-coordinates of building left and right edges
        List<Integer> xCoords = new ArrayList<>();
        for (int[] h : height) {
            xCoords.add(h[0]);
            xCoords.add(h[1]);
        }
        // Sort the x-coordinates in increasing order
        Collections.sort(xCoords);

        // Initialize an array to store the key points of the border line
        int[][] keyPoints = new int[xCoords.size()][2];

        // Initialize variables for the previous height and the current index in the keyPoints array
        int prevHeight = 0;
        int index = 0;

        // Iterate through the sorted x-coordinates
        for (int x : xCoords) {
            int maxHeight = 0;
            // Iterate through the buildings to find the maximum height at this x-coordinate
            for (int[] h : height) {
                if (x >= h[0] && x < h[1]) {
                    maxHeight = Math.max(maxHeight, h[2]);
                }
            }
            // If the maximum height at this x-coordinate is different from the previous height,
            // add a new key point to the keyPoints array
            if (maxHeight != prevHeight) {
                keyPoints[index][0] = x;
                keyPoints[index][1] = maxHeight;
                prevHeight = maxHeight;
                index++;
            }
        }

        // Trim the keyPoints array to remove unused elements
        int[][] result = new int[index][2];
        for (int i = 0; i < index; i++) {
            result[i] = keyPoints[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] height = {{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}};
        int[][] borderLine = getBorderLine(height);
        for (int[] point : borderLine) {
            System.out.println(point[0] + "," + point[1]);
        }
    }

}