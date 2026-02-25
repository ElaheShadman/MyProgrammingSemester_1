package hospital;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public static List<Surgery> loadSurgeries() {
        List<Surgery> list = new ArrayList<>();
        try {
            InputStream is = DataLoader.class.getResourceAsStream("/Surgeries.json");

            if (is == null) {
                System.out.println("Error: Surgeries.json not found in resources.");
                return list;
            }

            byte[] bytes = is.readAllBytes();
            String content = new String(bytes);

            // خط حیاتی که فراموش شده بود:
            JSONArray arr = new JSONArray(content);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                list.add(new Surgery(
                        o.getString("Dept"),
                        o.getString("Surgeon ID"),
                        o.getString("Procedure Name"),
                        o.getInt("Duration (Mins)"),
                        o.getInt("Recovery (Days)")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        return list;
    }
}
