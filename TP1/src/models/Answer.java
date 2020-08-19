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
    private long time;

    public Answer(AnswerStatus status, int depth, int cost, int explored, int frontier, Set<BoardStatus> solution, long time){
        this.status = status;
        this.depth = depth;
        this.cost = cost;
        this. explored = explored;
        this.frontier = frontier;
        this.solution = solution;
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append("STATUS:").append(status).append("\n");
        answer.append("DEPTH:").append(depth).append("\n");
        answer.append("COST:").append(cost).append("\n");
        answer.append("EXPLORED:").append(explored).append("\n");
        answer.append("FRONTIER:").append(frontier).append("\n");
        answer.append("SOLUTION:").append(Arrays.toString(solution.toArray())).append("\n");
        answer.append("TIME:").append((float)(time/1000)).append("seconds\n");

        return "STATUS:" + status + "\n" +
                "DEPTH:" + depth + "\n" +
                "COST:" + cost + "\n" +
                "EXPLORED:" + explored + "\n" +
                "FRONTIER:" + frontier + "\n" +
                "SOLUTION:" + Arrays.toString(solution.toArray()) + "\n";
    }
}