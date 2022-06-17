package src.main.java.com.github.gitvoytenko.impltesttask2;

import static java.lang.Math.min;
import static java.util.Arrays.fill;

public class Matrix {
    private final int MAX_AMOUNT = 9999;
    private final int[][] costs;  // adjacency matrix

    /**
     * @param vertices is the number of vertices in the graph
     */
    public Matrix(int vertices) {
        costs = new int[vertices][vertices];
    }

    /**
     * Set the cost between the neighbors [Cities]
     *
     * @param i point and point j that create an edge [path]
     * @param j point and point i that create an edge [path]
     * @param weight [cost] of the edges [path] - non-negative number
     */
    public void setEdge(int i, int j, int weight) {
        costs[i][j] = weight;
    }

    /**
     * Freight cost
     *
     * @param i point and point j that create an edge [path]
     * @param j point and point i that create an edge [path]
     * @return 0 if i and j are the same, MAX_AMOUNT if there is no connection between the edges [paths]
     */
    private int getCost(int i, int j) {
        if (i == j) {
            return 0;
        }
        if (costs[i][j] == 0) {
            return MAX_AMOUNT;
        }
        return costs[i][j];
    }

    /**
     * Select the closest untagged vertex
     *
     * @param result  assign the 1st vertex a label equal to "source", since it is a vertex
     * @param visited an array of visited cities
     * @return the index of the smallest element of distances, ignoring those in visited.
     */
    private int getUntaggedVertex(Integer[] result, boolean[] visited) {
        int markingIndex = -1;
        // sort out the vertices
        for (int i = 0; i < costs.length; i++) {
            // select the closest untagged vertex
            if (!visited[i] && ((markingIndex < 0) || (result[i] < result[markingIndex]))) {
                markingIndex = i;
            }
        }
        return markingIndex;
    }

    /**
     * Returns the ways of minimum cost between pairs of cities
     *
     * @param startIndex Initial vertex
     * @return an array of distances between cities
     */
    public Integer[] waysOfMinimumCostBetweenPairsOfCities(int startIndex) {
        boolean[] visited = new boolean[costs.length];  // visit the city
        Integer[] result = new Integer[costs.length];   // distance array
        fill(result, MAX_AMOUNT);                           // set the distance to all vertices INFINITY
        result[startIndex] = startIndex;                // initial vertex

        for (int[] ignored : costs) {
            int cityInd = getUntaggedVertex(result, visited);
            visited[cityInd] = true;
            for (int i = 0; i < costs.length; i++) {
                result[i] = min(result[i], result[cityInd] + getCost(cityInd, i));
            }
        }
        return result;
    }

}
