package engines;

import heuristics.Heuristics;
import models.*;

import java.util.*;
import static models.AnswerStatus.*;

public class AstarEngine extends SearchingAlgorithms implements Engines {

    @Override
    public Answer perform(Node node, Board board, double timeLimit, Map<String, Object> info) {
        Heuristics heuristic = (Heuristics) info.get("heuristic");
        Node currentNode = node;
        long time = System.currentTimeMillis();
        double maxTimeLimit = (timeLimit < 0) ? 2*time : timeLimit;
        if (node.getStatus().isSolved()) {
                return new Answer(SUCCESS, currentNode.getDepth(), currentNode.getCost(), 0, 0, currentNode.getMovements(), System.currentTimeMillis() - time, info);
        }
        Queue<Node> frontier = new PriorityQueue<>((t1, t2) -> {
            int v1 = heuristic.compute(t1.getStatus()) + t1.getCost();
            int v2 = heuristic.compute(t2.getStatus()) + t2.getCost();
            return (v1) - (v2) ;
        });

        Set<BoardStatus> explored = new HashSet<>();
        frontier.add(node);
        time = System.currentTimeMillis();
        double diff;
        while ((diff = (System.currentTimeMillis() - time)) <= maxTimeLimit && !frontier.isEmpty()){
            currentNode = frontier.poll();
            explored.add(currentNode.getStatus());
            List<Node> children = getChildren(currentNode, board);
            for (Node child : children) {
                Set<BoardStatus> childrenMovements = new LinkedHashSet<>(currentNode.getMovements());
                childrenMovements.add(child.getStatus());
                child.setMovements(childrenMovements);
                if (!((explored.contains(child.getStatus()) || frontier.contains(child)))) {
                    if (child.getStatus().isSolved()) {
                        return new Answer(SUCCESS, child.getDepth(), child.getCost(), explored.size(), frontier.size(), child.getMovements(), diff, info);
                    }
                    frontier.add(child);
                }
            }
            if(timeLimit < 0 ) {
                maxTimeLimit += (System.currentTimeMillis() - time);
            }
        }

        if(timeLimit > 0 && diff > timeLimit) {
            return new Answer(TIMEOUT, currentNode.getDepth(), currentNode.getCost(), explored.size(), frontier.size(), currentNode.getMovements(), diff, info);

        }

        return new Answer(FAIL, currentNode.getDepth(), currentNode.getCost(), explored.size(), frontier.size(), currentNode.getMovements(), diff, info);
    }

    @Override
    public String toString() {
        return "ASTAR";
    }
}
