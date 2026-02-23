import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

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
        System.out.println("Add Patient button clicked!");
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
