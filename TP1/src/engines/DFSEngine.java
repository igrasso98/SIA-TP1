package engines;

import models.*;

import java.util.*;

public class DFSEngine extends SearchingAlgorithms {

    public Node perform(Node node, Board board) {
        if (node.getStatus().isSolved()) {
            // return Solution(node);
            return node;
        }

        Stack<Node> frontier = new Stack<>();
        HashSet<BoardStatus> explored = new HashSet<>();

        frontier.push(node);

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.pop();
            explored.add(currentNode.getStatus());
            List<Node> children = getChildren(currentNode, board);
            for (Node child : children) {
                Set<BoardStatus> childrenMovements = new LinkedHashSet<>(currentNode.getMovements());
                childrenMovements.add(child.getStatus());
                child.setMovements(childrenMovements);
                if (!((explored.contains(child.getStatus()) || frontier.contains(child)))) {
                    if (child.getStatus().isSolved()) {
//                        return Solution(child);
                        for(BoardStatus stat : child.getMovements()){
                            board.printBoard(stat);
                        }
                        return child;
                    }
                    frontier.push(child);
                }
            }
        }
        return null;
    }
}
