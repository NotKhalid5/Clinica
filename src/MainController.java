import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class MainController {
    // Declare button fx:id fields
    @FXML private Button btnAddPatient;
    @FXML private Button btnAddAppointment;
    @FXML private Button btnExit;
    @FXML private ComboBox<String> cmbPatients; // ComboBox 4 patient names
    @FXML private TableView<?> tblAppointments; // TableView 4 disp appts

    private MedicalOffice office;

    // set office instance 2 pass n2 main app
    public void setMedicalOffice(MedicalOffice office) {
        this.office = office;
        loadPatientList(); // Populate ComboBox w/ patients when office is set
    }

    // load patients n2 ComboBox
    private void loadPatientList() {
        for (int i = 0; i < office.getPatientSize(); i++) {
            // Add patient names
            String fullName = office.getPatient(i).getFirstName() + " " + office.getPatient(i).getLastName();
            cmbPatients.getItems().add(fullName);
        }
    }

    // handle view appt button clk
    @FXML
    private void handleViewAppointments() {
        String selectedPatient = cmbPatients.getValue();
        if(selectedPatient != null) {
            // Show appts
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Appointments");
            alert.setHeaderText("Appointments for " + selectedPatient);
            alert.setContentText("Displaying appointments for " + selectedPatient);
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Patient Selected");
            alert.setHeaderText("Please selected a patient.");
            alert.showAndWait();
        }
    }

    // handle add patient btn clk
    @FXML
    private void handleAddPatient() {
        // custom dialog
        Dialog<Patient> dialog = new Dialog<>();
        dialog.setTitle("Add New Patient");
        dialog.setHeaderText("Enter patient details: ");

        // set btns
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // create fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // make text box for each Patient property
        TextField tfID = new TextField();
        tfID.setPromptText("Patient ID");
        TextField tfFirst = new TextField();
        tfFirst.setPromptText("First Name");
        TextField tfLast = new TextField();
        tfLast.setPromptText("Last Name");
        TextField tfGender = new TextField();
        tfGender.setPromptText("Gender (M/F)");
        TextField tfDOB = new TextField();
        tfDOB.setPromptText("Date of Birth (MM/DD/YYYY)");
        TextField tfPhysician = new TextField();
        tfPhysician.setPromptText("Primary Physician");

        grid.add(new Label("ID:"), 0, 0);
        grid.add(tfID, 1, 0);
        grid.add(new Label("First Name:"), 0, 2);
        grid.add(tfFirst,1,1);
        grid.add(new Label("Last Name"),0,3);
        grid.add(tfLast,1,3);
        grid.add(new Label("Gender:"),0,4);
        grid.add(tfGender,1,4);
        grid.add(new Label("Date of Birth:"),0,5);
        grid.add(tfDOB,1,5);

        dialog.getDialogPane().setContent(grid);

        // convert result 2 Patient obj when add btn is clkd
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    int id = Integer.parseInt(tfID.getText().trim());
                    char gender = tfGender.getText().trim().charAt(0);
                    return new Patient(id, tfFirst.getText(), tfLast.getText(), gender,tfDOB.getText(),tfPhysician.getText());
                }
                catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText("Failed to create patient");
                    alert.setContentText("Please check your input values.");
                    alert.showAndWait();
                }
            }
            return null;
        });

        // Show dialog and capture result
        Optional<Patient> result = dialog.showAndWait();
        result.ifPresent(patient -> {
            office.addPatient(patient); // add patient 2 MedicalOffice
            cmbPatients.getItems().add(patient.getFirstName() + " " + patient.getLastName()); // update ComboBox
            System.out.println("Patient added:\n\n" + patient);
        });
    }

    // handle add appt btn clk
    @FXML
    private void handleAddAppointment() {
//        Dialog<Appointment> dialog = new Dialog<>();
//        dialog.setTitle("Add New Appointment");
//        dialog.setHeaderText("Enter Appointment Detais");
//
//        // set btns
//        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
//        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
//
//        // create fields
//        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//
//        // mk txt box 4 each Appt field
//        TextField tfID = new TextField();
//        tfID.setPromptText("Patient ID");
//        TextField tfApptDate = new TextField();
//        tfApptDate.setPromptText("Appointment Date");
//        TextField tfPhys = new TextField();
//        tfPhys.setPromptText("Physician");
//        TextField tfHeight = new TextField();
//        tfHeight.setPromptText("Height");
//        TextField tfWeight = new TextField();
//        tfWeight.setPromptText("Weight");
//        TextField tfTemp = new TextField();
//        tfTemp.setPromptText("Temperature");
//        TextField tfPulse = new TextField();
//        tfPulse.setPromptText("Pulse");
//        TextField tfBPS = new TextField();
//        tfBPS.setPromptText("BPSystolic");
//        TextField tfBPD = new TextField();
//        tfBPD.setPromptText("BPDiastolic");
//
//        grid.add(new Label("ID"),0,0);
//        grid.add(tfID, 1,0);
//        grid.add(new Label("Date"),0,1);
//        grid.add(tfApptDate, 1,1);
//        grid.add(new Label("Physician"),0,2);
//        grid.add(tfPhys, 1,2);
//        grid.add(new Label("Height"),0,3);
//        grid.add(tfHeight, 1,3);
//        grid.add(new Label("Weight"),0,4);
//        grid.add(tfWeight, 1,4);
//        grid.add(new Label("Temperature"),0,5);
//        grid.add(tfTemp, 1,5);
//        grid.add(new Label("Pulse"),0,6);
//        grid.add(tfPulse, 1,6);
//        grid.add(new Label("BPSystolic"),0,7);
//        grid.add(tfBPS, 1,7);
//        grid.add(new Label("BPDiastolic"),0,8);
//        grid.add(tfBPD, 1,8);
//
//        dialog.getDialogPane().setContent(grid);
//
//        // convert result 2 Appt obj when add is clkd
//        dialog.setResultConverter(dialogButton -> {
//            if (dialogButton == addButtonType) {
//                try {
//                    int id = Integer.parseInt(tfID.getText().trim());
//                    int pulse = Integer.parseInt(tfPulse.getText().trim());
//                    int bps = Integer.parseInt(tfBPS.getText().trim());
//                    int bpd = Integer.parseInt(tfBPD.getText().trim());
//                    double height = Double.parseDouble(tfHeight.getText().trim());
//                    double weight = Double.parseDouble(tfWeight.getText().trim());
//                    double temp = Double.parseDouble(tfTemp.getText().trim());
//                    return new Appointment(id, tfApptDate.getText(), tfPhys.getText(), height, weight, temp, pulse, bps, bpd);
//                }
//                catch(Exception e) {
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Invalid Input");
//                    alert.setHeaderText("Failed to create appointment");
//                    alert.setContentText("Please check your input values.");
//                    alert.showAndWait();
//                }
//            }
//            return null;
//        });
//
//        // show dialog and capt result
//        Optional<Appointment> result = dialog.showAndWait();
//        result.ifPresent(appointment -> {
//            office.addAppointment(appointment); // add patient 2 MedicalOffice
//            tblAppointments.getItems().add(appointment); // update ComboBox
//            System.out.println("Appointment added:\n\n" + appointment);
//        });
        System.out.println("Appointment Added!");
    }

    // handle exit btn clk
    @FXML
    private void handleExit() {
        System.out.println("Exit button clicked!");
        System.exit(0); // exit app
    }
}
