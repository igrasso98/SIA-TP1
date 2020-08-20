package exceptions;

public class NoHeuristicException extends Exception {
    @Override
    public String getMessage() {
        return "Heuristic not found";
    }
}
