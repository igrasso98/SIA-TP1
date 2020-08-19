package engines;

import heuristics.Heuristics;
import models.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class SearchingAlgorithms {

    List<Node> getChildren(Node node, Board board) {
        BoardStatus currentStatus = node.getStatus();
        List<Node> children = new ArrayList<>();
        List<Directions> validDirections = board.getValidDirections(currentStatus);
        for (Directions direction : validDirections) {
            BoardStatus newBoardStatus = StatusManager.createStatus(currentStatus, direction);
            Node newChildren = new Node(newBoardStatus, node.getDepth() + 1, node.getCost() + 1);

            children.add(newChildren);
        }
        return children;
    }

    Engines getAlgorithm(String algorithm) {
        algorithm = algorithm.toUpperCase();
        switch (algorithm) {
            case "DFS":
                return new DFSEngine();
            case "BFS":
                return  new BFSEngine();
            case "IDDFS":
                return new IDDFSEngine();
            case "GREEDY":
                return  new GreedyEngine();
            case "IDASTAR":
                return new IDAstarEngine();
            case "ASTAR":
                return new AstarEngine();

        }

        return null;
    }
}

