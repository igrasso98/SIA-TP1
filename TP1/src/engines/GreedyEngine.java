package engines;

import Heuristics.Heuristics;
import models.Board;
import models.BoardStatus;
import models.Coordinate;
import models.Node;

import java.util.*;

public class GreedyEngine extends SearchingAlgorithms {

    public Node perform(Node node, Board board, Heuristics heuristic) {
        if (node.getStatus().isSolved()) {
            // return Solution(node);
            return node;
        }

        Queue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(t -> heuristic.compute(t.getStatus())));
        Set<BoardStatus> explored = new HashSet<>();

        frontier.add(node);
        while (!frontier.isEmpty()){
            Node currentNode = frontier.poll();
            explored.add(currentNode.getStatus());
            List<Node> children = getChildren(currentNode, board);
            for(Node child : children){
                Set<BoardStatus> childrenMovements = new LinkedHashSet<>(currentNode.getMovements());
                childrenMovements.add(child.getStatus());
                child.setMovements(childrenMovements);
                if (!((explored.contains(child.getStatus()) || frontier.contains(child)))) {
                    if (child.getStatus().isSolved()) {
                        for(BoardStatus stat : child.getMovements()){
                            board.printBoard(stat);
                        }
//                        return Solution(child);
                        return child;
                    }
                    frontier.add(child);
                }
            }
        }

        return null;
    }

}