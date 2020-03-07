package src;

import com.google.gson.Gson;
import src.TexFileContents;
import src.Module;


/**
 * Converts between String of JSON to String of Latex code.
 */
public class JSONConverter {
    public static String convert(String json) {
        Gson gson = new Gson();
        Module[] modules = gson.fromJson(json, Module[].class);
        TexFileContents texFile = new TexFileContents();
        for (int i = 0; i < modules.length; i++) {
            texFile.addModule(modules[i]);
        }
        return texFile.getText();
    }
}
