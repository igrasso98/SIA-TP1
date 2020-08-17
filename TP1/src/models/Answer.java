package models;

import models.Coordinate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class Answer {
    private AnswerStatus status;
    private int depth;
    private int cost;
    private Set<BoardStatus> explored;
    private Collection<Node> frontier;
    private Set<BoardStatus> solution;

    public Answer(AnswerStatus status, int depth, int cost, Set<BoardStatus> explored, Collection<Node> frontier, Set<BoardStatus> solution){
        this.status = status;
        this.depth = depth;
        this.cost = cost;
        this. explored = explored;
        this.frontier = frontier;
        this.solution = solution;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append("STATUS:").append(status).append("\n");
        answer.append("DEPTH:").append(depth).append("\n");
        answer.append("COST:").append(cost).append("\n");
        answer.append("EXPLORED:").append(Arrays.toString(explored.toArray())).append("\n");
        answer.append("FRONTIER:").append(Arrays.toString(frontier.toArray())).append("\n");
        answer.append("SOLUTION:").append(Arrays.toString(solution.toArray())).append("\n");

        return answer.toString();
    }
}