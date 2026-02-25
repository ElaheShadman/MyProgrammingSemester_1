package hospital;

import javax.swing.*;
import java.util.List;

public class HospitalManagement {

    public static void main(String[] args) {
        List<hospital.Surgery> logs = OrManagement.src.main.java.hospital.DataLoader.loadSurgeries();
        if (logs.isEmpty()) {
            System.out.println("No data loaded. Check Surgeries.json path.");
            return;
        }

        SwingUtilities.invokeLater(() -> {
            hospital.UI ui = new hospital.UI(logs);
            ui.setVisible(true);
        });
    }
}
