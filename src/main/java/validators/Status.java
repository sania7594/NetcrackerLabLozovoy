package validators;

public enum Status {
    OK("very good"), ERROR("Fatal error");


    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}