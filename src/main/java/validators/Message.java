package validators;

/**
 * Message class
 *
 * @author Alex Lozovoy
 */

public class Message {
    private String message;

    private Status status;

    public Message(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public Message(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
