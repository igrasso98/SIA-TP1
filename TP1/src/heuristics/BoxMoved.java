package heuristics;

import models.BoardStatus;
import models.Coordinate;
import java.util.Collection;

public class BoxMoved extends BoxesToGoalsMinDistance implements Heuristics {
    @Override
    public int compute(BoardStatus status) {
        int sum = 0;
        for (Coordinate cord : status.getBoxes()) {
            sum += getNearestGoalDistance(cord, status.getGoals());
        }
        return sum + boxMovesScale(status.getBoxMoved(), status.getGoals().values());
    }

    private int boxMovesScale(int moves, Collection<Boolean> goals){
        int goalsCompleted = 0;
        for(Boolean goal : goals){
            if (goal){
                goalsCompleted++;
            }
        }

        return moves - goalsCompleted ;
    }
}
