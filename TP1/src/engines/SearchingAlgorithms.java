package engines;

import models.*;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchingAlgorithms {
    List<Node> getChildren(Node node, Board board) {
        BoardStatus currentStatus = node.getStatus();
        List<Node> children = new ArrayList<>();
        List<Directions> validDirections = board.getValidDirections(currentStatus);
        for(Directions direction : validDirections) {
            BoardStatus newBoardStatus = StatusManager.createStatus(currentStatus, direction);
            Node newChildren = new Node(newBoardStatus, node.getDepth()+1, node.getCost()+1);
            children.add(newChildren);
        }
        return children;
    }
}
