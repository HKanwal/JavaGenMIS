package src;

import src.Module;
import src.GenLatex;
import java.util.ArrayList;


/**
 * Represents the text in the .tex file used to generate the MIS .pdf.
 * @author Harkeerat Kanwal
 */
public class TexFileContents {
    private String preamble;
    private ArrayList<String> packages;
    private ArrayList<String> modules;

    /**
     * Constructor generate the preamble text, including packages to use.
     */
    public TexFileContents() {
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
        
        // Add packages as latex code.
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
     * Takes a module, generates the correspoding latex code and adds it to
     * the list of modules contained in this document.
     * @param module A module of type Module to be added to the tex file.
     */
    public void addModule(Module module) {
        GenLatex parser = new GenLatex();
        String moduleText = parser.fromModule(module);
        modules.add(moduleText);
    }

    /**
     * Collate preamble and modules into one string.
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
     * TODO Adds a latex package.
     * @param packge A String. Another package to use.
     */
    // public void addPackage(String packge) {
    //     int insertIndex = preamble.indexOf("\\usepackage");
    //     System.out.println(insertIndex);
    //     packages.add(packge);
    // }
}
