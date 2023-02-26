package QuestionNo1;

import java.util.*;

public class QuestionNo1b {
    public static List<Integer> findImpactedDevices(int[][] edges, int targetDevice) {
        // creating an adjacency list for representation of the graph
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            adjacencyList.putIfAbsent(x, new ArrayList<>()); // add a new empty list for node x if it doesn't already exist
            adjacencyList.putIfAbsent(y, new ArrayList<>()); // add a new empty list for node y if it doesn't already exist
            adjacencyList.get(x).add(y); // add y as a neighbor of x
            adjacencyList.get(y).add(x); // add x as a neighbor of y
        }

        // Remove the edge connecting the failing device to its neighbor.
        List<Integer> neighbors = adjacencyList.get(targetDevice); // get the list of neighbors of the failing device
        neighbors.remove((Integer) targetDevice); // remove the failing device from its neighbor list
        adjacencyList.put(targetDevice, new ArrayList<>()); // remove all edges connected to the failing device


        // starting a BFS from node 0 to locate all the related nodes
        Set<Integer> visited = new HashSet<>(); // create an empty set to keep track of visited nodes
        Queue<Integer> queue = new LinkedList<>(); // create a queue for BFS
        queue.add(0); // start the BFS from node 0
        while (!queue.isEmpty()) {
            int node = queue.poll(); // remove the next node from the queue
            visited.add(node); // mark the node as visited
            List<Integer> nodeNeighbors = adjacencyList.getOrDefault(node, new ArrayList<>()); // get the list of neighbors of the current node
            for (int neighbor : nodeNeighbors) { // for each neighbor of the current node
                if (!visited.contains(neighbor)) { // if the neighbor hasn't been visited before
                    queue.add(neighbor); // add the neighbor to the queue
                }
            }
        }

        //Any node that does not visit the gateway during the BFS gets disconnected.
        List<Integer> impactedDevices = new ArrayList<>(); // create an empty list to store the impacted devices
        for (int node : adjacencyList.keySet()) { // for each node in the graph
            if (!visited.contains(node)) { // if the node hasn't been visited during the BFS
                impactedDevices.add(node); // add the node to the impacted devices list
            }
        }

        return impactedDevices; // return the list of impacted devices
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 6}, {2, 4}, {4, 6}, {4, 5}, {5, 7}}; // define the graph edges as a 2D array
        int targetDevice = 4; // define the failing device
        List<Integer> impactedDevices = findImpactedDevices(edges, targetDevice); // find the impacted devices
        System.out.println("Impacted Device List = "+impactedDevices); // print the impacted devices list
    }
}
