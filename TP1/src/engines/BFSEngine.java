package engines;

import models.*;

import java.util.*;

import static models.AnswerStatus.FAIL;
import static models.AnswerStatus.SUCCESS;

public class BFSEngine extends SearchingAlgorithms {
    public Answer perform(Node node, Board board) {
        Node currentNode = node;
        Answer answer;
        if (currentNode.getStatus().isSolved()) {
            answer = new Answer(SUCCESS, currentNode.getDepth(), currentNode.getCost(), new HashSet<>(), new HashSet<>(), currentNode.getMovements());
            return answer;
        }

        Queue<Node> frontier = new LinkedList<>();
        Set<BoardStatus> explored = new HashSet<>();

        frontier.offer(currentNode);

        while (!frontier.isEmpty()) {
            currentNode = frontier.poll();
            BoardStatus currentStatus = currentNode.getStatus();
            explored.add(currentStatus);
            List<Node> children = getChildren(currentNode, board);
            for (Node child : children) {
                Set<BoardStatus> childrenMovements = new LinkedHashSet<>(currentNode.getMovements());
                childrenMovements.add(child.getStatus());
                child.setMovements(childrenMovements);
                if (!(explored.contains(child.getStatus()) || frontier.contains(child))) {
                    if (child.getStatus().isSolved()) {
                        for(BoardStatus stat : child.getMovements()){
                            board.printBoard(stat);
                        }
                        answer = new Answer(SUCCESS, child.getDepth(), child.getCost(), explored, frontier, child.getMovements());
                        return answer;
                    }
                    frontier.offer(child);
                }
            }
        }
        answer = new Answer(FAIL, currentNode.getDepth(), currentNode.getCost(), explored, frontier, currentNode.getMovements());
        return answer;
    }
}
