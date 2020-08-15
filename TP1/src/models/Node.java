package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Node {
    private Set<BoardStatus> errorStatusHistory;
    private BoardStatus status;
    private ArrayList<Node> children;
    private int depth;
    private int cost;

    public Node(BoardStatus status, int depth, int cost) {
        this.errorStatusHistory = new HashSet<>();
        this.status = status;
        this.children = new ArrayList<>();
        this.depth = depth;
        this.cost = cost;
    }

    public Node(Node node, Directions direction){
        this.errorStatusHistory = node.errorStatusHistory;
        this.status = node.getStatus();
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

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
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
}
