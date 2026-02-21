public class Appointment {
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

    public int getPatientID() {return patientID;}

    public void setPatientID(int ID) {this.patientID = ID;}

    public String getApptDate() {return apptDate;}

    public void setApptDate(String apptDate) {this.apptDate = apptDate;}

    public String getPhysician() {return physician;}

    public void setPhysician(String physician) {this.physician = physician;}

    public double getHeight() {return height;}

    public void setHeight(double height) {this.height = height;}

    public double getWeight() {return weight;}

    public void setWeight(double weight) {this.weight = weight;}

    public double getTemperature() {return temperature;}

    public void setTemperature(double temp) {this.temperature = temp;}

    public int getPulse() {return pulse;}

    public void setPulse(int pulse) {this.pulse = pulse;}

    public int getBpDiastolic() {return bpDiastolic;}

    public void setBpDiastolic(int bpd) {this.bpDiastolic = bpd;}

    public int getBpSystolic() {return bpSystolic;}

    public void setBpSystolic(int bps) {this.bpSystolic = bps; }

    public String getNotes() {return notes;}

    public void setNotes(String notes) {this.notes = notes;}


    public String toString() {return patientID + "*" + apptDate +
            "*" + physician + "*" + height + "*" + weight + "*"
            + temperature + "*" + pulse + "*" + bpSystolic + "*" + bpDiastolic
            + "*" + notes;}
}
