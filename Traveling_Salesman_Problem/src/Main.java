import java.util.*;

public class Main
{
    public static void main(String[] args)
    {

        int numberOfCities = 10;
        Graph randomGraph = new Graph(numberOfCities);

        for (int i = 0; i < numberOfCities; i++)
        {
            randomGraph.addCity("City" + i);
        }
        randomGraph.generateRandomDistances();
        randomGraph.printDistances();

        BFS bfs = new BFS(randomGraph);
        LeastCost leastCost = new LeastCost(randomGraph);
        A_Star aStar = new A_Star(randomGraph);

        long startTime = System.nanoTime();
        List<City> bfsResult = bfs.search();
        long endTime = System.nanoTime();
        double bfsCost = calculatePathCost(randomGraph, bfsResult);
        System.out.println("BFS:");
        System.out.println(bfsResult);
        System.out.println("Cost: " + bfsCost);
        System.out.println("Execution time (ms): " + (endTime - startTime) / 1_000_000);

        startTime = System.nanoTime();
        List<City> leastCostResult = leastCost.search();
        endTime = System.nanoTime();
        double leastCostCost = leastCost.pathCost(leastCostResult);
        System.out.println("Least Cost:");
        System.out.println(leastCostResult);
        System.out.println("Cost: " + leastCostCost);
        System.out.println("Execution time (ms): " + (endTime - startTime) / 1_000_000);

        startTime = System.nanoTime();
        List<City> aStarResult = aStar.search();
        endTime = System.nanoTime();
        double aStarCost = calculatePathCost(randomGraph, aStarResult);
        System.out.println("A*:");
        System.out.println(aStarResult);
        System.out.println("Cost: " + aStarCost);
        System.out.println("Execution time (ms): " + (endTime - startTime) / 1_000_000);
    }

    public static double calculatePathCost(Graph graph, List<City> path)
    {
        double cost = 0;
        for (int i = 1; i < path.size(); i++)
        {
            cost += graph.getDistance(path.get(i - 1).index, path.get(i).index);
        }
        return cost;
    }
}
