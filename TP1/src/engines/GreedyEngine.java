package engines;

import heuristics.Heuristics;
import models.*;

import java.util.*;

import static models.AnswerStatus.*;

public class GreedyEngine extends SearchingAlgorithms {

    public Answer perform(Node node, Board board, Heuristics heuristic) {
        Node currentNode = node;
        if (currentNode.getStatus().isSolved()) {
             return new Answer(SUCCESS, currentNode.getDepth(), currentNode.getCost(), new HashSet<>(), new HashSet<>(), currentNode.getMovements());

        }

        Queue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(t -> heuristic.compute(t.getStatus())));
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
                        for (BoardStatus stat : child.getMovements()) {
                            board.printBoard(stat);
                        }
                        return new Answer(SUCCESS, child.getDepth(), child.getCost(), explored, frontier, child.getMovements());
                    }
                    frontier.add(child);
                }
            }
        }
        return new Answer(FAIL, currentNode.getDepth(), currentNode.getCost(), explored, frontier, currentNode.getMovements());
    }

}