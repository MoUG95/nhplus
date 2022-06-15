package utils;

import javafx.scene.control.Alert;

/**
 * The <code>CheckInputForNUll</code> checks the elements of the input array for null values/empty strings and is used
 * to prevent empty inputs in textfields of nhplus.
 */

public class CheckInputsForNull {
    /**
     * Checks the elements of the input array for null values/empty strings and returns false if
     * empty strings/null values are found and shows an alert.
     * @param array
     * @return boolean
     */
    public static boolean checkInputsForNull(Object[] array){
        boolean noNullValues = true;
        for (int i=0; i<=array.length-1; i++) {
            if (array[i] == null || array[i] == "") {
                noNullValues = false;
            }
        }
        if (!noNullValues){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Eingabewert fehlt!");
            alert.setContentText("Bitte alle Felder ausfüllen!");
            alert.showAndWait();
        }
        return noNullValues;
    }
}
