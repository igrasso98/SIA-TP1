package models;

import java.util.*;

public class Node {
    private List<Node> children;
    private Set<BoardStatus> movements;
    private BoardStatus status;
    private Integer depth;
    private Integer cost;

    public Node(BoardStatus status, int depth, int cost) {
        this.status = status;
        this.movements = new LinkedHashSet<>();
        this.children = new ArrayList<>();
        this.depth = depth;
        this.cost = cost;
    }

    public BoardStatus getStatus() {
        return status;
    }

    public Set<BoardStatus> getMovements() {
        return movements;
    }

    public void setMovements(Set<BoardStatus> newMovements){
        this.movements = newMovements;
    }

    public void setStatus(BoardStatus status) {
        this.status = status;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Node)) {
            return false;
        }

        Node otherNode = (Node) o;
        return otherNode.children.equals(this.children) && otherNode.depth.equals(this.depth) && otherNode.cost.equals(this.cost) && otherNode.status.equals(this.status);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((children == null) ? 0 : children.hashCode());
        result = prime * result + ((depth == null) ? 0 : depth.hashCode());
        result = prime * result + ((cost == null) ? 0 : cost.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return status.toString();
    }
}
