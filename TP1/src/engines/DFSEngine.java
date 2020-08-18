package engines;

import models.*;

import java.util.*;

import static models.AnswerStatus.FAIL;
import static models.AnswerStatus.SUCCESS;

public class DFSEngine extends SearchingAlgorithms {

    public Answer perform(Node node, Board board) {
        Node currentNode = node;
        if (currentNode.getStatus().isSolved()) {
            return new Answer(SUCCESS, currentNode.getDepth(), currentNode.getCost(), new HashSet<>(), new Stack<>(), currentNode.getMovements());
        }

        Stack<Node> frontier = new Stack<>();
        HashSet<BoardStatus> explored = new HashSet<>();

        frontier.push(currentNode);

        while (!frontier.isEmpty()) {
            currentNode = frontier.pop();
            explored.add(currentNode.getStatus());
            List<Node> children = getChildren(currentNode, board);
            for (Node child : children) {
                Set<BoardStatus> childrenMovements = new LinkedHashSet<>(currentNode.getMovements());
                childrenMovements.add(child.getStatus());
                child.setMovements(childrenMovements);
                if (!((explored.contains(child.getStatus()) || frontier.contains(child)))) {
                    if (child.getStatus().isSolved()) {
                        for(BoardStatus stat : child.getMovements()){
                            board.printBoard(stat);
                        }
                        return new Answer(SUCCESS, child.getDepth(), child.getCost(), explored, frontier, child.getMovements());
                    }
                    frontier.push(child);
                }
            }
        }
         return new Answer(FAIL, currentNode.getDepth(), currentNode.getCost(), explored, frontier, currentNode.getMovements());
    }
}
