package engines;

import heuristics.Heuristics;
import models.Answer;
import models.Board;
import models.BoardStatus;
import models.Node;

import java.util.*;

import static models.AnswerStatus.FAIL;
import static models.AnswerStatus.SUCCESS;

public class AstarEngine extends SearchingAlgorithms {

    public Answer perform(Node node, Board board, Heuristics heuristic) {
        Node currentNode = node;
        if (node.getStatus().isSolved()) {
                return new Answer(SUCCESS, currentNode.getDepth(), currentNode.getCost(), new HashSet<>(), new Stack<>(), currentNode.getMovements());
        }

        Queue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(t -> (heuristic.compute(t.getStatus()) + t.getCost())));
        Set<BoardStatus> explored = new HashSet<>();

        frontier.add(node);
        while (!frontier.isEmpty()){
            currentNode = frontier.poll();
            explored.add(currentNode.getStatus());
            List<Node> children = getChildren(currentNode, board);
            for(Node child : children){
                Set<BoardStatus> childrenMovements = new LinkedHashSet<>(currentNode.getMovements());
                childrenMovements.add(child.getStatus());
                child.setMovements(childrenMovements);
                if (!((explored.contains(child.getStatus()) || frontier.contains(child)))) {
                    if (child.getStatus().isSolved()) {
                        return new Answer(SUCCESS, child.getDepth(), child.getCost(), new HashSet<>(), new Stack<>(), child.getMovements());
                    }
                    frontier.add(child);
                }
            }
        }

        return new Answer(FAIL, currentNode.getDepth(), currentNode.getCost(), new HashSet<>(), new Stack<>(), currentNode.getMovements());
    }
}
