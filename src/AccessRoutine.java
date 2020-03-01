package src;

public class AccessRoutine {
    private String name;
    private String transition;
    private String output;
    private String exception;

    public AccessRoutine() {}

    public boolean hasTransition() {
        return transition.length() > 0;
    }

    public boolean hasOutput() {
        return output.length() > 0;
    }

    public boolean hasException() {
        return exception.length() > 0 && !(exception.equals("none") || exception.equals("None"));
    }

    public String getName() {
        return name;
    }

    public String getTransition() {
        return transition;
    }

    public String getOutput() {
        return output;
    }

    public String getException() {
        return exception;
    }
}
