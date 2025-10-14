import java.util.*;

public class LocalSearch {

    private Graph currentSolution;

    public LocalSearch(Graph initialSolution) {
        this.currentSolution = new Graph(initialSolution);
    }

    public Graph optimize(Graph fullGraphInstance) {
        System.out.println("\n--- Starting Local Search ---");
        boolean improvementFound;

        do {
            improvementFound = false;
            Graph bestNeighbor = exploreNeighborhood(currentSolution, fullGraphInstance);

            if (bestNeighbor.getTotalCost() < currentSolution.getTotalCost()) {
                System.out.println("Improvement found! New cost: " + bestNeighbor.getTotalCost() +
                                   " (Previous: " + currentSolution.getTotalCost() + ")"
                );
                currentSolution = bestNeighbor;
                improvementFound = true;
            }

        } while (improvementFound);

        System.out.println("--- Local Search Finished ---");
        System.out.println("Local optimum found with final cost: " + currentSolution.getTotalCost());
        return currentSolution;
    }

    private Graph exploreNeighborhood(Graph solutionToExplore, Graph fullGraph) {
        Graph bestNeighborFound = new Graph(solutionToExplore);

        for (Edge edgeToAdd : fullGraph.getEdges()) {
            if (solutionToExplore.containsEdge(edgeToAdd)) {
                continue;
            }

            List<Edge> path = findPathInTree(solutionToExplore, edgeToAdd.getSource(), edgeToAdd.getDestination());
            if (path == null) {
                continue;
            }

            Edge edgeToRemove = edgeToAdd;
            for (Edge pathEdge : path) {
                //TODO -> se costi uguali da controllare
                if (pathEdge.getCost() > edgeToRemove.getCost()) {
                    edgeToRemove = pathEdge;
                }
            }

            Graph newNeighbor = new Graph(solutionToExplore);
            newNeighbor.addEdge(edgeToAdd);
            newNeighbor.removeEdge(edgeToRemove);

            if (newNeighbor.getTotalCost() < bestNeighborFound.getTotalCost()) {
                bestNeighborFound = newNeighbor;
            }
        }

        return bestNeighborFound;
    }

    private List<Edge> findPathInTree(Graph tree, int startNode, int endNode) {
        Map<Integer, List<Edge>> adjacencyList = new HashMap<>();
        for (Edge edge : tree.getEdges()) {
            adjacencyList.computeIfAbsent(edge.getSource(), k -> new ArrayList<>()).add(edge);
            adjacencyList.computeIfAbsent(edge.getDestination(), k -> new ArrayList<>()).add(edge);
        }

        Queue<Integer> nodesToVisit = new LinkedList<>();
        Map<Integer, Edge> edgeToReachNode = new HashMap<>();
        Set<Integer> visitedNodes = new HashSet<>();

        nodesToVisit.add(startNode);
        visitedNodes.add(startNode);
        edgeToReachNode.put(startNode, null);

        while (!nodesToVisit.isEmpty()) {
            int currentNode = nodesToVisit.poll();
            if (currentNode == endNode) {
                break;
            }

            if (adjacencyList.get(currentNode) == null) continue;

            for (Edge edge : adjacencyList.get(currentNode)) {
                int neighbor = (edge.getSource() == currentNode) ? edge.getDestination() : edge.getSource();
                if (!visitedNodes.contains(neighbor)) {
                    visitedNodes.add(neighbor);
                    nodesToVisit.add(neighbor);
                    edgeToReachNode.put(neighbor, edge);
                }
            }
        }

        if (!edgeToReachNode.containsKey(endNode)) {
            return null;
        }

        List<Edge> path = new ArrayList<>();
        Integer currentNode = endNode;
        while (edgeToReachNode.get(currentNode) != null) {
            Edge edge = edgeToReachNode.get(currentNode);
            path.add(edge);
            currentNode = (edge.getSource() == currentNode) ? edge.getDestination() : edge.getSource();
        }
        Collections.reverse(path);
        return path;
    }
}