package engines;

import models.*;

import java.util.*;

public class BFSStorage implements SearchingAlgorithms {

    private class BFSNode {
        Node node;
        Set<BoardStatus> movements;

        BFSNode(Node node, Set<BoardStatus> movements) {
            this.node = node;
            this.movements = movements;
        }
    }

    @Override
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
            if (currentStatus.getBoxes().equals(goals)) {
                return currentNode;
            }
            explored.add(currentStatus);
            List<Node> nodeChildren = getChildren(node, board);
            currentNode.setChildren(nodeChildren);
            for (Node children : nodeChildren) {
                if (!explored.contains(children.getStatus())) {
                    Set<BoardStatus> childrenMovements = new HashSet<>(aux.movements);
                    childrenMovements.add(children.getStatus());
                    BFSNode newChildren = new BFSNode(children, childrenMovements);
                    frontiers.offer(newChildren);
                }
            }
        }
        return null;
    }

    @Override
    public List<Node> getChildren(Node node, Board board) {
        BoardStatus currentStatus = node.getStatus();

        List<Node> children = new ArrayList<>();
        for(Directions dir : Directions.values()) {
           BoardStatus newBoardStatus = StatusValidation.validate(board, currentStatus, dir);
           if(newBoardStatus != null) {
               Node newChildren = new Node(newBoardStatus, node.getDepth()+1, node.getCost()+1);
               children.add(newChildren);
           }
        }
        return children;
    }
}
