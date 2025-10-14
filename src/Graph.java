import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {
    private final List<Edge> edges;

    public Graph() {
        this.edges = new ArrayList<>();
    }

    public Graph(Graph other) {
        this.edges = new ArrayList<Edge>(other.getEdges());
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    public void removeEdge(Edge edge) {
        this.edges.removeIf(e -> e.getSource() == edge.getSource() &&
                e.getDestination() == edge.getDestination() &&
                e.getCost() == edge.getCost());
    }

    public boolean containsEdge(Edge edge) {
        return edges.stream().anyMatch(e ->
                (e.getSource() == edge.getSource() && e.getDestination() == edge.getDestination()) ||
                        (e.getSource() == edge.getDestination() && e.getDestination() == edge.getSource())
        );
    }

    /**
     * Restituisce la lista di tutti gli archi nel grafo.
     * @return La lista degli archi.
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * Calcola il costo totale del grafo sommando i costi di tutti i suoi archi.
     * @return Il costo totale.
     */
    public int getTotalCost() {
        int totalCost = 0;
        for (Edge edge : edges) {
            totalCost += edge.getCost();
        }
        return totalCost;
    }

    /**
     * Legge un file di testo per popolare il grafo.
     * Il formato atteso è "nodo1 nodo2 costo" per riga.
     * Le righe che iniziano con '#' vengono ignorate come commenti.
     * @param filePath Il percorso del file da leggere.
     * @throws IOException Se si verifica un errore durante la lettura del file.
     */
    public void loadFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                // Ignora le righe vuote e i commenti
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split("\\s+");
                if (parts.length == 3) {
                    int source = Integer.parseInt(parts[0]);
                    int destination = Integer.parseInt(parts[1]);
                    int cost = Integer.parseInt(parts[2]);
                    this.addEdge(new Edge(source, destination, cost));
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph content:\n");
        for (Edge edge : edges) {
            sb.append(edge).append("\n");
        }
        sb.append("Total cost: ").append(getTotalCost());
        return sb.toString();
    }

    public boolean createsCycle(Edge edgeToCheck) {
        int startNode = edgeToCheck.getSource();
        int endNode = edgeToCheck.getDestination();

        // Struttura dati per la visita: una mappa di adiacenza per navigare facilmente
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for (Edge edge : this.edges) {
            adjacencyList.computeIfAbsent(edge.getSource(), k -> new LinkedList<>()).add(edge.getDestination());
            adjacencyList.computeIfAbsent(edge.getDestination(), k -> new LinkedList<>()).add(edge.getSource());
        }

        // Se uno dei due nodi non ha archi collegati, non può esistere un percorso.
        if (!adjacencyList.containsKey(startNode) || !adjacencyList.containsKey(endNode)) {
            return false;
        }

        // Inizializzazione per la visita in ampiezza (BFS)
        Queue<Integer> nodesToVisit = new LinkedList<>();
        Set<Integer> visitedNodes = new HashSet<>();

        nodesToVisit.add(startNode);
        visitedNodes.add(startNode);

        // Esegui la visita
        while (!nodesToVisit.isEmpty()) {
            int currentNode = nodesToVisit.poll();

            // Se abbiamo raggiunto il nodo di destinazione, significa che un percorso esiste.
            if (currentNode == endNode) {
                return true; // L'aggiunta dell'arco creerebbe un ciclo!
            }

            // Esplora i vicini del nodo corrente
            if (adjacencyList.get(currentNode) != null) {
                for (Integer neighbor : adjacencyList.get(currentNode)) {
                    if (!visitedNodes.contains(neighbor)) {
                        visitedNodes.add(neighbor);
                        nodesToVisit.add(neighbor);
                    }
                }
            }
        }

        // Se la visita termina senza aver raggiunto endNode, non c'era un percorso.
        return false; // L'arco può essere aggiunto senza creare cicli.
    }
}