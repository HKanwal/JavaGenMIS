package src;

import src.Module;
import src.AccessProgram;
import src.StateVariable;
import src.AccessRoutine;
import java.util.ArrayList;


/**
 * Represents the the text in the .tex file used to generate the MIS .pdf.
 * @author Harkeerat Kanwal
 */
public class TexFile {
    private String preamble;
    private ArrayList<String> packages;
    private ArrayList<String> modules;

    /**
     * Constructor geenrate the preamble text, including packages to use.
     */
    public TexFile() {
        modules = new ArrayList<String>();

        // Add default packages.
        packages = new ArrayList<String>();
        packages.add("graphicx");
        packages.add("paralist");
        packages.add("amsfonts");
        packages.add("amsmath");
        packages.add("hhline");
        packages.add("booktabs");
        packages.add("multirow");
        packages.add("multicol");
        packages.add("url");

        // Latex header.
        preamble = "\\documentclass[12pt]{article}\n";
        
        // Add packaged as latex code.
        for (int i = 0; i < packages.size(); i++) {
            preamble += "\n\\usepackage{" + packages.get(i) + "}";
        }

        // Other default settings.
        preamble += "\n\n\\oddsidemargin 0mm";
        preamble += "\n\\evensidemargin 0mm";
        preamble += "\n\\textwidth 160mm";
        preamble += "\n\\textheight 200mm";
        preamble += "\n\\renewcommand\\baselinestretch{1.0}";
        preamble += "\n\n\\pagestyle{plain}";
        preamble += "\n\\pagenumbering{arabic}";
    }

    /**
     * Takes a module and generates the correspoding text in latex.
     * @param module A module of type Module to be added to the tex file.
     */
    public void addModule(Module module) {
        // Begin each module on a new page.
        String moduleText = "\\newpage";

        // Module title and definitions.
        moduleText += "\n\n\\section*{" + module.name + " Module}";
        moduleText += "\n\n\\subsection*{Module}";
        moduleText += "\n\n" + module.definition;

        // The "Uses" section.
        moduleText += "\n\n\\subsection*{Uses}\n\n";
        if (module.uses.length == 0) {
            moduleText += "None";
        }
        else {
            moduleText += module.uses[0];
            for (int i = 1; i < module.uses.length; i++) {
                moduleText += ", " + module.uses[i];
            }
        }

        // The "Syntax" section.
        moduleText += "\n\n\\subsection*{Syntax}";

        // "Exported Constants" subsection.
        moduleText += "\n\n\\subsubsection*{Exported Constants}";
        if (!module.syntax.hasExportedConstants()) {
            moduleText += "\n\nNone";
        }
        else {
            for (int i = 0; i < module.syntax.exportedConstants.length; i++) {
                moduleText += "\n\n" + module.syntax.exportedConstants[i];
            }
        }

        // "Exported Types" subsection.
        moduleText += "\n\n\\subsubsection*{Exported Types}";
        if (!module.syntax.hasExportedTypes()) {
            moduleText += "\n\nNone";
        }
        else {
            for (int i = 0; i < module.syntax.exportedTypes.length; i++) {
                moduleText += "\n\n" + module.syntax.exportedTypes[i];
            }
        }

        // "Exported Access Programs" subsection.
        moduleText += "\n\n\\subsubsection*{Exported Access Programs}";
        if (!module.syntax.hasAccessPrograms()) {
            moduleText += "\n\nNone";
        }
        else {
            AccessProgram accessProgram;
            moduleText += "\n\n\\begin{tabular}{| l | l | l | l |}";
            moduleText += "\n\\hline";
            moduleText += "\n\\textbf{Routine name} & \\textbf{In} & \\textbf{Out} & \\textbf{Exceptions}\\\\";
            moduleText += "\n\\hline";
            for (int i = 0; i < module.syntax.accessPrograms.length; i++) {
                accessProgram = module.syntax.accessPrograms[i];
                moduleText += "\n" + accessProgram.name + " & " + accessProgram.in + " & " + accessProgram.out + " & " + accessProgram.exceptions + "\\\\";
                moduleText += "\n\\hline";
            }
            moduleText += "\n\\end{tabular}";
        }

        // The "Semantics" section.
        moduleText += "\n\n\\subsection*{Semantics}";

        // "State Variables" subsection.
        moduleText += "\n\n\\subsubsection*{State Variables}\n";
        if (!module.semantics.hasStateVariables()) {
            moduleText += "\nNone";
        }
        else {
            StateVariable stateVariable;
            for (int i = 0; i < module.semantics.stateVariables.length; i++) {
                stateVariable = module.semantics.stateVariables[i];
                moduleText += "\n" + stateVariable.identifier + ": " + stateVariable.text + "\\\\";
            }
        }

        // "State Invariant" subsection.
        moduleText += "\n\n\\subsubsection*{State Invariant}\n";
        if (!module.semantics.hasStateInvariants()) {
            moduleText += "\nNone";
        }
        else {
            for (int i = 0; i < module.semantics.stateInvariants.length; i++) {
                moduleText += "\n" + module.semantics.stateInvariants[i];
                if (i != module.semantics.stateInvariants.length - 1) {
                    moduleText += "\\\\";
                }
            }
        }

        // "Assumptions" subsection.
        moduleText += "\n\n\\subsubsection*{Assumptions}";
        if (!module.semantics.hasAssumptions()) {
            moduleText += "\n\nNone";
        }
        else {
            moduleText += "\n\n\\begin{itemize}";
            for (int i = 0; i < module.semantics.assumptions.length; i++) {
                moduleText += "\n\\item " + module.semantics.assumptions[i];
            }
            moduleText += "\n\\end{itemize}";
        }

        // "Access Routine Semantics" subsection.
        moduleText += "\n\n\\subsubsection*{Access Routine Semantics}\n";
        if (!module.semantics.hasAccessRoutines()) {
            moduleText += "\nNone";
        }
        else {
            AccessRoutine accessRoutine;
            for (int i = 0; i < module.semantics.accessRoutines.length; i++) {
                accessRoutine = module.semantics.accessRoutines[i];
                moduleText += "\n\\noindent " + accessRoutine.name + ":";
                moduleText += "\n\\begin{itemize}";
                if (accessRoutine.hasTransition()) {
                    moduleText += "\n\\item transition: $" + accessRoutine.transition + "$";
                }
                if (accessRoutine.hasOutput()) {
                    moduleText += "\n\\item output: $" + accessRoutine.output + "$";
                }
                moduleText += "\n\\item exception: ";
                if (accessRoutine.hasException() && !accessRoutine.exception.equals("none")) {
                    moduleText += "$" + accessRoutine.exception + "$";
                }
                else {
                    moduleText += "none";
                }
                moduleText += "\n\\end{itemize}\n";
            }
        }

        // Append the module to list of modules in this document.
        modules.add(moduleText);
    }

    /**
     * Generate and return the complete text of this .tex file.
     * @return A string that is the contents of the latex file.
     */
    public String getText() {
        String text = preamble;
        text += "\n\n\\begin{document}";
        for (int i = 0; i < modules.size(); i++) {
            text += "\n\n" + modules.get(i);
        }
        text += "\n\n\\end{document}";
        return text;
    }

    /**
     * Adds a latex package.
     * @param packge A String. Another package to use.
     */
    public void addPackage(String packge) {
        int insertIndex = preamble.indexOf("\\usepackage");
        System.out.println(insertIndex);
        packages.add(packge);
    }
}
