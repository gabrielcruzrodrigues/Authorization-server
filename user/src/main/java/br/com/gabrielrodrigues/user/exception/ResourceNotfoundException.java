package br.com.gabrielrodrigues.user.exception;

public class ResourceNotfoundException extends RuntimeException {
    public ResourceNotfoundException(String message) {
        super(message);
    }
}
