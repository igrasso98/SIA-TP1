package engines;

import models.*;

import java.util.*;

import static models.AnswerStatus.FAIL;
import static models.AnswerStatus.SUCCESS;

public class DFSEngine extends SearchingAlgorithms implements Engines {

    @Override
    public Answer perform(Node node, Board board, long timeLimit, Map<String, Object> info) {
        Node currentNode = node;
        long time = System.currentTimeMillis();
        if (currentNode.getStatus().isSolved()) {
            return new Answer(SUCCESS, currentNode.getDepth(), currentNode.getCost(), 0, 0, currentNode.getMovements(), System.currentTimeMillis() - time);
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
                        return new Answer(SUCCESS, child.getDepth(), child.getCost(), explored.size(), frontier.size(), child.getMovements(), System.currentTimeMillis() - time);
                    }
                    frontier.push(child);
                }
            }
        }
        return new Answer(FAIL, currentNode.getDepth(), currentNode.getCost(), explored.size(), frontier.size(), currentNode.getMovements(), System.currentTimeMillis() - time);
    }

    @Override
    public String toString() {
        return "DFS";
    }
}
