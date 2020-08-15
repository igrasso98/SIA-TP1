package engines;

import models.*;

import java.util.*;

public class BFSEngine extends SearchingAlgorithms {

    private static class BFSNode {
        Node node;
        Set<BoardStatus> movements;

        BFSNode(Node node, Set<BoardStatus> movements) {
            this.node = node;
            this.movements = movements;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            if (!(o instanceof BFSNode)) {
                return false;
            }

            BFSNode otherBfsNode = (BFSNode) o;
            return otherBfsNode.node.getStatus().equals(this.node.getStatus());
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((node.getStatus() == null) ? 0 : node.getStatus().hashCode());
            return result;
        }
    }

    public Node perform(Node node, Board board) {
        if (node.getStatus().isSolved()) {
            // return Solution(node);
            return node;
        }

        Queue<BFSNode> frontiers = new LinkedList<>();
        Set<BoardStatus> explored = new HashSet<>();

        BFSNode root = new BFSNode(node, new HashSet<>());
        root.movements.add(node.getStatus());

        frontiers.offer(root);

        while (!frontiers.isEmpty()) {
            BFSNode currentBfsNode = frontiers.poll();
            Node currentNode = currentBfsNode.node;
            BoardStatus currentStatus = currentNode.getStatus();
            explored.add(currentStatus);
            List<Node> children = getChildren(currentNode, board);
            for (Node child : children) {
                Set<BoardStatus> childrenMovements = new HashSet<>(currentBfsNode.movements);
                childrenMovements.add(child.getStatus());
                BFSNode newChildren = new BFSNode(child, childrenMovements);
                if (!(explored.contains(child.getStatus()) || frontiers.contains(newChildren))) {
                    if (child.getStatus().isSolved()) {
                        System.out.println(child.getDepth());
                        return currentNode;
                    }
                    frontiers.offer(newChildren);
                }
            }
        }
        return null;
    }
}
