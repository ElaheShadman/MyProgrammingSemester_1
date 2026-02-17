package hospital;

public class Surgery {
    public String department;
    public String surgeonId;
    public String procedureName;
    public int duration;
    public int recoveryDays;

    public Surgery(String department, String surgeonId, String procedureName, int duration, int recoveryDays) {
        this.department = department;
        this.surgeonId = surgeonId;
        this.procedureName = procedureName;
        this.duration = duration;
        this.recoveryDays = recoveryDays;
    }
}
