package engines;

import models.Answer;
import models.Board;
import models.BoardStatus;
import models.Node;

import java.util.*;

import static models.AnswerStatus.FAIL;
import static models.AnswerStatus.SUCCESS;

public class IDDFSEngine extends SearchingAlgorithms implements Engines {
    @Override
    public Answer perform(Node node, Board board, int timeLimit, Map<String, Object> info) {
        int limit = (Integer) info.get("limit");
        Node currentNode = node;
        long time = System.currentTimeMillis();
        if (node.getStatus().isSolved()) {
            return new Answer(SUCCESS, currentNode.getDepth(), currentNode.getCost(), 0, 0, currentNode.getMovements(), System.currentTimeMillis() - time);
        }

        int maxLimit = limit;
        Stack<Node> frontier = new Stack<>();
        Set<BoardStatus> explored = new HashSet<>();
        Stack<Node> backUpStack = new Stack<>();
        backUpStack.add(node);
        while (!backUpStack.isEmpty()) {
            frontier.push(backUpStack.pop());
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
                            return new Answer(SUCCESS, child.getDepth(), child.getCost(), explored.size(),frontier.size(), child.getMovements(), System.currentTimeMillis() - time);
                        }
                        if (child.getDepth() < maxLimit) {
                            frontier.push(child);
                        } else {
                            backUpStack.add(child);
                        }
                    }
                }
            }
            maxLimit += 30;
        }
        return new Answer(FAIL, currentNode.getDepth(), currentNode.getCost(), explored.size(), frontier.size(), currentNode.getMovements(), System.currentTimeMillis() - time);
    }

    @Override
    public String toString() {
        return "IDDFS";
    }

}
