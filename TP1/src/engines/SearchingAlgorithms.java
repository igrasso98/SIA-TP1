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
<<<<<<< HEAD
            int cost = computeCost(node);
            Node newChildren = new Node(newBoardStatus, node.getDepth() + 1, cost);
=======
            Node newChildren = new Node(newBoardStatus, node.getDepth() + 1, node.getCost() + 1);
>>>>>>> new-heuristic
            children.add(newChildren);
        }
        return children;
    }

    private int computeCost(Node currentNode) {
        int aux = 0;
        for (boolean val : currentNode.getStatus().getGoals().values()) {
            if (!val) {
                aux++;
            }
        }
        return currentNode.getCost() + aux;
    }
}

