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

//    public Status getStatus() {
//        return status;
//    }
//
//    public Coordinate getCoordinate() {
//        return player;
//    }
//
//    public Set<BoardStatus> getMovements() {
//        return boardHistory;
//    }
//
//    public void setCoordinate(Coordinate coordinate) {
//        this.player = coordinate;
//    }
//
//    public void setMovements(Set<BoardStatus> movements) {
//        this.boardHistory = movements;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
}
