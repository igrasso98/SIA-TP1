package heuristics;
import models.BoardStatus;
import models.Coordinate;
import java.util.Map;
import java.util.Set;

public class Euclidean implements Heuristics {

    @Override
    public int compute(BoardStatus currentStatus) {
        return distanceFromPlayerToBoxes(currentStatus.getPlayer(), currentStatus.getBoxes()) + distanceFromBoxesToGoals(currentStatus.getBoxes(), currentStatus.getGoals());
    }

    private int distanceFromPlayerToBoxes(Coordinate player, Set<Coordinate> boxes) {
        int distance = 0;
        for (Coordinate box : boxes) {
            distance += euclideanDistance(box,player);
        }
        return distance;
    }

    private int distanceFromBoxesToGoals(Set<Coordinate> boxes, Map<Coordinate, Boolean> goals) {
        int distance = 0;
        for (Coordinate goal : goals.keySet()) {
            if (!goals.get(goal)) {
                for (Coordinate box : boxes) {
                    distance += euclideanDistance(box,goal);
                }
            }
        }
        return distance;
    }

    private int euclideanDistance(Coordinate c1, Coordinate c2){
        return (int)(Math.sqrt(Math.pow(c1.getX() - c2.getX(), 2) + Math.pow(c1.getY() - c2.getY(), 2)));
    }

    @Override
    public String toString() {
        return "Euclidean";
    }
}