package engines;

import models.Board;
import models.BoardStatus;
import models.Node;

import java.util.*;

public class IDDFSEngine extends SearchingAlgorithms {
    public Node perform(Node node, Board board, int limit) {
        if (node.getStatus().isSolved()) {
            return node;
        }

        int maxLimit = limit;
        Stack<Node> frontier = new Stack<>();
        Set<BoardStatus> explored = new HashSet<>();
        Stack<Node> backUpStack = new Stack<>();
        backUpStack.add(node);
        while(!backUpStack.isEmpty()) {
            frontier.push(backUpStack.pop());
            while(!frontier.isEmpty()) {
                Node currentNode = frontier.pop();
                explored.add(currentNode.getStatus());
                List<Node> children = getChildren(currentNode, board);
                for (Node child : children) {
                    Set<BoardStatus> childrenMovements = new LinkedHashSet<>(currentNode.getMovements());
                    childrenMovements.add(child.getStatus());
                    child.setMovements(childrenMovements);
                    if (!((explored.contains(child.getStatus()) || frontier.contains(child)))) {
                        if (child.getStatus().isSolved()) {
                            return child;
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
        return null;
    }

}
