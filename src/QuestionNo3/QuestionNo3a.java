package QuestionNo3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionNo3a {

    public static int getMinProductDifference(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n / 2; i++) {
            int product1 = nums[i] * nums[n - 1 - i];
            int product2 = nums[i + n / 2] * nums[n - 1 - i - n / 2];
            int diff = Math.abs(product1 - product2);
            if (diff < minDiff) {
                minDiff = diff;
            }
        }
        return minDiff;
    }

    public static void main(String [] args) {
        int[] nums = {5, 2, 4, 11};
        int minProductDiff = getMinProductDifference(nums);
        System.out.println(minProductDiff);
    }

}
