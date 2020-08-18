package models;

import models.Coordinate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class Answer {
    private AnswerStatus status;
    private int depth;
    private int cost;
    private int explored;
    private int frontier;
    private Set<BoardStatus> solution;

    public Answer(AnswerStatus status, int depth, int cost, int explored, int frontier, Set<BoardStatus> solution){
        this.status = status;
        this.depth = depth;
        this.cost = cost;
        this. explored = explored;
        this.frontier = frontier;
        this.solution = solution;
    }

    @Override
    public String toString() {

        return "STATUS:" + status + "\n" +
                "DEPTH:" + depth + "\n" +
                "COST:" + cost + "\n" +
                "EXPLORED:" + explored + "\n" +
                "FRONTIER:" + frontier + "\n" +
                "SOLUTION:" + Arrays.toString(solution.toArray()) + "\n";
    }
}