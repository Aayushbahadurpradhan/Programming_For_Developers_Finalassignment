package QuestionNo7;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class QuestionNo7a {
    private final int[][] a; // first matrix
    private final int[][] b; // second matrix
    private final int[][] result; // result of matrix multiplication
    private final int n; // size of the matrix
    private final int numThreads; // number of threads to use

    public QuestionNo7a(int[][] a, int[][] b, int numThreads) {
        this.a = a;
        this.b = b;
        this.n = a.length;
        this.result = new int[n][n]; // create empty matrix for result
        this.numThreads = numThreads;
    }

    public void multiply() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads); // create thread pool
        // loop through each row and column of the matrix to assign a thread to each element
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                executor.execute(new QuestionNo7a.WorkerThread(i, j)); // assign a thread to compute the result for the element at (i, j)
            }
        }
        executor.shutdown(); // shut down the thread pool
        executor.awaitTermination(1, TimeUnit.HOURS); // wait for all threads to finish
    }

    public int[][] getResult() {
        return result;
    }

    private class WorkerThread implements Runnable {
        private final int row; // row of the element to compute
        private final int col; // column of the element to compute

        public WorkerThread(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void run() {
            int sum = 0;
            // multiply corresponding elements of the two matrices and add to the sum
            for (int i = 0; i < n; i++) {
                sum += a[row][i] * b[i][col];
            }
            result[row][col] = sum; // assign the sum as the value of the element at (row, col) in the result matrix
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[][] a = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        int[][] b = {{9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}};
        QuestionNo7a multiplication = new QuestionNo7a(a, b, 4); // create instance of QuestionNo7a with 4 threads
        multiplication.multiply(); // compute the result matrix
        int[][] result = multiplication.getResult(); // get the result matrix
        for (int[] row : result) {
            System.out.println(Arrays.toString(row)); // print each row of the result matrix
        }
    }
}
