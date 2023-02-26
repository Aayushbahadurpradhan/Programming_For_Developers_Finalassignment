package QuestionNo7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class QuestionNo7b {
    private static final int NUM_THREADS = 10; // number of threads to use

    public static void main(String[] args) {
        // create an ExecutorService with NUM_THREADS threads
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

        // create a list to store results from each task
        List<Future<String>> results = new ArrayList<>();

        // submit tasks to executor service for crawling 100 different URLs
        for (int i = 0; i < 100; i++) {
            results.add(executorService.submit(new CrawlTask("http://xyz.com/" + i)));
        }

        // wait for all tasks to finish and collect results
        for (Future<String> result : results) {
            try {
                // print the result of each task
                System.out.println(result.get());
            } catch (InterruptedException | ExecutionException e) {
                // print error message if task fails
                System.out.println("Task failed: " + e.getMessage());
            }
        }

        // shut down executor service
        executorService.shutdown();
    }

    private static class CrawlTask implements Callable<String> {
        private final String url;

        public CrawlTask(String url) {
            this.url = url;
        }

        @Override
        public String call() throws Exception {
            // implement web crawling logic here
            return "Crawled " + url;
        }
    }

}

