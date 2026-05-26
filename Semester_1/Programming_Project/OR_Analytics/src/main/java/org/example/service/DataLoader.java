package org.example.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Surgery;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DataLoader {

    public static List<Surgery> loadData(String path) {
        try {
            if (!Files.exists(Paths.get(path))) {
                System.out.println("File not found: " + path);
                return null;
            }

            Gson gson = new Gson();
            return gson.fromJson(new FileReader(path),
                    new TypeToken<List<Surgery>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
