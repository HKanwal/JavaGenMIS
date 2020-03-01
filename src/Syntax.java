package src;

import src.AccessProgram;

public class Syntax {
    private String[] exportedConstants;
    private String[] exportedTypes;
    private AccessProgram[] accessPrograms;

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

    public String[] getExportedConstants() {
        String[] copy = new String[exportedConstants.length];
        for (int i = 0; i < exportedConstants.length; i++) {
            copy[i] = exportedConstants[i];
        }
        return copy;
    }

    public String[] getExportedTypes() {
        String[] copy = new String[exportedTypes.length];
        for (int i = 0; i < exportedTypes.length; i++) {
            copy[i] = exportedTypes[i];
        }
        return copy;
    }

    public AccessProgram[] getAccessPrograms() {
        return accessPrograms;
    }

    public boolean isEmpty() {
        return !(hasExportedConstants() || hasExportedTypes() || hasAccessPrograms());
    }
}
