package src;

import src.Syntax;
import src.Semantics;
import src.LocalFunction;


/**
 * Represents a MIS module. Obtained by parsing JSON.
 * @author Harkeerat Kanwal
 */
public class Module {
    public String name;
    public String[] classes;
    public String inherits;
    public String is;
    public String definition;
    public String[] uses;
    public Syntax syntax;
    public Semantics semantics;
    public LocalFunction[] localFunctions;
    public String considerations;

    public Module() {}
}
