import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KruskalAlgo {
    private Graph graph;

    public KruskalAlgo(Graph graph) {
        this.graph = graph;
    }

    public Graph optimize() {

        // Si lavora sulla copia non su originale
        List<Edge> sortedEdges = new ArrayList<>(graph.getEdges());

        // Ordina la copia della lista in base al costo
        sortedEdges.sort(Comparator.comparingInt(Edge::getCost));

        // Grafo che conterrà la soluzione MST
        Graph mstSolution = new Graph();

        // Calcola il numero di nodi per sapere quando fermarti (N-1 archi)
        Set<Integer> allNodes = new HashSet<>();
        for (Edge edge : sortedEdges) {
            allNodes.add(edge.getSource());
            allNodes.add(edge.getDestination());
        }
        int totalNodes = allNodes.size();

        // Itera sugli archi ordinati
        for (Edge candidateEdge : sortedEdges) {
            // Se l'aggiunta dell'arco non crea un ciclo...
            if (!mstSolution.createsCycle(candidateEdge)) {
                // ...aggiungilo alla soluzione.
                mstSolution.addEdge(candidateEdge);
            }

            // Ottimizzazione: se l'albero è completo, esci dal ciclo.
            if (mstSolution.getEdges().size() == totalNodes - 1) {
                break;
            }
        }
        return mstSolution;
    }
}