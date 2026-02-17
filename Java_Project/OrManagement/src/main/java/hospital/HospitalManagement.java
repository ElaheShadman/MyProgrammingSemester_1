package hospital;

import javax.swing.*;
import java.util.List;

public class HospitalManagement {

    public static void main(String[] args) {
        List<Surgery> logs = DataLoader.loadSurgeries();
        if (logs.isEmpty()) {
            System.out.println("No data loaded. Check Surgeries.json path.");
            return;
        }

        SwingUtilities.invokeLater(() -> {
            UI ui = new UI(logs);
            ui.setVisible(true);
        });
    }
}
