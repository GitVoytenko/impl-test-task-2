package src.main.java.com.github.gitvoytenko.impltesttask2;

import static java.lang.Math.min;
import static java.util.Arrays.fill;

public class Matrix {
    private final int INFINITY = 10001;
    private final int[][] cost;  // adjacency matrix

    /**
     * @param vertices is the number of vertices in the graph
     */
    public Matrix(int vertices) {
        cost = new int[vertices][vertices];
    }

    /**
     * Set the weight between the neighbors [Cities]
     *
     * @param i point and point j that create an edge [path]
     * @param j point and point i that create an edge [path]
     * @param weight of the edges - non-negative number
     */
    public void setEdge(int i, int j, int weight) {
        cost[i][j] = weight;
    }

    /**
     * Freight cost
     *
     * @param i point and point j that create an edge [path]
     * @param j point and point i that create an edge [path]
     * @return 0 if i and j are the same, infinity, if there is no connection between the edges of the edges
     */
    private int getCost(int i, int j) {
        if (i == j) {
            return 0;
        }
        if (cost[i][j] == 0) {
            return INFINITY;
        }
        return cost[i][j];
    }

    /**
     * Select the closest untagged vertex
     *
     * @param result  assign the 1st vertex a label equal to "source", since it is a vertex
     * @param visited an array of visited cities
     * @return the index of the smallest element of distances, ignoring those in visited.
     */
    private int getUntaggedVertex(Integer[] result, boolean[] visited) {
        int best = -1;
        // sort out the vertices
        for (int i = 0; i < cost.length; i++) {
            // select the closest untagged vertex
            if (!visited[i] && ((best < 0) || (result[i] < result[best]))) {
                best = i;
            }
        }
        return best;
    }

    /**
     * Returns the ways of minimum cost between pairs of cities
     *
     * @param startIndex Initial vertex
     * @return an array of distances between cities
     */
    public Integer[] waysOfMinimumCostBetweenPairsOfCities(int startIndex) {
        boolean[] visited = new boolean[cost.length];// visit the city
        Integer[] result = new Integer[cost.length]; // distance array
        fill(result, INFINITY);                      // set the distance to all vertices INFINITY
        result[startIndex] = startIndex;             // initial vertex

        for (int[] ignored : cost) {
            int cityInd = getUntaggedVertex(result, visited);
            visited[cityInd] = true;
            for (int i = 0; i < cost.length; i++) {
                result[i] = min(result[i], result[cityInd] + getCost(cityInd, i));
            }
        }
        return result;
    }

}
