package utils;

import javafx.scene.control.Alert;

public class CheckInputsForNull {
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
