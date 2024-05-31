import java.util.ArrayList;
import java.util.List;
import java.util.*;

//Clasa pentru reprezentarea grafului de orase si distantele dintre ele
public class Graph
{
    // Matrice pentru stocarea distantelor dintre 2 orase
    private double[][] distances;
    // Lista de orase
    private List<City> cities;

    public Graph(int numberOfCities)
    {
        distances = new double[numberOfCities][numberOfCities];
        cities = new ArrayList<>();
    }

    // Mdetoda pentru adaugarea unui oras in lista
    public void addCity(String name)
    {
        cities.add(new City(name, cities.size()));
    }

    // Metoda pentru adaugarea distantei dintre 2 orase
    public void addDistance(int cityIndex1, int cityIndex2, double distance)
    {
        distances[cityIndex1][cityIndex2] = distance;
        distances[cityIndex2][cityIndex1] = distance;
    }

    // Metoda pentru obtinerea distantei dintre 2 orase
    public double getDistance(int cityIndex1, int cityIndex2)
    {
        return distances[cityIndex1][cityIndex2];
    }

    // Metoda pentru obtinerea listei de orase
    public List<City> getCities()
    {
        return cities;
    }


    // Metoda pentru generarea aleatorie a distantelor intre orase
    public void generateRandomDistances()
    {
        Random random = new Random();
        for (int i = 0; i < cities.size(); i++) {
            for (int j = i + 1; j < cities.size(); j++) {
                double distance = random.nextDouble() * 100; // Se genereaza aleatoriu distante inrtre 1 si 100
                addDistance(i, j, distance);
            }
        }
    }
    public void printDistances()
    {
        System.out.println("Distances between cities:");
        System.out.print("     ");
        for (int i = 0; i < cities.size(); i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < cities.size(); i++) {
            System.out.print(i + " -> ");
            for (int j = 0; j < cities.size(); j++) {
                System.out.print((int)distances[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}


