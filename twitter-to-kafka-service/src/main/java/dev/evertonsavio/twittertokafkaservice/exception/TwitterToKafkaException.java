package dev.evertonsavio.twittertokafkaservice.exception;

public class TwitterToKafkaException extends RuntimeException{

    public TwitterToKafkaException() {
        super();
    }

    public TwitterToKafkaException(String message) {
        super(message);
    }

    public TwitterToKafkaException(String message, Throwable cause) {
        super(message, cause);
    }
}
