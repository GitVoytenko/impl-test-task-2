package src.main.java.com.github.gitvoytenko.impltesttask2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int MAX_AMOUNT_OF_CITIES = 10000;

    public static void main(String[] args) throws IOException {

        File path = new File("src/main/resources/test_data.txt");
        // create an object to read data from the file
        BufferedReader br = new BufferedReader(new FileReader(path.getAbsolutePath()));
        // reading the first line of the file (the number of tests)
        String line = br.readLine();
        int numberOfTests = Integer.parseInt(line);

        // test cycle (determining the number of cities)
        for (int testIndex = 0; testIndex < numberOfTests; testIndex++) {
            String[] cities = new String[MAX_AMOUNT_OF_CITIES];
            line = br.readLine();
            int countCities = Integer.parseInt(line);
            int matrixSize = countCities + 1;
            Matrix matrix = new Matrix(matrixSize);

            // find out the name of the city and the number of neighbors and enter it into the array
            for (int cityIndex = 0; cityIndex < countCities; cityIndex++) {
                line = br.readLine();
                String cityName = line;
                cities[cityIndex] = cityName;
                line = br.readLine();
                int numberOfNeighbors = Integer.parseInt(line);

                // reading the neighbor's index and the price of the road to it and entering it into the adjacency matrix
                for (int neighborIndex = 0; neighborIndex < numberOfNeighbors; neighborIndex++) {
                    line = br.readLine();
                    String[] splitLine = line.split(" ");
                    int nearbyCity = Integer.parseInt(splitLine[0]);
                    int costOfWay = Integer.parseInt(splitLine[1]);
                    matrix.setEdge(cityIndex, nearbyCity, costOfWay);
                }
            }

            // creating a list and transfer values from the array of city names into it
            List<String> list = new ArrayList<>();

            for (String s : cities) {
                if (s != null) {
                    list.add(s);
                }
            }

            cities = list.toArray(new String[0]);

            // reading the number of paths found
            line = br.readLine();
            int routesToFind = Integer.parseInt(line);

            // finding out the cities to find the minimum fare between them
            for (int routesIndex = 0; routesIndex < routesToFind; routesIndex++) {
                line = br.readLine();
                String[] cityNames = line.split(" ");
                String start = cityNames[0];
                String destination = cityNames[1];

                int startIndex = 0;
                int destinationIndex = 0;

                // find the index of the initial city
                for (int i = 0; i < cities.length; i++)
                    if (start.equals(cities[i])) {
                        startIndex = i;
                        break;
                    }

                // find the index of the destination city
                for (int i = 0; i < cities.length; i++) {
                    if (destination.equals(cities[i])) {
                        destinationIndex = i;
                        break;
                    }
                }

                Integer[] distancesFromSource = matrix.waysOfMinimumCostBetweenPairsOfCities(startIndex);
                int minCostOfPath = distancesFromSource[destinationIndex];
                System.out.println(minCostOfPath);
            }
        }
        br.close();
    }
}