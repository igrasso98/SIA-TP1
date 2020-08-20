package engines;

import heuristics.Heuristics;
import models.*;

import java.util.*;

import static models.AnswerStatus.*;

public class GreedyEngine extends SearchingAlgorithms {

    public Answer perform(Node node, Board board, Heuristics heuristic) {
        Node currentNode = node;
        if (currentNode.getStatus().isSolved()) {
            return new Answer(SUCCESS, currentNode.getDepth(), currentNode.getCost(), 0, 0, currentNode.getMovements());
        }
        Queue<Node> frontier = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node t1, Node t2) {
                int v1 = heuristic.compute(t1.getStatus());
                int v2 = heuristic.compute(t2.getStatus());
                return (v1) - (v2);
            }});
        Set<BoardStatus> explored = new HashSet<>();
        frontier.add(currentNode);

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
                    frontier.add(child);
                }
            }
        }
        return new Answer(FAIL, currentNode.getDepth(), currentNode.getCost(), explored.size(), frontier.size(), currentNode.getMovements());
    }

}