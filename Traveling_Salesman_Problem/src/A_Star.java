import java.util.*;

// Folosește o coada de prioritati si o functie euristica pentru a gasi traseul optim
//Functia euristica este distanta directa catre orasul de start
public class A_Star
{
    private final Graph graph;

    public A_Star(Graph graph)
    {
        this.graph = graph;
    }

    public List<City> search()
    {
        List<City> result = new ArrayList<>(); // Lista pt stocarea rezultatului
        // Coada de prioritati pt noduri, ordonata după costul total: costul curent + euristica
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.cost + heuristic(n.path)));
        List<City> initialPath = new ArrayList<>(); // Lista pt traseul initial
        initialPath.add(graph.getCities().get(0));  //Se adauga orasul de start in traseul unitial
        pq.add(new Node(initialPath, 0)); // Se adauga nodul initial in coada

        while (!pq.isEmpty())
        {
            Node node = pq.poll(); // Se extrage nodul cu cel mai mic cost din coada
            List<City> path = node.path; // Se obtine traseul din nod
            // Daca traseul viziteaza toate orasele se adauga orasul de start la final si se returneaza traseul
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
                    List<City> newPath = new ArrayList<>(path); // Se creaza un nou traseu extins
                    newPath.add(city); // Se adauga orasul la traseu
                    // Se calculeaza noul cost al traseului
                    double newCost = node.cost + graph.getDistance(path.get(path.size() - 1).index, city.index);
                    pq.add(new Node(newPath, newCost)); // Se adauga in coada noul nod
                }
            }
        }
        return result; // Returneaza traseul final
    }

    // Functia euristica pt estimarea costului ramas, adica distanta de la ultimul oras din traseu
    // pana la orasul de start
    private double heuristic(List<City> path)
    {
        City start = graph.getCities().get(0); // Obtinem orasul de start
        City last = path.get(path.size() - 1); // Obtinem ultimul oras din traseu
        // Se returneaza distanta dintre ultimul si primul oras
        return graph.getDistance(last.index, start.index);
    }

    class Node {
        List<City> path;
        double cost;

        public Node(List<City> path, double cost) {
            this.path = path;
            this.cost = cost;
        }
    }
}
