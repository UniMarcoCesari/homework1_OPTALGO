import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Graph fullInstance = new Graph();
        Graph initialSolution = new Graph();

        // Nomi dei file
        String instanceFilePath = "graph_instance.txt";
        String solutionFilePath = "initial_solution.txt";

        try {
            System.out.print("Caricamento istanza: " + instanceFilePath + "--> ");
            fullInstance.loadFromFile(instanceFilePath);
            System.out.println("OK");

            System.out.print("Caricamento soluzione iniziale: " + solutionFilePath + "--> ");
            initialSolution.loadFromFile(solutionFilePath);
            System.out.println("OK");


            //algoritmo esatto --> ottimo globale
            KruskalAlgo kruskalAlgo = new KruskalAlgo(fullInstance);
            int costo_ottimo = kruskalAlgo.optimize().getTotalCost();
            System.out.println("Ottimo globale (Kruskal) : " + costo_ottimo);

            //algoritmo euristico
            LocalSearch localSearch = new LocalSearch(initialSolution);
            Graph finalSolution = localSearch.optimize(fullInstance);
            System.out.println("Ottimo local (LocalSearch) : " + finalSolution.getTotalCost());

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}