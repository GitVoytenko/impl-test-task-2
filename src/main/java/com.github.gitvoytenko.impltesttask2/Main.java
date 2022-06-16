package src.main.java.com.github.gitvoytenko.impltesttask2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int NUMBER_OF_CITIES = 10000;

    public static void main(String[] args) throws IOException {
        File path = new File("src/main/resources/test_data.txt");
        BufferedReader br = new BufferedReader(new FileReader(path.getAbsolutePath()));
        String line = br.readLine();
        int source = Integer.parseInt(line);

        for (int testIndex = 0; testIndex < source; testIndex++) {
            String[] citiesInd = new String[NUMBER_OF_CITIES];
            line = br.readLine();
            int countCities = Integer.parseInt(line);
            int matrixSize = countCities + 1;
            Matrix matrix = new Matrix(matrixSize);

            for (int cityIndex = 0; cityIndex < countCities; cityIndex++) {
                line = br.readLine();
                String cityName = line;
                citiesInd[cityIndex] = cityName;
                line = br.readLine();
                int p = Integer.parseInt(line);

                for (int neighborIndex = 0; neighborIndex < p; neighborIndex++) {
                    line = br.readLine();
                    String[] brokenLine = line.split(" ");
                    int cityToConnect = Integer.parseInt(brokenLine[0]);
                    int weightOfConnection = Integer.parseInt(brokenLine[1]);
                    matrix.setEdge(cityIndex, cityToConnect, weightOfConnection);
                }
            }
            line = br.readLine();
            int routesToFind = Integer.parseInt(line);
            for (int routesIndex = 0; routesIndex < routesToFind; routesIndex++) {
                line = br.readLine();
                String[] cityNames = line.split(" ");
                String start = cityNames[0];
                String destination = cityNames[1];
                List<String> list = new ArrayList<>();

                for (String s : citiesInd) {
                    if (s != null) {
                        list.add(s);
                    }
                }

                citiesInd = list.toArray(new String[0]);
                int startIndex = 0;
                int destinationIndex = 0;
                // find the index of the initial city
                for (int i = 0; i < citiesInd.length; i++)
                    if (start.equals(citiesInd[i])) {
                        startIndex = i;
                        break;
                    }
                // find the index of the end of the city
                for (int i = 0; i < citiesInd.length; i++) {
                    if (destination.equals(citiesInd[i])) {
                        destinationIndex = i;
                        break;
                    }
                }
                Integer[] distancesFromSource = matrix.waysOfMinimumCostBetweenPairsOfCities(startIndex);
                int destinationDistance = distancesFromSource[destinationIndex];
                System.out.println(destinationDistance);
            }
        }
        br.close();
    }
}