package engines;

import models.*;

import java.util.*;

public class BFSEngine extends SearchingAlgorithms {

    private class BFSNode {
        Node node;
        Set<BoardStatus> movements;

        BFSNode(Node node, Set<BoardStatus> movements) {
            this.node = node;
            this.movements = movements;
        }
    }

    public Node perform(Node node, Board board) {
        Queue<BFSNode> frontiers = new LinkedList<>();
        Set<BoardStatus> explored = new HashSet<>();
        Set<Coordinate> goals = board.getGoals();

        BFSNode root = new BFSNode(node, new HashSet<>());
        frontiers.offer(root);

        while (!frontiers.isEmpty()) {
            BFSNode aux = frontiers.poll();
            Node currentNode = aux.node;
            BoardStatus currentStatus = currentNode.getStatus();
            if (currentStatus.isSolved()) {
                return currentNode;
            }
            explored.add(currentStatus);
            List<Node> nodeChildren = getChildren(node, board);
            for (Node children : nodeChildren) {
                if (!explored.contains(children.getStatus())) {
                    Set<BoardStatus> childrenMovements = new HashSet<>(aux.movements);
                    childrenMovements.add(children.getStatus());
                    BFSNode newChildren = new BFSNode(children, childrenMovements);
                    if(!frontiers.contains(newChildren)) {
                        frontiers.offer(newChildren);
                    }
                }
            }
        }
        return null;
    }

}
