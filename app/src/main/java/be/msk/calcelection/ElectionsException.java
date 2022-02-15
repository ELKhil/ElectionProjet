package be.msk.calcelection;

public class ElectionsException extends RuntimeException{
    public ElectionsException() {
    }

    public ElectionsException(String message) {
        super(message);
    }

    public ElectionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElectionsException(Throwable cause) {
        super(cause);
    }


}
