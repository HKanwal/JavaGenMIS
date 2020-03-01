package src;

import src.Module;
import src.Syntax;
import src.AccessProgram;
import src.Semantics;
import src.StateVariable;
import src.AccessRoutine;
import src.LocalFunction;


/**
 * Generator that generates latex code from corresponding java objects that represent the document
 * structure (like Syntax, Semantics, Uses, etc). It is a library abstract object. In general,
 * all portions of generated text will not end in a newline.
 * @author Harkeerat Kanwal
 */
public class GenLatex {
    public GenLatex() {}

    /**
     * Generates latex code from a Module.
     * @param module Module to generate latex code for.
     * @return A String. The generated latex code.
     */
    public String fromModule(Module module) {
        // Begin each module on a new page.
        String text = "\n\n\\newpage";

        // Module title and definitions.
        text += "\n\n\\section*{" + module.getName() + " Module}";
        text += "\n\n\\subsection*{Module}";
        text += "\n\n" + module.getDefinition();

        if (!(module.hasSuper() && module.getSyntax().isEmpty() && module.getSemantics().isEmpty())) {
            // The "Uses" section.
            text += "\n\n\\subsection*{Uses}\n\n";
            if (module.uses().length == 0) {
                text += "None";
            }
            else {
                String[] uses = module.uses();
                text += uses[0];
                for (int i = 1; i < uses.length; i++) {
                    text += ", " + uses[i];
                }
            }

            // The "Syntax" section.
            text += fromSyntax(module.getSyntax());

            if (!module.isInterface()) {
                // The "Semantics" section.
                text += fromSemantics(module.getSemantics());

                // The "Local Functions" sections.
                if (module.hasLocalFunctions()) {
                    text += fromLocalFunctions(module.getLocalFunctions());
                }
            }

            if (module.hasConsiderations()) {
                text += "\n\n\\subsection*{Considerations}";
                text += "\n\n" + module.getConsiderations();
            }
        }

        return text;
    }

    /**
     * Generate latex code from a Syntax.
     * @param syntax The syntax to generate latex code for.
     * @return A String. The generated latex code.
     */
    public String fromSyntax(Syntax syntax) {
        String text = "\n\n\\subsection*{Syntax}";

        // "Exported Constants" subsection.
        text += "\n\n\\subsubsection*{Exported Constants}";
        if (!syntax.hasExportedConstants()) {
            text += "\n\nNone";
        }
        else {
            String[] exportedConstants = syntax.getExportedConstants();
            for (int i = 0; i < exportedConstants.length; i++) {
                text += "\n\n" + exportedConstants[i];
            }
        }

        // "Exported Types" subsection.
        text += "\n\n\\subsubsection*{Exported Types}";
        if (!syntax.hasExportedTypes()) {
            text += "\n\nNone";
        }
        else {
            String[] exportedTypes = syntax.getExportedTypes();
            for (int i = 0; i < exportedTypes.length; i++) {
                text += "\n\n" + exportedTypes[i];
            }
        }

        // "Exported Access Programs" subsection.
        text += "\n\n\\subsubsection*{Exported Access Programs}";
        if (!syntax.hasAccessPrograms()) {
            text += "\n\nNone";
        }
        else {
            AccessProgram[] accessPrograms = syntax.getAccessPrograms();
            AccessProgram accessProgram;
            text += "\n\n\\begin{tabular}{| l | l | l | l |}";
            text += "\n\\hline";
            text += "\n\\textbf{Routine name} & \\textbf{In} & \\textbf{Out} & \\textbf{Exceptions}\\\\";
            text += "\n\\hline";
            for (int i = 0; i < accessPrograms.length; i++) {
                accessProgram = accessPrograms[i];
                text += "\n" + accessProgram.getName() + " & " + accessProgram.getInputs() + " & " + accessProgram.getOutputs() + " & " + accessProgram.getExceptions()+ "\\\\";
                text += "\n\\hline";
            }
            text += "\n\\end{tabular}";
        }

        return text;
    }

    /**
     * Generate latex code from Semantics.
     * @param semantics The semantics to generate latex code for.
     * @return A String. The generated latex code.
     */ 
    public String fromSemantics(Semantics semantics) {
        String text = "\n\n\\subsection*{Semantics}";

        // "State Variables" subsection.
        text += "\n\n\\subsubsection*{State Variables}\n";
        if (!semantics.hasStateVariables()) {
            text += "\nNone";
        }
        else {
            StateVariable[] stateVariables = semantics.getStateVariables();
            StateVariable stateVariable;
            for (int i = 0; i < stateVariables.length; i++) {
                stateVariable = stateVariables[i];
                text += "\n" + stateVariable.getIdentifier() + ": " + stateVariable.getText();
                if (i != stateVariables.length - 1) {
                    text += "\\\\";
                }
            }
        }

        // "State Invariant" subsection.
        text += "\n\n\\subsubsection*{State Invariant}\n";
        if (!semantics.hasStateInvariants()) {
            text += "\nNone";
        }
        else {
            String[] stateInvariants = semantics.getStateInvariants();
            for (int i = 0; i < stateInvariants.length; i++) {
                text += "\n" + stateInvariants[i];
                if (i != stateInvariants.length - 1) {
                    text += "\\\\";
                }
            }
        }

        // "Assumptions" subsection.
        text += "\n\n\\subsubsection*{Assumptions}";
        if (!semantics.hasAssumptions()) {
            text += "\n\nNone";
        }
        else {
            String[] assumptions = semantics.getAssumptions();
            text += "\n\n\\begin{itemize}";
            for (int i = 0; i < assumptions.length; i++) {
                text += "\n\\item " + assumptions[i];
            }
            text += "\n\\end{itemize}";
        }

        // "Access Routine Semantics" subsection.
        text += "\n\n\\subsubsection*{Access Routine Semantics}\n";
        if (!semantics.hasAccessRoutines()) {
            text += "\nNone";
        }
        else {
            AccessRoutine[] accessRoutines = semantics.getAccessRoutines();
            AccessRoutine accessRoutine;
            for (int i = 0; i < accessRoutines.length; i++) {
                accessRoutine = accessRoutines[i];
                text += "\n\\noindent " + accessRoutine.getName() + ":";
                text += "\n\\begin{itemize}";
                if (accessRoutine.hasTransition()) {
                    text += "\n\\item transition: $" + accessRoutine.getTransition() + "$";
                }
                if (accessRoutine.hasOutput()) {
                    text += "\n\\item output: $" + accessRoutine.getOutput() + "$";
                }
                text += "\n\\item exception: ";
                if (accessRoutine.hasException()) {
                    text += "$" + accessRoutine.getException() + "$";
                }
                else {
                    text += "none";
                }
                text += "\n\\end{itemize}";
            }
        }

        return text;
    }

    /**
     * Takes an array of Local Functions and generates the corresponding latex code.
     * @param localFunctions The Local Functions to convert to latex code.
     * @return A String. The generated latex code.
     */
    public String fromLocalFunctions(LocalFunction[] localFunctions) {
        String text = "\n\n\\subsection*{Local Functions}";
        LocalFunction localFunction;

        for (int i = 0; i < localFunctions.length; i++) {
            localFunction = localFunctions[i];
            text += "\n\n\\noindent " + localFunction.getSignature() + "\\\\";
            text += "\n\\noindent " + localFunction.getDefinition() + "\\\\";
        }

        return text;
    }
}
