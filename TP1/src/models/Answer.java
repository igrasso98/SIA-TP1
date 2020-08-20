package models;

import com.sun.xml.ws.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class Answer {
    private AnswerStatus status;
    private int depth;
    private int cost;
    private int explored;
    private int frontier;
    private Set<BoardStatus> solution;
    private double time;
    private Map<String, Object> levelInfo;
    private Board board;
    public Answer(AnswerStatus status, int depth, int cost, int explored, int frontier, Set<BoardStatus> solution, double time, Map<String, Object> levelInfo, Board board){
        this.status = status;
        this.depth = depth;
        this.cost = cost;
        this. explored = explored;
        this.frontier = frontier;
        this.solution = solution;
        this.time = time;
        this.levelInfo = levelInfo;
        this.board = board;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append("\nInitial Parameters:\n");
        printParameters(answer);
        for(BoardStatus bs : solution) {
            board.printBoard(bs);
        }
        answer.append("\nResults:\n");
        answer.append("* Status:").append(status).append("\n");
        answer.append("* Depth:").append(depth).append("\n");
        answer.append("* Cost:").append(cost).append("\n");
        answer.append("* Explored:").append(explored).append("\n");
        answer.append("* Nodes left in frontier:").append(frontier).append("\n");
        answer.append("* Solution coordinates:").append(Arrays.toString(solution.toArray())).append("\n");
        answer.append("* Time to solve:").append((float)(time/1000)).append("s\n");
        return answer.toString();
    }

    private void printParameters(StringBuilder answer) {
        for(String label : levelInfo.keySet()) {
            String info = String.format("* %s: ", StringUtils.capitalize(label).replace("-", " "));
            answer.append(info).append(levelInfo.get(label)).append('\n');
        }
    }
}