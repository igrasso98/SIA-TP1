package engines;

import models.Answer;
import models.Board;
import models.BoardStatus;
import models.Node;

import java.util.*;

import static models.AnswerStatus.FAIL;
import static models.AnswerStatus.SUCCESS;
import static models.AnswerStatus.TIMEOUT;

public class IDDFSEngine extends SearchingAlgorithms implements Engines {
    @Override
    public Answer perform(Node node, Board board, double timeLimit, Map<String, Object> info) {
        long limit = (Long) info.get("limit");
        limit = (limit < 0 ? Long.MAX_VALUE : limit);
        long step = (Long) info.get("step");

        Node currentNode = node;
        long time = System.currentTimeMillis();
        if (node.getStatus().isSolved()) {
            return new Answer(SUCCESS, currentNode.getDepth(), currentNode.getCost(), 0, 0, currentNode.getMovements(), System.currentTimeMillis() - time, info, board);
        }

        Stack<Node> frontier = new Stack<>();
        Set<BoardStatus> explored = new HashSet<>();
        Stack<Node> backUpStack = new Stack<>();
        backUpStack.add(node);
        time = System.currentTimeMillis();
        double maxTimeLimit = (timeLimit < 0) ? 2*time : timeLimit;

        double diff;
        while ((diff = (System.currentTimeMillis() - time)) <= maxTimeLimit && !backUpStack.isEmpty()) {
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
                            return new Answer(SUCCESS, child.getDepth(), child.getCost(), explored.size(), frontier.size(), child.getMovements(), diff, info, board);
                        }
                        if(child.getDepth() < limit) {
                            frontier.push(child);
                        } else {
                            backUpStack.add(child);
                        }
                    }
                }
            }
            limit += (step < 0 ? 0 : step);
            if(timeLimit < 0) {
                maxTimeLimit += System.currentTimeMillis();
            }
        }
        if(timeLimit > 0 && diff > timeLimit) {
            return new Answer(TIMEOUT, currentNode.getDepth(), currentNode.getCost(), explored.size(), frontier.size(), currentNode.getMovements(), diff, info, board);
        }
        return new Answer(FAIL, currentNode.getDepth(), currentNode.getCost(), explored.size(), frontier.size(), currentNode.getMovements(), diff, info, board);
    }

    @Override
    public String toString() {
        return "IDDFS";
    }

}
