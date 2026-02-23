import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    private MedicalOffice office; // declare MedicalOffice obj

    @Override
    public void start(Stage stage) throws Exception {
        // initialize MedicalOffice obj by invoking MedOff no-arg constr.
        office = new MedicalOffice();
        office.readMedicalOfficeData("medicalDataSmall.txt"); // passedd input file n as arg

        // Call sorting methods b4 displaying or saving so output appear in alph order
        // sort Array Lists
        office.sortData();

        // Display
        System.out.println("Sorted Medical Office Data:");
        System.out.println(office);

        // Load FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        Parent root = loader.load();

        // Get controller
        MainController controller = loader.getController();

        // pass MedOff instance
        controller.setMedicalOffice(office);

        Scene scene = new Scene(root);
        stage.setTitle("Medical Office");
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String [] args) {
        launch(args); // JavaFX
    }

}
