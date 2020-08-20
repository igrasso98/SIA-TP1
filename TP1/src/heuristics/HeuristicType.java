package heuristics;

public enum HeuristicType {
    EUCLIDEAN(new Euclidean()),
    BOXESTOGOALSMINDISTANCE(new BoxesToGoalsMinDistance()),
    BOXESTOGOALS(new BoxesToGoals()),
    BOXMOVED(new BoxMoved());

    private Heuristics heuristic;
    HeuristicType(Heuristics heuristics) {
        this.heuristic = heuristics;
    }

    public Heuristics getHeuristic() {
        return this.heuristic;
    }

    public static boolean contains(String name) {
        for(HeuristicType h : HeuristicType.values()) {
            if(h.toString().toUpperCase().equals(name.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
