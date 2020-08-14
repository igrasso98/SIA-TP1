package models;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BoardStatus {
    private Set<Coordinate> boxes;
    private Coordinate player;
    private Map<Coordinate, Boolean> goals;
    private Set<Coordinate> dynamicDeadlocks;

    public BoardStatus(Set<Coordinate> boxes, Map<Coordinate, Boolean> goals, Coordinate player) {
        this.boxes = boxes;
        this. player = player;
        this.goals = goals;
        this.player = player;
        this.dynamicDeadlocks = new HashSet<>();
    }

    public boolean isSolved(){
        return boxes.equals(goals.keySet());
    }

    public Set<Coordinate> getBoxes() {
        return boxes;
    }

    public Map<Coordinate, Boolean> getGoals(){
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

    public Set<Coordinate> getDynamicDeadlocks() {
        return dynamicDeadlocks;
    }

    public boolean validateSolution() {
        for(Coordinate c : goals.keySet()) {
            if(!goals.get(c)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }

        if(!(o instanceof BoardStatus)) {
            return false;
        }

        BoardStatus boardStatus = (BoardStatus) o;
        return boxes.equals(boardStatus.boxes) && player.equals(boardStatus.player) && goals.equals(boardStatus.goals);
    }
}
