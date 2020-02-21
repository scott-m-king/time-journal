package persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;

import java.io.*;

// https://www.javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/package-summary.html
// https://mkyong.com/java/how-to-parse-json-with-gson/

public class SaveWriter {
    FileWriter writer;
    Gson gson;

    public SaveWriter(File file) throws IOException {
        writer = new FileWriter(file);
    }

    public void saveFile(JournalLog j) {
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        gson.toJson(j, writer);
    }

    public void saveFile(CategoryList c) {
        gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(c, writer);
    }

    public void close() throws IOException {
        writer.close();
    }
}