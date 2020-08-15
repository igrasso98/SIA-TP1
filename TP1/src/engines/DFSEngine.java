package engines;

import com.sun.org.apache.xpath.internal.operations.Bool;
import models.*;

import java.util.*;

public class DFSEngine implements SearchingAlgorithms {

    @Override
    public Node perform(Node node, Board board) {
        if (node.getStatus().isSolved()) {
            // return Solution(node);
            return node;
        }

        Stack<Node> frontier = new Stack<>();
        HashSet<BoardStatus> explored = new HashSet<>();

        frontier.push(node);

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.pop();
            explored.add(currentNode.getStatus());
            List<Node> children = getChildren(currentNode, board);
            for (Node child : children) {
                if (!((explored.contains(child.getStatus()) || frontier.contains(child)))) {
                    if (child.getStatus().isSolved()) {
//                        return Solution(child);
                        return child;
                    }
                    frontier.push(child);
                }
            }
        }
        return null;
    }

    @Override
    public List<Node> getChildren(Node currentNode, Board board) {
        BoardStatus currentStatus = currentNode.getStatus();
        List<Node> children = new ArrayList<>();
        List<Directions> validDirections = board.getValidDirections(currentStatus);

        for (Directions direction : validDirections) {
            BoardStatus newStatus = createStatus(currentStatus, direction);
            Node newChildren = new Node(newStatus, currentNode.getDepth() + 1, currentNode.getCost() + 1);
            children.add(newChildren);
        }
        return children;
    }

    private BoardStatus createStatus(BoardStatus currentStatus, Directions direction) {
        int newX = currentStatus.getSokoban().getX() + direction.getX();
        int newY = currentStatus.getSokoban().getY() + direction.getY();
        Coordinate newCoordinate = new Coordinate(newX, newY);
        Set<Coordinate> boxesUpdated = new HashSet<>(currentStatus.getBoxes());
        Map<Coordinate, Boolean> goalsUpdated = new HashMap<>(currentStatus.getGoals());
        if (currentStatus.getBoxes().contains(newCoordinate)) {
            newX = newCoordinate.getX() + direction.getX();
            newY = newCoordinate.getY() + direction.getY();
            Coordinate newBoxCoordinate = new Coordinate(newX, newY);
            boxesUpdated.remove(newCoordinate);
            boxesUpdated.add(newBoxCoordinate);

            if (currentStatus.getGoals().containsKey(newBoxCoordinate)) {
                goalsUpdated.replace(newBoxCoordinate, true);
            }
        }
        return new BoardStatus(boxesUpdated, newCoordinate, goalsUpdated);
    }
}
