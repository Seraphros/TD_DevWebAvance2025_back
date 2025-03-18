package fr.cszw.tddevwebavance2025.exceptions;

public class NotFoundException extends Throwable {
    public NotFoundException(String message, String cause) {
        super(message, new Throwable(cause));
    }
}
