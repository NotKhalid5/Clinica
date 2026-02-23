import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.Collections;
import java.util.Comparator;

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
            // Opens the input file n preps 2 read structured data n the
            // exact order def'd by the assignment's required file format.
            Scanner infile = new Scanner(new File(infileName));

            physicians.clear(); // clear old data
            patients.clear(); // clear old data

            // 1st ln of file containing med prac name
            practiceName = infile.nextLine();

            int numPhysicians = Integer.parseInt(infile.nextLine().trim()); // from input file format wk that val should b read as an int so wk how many times to run ts loop 4 ts Office's group odf physicians

            for (int i = 0; i < numPhysicians; i++) {
                physicians.add(infile.nextLine()); // add each physician to physicians ArrayList<>()
            }

            int numPatients = Integer.parseInt(infile.nextLine().trim()); // from input file wk that is val should b read as an int so wk how many times to run ts loop based on tot # of patient records

            for (int i = 0; i < numPatients; i++) { // For each patient, read the demographic information in the exact order specified by the file format
                // read n each line as per the ip file format converting data type using Wrapper class parsing as needed
                int patientID = Integer.parseInt(infile.nextLine().trim());
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

                // number of appointment if this specific patients
                int numAppointments = Integer.parseInt(infile.nextLine().trim());// from input file wk that is val should b read as an int so wk how many times to run ts loop 4 ts patient

                for (int j = 0; j < numAppointments; j++) { // nested loop so 4 each patient an appt is set
                    // again reading n each line as per the ip file format converting data type using Wrapper class parsing as needed
                    String line = infile.nextLine();

                    // since input is of the format x*y*z read the whole line and split the whole line into parts at the *
                    String [] parts = line.split("\\*", -1); // split the parts into a Str array

                    if (parts.length != 10) {
                        System.out.println("Invalid appointment format: " + line);
                        continue;
                    }
                    // assign each field to the corresponding element in the partsStr array
                    // + trim each value b4 parsing to avoid NumberFormatException
                    int apptPatientID = Integer.parseInt(parts[0].trim());
                    String apptDate = parts[1].trim();
                    String physician = parts[2].trim();
                    double height = Double.parseDouble(parts[3].trim());
                    double weight = Double.parseDouble(parts[4].trim());
                    double temperature = Double.parseDouble(parts[5].trim());
                    int pulse = Integer.parseInt(parts[6].trim());
                    int bpSystolic = Integer.parseInt(parts[7].trim());
                    int bpDiastolic = Integer.parseInt(parts[8].trim());
                    String notes = parts[9].trim();

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

                // Once all appointments for a patient are read the fully constr. Patient object is added to the Medical Office patient list
                patients.add(patient);
            }

            infile.close(); // Close the Scanner to releasae the file resource
        }
        catch(java.io.FileNotFoundException e ) { // error catching for if File Not Found
            System.out.println("Error: " + e);
            System.exit(-1);
        }
    }

    public void saveMedicalOfficeData(String outfileName) {
        try{
            PrintWriter writer = new PrintWriter(outfileName); // Creates a PrintWriter obj 2 write structured data 2 the output file n the same format as the input file

            writer.println(practiceName); // 1st ln is prac name

            // Write the # of physicians followed by each physician name on its own ln
            writer.println(physicians.size());
            for (String physician : physicians) {
                writer.println(physician);
            }

            writer.println(patients.size()); // write the # of patients so the file can later reconstruct the correct # of records

            // For each patient, write demographic information n the exact order as per the input format
            for (Patient patient : patients) { // enhanced 4 loop allows us 2 run ts 4 each object stored n the patients Array List w/out knowing exactly how many that is
                writer.println(patient.getPatientID());
                writer.println(patient.getFirstName());
                writer.println(patient.getLastName());
                writer.println(patient.getGender());
                writer.println(patient.getDateOfBirth());
                writer.println(patient.getPrimaryPhysician());
                writer.println(patient.getAppointmentSize()); // Write # of appointments belonging to ts patient

                for (int i = 0; i < patient.getAppointmentSize(); i++) { // uses patient method to indicate how many times to run
                    // Each appointment is written as a single ln w/ fields separated by '*' to match the required file format so file can b read again w/out errors
                    Appointment appt = patient.getAppointment(i);

                    // Write the # of appointment belonging to ts specific patient
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
            writer.close(); // close the writer to ensure all data is flushed and properly saved 2 the output file
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
        // Retrieves the system-specific ln separator to ensure the output format works correctly on any OS
        String separator = System.getProperty("line.separator");

        //Start building the string rep w/ the prac name as the 1st ln
        String result = practiceName + separator;

        // Append each physician name 2 the result string separating each entry w/ the sys ln sep
        for (String physician : physicians) {
            result += physician + separator;
        }

        // Append the str rep of each patient; Each Patient obj's toString() method is called which includes its appts
        for (Patient patient : patients) {
            result += patient.toString() + separator;
        }

        return result; // return the fully constrd str rep of the MedicalOffice obj
    }

    // sort methods can't b private if they r accessed by a diff class
    // thus store the private class info in a public method accessed n main as per the unit test
    // (these methods modify existing ArrayLists)

    // Sort patients alphabetically by last name with first names used as secondary comparisons
    private void sortPatients() {
        Collections.sort(patients, new Comparator<Patient>() {
            @Override
            public int compare(Patient p1, Patient p2) {
                int lastCompare = p1.getLastName().compareToIgnoreCase(p2.getLastName());
                if (lastCompare != 0) {
                    return lastCompare;
                }
                return p1.getFirstName().compareToIgnoreCase(p2.getFirstName());
            }
        });
    }

    // Sorts physician list alphabetically; alr stored a last, first so default sorting orders by last name
    private void sortPhysicians() {
        Collections.sort(physicians, String.CASE_INSENSITIVE_ORDER);
    }

    public void sortData() {
        sortPhysicians();
        sortPatients();
    }
}
