import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import javax.swing.*;
import org.json.*;

public class AirJsonParse {
    public static void main(String[] args) {
        String jsonData = """
            {
              "coord": [50, 50],
              "list": [
                {
                  "dt": 1605182400,
                  "main": { "aqi": 1 },
                  "components": {
                    "co": 201.94,
                    "no": 0.018,
                    "no2": 0.77,
                    "o3": 68.66,
                    "so2": 0.64,
                    "pm2_5": 12.5,
                    "pm10": 15.3,
                    "nh3": 0.12
                  }
                },
                {
                  "dt": 1605268800,
                  "main": { "aqi": 2 },
                  "components": {
                    "co": 220.50,
                    "no": 0.025,
                    "no2": 1.20,
                    "o3": 55.30,
                    "so2": 0.85,
                    "pm2_5": 18.3,
                    "pm10": 22.1,
                    "nh3": 0.18
                  }
                },
                {
                  "dt": 1605355200,
                  "main": { "aqi": 1 },
                  "components": {
                    "co": 195.30,
                    "no": 0.015,
                    "no2": 0.65,
                    "o3": 72.40,
                    "so2": 0.55,
                    "pm2_5": 9.8,
                    "pm10": 12.7,
                    "nh3": 0.10
                  }
                },
                {
                  "dt": 1605441600,
                  "main": { "aqi": 2 },
                  "components": {
                    "co": 235.80,
                    "no": 0.030,
                    "no2": 1.45,
                    "o3": 48.90,
                    "so2": 0.92,
                    "pm2_5": 22.7,
                    "pm10": 28.5,
                    "nh3": 0.22
                  }
                },
                {
                  "dt": 1605528000,
                  "main": { "aqi": 1 },
                  "components": {
                    "co": 210.20,
                    "no": 0.020,
                    "no2": 0.88,
                    "o3": 65.10,
                    "so2": 0.70,
                    "pm2_5": 14.2,
                    "pm10": 18.9,
                    "nh3": 0.15
                  }
                },
                {
                  "dt": 1605614400,
                  "main": { "aqi": 2 },
                  "components": {
                    "co": 245.60,
                    "no": 0.035,
                    "no2": 1.60,
                    "o3": 42.30,
                    "so2": 1.05,
                    "pm2_5": 26.8,
                    "pm10": 32.4,
                    "nh3": 0.25
                  }
                },
                {
                  "dt": 1605700800,
                  "main": { "aqi": 3 },
                  "components": {
                    "co": 260.40,
                    "no": 0.042,
                    "no2": 1.85,
                    "o3": 38.70,
                    "so2": 1.20,
                    "pm2_5": 31.5,
                    "pm10": 38.2,
                    "nh3": 0.30
                  }
                }
              ]
            }
            """;


        JSONObject jsonObject = new JSONObject(jsonData);

        JSONArray listArray = jsonObject.getJSONArray("list");

        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        double[] pm25Values = new double[days.length];
        for (int i = 0; i < days.length; i++) {
            JSONObject dayDate = listArray.getJSONObject(i);

            double pm25 = dayDate.getJASONObject("components").getDouble("pm2_5");

            pm25Values[i] = pm25;
            }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < days.length; i++) {
            dataset.addValue(pm25Values[i], "PM2.5", days[i]);
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Weekly Air Quality: PM2.5 Levels",
                "Day of Week",
                "PM2.5 (µg/m³)",
                dataset);


        ChartFrame frame = new ChartFrame("Air Quality Dashboard", chart);
        frame.setSize(900, 650);
        frame.setLocationRelativeTo(null);  
        frame.setVisible(true);
    }
}
