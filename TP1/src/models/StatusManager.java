package models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StatusManager {
    public static BoardStatus createStatus(BoardStatus currentStatus, Directions direction) {
        int newX = currentStatus.getPlayer().getX() + direction.getX();
        int newY = currentStatus.getPlayer().getY() + direction.getY();
        Coordinate newCoordinate = new Coordinate(newX, newY);
        Set<Coordinate> boxesUpdated = new HashSet<>(currentStatus.getBoxes());
        Map<Coordinate, Boolean> goalsUpdated = new HashMap<>(currentStatus.getGoals());
        if (currentStatus.getBoxes().contains(newCoordinate)) {
            newX = newCoordinate.getX() + direction.getX();
            newY = newCoordinate.getY() + direction.getY();
            Coordinate newBoxCoordinate = new Coordinate(newX, newY);
            boxesUpdated.remove(newCoordinate);
            boxesUpdated.add(newBoxCoordinate);

            if (currentStatus.getGoals().containsKey(newBoxCoordinate)) {
                goalsUpdated.replace(newBoxCoordinate, true);
            }
        }
        return new BoardStatus(boxesUpdated, goalsUpdated, newCoordinate);
    }


}
