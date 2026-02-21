import java.util.ArrayList;

public class Patient {
    private int patientID;
    private String firstName;
    private String lastName;
    private char gender;
    private String dateOfBirth;
    private String primaryPhysician;
    private ArrayList<Appointment> appointments;

    public Patient() {
        patientID = 0;
        firstName = null;
        lastName = null;
        gender = '\0';
        dateOfBirth = null;
        primaryPhysician = null;
        appointments = new ArrayList<>();
    }

    public Patient(int patientID, String firstName, String lastName, char gender, String dateOfBirth, String primaryPhysician) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.primaryPhysician = primaryPhysician;
        appointments = new ArrayList<>();
    }

    public int getPatientID() {return patientID;}
    public void setPatientID(int patientID) {this.patientID = patientID;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public char getGender() {return gender;}
    public void setGender(char gender) {this.gender = gender;}

    public String getDateOfBirth() {return dateOfBirth;}
    public void setDateOfBirth(String DOB) {this.dateOfBirth = DOB;}

    public String getPrimaryPhysician() {return primaryPhysician;}
    public void setPrimaryPhysician(String primaryPhysician) {this.primaryPhysician = primaryPhysician;}

    public int getNumAppointment() {return appointments.size();}
    public Appointment getAppointment(int index) {return appointments.get(index);}
    public void setAppointment (int index, Appointment appointment) {appointments.set(index, appointment);}
    public void addAppointment(Appointment appointment) {appointments.add(appointment);}
    public Appointment removeAppointment(int index) {return appointments.remove(index);}

    public String toString() {
        String newLine = System.getProperties("line.separator");

        String result = patientID + newLine
                + firstName + newLine
                + lastName + newLine
                + gender + newLine
                + dateOfBirth +newLine
                + primaryPhysician;

        for (int i = 0; i < appointments.size(); i++) {
            result += newLine + appointments.get(i).toString();
        }
        return result;
    }
}
