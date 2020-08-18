package engines;

import Heuristics.Heuristics;
import models.Board;
import models.BoardStatus;
import models.Node;

import java.util.*;

public class IDAstarEngine extends SearchingAlgorithms {
    public Node perform(Node node, Board board, Heuristics heuristic, int limit) {
        if (node.getStatus().isSolved()) {
            return node;
        }

        int maxLimit = limit;
        Queue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(t -> (heuristic.compute(t.getStatus()) + t.getCost())));
        Set<BoardStatus> explored = new HashSet<>();
        Queue<Node> backUpStack = new PriorityQueue<>(Comparator.comparingInt(t -> (heuristic.compute(t.getStatus()) + t.getCost())));;
        backUpStack.add(node);
        while(!backUpStack.isEmpty()) {
            frontier.add(backUpStack.poll());
            while(!frontier.isEmpty()) {
                Node currentNode = frontier.poll();
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
                            frontier.add(child);
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
