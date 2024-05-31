import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
public class Main
{
    public static void main(String[] args)
    {
        // Testare seturi de date TSP-LIB
        testTSPDataSet("data/att48.tsp", 48);
        testTSPDataSet("data/dantzig42.tsp", 42);

    }

    private static void testTSPDataSet(String filename, int numberOfCities)
    {
        Graph graph = new Graph(numberOfCities);
        List<double[]> coordinates = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            String line;
            boolean coordSection = false;

            while ((line = br.readLine()) != null)
            {
                line = line.trim();

                if (line.startsWith("NODE_COORD_SECTION"))
                {
                    coordSection = true;
                    continue;
                }

                if (coordSection)
                {
                    if (line.equals("EOF"))
                    {
                        break;
                    }
                    String[] parts = line.split("\\s+");
                    if (parts.length < 3)
                    {
                        System.err.println("Invalid line: " + line);
                        continue;
                    }
                    try
                    {
                        double x = Double.parseDouble(parts[1]);
                        double y = Double.parseDouble(parts[2]);
                        coordinates.add(new double[]{x, y});
                        graph.addCity(parts[0]);
                    } catch (NumberFormatException e)
                    {
                        System.err.println("Error parsing line: " + line);
                    }
                }
            }

            if (coordinates.isEmpty())
            {
                System.err.println("No coordinates read from file: " + filename);
                return;
            }
        } catch (IOException e)
        {
            System.err.println("Error reading file: " + filename);
            e.printStackTrace();
            return;
        }

        for (int i = 0; i < numberOfCities; i++)
        {
            for (int j = i + 1; j < numberOfCities; j++)
            {
                double distance = calculateEuclideanDistance(coordinates.get(i), coordinates.get(j));
                graph.addDistance(i, j, distance);
            }
        }

        System.out.println("Testing TSP Data Set: " + filename);
        runAlgorithms(graph);
    }

    private static double calculateEuclideanDistance(double[] coord1, double[] coord2)
    {
        return Math.sqrt(Math.pow(coord1[0] - coord2[0], 2) + Math.pow(coord1[1] - coord2[1], 2));
    }

    private static void runAlgorithms(Graph graph)
    {
        BFS bfsSolver = new BFS(graph);
        LeastCost leastCostSolver = new LeastCost(graph);
        A_Star aStarSolver = new A_Star(graph);

        List<City> bfsResult = bfsSolver.search();
        List<City> leastCostResult = leastCostSolver.search();
        List<City> aStarResult = aStarSolver.search();

        System.out.println("BFS Result: " + bfsResult);
        System.out.println("Least-Cost Search Result: " + leastCostResult);
        System.out.println("A* Search Result: " + aStarResult);
        System.out.println();
    }
}*/



public class Main
{
    public static void main(String[] args) {
        // Generăm un graf cu 10 orașe
        Graph graph = new Graph(10);

        // Adăugăm orașele în graf
        for (int i = 0; i < 10; i++) {
            graph.addCity("City " + i);
        }

        graph.generateRandomDistances();
        graph.printDistances();


        long startTime, endTime;
        BFS bfs = new BFS(graph);
        startTime = System.currentTimeMillis();
        List<City> bfsPath = bfs.search();
        endTime = System.currentTimeMillis();
        System.out.println("BFS Path: " + bfsPath);
        System.out.println("BFS Time: " + (endTime - startTime) + " milliseconds");

        System.out.println();

        LeastCost leastCost = new LeastCost(graph);
        startTime = System.currentTimeMillis();
        List<City> leastCostPath = leastCost.search();
        endTime = System.currentTimeMillis();
        System.out.println("Least Cost Path: " + leastCostPath);
        System.out.println("Least Cost Time: " + (endTime - startTime) + " milliseconds");

        System.out.println();

        A_Star aStar = new A_Star(graph);
        startTime = System.currentTimeMillis();
        List<City> aStarPath = aStar.search();
        endTime = System.currentTimeMillis();
        System.out.println("A* Path: " + aStarPath);
        System.out.println("A* Time: " + (endTime - startTime) + " milliseconds");
    }
}

