package engines;

import heuristics.*;
import models.Answer;
import models.Board;
import models.BoardStatus;
import models.Node;

import java.util.*;

import static models.AnswerStatus.FAIL;
import static models.AnswerStatus.SUCCESS;

public class IDAstarEngine extends SearchingAlgorithms implements Engines {
    @Override
    public Answer perform(Node node, Board board, long timeLimit, Map<String, Object> info) {
        Heuristics heuristic = (Heuristics) info.get("heuristic");
        int limit = (Integer) info.get("limit");
        Node currentNode = node;
        long time = System.currentTimeMillis();
        if (node.getStatus().isSolved()) {
            return new Answer(SUCCESS, currentNode.getDepth(), currentNode.getCost(), 0, 0, currentNode.getMovements(), System.currentTimeMillis() - time);
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
                            return new Answer(SUCCESS, child.getDepth(), child.getCost(), explored.size(), frontier.size(), child.getMovements(), System.currentTimeMillis() - time);
                        }
                        if(heuristic.compute(child.getStatus()) + child.getCost() < maxLimit) {
                            frontier.add(child);
                        } else {
                            backUpStack.add(child);
                        }
                    }
                }
            }
            maxLimit =  heuristic.compute(currentNode.getStatus()) + currentNode.getCost();
        }
        return new Answer(FAIL, currentNode.getDepth(), currentNode.getCost(), explored.size(), frontier.size(), currentNode.getMovements(), System.currentTimeMillis() - time);
    }

    @Override
    public String toString() {
        return "IDASTAR";
    }
}
