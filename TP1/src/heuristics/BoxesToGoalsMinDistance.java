package heuristics;

import models.*;
import java.util.Map;

public class BoxesToGoalsMinDistance implements Heuristics {

    @Override
    public int compute(BoardStatus status) {
        int sum = 0;
        for(Coordinate cord : status.getBoxes()){
            sum += getNearestGoalDistance(cord,status.getGoals());
        }
        return sum;
    }

    int getNearestGoalDistance(Coordinate coordinate, Map<Coordinate, Boolean> goals) {
        int minDistance = 100000000;
        if(!goals.containsKey(coordinate)){
            for (Coordinate cord : goals.keySet()){
                if(!goals.get(cord)){
                    int distance = manhattanDistance(coordinate, cord);
                    if(distance < minDistance){
                        minDistance = distance;
                    }
                }
            }
        }

        if(minDistance >= 100000000){
            minDistance = 0;
        }
        return minDistance;
    }

    private int manhattanDistance(Coordinate c1, Coordinate c2) {
        return (Math.abs(c1.getX() - c2.getX()) + Math.abs(c1.getY() - c2.getY()));
    }
}
