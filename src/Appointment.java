public class Appointment {
    // encapsulated fields
    private int patientID;
    private String apptDate;
    private String physician;
    private double height;
    private double weight;
    private double temperature;
    private int pulse;
    private int bpSystolic;
    private int bpDiastolic;
    private String notes;

    // no-arg constructor initializing default vals
    public Appointment() {
        patientID = 0;
        apptDate = null;
        physician = null;
        height = 0.0;
        weight = 0.0;
        temperature = 0.0;
        pulse = 0;
        bpSystolic = 0;
        bpDiastolic = 0;
        notes = null;
    }

    // constructor with field paramaeters
    public Appointment(int patientID, String apptDate, String physician, double height, double weight, double temperature, int pulse, int bpSystolic, int bpDiastolic, String notes) {
        this.patientID = patientID;
        this.apptDate = apptDate;
        this.physician = physician;
        this.height = height;
        this.weight = weight;
        this.temperature = temperature;
        this.pulse = pulse;
        this.bpSystolic = bpSystolic;
        this.bpDiastolic = bpDiastolic;
        this.notes = notes;
    }

    // constr. w/ non-notes field
    public Appointment(int patientID, String apptDate, String physician, double height, double weight, double temperature, int pulse, int bpSystolic, int bpDiastolic) {
        this.patientID = patientID;
        this.apptDate = apptDate;
        this.physician = physician;
        this.height = height;
        this.weight = weight;
        this.temperature = temperature;
        this.pulse = pulse;
        this.bpSystolic = bpSystolic;
        this.bpDiastolic = bpDiastolic;
    }

    // Patient ID getters and setters
    public int getPatientID() {return patientID;}
    public void setPatientID(int ID) {this.patientID = ID;}

    // Appointment Date getters and setters
    public String getApptDate() {return apptDate;}
    public void setApptDate(String apptDate) {this.apptDate = apptDate;}

    // Physician getters and setters
    public String getPhysician() {return physician;}
    public void setPhysician(String physician) {this.physician = physician;}

    // Height getters and setters
    public double getHeight() {return height;}
    public void setHeight(double height) {this.height = height;}

    // Weight getters and setters
    public double getWeight() {return weight;}
    public void setWeight(double weight) {this.weight = weight;}

    // Temperature getters and setters
    public double getTemperature() {return temperature;}
    public void setTemperature(double temp) {this.temperature = temp;}

    // Pulse getters and setters
    public int getPulse() {return pulse;}
    public void setPulse(int pulse) {this.pulse = pulse;}

    // BpDiastolic getters and setters
    public int getBpDiastolic() {return bpDiastolic;}
    public void setBpDiastolic(int bpd) {this.bpDiastolic = bpd;}

    // BpSystolic getters and setters
    public int getBpSystolic() {return bpSystolic;}
    public void setBpSystolic(int bps) {this.bpSystolic = bps; }

    // Notes getters and setters
    public String getNotes() {return notes;}
    public void setNotes(String notes) {this.notes = notes;}


    // toString w/ fields separated by a (*)
    public String toString() {return patientID + "*" + apptDate +
            "*" + physician + "*" + height + "*" + weight + "*"
            + temperature + "*" + pulse + "*" + bpSystolic + "*" + bpDiastolic
            + "*" + notes;}
}
