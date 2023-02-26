package QuestionNo1;

import java.util.*;

// Define a Country class to represent a node in the graph
class Country {
    int id; // The node's id
    int time; // The time taken to reach this node
    int cost; // The cost to reach this node

    public Country(int id, int time, int cost) {
        this.id = id;
        this.time = time;
        this.cost = cost;
    }
}

class QuestionNo1a {
    public static int findCheapestRoute(int[][] edges, int[] charges, int source, int destination, int timeConstraint) {
        // Create a graph represented as an adjacency list
        Map<Integer, List<Country>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0]; // The id of the node where the edge originates
            int to = edge[1]; // The id of the node where the edge ends
            int time = edge[2]; // The time taken to travel along the edge
            int cost = charges[to]; // The cost to travel along the edge
            List<Country> list = graph.getOrDefault(from, new ArrayList<>()); // Get the list of nodes adjacent to the source node
            list.add(new Country(to, time, cost)); // Add the new node to the list
            graph.put(from, list); // Put the updated list back into the graph
        }

        // Initialize the distances and visited flags
        int[] distances = new int[charges.length];
        boolean[] visited = new boolean[charges.length];
        Arrays.fill(distances, Integer.MAX_VALUE); // Set all distances to a large value initially
        distances[source] = 0; // Set the distance to the source node to 0

        // Use a priority queue to select the node with the smallest distance
        PriorityQueue<Country> queue = new PriorityQueue<>((a, b) -> a.time - b.time);
        queue.offer(new Country(source, 0, charges[source])); // Add the source node to the queue with a time of 0 and a cost of charges[source]

        // Dijkstra's algorithm with a time constraint
        while (!queue.isEmpty()) {
            Country curr = queue.poll(); // Remove the node with the smallest time from the queue
            if (curr.id == destination) { // If we have reached the destination node, return the cost to get there
                return curr.cost;
            }
            if (visited[curr.id]) { // If we have already visited this node, skip it
                continue;
            }
            visited[curr.id] = true; // Mark this node as visited
            for (Country neighbor : graph.getOrDefault(curr.id, new ArrayList<>())) { // Iterate through the neighbors of the current node
                int newTime = curr.time + neighbor.time; // Calculate the new time to reach the neighbor node
                int newCost = curr.cost + charges[neighbor.id]; // Calculate the new cost to reach the neighbor node
                if (newTime <= timeConstraint && newCost < distances[neighbor.id]) { // If we can reach the neighbor node within the time constraint and the new cost is less than the current distance to the neighbor node, update the distances array and add the neighbor node to the queue
                    distances[neighbor.id] = newCost;
                    queue.offer(new Country(neighbor.id, newTime, newCost));
                }
            }
        }

        return -1; // No path found
    }

    public static void main(String[] args) {
        int a [][]={{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}};
        System.out.println(findCheapestRoute(a, new int[]{10, 2, 3, 25, 25, 4},0,5,14));
    }
}

