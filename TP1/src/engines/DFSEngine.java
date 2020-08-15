package engines;

import models.*;

import java.util.*;

public class DFSEngine extends SearchingAlgorithms {

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
            System.out.println(currentNode.getStatus().toString());
            explored.add(currentNode.getStatus());
            List<Node> children = getChildren(currentNode, board);
            for (Node child : children) {
                if (!((explored.contains(child.getStatus()) || frontier.contains(child)))) {
                    if (child.getStatus().isSolved()) {
//                        return Solution(child);
                        System.out.println(child.getStatus().toString());
                        return child;
                    }
                    frontier.push(child);
                }
            }
        }
        return null;
    }
}
