package engines;

import models.*;

import java.util.*;

public class BFSEngine extends SearchingAlgorithms {

//    private static class BFSNode {
//        Node node;
//        Set<BoardStatus> movements;
//
//        BFSNode(Node node, Set<BoardStatus> movements) {
//            this.node = node;
//            this.movements = movements;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (o == this) {
//                return true;
//            }
//
//            if (!(o instanceof BFSNode)) {
//                return false;
//            }
//
//            BFSNode otherBfsNode = (BFSNode) o;
//            return otherBfsNode.node.getStatus().equals(this.node.getStatus());
//        }
//
//        @Override
//        public int hashCode() {
//            final int prime = 31;
//            int result = 1;
//            result = prime * result + ((node.getStatus() == null) ? 0 : node.getStatus().hashCode());
//            return result;
//        }
//    }

    public Node perform(Node node, Board board) {
        if (node.getStatus().isSolved()) {
            // return Solution(node);
            return node;
        }

        Queue<Node> frontiers = new LinkedList<>();
        Set<BoardStatus> explored = new HashSet<>();

        frontiers.offer(node);

        while (!frontiers.isEmpty()) {
            Node currentNode = frontiers.poll();
//            Node currentNode = currentBfsNode.node;
            BoardStatus currentStatus = currentNode.getStatus();
            explored.add(currentStatus);
            List<Node> children = getChildren(currentNode, board);
            for (Node child : children) {
                Set<BoardStatus> childrenMovements = new LinkedHashSet<>(currentNode.getMovements());
                childrenMovements.add(child.getStatus());
                child.setMovements(childrenMovements);
                if (!(explored.contains(child.getStatus()) || frontiers.contains(child))) {
                    if (child.getStatus().isSolved()) {
                        for(BoardStatus stat : child.getMovements()){
                            board.printBoard(stat);
                        }
                        return child;
                    }
                    frontiers.offer(child);
                }
            }
        }
        return null;
    }
}
