package src;

import src.StateVariable;
import src.AccessRoutine;

public class Semantics {
    private StateVariable[] stateVariables;
    private String[] stateInvariants;
    private String[] assumptions;
    private AccessRoutine[] accessRoutines;

    public Semantics() {}

    public boolean hasStateVariables() {
        return stateVariables.length > 0;
    }

    public boolean hasStateInvariants() {
        return stateInvariants.length > 0;
    }

    public boolean hasAssumptions() {
        return assumptions.length > 0;
    }

    public boolean hasAccessRoutines() {
        return accessRoutines.length > 0;
    }

    public StateVariable[] getStateVariables() {
        return stateVariables;
    }

    public AccessRoutine[] getAccessRoutines() {
        return accessRoutines;
    }

    public String[] getStateInvariants() {
        String[] copy = new String[stateInvariants.length];
        for (int i = 0; i < stateInvariants.length; i++) {
            copy[i] = stateInvariants[i];
        }
        return copy;
    }

    public String[] getAssumptions() {
        String[] copy = new String[assumptions.length];
        for (int i = 0; i < assumptions.length; i++) {
            copy[i] = assumptions[i];
        }
        return copy;
    }

    public boolean isEmpty() {
        return !(hasStateVariables() || hasStateInvariants() || hasAssumptions() || hasAccessRoutines());
    }
}
