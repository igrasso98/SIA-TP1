package exceptions;

public class NoAlgorithmException extends Exception {
    @Override
    public String getMessage() {
        return "Algorithm not found";
    }
}
