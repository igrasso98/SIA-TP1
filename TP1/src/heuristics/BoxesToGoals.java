package heuristics;
import models.Board;
import models.BoardStatus;
import models.Coordinate;
import java.util.Map;
import java.util.Set;

public class BoxesToGoals implements Heuristics {

    @Override
    public int compute(BoardStatus currentStatus) {
        return distanceFromPlayerToBoxes(currentStatus.getPlayer(), currentStatus.getBoxes()) + distanceFromBoxesToGoals(currentStatus.getBoxes(), currentStatus.getGoals());
    }

    private int distanceFromPlayerToBoxes(Coordinate player, Set<Coordinate> boxes) {
        int distance = 0;
        for (Coordinate box : boxes){
            distance += manhattanDistance(box, player);
        }
        return distance;
    }

    private int distanceFromBoxesToGoals(Set<Coordinate> boxes, Map<Coordinate, Boolean> goals) {
        int distance = 0;
        for (Coordinate goal : goals.keySet()) {
            if (!goals.get(goal)) {
                for (Coordinate box : boxes) {
                    if(!goals.containsKey(box)){
                        distance += manhattanDistance(box, goal);
                    }
                }
            }
        }
        return distance;
    }

    private int manhattanDistance(Coordinate c1, Coordinate c2){
        return (Math.abs(c1.getX() - c2.getX()) + Math.abs(c1.getY() - c2.getY()));
    }

    @Override
    public String toString() {
        return "BoxesToGoals";
    }
}