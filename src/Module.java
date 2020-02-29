package src;

import src.Syntax;
import src.Semantics;
import src.LocalFunction;

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
}
