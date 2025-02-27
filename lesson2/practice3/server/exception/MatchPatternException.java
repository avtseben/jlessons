package lesson2.practice3.server.exception;

public class MatchPatternException extends RuntimeException{
    private final String messageWithCorrectSyntax;

    public MatchPatternException(String message) {
        this.messageWithCorrectSyntax = message;
    }
}
