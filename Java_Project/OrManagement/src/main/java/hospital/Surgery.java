package hospital;

public class Surgery {
    public String department;
    public String surgeonID;
    public String procedureName;
    public int duration;
    public int recoveryDays;

    public Surgery(String dept, String id, String name, int dur, int rec) {
        this.department = dept;
        this.surgeonID = id;
        this.procedureName = name;
        this.duration = dur;
        this.recoveryDays = rec;
    }
}
