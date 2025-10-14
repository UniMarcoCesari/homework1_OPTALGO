public class Edge {
    private final int source;
    private final int destination;
    private final int cost;

    public Edge(int source, int destination, int cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + source +
                ", to=" + destination +
                ", cost=" + cost +
                '}';
    }
}