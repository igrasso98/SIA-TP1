package models;

import java.util.Map;
import java.util.Set;

public class BoardStatus {
    private Set<Coordinate> boxes;
    private Coordinate player;
    private Map<Coordinate, Boolean> goals;

    public BoardStatus(Set<Coordinate> boxes, Coordinate player,Map<Coordinate, Boolean> goals) {
        this.boxes = boxes;
        this. player = player;
        this.goals = goals;
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

    public Coordinate getSokoban() {
        return player;
    }

    public void setSokoban(Coordinate sokoban) {
        this.player = sokoban;
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
