package src;

import src.Syntax;
import src.Semantics;
import src.LocalFunction;


/**
 * Represents a MIS module. Obtained by parsing JSON.
 * @author Harkeerat Kanwal
 */
public class Module {
    private String name;
    private String[] classes;
    private String inherits;
    private String is;
    private String definition;
    private String[] uses;
    private Syntax syntax;
    private Semantics semantics;
    private LocalFunction[] localFunctions;
    private String considerations;

    public Module() {}

    /**
     * Determines whether or not this module is of given type/class
     * (Generic, Template or Interface).
     * @param type Check if this module is belongs to this class.
     * @return True if class found in classes[], false otherwise.
     */
    private boolean isClass(String type) {
        for (int i = 0; i < classes.length; i++) {
            if (classes[i].equals(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter for module name.
     * @return A String. The name of the module.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the templates this module implements.
     * @return A String. The template this module implements seperated by commas.
     */
    public String inherits() {
        return inherits;
    }

    /**
     * Getter for the module this module extends.
     * @return A String. The name of the super module.
     */
    public String getSuper() {
        return is;
    }

    /**
     * Getter for module definition.
     * @return A String. The definition of the module (the third line on the page).
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Getter for module syntax.
     * @return A Syntax. The syntax of this module.
     */
    public Syntax getSyntax() {
        return syntax;
    }

    /**
     * Getter for module semantics.
     * @return A Semantics. The semantics of this module.
     */
    public Semantics getSemantics() {
        return semantics;
    }

    /**
     * Getter for an array of modules this module uses.
     * @return An array of String. A copy of the modules this module uses.
     */
    public String[] uses() {
        String[] usesCopy = new String[uses.length];
        for (int i = 0; i < uses.length; i++) {
            usesCopy[i] = uses[i];
        }
        return usesCopy;
    }

    /**
     * Getter for an array of Local Functions.
     * @return An array of containing type LocalFunction. A mutable reference to the array of Local Functions.
     * in this module.
     */
    public LocalFunction[] getLocalFunctions() {
        return localFunctions;
    }

    /**
     * Getter for considerations.
     * @return A String, the considerations.
     * in this module.
     */
    public String getConsiderations() {
        return considerations;
    }

    public boolean isTemplate() {
        return isClass("Template");
    }

    public boolean isGeneric() {
        return isClass("Generic");
    }

    public boolean isInterface() {
        return isClass("Interface");
    }

    public boolean hasSuper() {
        return is.length() > 0;
    }

    public boolean hasLocalFunctions() {
        return localFunctions.length > 0;
    }

    public boolean hasConsiderations() {
        return considerations.length() > 0;
    }
}
