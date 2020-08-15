package models;

import engines.BFSEngine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {
    private Set<BoardStatus> errorStatusHistory;
    private BoardStatus status;
    private List<Node> children;
    private Integer depth;
    private Integer cost;

    public Node(BoardStatus status, int depth, int cost) {
        this.errorStatusHistory = new HashSet<>();
        this.status = status;
        this.children = new ArrayList<>();
        this.depth = depth;
        this.cost = cost;
    }


    public Set<BoardStatus> getErrorStatusHistory() {
        return errorStatusHistory;
    }

    public void setErrorStatusHistory(Set<BoardStatus> errorStatusHistory) {
        this.errorStatusHistory = errorStatusHistory;
    }

    public BoardStatus getStatus() {
        return status;
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
}
