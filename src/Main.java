public class Main {

    public static void main (String [] args) {
        MedicalOffice office = new MedicalOffice(); // declare and initialize MedicalOffice obj by invoking MedOff no-arg constr.
        office.readMedicalOfficeData(args[0]);

        // Call sorting methods b4 displaying or saving so output appear in alph order
        // sort Array Lists
        office.sortData();

        // Display
        System.out.println("Sorted Medical Office Data:");
        System.out.println(office);

        office.saveMedicalOfficeData(args[1]);
    }

}
