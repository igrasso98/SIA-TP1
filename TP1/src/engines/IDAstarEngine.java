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
            return new Answer(SUCCESS, currentNode.getDepth(), currentNode.getCost(), new HashSet<>(), new Stack<>(), currentNode.getMovements());
        }

        int maxLimit = limit;
        Queue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(t -> (heuristic.compute(t.getStatus()) + t.getCost())));
        Set<BoardStatus> explored = new HashSet<>();
        Queue<Node> backUpStack = new PriorityQueue<>(Comparator.comparingInt(t -> (heuristic.compute(t.getStatus()) + t.getCost())));;
        backUpStack.add(node);
        while(!backUpStack.isEmpty()) {
            frontier.add(backUpStack.poll());
            while(!frontier.isEmpty()) {
                currentNode = frontier.poll();
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
                            frontier.add(child);
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
