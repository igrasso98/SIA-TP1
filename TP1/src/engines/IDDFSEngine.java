package engines;

import models.Answer;
import models.Board;
import models.BoardStatus;
import models.Node;

import java.util.*;

import static models.AnswerStatus.FAIL;
import static models.AnswerStatus.SUCCESS;

public class IDDFSEngine extends SearchingAlgorithms {
    public Answer perform(Node node, Board board, int limit) {
        Node currentNode = node;
        if (node.getStatus().isSolved()) {
            return new Answer(SUCCESS, currentNode.getDepth(), currentNode.getCost(), new HashSet<>(), new Stack<>(), currentNode.getMovements());
        }

        int maxLimit = limit;
        Stack<Node> frontier = new Stack<>();
        Set<BoardStatus> explored = new HashSet<>();
        Stack<Node> backUpStack = new Stack<>();
        backUpStack.add(node);
        while(!backUpStack.isEmpty()) {
            frontier.push(backUpStack.pop());
            while(!frontier.isEmpty()) {
                currentNode = frontier.pop();
                explored.add(currentNode.getStatus());
                List<Node> children = getChildren(currentNode, board);
                for (Node child : children) {
                    Set<BoardStatus> childrenMovements = new LinkedHashSet<>(currentNode.getMovements());
                    childrenMovements.add(child.getStatus());
                    child.setMovements(childrenMovements);
                    if (!((explored.contains(child.getStatus()) || frontier.contains(child)))) {
                        if (child.getStatus().isSolved()) {
                            return new Answer(SUCCESS, child.getDepth(), child.getCost(), new HashSet<>(), new Stack<>(), child.getMovements());
                        }
                        if(child.getDepth() < maxLimit) {
                            frontier.push(child);
                        } else {
                            backUpStack.add(child);
                        }
                    }
                }
            }
            maxLimit += 30;
        }
        return new Answer(FAIL, currentNode.getDepth(), currentNode.getCost(), new HashSet<>(), new Stack<>(), currentNode.getMovements());
    }

}
