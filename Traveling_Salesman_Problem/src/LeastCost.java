import java.util.*;

// Prioritizeaza traseele pe baza costului lor total curent folosind o coada de prioritati
//Traseul cu costul cel mai mic este extins la fiecare pas
public class LeastCost
{
    private final Graph graph;

    public LeastCost(Graph graph)
    {
        this.graph = graph;
    }

    public List<City> search()
    {
        List<City> result = new ArrayList<>(); // Lista pt stocarea rezultatului
        // Coada de prioritate pt trasee, ordonata dupa cost
        PriorityQueue<List<City>> pq = new PriorityQueue<>(Comparator.comparingDouble(this::pathCost));
        List<City> initialPath = new ArrayList<>(); // Lista pt traseul initial
        initialPath.add(graph.getCities().get(0)); // Se adauga orasul de start in traseul initial
        pq.add(initialPath); // Se adauga traseul intial in coada

        while (!pq.isEmpty())
        {
            List<City> path = pq.poll(); // Se extrage traseul cu cel mai mic cost din coada
            // Daca traseul viziteaza toate orasele,se adauga orasul de start la final si se returneaza traseul
            if (path.size() == graph.getCities().size())
            {
                path.add(graph.getCities().get(0));
                result = path;
                break;
            }
            // Se extinde traseul actual cu fiecare oras care nu afost vizitat inca
            for (City city : graph.getCities())
            {
                if (!path.contains(city))
                {
                    List<City> newPath = new ArrayList<>(path);
                    newPath.add(city);
                    pq.add(newPath);
                }
            }
        }
        return result;
    }

    // Metoda pentru calcularea costului unui traseu
    private double pathCost(List<City> path)
    {
        double cost = 0;
        for (int i = 1; i < path.size(); i++)
        {
            // Se adauga distanta dintre 2 orase consecutive
            cost += graph.getDistance(path.get(i - 1).index, path.get(i).index);
        }
        return cost;
    }
}
