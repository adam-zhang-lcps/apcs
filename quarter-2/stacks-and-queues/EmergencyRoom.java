import java.util.PriorityQueue;
import javax.swing.JOptionPane;

public class EmergencyRoom {
    // list of conditions
    private static final String[] conditions = {
        "Cardiac Arrest", "Stroke", "Poisoning", "Minor Head Injury",
        "Broken Ankle"};

    // other fields, constructor, getter, and toString
    private String name;
    private PriorityQueue<Patient> patients;

    public EmergencyRoom(String name) {
        this.name = name;
        patients = new PriorityQueue<Patient>();
    }

    public String getName() { return name; }

    public String toString() {
        String s = "Patients: ";
        for (Patient p : patients)
            s += p + ", ";
        return s;
    }

    // add triage method
    public int triage(int age, String condition) {
        return switch (condition) {
        case "Cardiac Arrest" -> age >= 65 ? 1:
            2;
        case "Stroke" -> age >= 65 ? 3:
            4;
        case "Poisoning" -> age <= 10 ? 5:
            6;
        case "Minor Head Injury" -> age <= 18 ? 7:
            8;
        case "Broken Ankle" -> age >= 65 ? 9:
            10;
            default -> - 1;
        };
    }

    public void addPatient() {
        // uses dialogue boxes and dropdowns to get patient info
        String condition = (String)JOptionPane.showInputDialog(
            null, "Choose a condition.", "Select Patient Condition",
            JOptionPane.PLAIN_MESSAGE, null, conditions, conditions[0]);
        int age = Integer.parseInt(
            JOptionPane.showInputDialog("Enter Patient's Age: "));
        String name = JOptionPane.showInputDialog("Enter Patient's Name: ");

        // finish method
        int priority = triage(age, condition);
        patients.add(new Patient(name, priority));
    }

    // add treatPatient method
    public Patient treatPatient() {
        var patient = patients.poll();
        if (patient == null) {
            System.out.println("All patients have been treated!");
            return null;
        }

        System.out.printf("%s is now being treated.%n", patient.getName());
        System.out.println(
            "The following patients are still awaiting treatment:");
        System.out.println(patients);
        return patient;
    }

    // add test cases to main
    public static void main(String[] args) {
        EmergencyRoom er = new EmergencyRoom("ER");
        er.addPatient();
        er.addPatient();
        er.addPatient();
        er.addPatient();
        er.addPatient();
        er.treatPatient();
        er.treatPatient();
        er.treatPatient();
        er.treatPatient();
        er.treatPatient();
        er.treatPatient();
    }
}
