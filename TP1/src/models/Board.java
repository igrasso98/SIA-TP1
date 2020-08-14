package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {
    private Set<Coordinate> deadlocks;
    private Set<Coordinate> walls;
    private Set<Coordinate> goals;

    public Board() {
        this.deadlocks = new HashSet<>();
        this.walls = new HashSet<>();
        this.goals = new HashSet<>();
    }

    public Set<Coordinate> getDeadlocks() {
        return deadlocks;
    }

    public void setDeadlocks(Set<Coordinate> deadlocks) {
        this.deadlocks = deadlocks;
    }

    public Set<Coordinate> getWalls() {
        return walls;
    }

    public void setWalls(Set<Coordinate> walls) {
        this.walls = walls;
    }

    public Set<Coordinate> getGoals() {
        return goals;
    }

    public void setGoals(Set<Coordinate> goals) {
        this.goals = goals;
    }

    public boolean validCoordinate(Coordinate coordinate, Directions dir) {
        return !deadlocks.contains(coordinate) && !walls.contains(coordinate);
    }

    public List<Directions> getValidDirections(BoardStatus boardStatus) {
        Coordinate player = boardStatus.getPlayer();
        List<Directions> validDirections = new ArrayList<>();
        for (Directions dir : Directions.values()) {
            Coordinate newCoordinate = new Coordinate(player.getX(), player.getY());
            newCoordinate.move(dir);
            //CHEQUEAR DEADLOCK
            if (!walls.contains(newCoordinate)) {
                if (boardStatus.getBoxes().contains(newCoordinate)) {
                    Coordinate newBoxCoordinate = new Coordinate(newCoordinate.getX(), newCoordinate.getY());
                    newBoxCoordinate.move(dir);
                    //CHEQUEAR DEADLOCK
                    if (!walls.contains(newBoxCoordinate) && !boardStatus.getBoxes().contains(newBoxCoordinate)) {
                        validDirections.add(dir);
                    }
                } else {
                    validDirections.add(dir);
                }
            }
        }
        return validDirections;
    }
}
