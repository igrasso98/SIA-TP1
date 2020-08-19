package heuristics;

public enum HeuristicType {
    EUCLIDEAN(new Euclidean()),
    MANHATTAN(new Manhattan());

    private Heuristics heuristic;
    HeuristicType(Heuristics heuristics) {
        this.heuristic = heuristics;
    }

    public Heuristics getHeuristic() {
        return this.heuristic;
    }
}
