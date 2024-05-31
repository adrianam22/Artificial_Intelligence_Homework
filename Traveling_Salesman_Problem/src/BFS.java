import java.util.*;

// Pentru a rezolva problema folosind BFS, se porneste de la orasul initial si extine toate traseele posibile
// pana gaseste un traseu complet
public class BFS
{
    // Graful care are orasele dinstantele dintre ele
    private final Graph graph;

    public BFS(Graph graph)
    {
        this.graph = graph;
    }

    public List<City> search()
    {
        List<City> result = new ArrayList<>(); // Lista pentru a memora rezultatul
        Queue<List<City>> queue = new LinkedList<>(); // Coada pt a pastra traseele neterminate
        List<City> initialPath = new ArrayList<>(); // Lista pt traseul initial
        initialPath.add(graph.getCities().get(0)); // Se adauga orasul de start in traseul unitial
        queue.add(initialPath); // Adauga traseul initial in coada

        while (!queue.isEmpty())
        {
            List<City> path = queue.poll(); // Extrage un traseu din coada
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
                    newPath.add(city); // Adauga orasul la traseu
                    queue.add(newPath); // Adauga traseul extins in coada
                }
            }
        }
        // Se returneaza traseul final
        return result;
    }
}
