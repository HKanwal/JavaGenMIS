package src;

import src.AccessProgram;

public class Syntax {
    public String[] exportedConstants;
    public String[] exportedTypes;
    public AccessProgram[] accessPrograms;

    public Syntax() {}

    public boolean hasExportedConstants() {
        return exportedConstants.length > 0;
    }

    public boolean hasExportedTypes() {
        return exportedTypes.length > 0;
    }

    public boolean hasAccessPrograms() {
        return accessPrograms.length > 0;
    }

    public boolean isEmpty() {
        return !(hasExportedConstants() || hasExportedTypes() || hasAccessPrograms());
    }
}
