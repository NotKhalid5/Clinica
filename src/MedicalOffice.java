import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class MedicalOffice {
    // encapsulated fields
    private String practiceName;
    private ArrayList<String> physicians;
    private ArrayList<Patient> patients;

    // no arg const. initializing field 2 def val
    public MedicalOffice() {
        practiceName = null;
        physicians = new ArrayList<>();
        patients = new ArrayList<>();
    }

    // constr. w/ field params
    public MedicalOffice(String practiceName) {
        this.practiceName = practiceName;
        physicians = new ArrayList<>();
        patients = new ArrayList<>();
    }

    // Practice Name getter and setter
    public String getPracticeName() {return practiceName;}
    public void setPracticeName(String practiceName) {this.practiceName = practiceName;}

    // Physician ArrayList method
    public int getPhysicianSize() {return physicians.size();}
    public String getPhysician(int index) {return physicians.get(index);}
    public void setPhysician(int index, String physician) {physicians.set(index, physician);}
    public void addPhysician(String physician) {physicians.add(physician);}
    public String removePhysician(int index) {return physicians.remove(index);}

    // Patient  ArrayList method
    public int getPatientSize() {return patients.size();}
    public Patient getPatient(int index) {return patients.get(index);}
    public void setPatient(int index, Patient patient) {patients.set(index, patient);}
    public void addPatient(Patient patient) {patients.add(patient);}
    public Patient removePatient(int index) {return patients.remove(index);}

    public void readMedicalOfficeData(String infileName) {

        // try - catch in case file intended to b read DNE
        try {
            Scanner infile = new Scanner(new File(infileName));

            physicians.clear(); // clear old data
            patients.clear(); // clear old data

            practiceName = infile.nextLine();

            int numPhysicians = Integer.parseInt(infile.nextLine()); // from input file format wk that val should b read as an int so wk how many times to run ts loop 4 ts Office's group odf physicians

            for (int i = 0; i < numPhysicians; i++) {
                physicians.add(infile.nextLine()); // add each physician to physicians ArrayList<>()
            }

            int numPatients = Integer.parseInt(infile.nextLine()); // from input file wk that is val should b read as an int so wk how many times to run ts loop 4 ts patient

            for (int i = 0; i < numPatients; i++) {
                // read n each line as per the ip file format converting data type using Wrapper class parsing as needed
                int patientID = Integer.parseInt(infile.nextLine());
                String firstName = infile.nextLine(); // alr read as Str
                String lastName = infile.nextLine();
                char gender = infile.nextLine().trim().charAt(0); // trim strips the line of spaces ensuring the character is moved to the first index and charAt(0) tells the OS to treat the String @ that idx as a char
                String dob = infile.nextLine();
                String primaryPhysician = infile.nextLine();
                Patient patient = new Patient( // patient obj w/ fields invokes Patient constr. w/ args
                        patientID,
                        firstName,
                        lastName,
                        gender,
                        dob,
                        primaryPhysician);

                int numAppointments = Integer.parseInt(infile.nextLine());// from input file wk that is val should b read as an int so wk how many times to run ts loop 4 ts patient

                for (int j = 0; j < numAppointments; j++) { // nested loop so 4 each patient an appt is set
                    // again reading n each line as per the ip file format converting data type using Wrapper class parsing as needed
                    String line = infile.nextLine();

                    // since input is of the format x*y*z read the whole line and split
                    String [] parts = line.split("\\*"); // split the parts into a Str array

                    // assign each field to the corresponding element in the partsStr array
                    int apptPatientID = Integer.parseInt(parts[0]);
                    String apptDate = parts[1];
                    String physician = parts[2];
                    double height = Double.parseDouble(parts[3]);
                    double weight = Double.parseDouble(parts[4]);
                    double temperature = Double.parseDouble(parts[5]);
                    int pulse = Integer.parseInt(parts[6]);
                    int bpSystolic = Integer.parseInt(parts[7]);
                    int bpDiastolic = Integer.parseInt(parts[8]);
                    String notes = parts[9];

                    Appointment appointment = new Appointment( // Appointment obj w/ fields invokes Appointment constr. w/ args
                            apptPatientID,
                            apptDate,
                            physician,
                            height,
                            weight,
                            temperature,
                            pulse,
                            bpSystolic,
                            bpDiastolic,
                            notes
                    ); // put appt fields inside to call constructor with args
                    patient.addAppointment(appointment);
                }

                patients.add(patient);
            }

            infile.close();
        }
        catch(java.io.FileNotFoundException e ) {
            System.out.println("Error reading file: " + e);
            System.exit(-1);
        }
    }

    public void saveMedicalOfficeData(String outfileName) {
        try{
            PrintWriter writer = new PrintWriter(outfileName); //

            writer.println(practiceName);

            writer.println(physicians.size());
            for (String physician : physicians) {
                writer.println(physician);
            }

            writer.println(patients.size());

            for (Patient patient : patients) { // enhanced 4 loop allows us 2 run ts 4 each object stored n the patients Array List w/out knowing exactly how many that is
                writer.println(patient.getPatientID());
                writer.println(patient.getFirstName());
                writer.println(patient.getLastName());
                writer.println(patient.getGender());
                writer.println(patient.getDateOfBirth());
                writer.println(patient.getPrimaryPhysician());
                writer.println(patient.getAppointmentSize());

                for (int i = 0; i < patient.getAppointmentSize(); i++) { // uses patient method to indicate how many times to run
                    Appointment appt = patient.getAppointment(i);

                    writer.println(
                            appt.getPatientID() + "*" +
                            appt.getApptDate() + "*" +
                            appt.getPhysician() + "*" +
                            appt.getHeight() + "*" +
                            appt.getWeight() + "*" +
                            appt.getTemperature() + "*" +
                            appt.getPulse() + "*" +
                            appt.getBpSystolic() + "*" +
                            appt.getBpDiastolic() + "*" +
                            appt.getNotes()
                    );

                }
            }
            writer.close();
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
            System.exit(-1);
        }

    }
    public void displayAppointment(int patientID, String apptDate) {
        for (Patient patient : patients) { // enhanced 4 loop allows us 2 run ts 4 each object stored n the patients Array List w/out knowing exactly how many that is
            if (patient.getPatientID() == patientID) { // checks whether patient ID in stored alr and if so display info in the following format
                for (int i = 0; i < patient.getAppointmentSize(); i++) {
                    Appointment appt = patient.getAppointment(i);
                    if (appt.getApptDate().equals(apptDate)) { // uses equals() method because the Patient class' getApptDate() method returns a String
                        String message =
                                "Medical Practice: " + practiceName +
                                        "\nPatient: " + patient.getFirstName() + " " + patient.getLastName() +
                                        "\nPhysician: " + appt.getPhysician() +
                                        "\n\nAppointment Date: " + appt.getApptDate() +
                                        "\nHeight: " + appt.getHeight() +
                                        "\nWeight: " + appt.getWeight() +
                                        "\nTemperature: " + appt.getTemperature() +
                                        "\nPulse: " + appt.getPulse() +
                                        "\nBlood Pressure: "
                                        + appt.getBpSystolic() + "/"
                                        + appt.getBpDiastolic() +
                                        "\nNotes: " + appt.getNotes();

                        JOptionPane.showMessageDialog(null, message);

                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "No appointment found for that date.");
                return;
            }
        }

        JOptionPane.showMessageDialog(null,"Patient ID not found.");
    }

    @Override
    public String toString() {
        String separator = System.getProperty("line.separator");
        String result = practiceName + separator;

        for (String physician : physicians) {
            result += physician + separator;
        }

        for (Patient patient : patients) {
            result += patient.toString() + separator;
        }
        return result;
    }
}
