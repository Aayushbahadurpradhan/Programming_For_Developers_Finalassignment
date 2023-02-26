package QuestionNo3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionNo3a {
    // Define a method to get the minimum absolute difference between the product of the two largest integers and the product of the two smallest integers in the array
    public static int getMinProductDifference(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums); // Sort the input array in non-decreasing order
        int minDiff = Integer.MAX_VALUE; // Initialize the minimum difference to the largest possible integer value
        for (int i = 0; i < n / 2; i++) { // Iterate over the first half of the array
            int product1 = nums[i] * nums[n - 1 - i]; // Calculate the product of the ith and (n-i-1)th element from the end of the array
            int product2 = nums[i + n / 2] * nums[n - 1 - i - n / 2]; // Calculate the product of the (i+n/2)th and (n-i-1-n/2)th element from the end of the array
            int diff = Math.abs(product1 - product2); // Calculate the absolute difference between the two products
            if (diff < minDiff) { // If the current difference is smaller than the minimum difference seen so far, update the minimum difference
                minDiff = diff;
            }
        }
        return minDiff; // Return the minimum absolute difference between the two products
    }

    public static void main(String [] args) {
        int[] nums = {5, 2, 4, 11}; // Initialize the input array
        int minProductDiff = getMinProductDifference(nums); // Call the getMinProductDifference method to compute the minimum absolute difference between the two products
        System.out.println(minProductDiff); // Print the result to the console
    }

}
