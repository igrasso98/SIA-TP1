package engines;

import models.*;

import java.util.*;

import static models.AnswerStatus.*;

public class BFSEngine extends SearchingAlgorithms implements Engines {
    @Override
    public Answer perform(Node node, Board board, double timeLimit, Map<String, Object> info) {
        Node currentNode = node;
        Answer answer;
        long time = System.currentTimeMillis();
        if (currentNode.getStatus().isSolved()) {
            answer = new Answer(SUCCESS, currentNode.getDepth(), currentNode.getCost(), 0, 0, currentNode.getMovements(), System.currentTimeMillis() - time, info);
            return answer;
        }

        Queue<Node> frontier = new LinkedList<>();
        Set<BoardStatus> explored = new HashSet<>();

        frontier.offer(currentNode);
        double maxTimeLimit = (timeLimit < 0) ? 2*time : timeLimit;


        time = System.currentTimeMillis();
        double diff;
        while ((diff = (System.currentTimeMillis() - time)) <= maxTimeLimit && !frontier.isEmpty()) {
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
                        return new Answer(SUCCESS, child.getDepth(), child.getCost(), explored.size(), frontier.size(), child.getMovements(), diff, info);
                    }
                    frontier.offer(child);
                }
            }
            if(timeLimit < 0) {
                maxTimeLimit += time;
            }
        }
        if(timeLimit > 0 && diff > timeLimit) {
            return new Answer(TIMEOUT, currentNode.getDepth(), currentNode.getCost(), explored.size(), frontier.size(), currentNode.getMovements(), diff, info);
        }
        return new Answer(FAIL, currentNode.getDepth(), currentNode.getCost(), explored.size(), frontier.size(), currentNode.getMovements(), diff, info);
    }

    @Override
    public String toString() {
        return "BFS";
    }
}
