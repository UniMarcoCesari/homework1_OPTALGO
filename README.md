# Progetto: Algoritmi di Ottimizzazione per MST
Soluzione del Minimum Spanning Tree tramite un algoritmo esatto (Kruskal) e un'euristica di ricerca locale.

## Descrizione del Progetto
Questo repository contiene l'implementazione in Java per l'homework del corso di "Optimization Algorithms". Il progetto si concentra sulla risoluzione del problema del Minimum Spanning Tree (MST) per una data istanza di un grafo non orientato e pesato.

Sono stati implementati due approcci principali, come richiesto dall'assignment:
1.  **Algoritmo Esatto (Kruskal):** Per calcolare la soluzione ottima globale e verificarne il costo.
2.  **Ricerca Locale:** Un'euristica che parte da una soluzione iniziale e la migliora iterativamente fino a raggiungere un ottimo locale.

## Struttura del Progetto
Il codice sorgente è organizzato nelle seguenti classi Java:

-   `Main.java`: Il punto di ingresso del programma. Si occupa di:
    -   Leggere i dati del grafo e della soluzione iniziale dai file di testo.
    -   Eseguire l'algoritmo di Kruskal per trovare la soluzione ottima.
    -   Eseguire l'algoritmo di Ricerca Locale partendo dalla soluzione data.
    -   Stampare a console i risultati e le fasi intermedie.
-   `Graph.java`: Rappresenta la struttura dati del grafo. Contiene una lista di archi (`Edge`) e metodi di utilità per manipolare il grafo, come l'aggiunta di archi, il calcolo del costo totale e la verifica della presenza di cicli.
-   `Edge.java`: Una classe per rappresentare un arco, con un nodo sorgente, un nodo destinazione e un costo.
-   `KruskalAlgo.java`: Contiene la logica per l'algoritmo di Kruskal, che costruisce un MST aggiungendo iterativamente l'arco di costo minimo che non forma un ciclo.
-   `LocalSearch.java`: Contiene la logica per l'algoritmo di ricerca locale. Implementa una strategia di vicinato basata sullo scambio di archi (*single-edge swap*) con una politica *best improvement*.

## Prerequisiti
Per compilare ed eseguire il progetto è necessario avere installato:
-   Java Development Kit (JDK) 11 o superiore.

## Come Eseguire il Progetto

1.  **Setup:** Assicurarsi che tutti i file sorgente (`.java`) e i file di input (`graph_instance.txt`, `initial_solution.txt`) si trovino nella stessa directory.
2.  **Compilazione:** Aprire un terminale o un prompt dei comandi nella directory del progetto e compilare i file sorgente:
    ```sh
    javac *.java
    ```
3.  **Esecuzione:** Dopo la compilazione, eseguire il programma:
    ```sh
    java Main
    ```

L'output del programma mostrerà a console:
-   La verifica della soluzione iniziale.
-   Il costo della soluzione ottima trovata con l'algoritmo di Kruskal.
-   I passaggi intermedi e il risultato finale della ricerca locale.

## Formato dei File di Input
Il progetto utilizza due file di testo come input. Entrambi i file seguono lo stesso formato per ogni riga:
`nodo1 nodo2 costo`

Le righe che iniziano con il carattere `#` sono considerate commenti e vengono ignorate.

-   `graph_instance.txt`: Contiene la definizione completa del grafo.
-   `initial_solution.txt`: Contiene gli archi della soluzione di partenza per la ricerca locale.

## Autore
Marco Cesari (Matricola: 740641)
