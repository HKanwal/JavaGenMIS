package src;

import src.TexFileContents;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import src.Module;

public class TexFile extends File {
    TexFileContents contents;

    public TexFile(String pathname) throws IOException {
        super(pathname);
        contents = new TexFileContents();
        update();
    }

    public TexFile(String pathname, Module[] modules) throws IOException {
        super(pathname);
        contents = new TexFileContents();
        for (int i = 0; i < modules.length; i++) {
            contents.addModule(modules[i]);
        }
        update();
    }

    public void addModule(Module module) {
        contents.addModule(module);
    }
    
    /**
     * Updates actual file latex text with the contents of this object.
     */
    public void update() throws IOException {
        FileWriter fr = new FileWriter(this);
        fr.write(contents.getText());
        fr.close();
    }
}
