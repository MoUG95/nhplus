package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import utils.PBKDF2WithHmacSHA1;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

public class MainWindowController {



    @FXML
    public Button btnLogin;
    @FXML
    public Button btnTreatment;
    @FXML
    public Button btnUser;
    @FXML
    public Button btnPatient;
    @FXML
    public Button btnLogout;
    @FXML
    public TextField txtUsername;
    @FXML
    public PasswordField txtPassword;
    @FXML
    public Label txtLogging;
    @FXML
    public Label txtWrong;
    @FXML
    private BorderPane mainBorderPane;

    private Node MainWindowBorderPaneCenter;

    @FXML
    private void handleShowAllPatient(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllPatientView.fxml"));
        try {
            if(MainWindowBorderPaneCenter == null){
                MainWindowBorderPaneCenter = mainBorderPane.getCenter();
            }
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllPatientController controller = loader.getController();
    }

    @FXML
    private void handleShowAllTreatments(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllTreatmentView.fxml"));
        try {
            if(MainWindowBorderPaneCenter == null){
                MainWindowBorderPaneCenter = mainBorderPane.getCenter();
            }
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllTreatmentController controller = loader.getController();
    }
    @FXML
    private void handleShowAllUsers(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllUserView.fxml"));
        try {
            if(MainWindowBorderPaneCenter == null){
                MainWindowBorderPaneCenter = mainBorderPane.getCenter();
            }
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllUserController controller = loader.getController();
    }

    @FXML
    private void handleLogout(ActionEvent e) {

        if(MainWindowBorderPaneCenter != null) {
            mainBorderPane.setCenter(MainWindowBorderPaneCenter);
        }
        HandleButtons();
    }

    public void FireClickEvent(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {

        String tempUser = txtUsername.getText();
        String tempPass = txtPassword.getText();

        boolean found = false;

            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            Connection conn = DriverManager.getConnection("jdbc:hsqldb:db/nursingHomeDB;user=SA;password=SA");
            PreparedStatement ps = conn.prepareStatement("select * from User where password = ?");

           ps.setString(1, PBKDF2WithHmacSHA1.generateStorngPasswordHash(tempPass));

            ResultSet results = ps.executeQuery();

            // User = a1 Passwort = 1111
            int cnt = 0;

            if(results.next()){
                cnt = results.getInt(1);
            }
            if(cnt > 0){
                found = true;
            }

            if (found) {
                btnUser.setDisable(false);
                btnLogout.setDisable(false);
                btnPatient.setDisable(false);
                btnTreatment.setDisable(false);
                btnLogin.setDisable(true);
                txtUsername.setDisable(true);
                txtPassword.setDisable(true);


                btnLogin.setVisible(false);
                txtUsername.setVisible(false);
                txtPassword.setVisible(false);
                txtLogging.setVisible(false);
                btnTreatment.setVisible(true);
                btnUser.setVisible(true);
                btnPatient.setVisible(true);
                btnLogout.setVisible(true);
                txtWrong.setVisible(false);
            } else {
                txtWrong.setVisible(true);
            }
        }


    public void HandleButtons() {

        btnUser.setDisable(true);
        btnLogout.setDisable(true);
        btnPatient.setDisable(true);
        btnTreatment.setDisable(true);
        btnLogin.setDisable(false);
        txtUsername.setDisable(false);
        txtPassword.setDisable(false);

        txtPassword.clear();
        txtUsername.clear();

        btnLogin.setVisible(true);
        txtUsername.setVisible(true);
        txtPassword.setVisible(true);
        txtLogging.setVisible(true);
        btnTreatment.setVisible(false);
        btnUser.setVisible(false);
        btnPatient.setVisible(false);
        btnLogout.setVisible(false);

    }
}
