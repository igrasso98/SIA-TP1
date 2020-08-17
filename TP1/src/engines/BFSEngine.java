package engines;

import models.*;

import java.util.*;

public class BFSEngine extends SearchingAlgorithms {
    public Node perform(Node node, Board board) {
        if (node.getStatus().isSolved()) {
            // return Solution(node);
            return node;
        }

        Queue<Node> frontiers = new LinkedList<>();
        Set<BoardStatus> explored = new HashSet<>();

        frontiers.offer(node);

        while (!frontiers.isEmpty()) {
            Node currentNode = frontiers.poll();
//            Node currentNode = currentBfsNode.node;
            BoardStatus currentStatus = currentNode.getStatus();
            explored.add(currentStatus);
            List<Node> children = getChildren(currentNode, board);
            for (Node child : children) {
                Set<BoardStatus> childrenMovements = new LinkedHashSet<>(currentNode.getMovements());
                childrenMovements.add(child.getStatus());
                child.setMovements(childrenMovements);
                if (!(explored.contains(child.getStatus()) || frontiers.contains(child))) {
                    if (child.getStatus().isSolved()) {
                        for(BoardStatus stat : child.getMovements()){
                            board.printBoard(stat);
                        }
                        return child;
                    }
                    frontiers.offer(child);
                }
            }
        }
        return null;
    }
}
