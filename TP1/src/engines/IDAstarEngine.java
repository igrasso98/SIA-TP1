package engines;

import heuristics.*;
import models.Answer;
import models.Board;
import models.BoardStatus;
import models.Node;

import java.util.*;

import static models.AnswerStatus.*;

public class IDAstarEngine extends SearchingAlgorithms implements Engines {
    @Override
    public Answer perform(Node node, Board board, double timeLimit, Map<String, Object> info) {
        Heuristics heuristic = (Heuristics) info.get("heuristic");
        long limit = (Long) info.get("limit");
        Node currentNode = node;
        long time = System.currentTimeMillis();
        if (node.getStatus().isSolved()) {
            return new Answer(SUCCESS, currentNode.getDepth(), currentNode.getCost(), 0, 0, currentNode.getMovements(), System.currentTimeMillis() - time, info, board);
        }

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
        double maxTimeLimit = (timeLimit < 0) ? 2*time : timeLimit;
        time = System.currentTimeMillis();
        double diff;
        while ((diff = (System.currentTimeMillis() - time)) < maxTimeLimit && !backUpStack.isEmpty()) {
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
                            return new Answer(SUCCESS, child.getDepth(), child.getCost(), explored.size(), frontier.size(), child.getMovements(), diff, info, board);
                        }
                        if(heuristic.compute(child.getStatus()) + child.getCost() < limit) {
                            frontier.add(child);
                        } else {
                            backUpStack.add(child);
                        }
                    }
                }
            }
            if(timeLimit < 0) {
                maxTimeLimit += System.currentTimeMillis();
            }
            limit =  heuristic.compute(currentNode.getStatus()) + currentNode.getCost();
        }
        if(timeLimit >= 0 && diff > timeLimit) {
            return new Answer(TIMEOUT, currentNode.getDepth(), currentNode.getCost(), explored.size(), backUpStack.size(), currentNode.getMovements(), diff, info, board);
        }
        return new Answer(FAIL, currentNode.getDepth(), currentNode.getCost(), explored.size(), frontier.size(), currentNode.getMovements(), diff, info, board);
    }

    @Override
    public String toString() {
        return "IDASTAR";
    }
}
