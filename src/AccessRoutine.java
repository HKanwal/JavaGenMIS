package src;

public class AccessRoutine {
    public String name;
    public String transition;
    public String output;
    public String exception;

    public AccessRoutine() {}

    public boolean hasTransition() {
        return this.transition.length() > 0;
    }

    public boolean hasOutput() {
        return this.output.length() > 0;
    }

    public boolean hasException() {
        return this.exception.length() > 0;
    }
}
