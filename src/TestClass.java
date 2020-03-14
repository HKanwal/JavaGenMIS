package src;

import src.Module;
import src.TexFileContents;
import src.TexFile;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;

public class TestClass {
    public static void main(String[] args) throws IOException {
        BufferedReader jsonFile = new BufferedReader(new FileReader("A2.json"));
        String json = "";
        String line;
        while ((line = jsonFile.readLine()) != null) {
            json += line;
        }
        Gson gson = new Gson();
        Module[] modules = gson.fromJson(json, Module[].class);
        TexFile texFile = new TexFile("tex/MIS.tex", gson.fromJson(json, Module[].class));
    }
}
