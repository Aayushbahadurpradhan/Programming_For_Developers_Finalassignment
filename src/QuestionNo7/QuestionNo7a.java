package QuestionNo7;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class QuestionNo7a {
    private final int[][] a;
    private final int[][] b;
    private final int[][] result;
    private final int n;
    private final int numThreads;

    public QuestionNo7a(int[][] a, int[][] b, int numThreads) {
        this.a = a;
        this.b = b;
        this.n = a.length;
        this.result = new int[n][n];
        this.numThreads = numThreads;
    }

    public void multiply() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                executor.execute(new QuestionNo7a.WorkerThread(i, j));
            }
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
    }

    public int[][] getResult() {
        return result;
    }

    private class WorkerThread implements Runnable {
        private final int row;
        private final int col;

        public WorkerThread(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void run() {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += a[row][i] * b[i][col];
            }
            result[row][col] = sum;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        int[][] a = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        int[][] b = {{9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}};
        QuestionNo7a multiplication = new QuestionNo7a(a, b, 4);
        multiplication.multiply();
        int[][] result = multiplication.getResult();
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}
