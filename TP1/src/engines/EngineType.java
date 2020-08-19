package engines;

public enum EngineType {
    DFS(new DFSEngine()),
    BFS(new BFSEngine()),
    IDDFS(new IDDFSEngine()),
    GREEDY(new GreedyEngine()),
    IDASTAR(new IDAstarEngine()),
    ASTAR(new AstarEngine());

    Engines engine;
    EngineType(Engines engine) {
        this.engine = engine;
    }

    public static boolean contains(String name) {
        for(EngineType engine : EngineType.values()) {
            if(engine.engine.toString().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public Engines getEngine() {
        return engine;
    }
}
