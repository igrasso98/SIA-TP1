package models;

import java.util.Map;
import java.util.Set;

public class BoardStatus {
    private Set<Coordinate> boxes;
    private Coordinate player;
    private Map<Coordinate, Boolean> goals;

    private Set<Coordinate> deadlocks;

    public BoardStatus(Set<Coordinate> boxes, Map<Coordinate, Boolean> goals, Coordinate player, Set<Coordinate> deadlocks) {
        this.boxes = boxes;
        this.player = player;
        this.goals = goals;
        this.deadlocks = deadlocks;
    }

    public boolean isSolved() {
        return boxes.equals(goals.keySet());
    }

    public Set<Coordinate> getBoxes() {
        return boxes;
    }

    public Map<Coordinate, Boolean> getGoals() {
        return this.goals;
    }

    public void setBoxes(Set<Coordinate> boxes) {
        this.boxes = boxes;
    }

    public Coordinate getPlayer() {
        return player;
    }

    public void setPlayer(Coordinate player) {
        this.player = player;
    }

    public boolean validateSolution() {
        for (Coordinate c : goals.keySet()) {
            if (!goals.get(c)) {
                return false;
            }
        }
        return true;
    }

    boolean isDeadlock(Coordinate coordinate, Directions direction, Set<Coordinate> walls) {

        for(Directions dir : Directions.values()) {
            if(dir != direction.getOpossite()) {
                Coordinate firstDirection = new Coordinate(coordinate.getX(), coordinate.getY());
                Coordinate secondDirection = new Coordinate(coordinate.getX(), coordinate.getY());
                firstDirection.move(dir);
                secondDirection.move(dir.getPartner());
                if(edgePosition(walls, firstDirection) && edgePosition(walls, secondDirection)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean edgePosition(Set<Coordinate> walls, Coordinate coordinate) {
        return walls.contains(coordinate) || boxes.contains(coordinate);
    }

    @Override
    public String toString() {
        return "(" +
                player.getX() +
                "," +
                player.getY() +
                ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof BoardStatus)) {
            return false;
        }

        BoardStatus boardStatus = (BoardStatus) o;
        return boxes.equals(boardStatus.boxes) && player.equals(boardStatus.player) && goals.equals(boardStatus.goals);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        result = prime * result + ((boxes == null) ? 0 : boxes.hashCode());
        result = prime * result + ((goals == null) ? 0 : goals.hashCode());
        return result;
    }
}