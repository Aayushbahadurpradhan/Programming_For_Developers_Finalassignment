package QuestionNo2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


//Question 2 a)

import java.util.*;

public class QuestionNo2a {

    public static int[] findNearestAncestors(int[] values, int[][] edges) {
        // Initialize a map to store the values and their indices
        Map<Integer, Integer> valueIndices = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            valueIndices.put(values[i], i);
        }

        // Initialize an array to store the nearest ancestor for each node
        int[] nearestAncestors = new int[values.length];

        // For each node, find its nearest ancestor that has a relatively prime value
        for (int i = 0; i < values.length; i++) {
            nearestAncestors[i] = -1; // initialize to -1

            // Starting from the node itself, traverse the tree upwards to find its nearest ancestor
            int current = i;
            while (current != -1) {
                // Check if the current node's value is relatively prime to the value of the original node
                if (gcd(values[current], values[i]) == 1) {
                    nearestAncestors[i] = current; // store the nearest ancestor and break out of the loop
                    break;
                }
                current = getParent(current, edges); // move to the parent node
            }
        }

        // Find the indices of nodes that have non-relative-prime ancestors
        Set<Integer> nonRelativePrimeAncestors = new HashSet<>();
        for (int i = 0; i < values.length; i++) {
            int ancestor = nearestAncestors[i];
            while (ancestor != -1) {
                if (gcd(values[i], values[ancestor]) != 1) {
                    nonRelativePrimeAncestors.add(ancestor);
                }
                ancestor = nearestAncestors[ancestor];
            }
        }

        // Set the nearest ancestor of nodes with non-relative-prime ancestors to -1
        for (int i : nonRelativePrimeAncestors) {
            nearestAncestors[i] = -1;
        }

        return nearestAncestors;
    }

    // Helper method to compute the greatest common divisor of two integers
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // Helper method to get the parent node of a given node
    private static int getParent(int node, int[][] edges) {
        for (int[] edge : edges) {
            if (edge[1] == node) {
                return edge[0];
            }
        }
        return -1; // node is the root
    }

    public static void main(String[] args) {
        int[] values = {3, 2, 6, 6, 4, 7, 12};
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}};

        int[] result = QuestionNo2a.findNearestAncestors(values, edges);

        System.out.println(Arrays.toString(result));
    }
}