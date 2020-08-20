package engines;

import heuristics.*;
import models.Answer;
import models.Board;
import models.BoardStatus;
import models.Node;

import java.util.*;

import static models.AnswerStatus.FAIL;
import static models.AnswerStatus.SUCCESS;

public class IDAstarEngine extends SearchingAlgorithms {
    public Answer perform(Node node, Board board, Heuristics heuristic, int limit) {
        Node currentNode = node;
        if (node.getStatus().isSolved()) {
            return new Answer(SUCCESS, currentNode.getDepth(), currentNode.getCost(), 0, 0, currentNode.getMovements());
        }

        int maxLimit = limit;
        Queue<Node> frontier = new PriorityQueue<>((t1, t2) -> {
            int v1 = heuristic.compute(t1.getStatus()) + t1.getCost();
            int v2 = heuristic.compute(t2.getStatus()) + t2.getCost();
            return (v1) - (v2) ;
        });
        Set<BoardStatus> explored = new HashSet<>();
        Queue<Node> backUpStack = new PriorityQueue<>((t1, t2) -> {
            int v1 = heuristic.compute(t1.getStatus()) + t1.getCost();
            int v2 = heuristic.compute(t2.getStatus()) + t2.getCost();
            return (v1) - (v2) ;
        });

        backUpStack.add(node);
        while (!backUpStack.isEmpty()) {
            frontier.add(backUpStack.poll());
            while (!frontier.isEmpty()) {
                currentNode = frontier.poll();
                explored.add(currentNode.getStatus());
                List<Node> children = getChildren(currentNode, board);
                for (Node child : children) {
                    Set<BoardStatus> childrenMovements = new LinkedHashSet<>(currentNode.getMovements());
                    childrenMovements.add(child.getStatus());
                    child.setMovements(childrenMovements);
                    if (!((explored.contains(child.getStatus()) || frontier.contains(child)))) {
                        if (child.getStatus().isSolved()) {
                            return new Answer(SUCCESS, child.getDepth(), child.getCost(), explored.size(), frontier.size(), child.getMovements());
                        }
                        if (child.getDepth() < maxLimit) {
                            frontier.add(child);
                        } else {
                            backUpStack.add(child);
                        }
                    }
                }
            }
            maxLimit += 30;
        }
        return new Answer(FAIL, currentNode.getDepth(), currentNode.getCost(), explored.size(), frontier.size(), currentNode.getMovements());
    }
}
