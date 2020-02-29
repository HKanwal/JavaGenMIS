package src;

import src.StateVariable;
import src.AccessRoutine;

public class Semantics {
    public StateVariable[] stateVariables;
    public String[] stateInvariants;
    public String[] assumptions;
    public AccessRoutine[] accessRoutines;

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
}
