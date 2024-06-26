package database;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class TinyDB {
    private final String fileStr;
    private JSONObject data;

    public TinyDB() throws IOException {
        this.fileStr = "database.json";
        this.loadData();
    }

    public TinyDB(String fileStr) throws IOException {
        this.fileStr = fileStr;
        this.loadData();
    }

    public Table table(String name) {
        return new Table(this, name);
    }

    public void dropTable(String name) {
        this.data.remove(name);
    }

    public JSONObject getData() {
        return data;
    }

    public final void loadData() throws IOException {
        if (exists(this.fileStr)) {
            String content = new String(Files.readAllBytes(Paths.get(this.fileStr)));
            if (!Objects.equals(content, "")) {
                data = new JSONObject(content);
            } else {
                data = new JSONObject();
            }
        } else {
            data = new JSONObject();
        }
    }

    public boolean exists(String fileStr) {
        return new File(fileStr).exists();
    }

    public void commit() throws IOException {
        try (FileWriter file = new FileWriter(fileStr)) {
            file.write(data.toString());
        }
    }

    public void close() throws IOException {
        this.commit();
    }
}
