package exceptions;

public class NoSuchLevelException extends Exception{
    @Override
    public String getMessage() {
        return "Level does not exist. Levels 1 to 9 are available";
    }
}
