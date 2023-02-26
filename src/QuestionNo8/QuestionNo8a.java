package QuestionNo8;

import java.util.Stack;

public class QuestionNo8a {
    // Define a static method maxSquareArea that takes a 2D matrix of integers as input and returns the maximum area of the largest square containing only 1's
    public static int maxSquareArea(int[][] matrix) {
        int m = matrix.length; // get the number of rows in the matrix
        int n = matrix[0].length; // get the number of columns in the matrix
        int[] heights = new int[n + 1]; // create an array to store the heights of each column
        int maxArea = 0; // variable to store the maximum area of the largest square containing only 1's

        // Loop through each row of the matrix
        for (int i = 0; i < m; i++) {
            Stack<Integer> stack = new Stack<>(); // create a stack to store the indices of the columns
            // Loop through each column of the matrix and update the heights array
            for (int j = 0; j <= n; j++) {
                if (j < n) {
                    if (matrix[i][j] == 0) {
                        heights[j]++;
                    } else {
                        heights[j] = 0;
                    }
                }
                // While the stack is not empty and the height of the current column is less than the height of the previous column,
                // calculate the area of the square containing only 1's that ends at the previous column and update maxArea if necessary
                while (!stack.isEmpty() && heights[stack.peek()] > heights[j]) {
                    int height = heights[stack.pop()];
                    int width = stack.isEmpty() ? j : j - stack.peek() - 1;
                    maxArea = Math.max(maxArea, Math.min(height, width) * Math.min(height, width));
                }
                stack.push(j); // push the index of the current column onto the stack
            }
        }
        return maxArea; // return the maximum area of the largest square containing only 1's
    }

    // Define the main method to test the maxSquareArea method
    public static void main(String[] args) {
        int[][] matrix = {{1, 0, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1}};
        System.out.println(maxSquareArea(matrix)); // print the result of calling maxSquareArea with the test matrix
        // Output: 4
    }

}
