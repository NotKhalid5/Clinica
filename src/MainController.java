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
            System.out.println("Patient added:\n" + patient);
        });
    }

    // handle add appt btn clk
    @FXML
    private void handleAddAppointment() {
        System.out.println("Add Appointment button clicked!");
    }

    // handle exit btn clk
    @FXML
    private void handleExit() {
        System.out.println("Exit button clicked!");
        System.exit(0); // exit app
    }
}
