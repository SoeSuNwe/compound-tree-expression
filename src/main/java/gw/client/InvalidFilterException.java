package gw.client;

public class InvalidFilterException extends RuntimeException{

    public InvalidFilterException(String message) {
        super(message);
    }
}