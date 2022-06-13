package controller;

import datastorage.DAOFactory;
import datastorage.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.User;
import model.UserSession;
import utils.BCryptHashing;

import java.io.IOException;
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
    public TextField txtId;
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
    private void handleShowAllCaregivers(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllCaregiverView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllCaregiverController controller = loader.getController();
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
        boolean status = true;
        UserSession userSession = UserSession.getInstance();
        userSession.clear();
        HandleButtons(status);
    }

    public void handleLogin(ActionEvent actionEvent) throws SQLException {

        String tempId = txtId.getText();
        String tempPass = txtPassword.getText();

        User user;
        boolean found = false;


            UserDAO userDAO = DAOFactory.getDAOFactory().createUserDAO();
        try {
            PreparedStatement passwordStatement = userDAO.getPasswordByUidStatementString(tempId);
            ResultSet rs = passwordStatement.executeQuery();

            if (rs.next()) {
                found = BCryptHashing.isValidPassword(tempPass, rs.getString(1));
            }
        }catch(Exception e){
            txtWrong.setVisible(true);
        }

        user = userDAO.readByUid(tempId);
        UserSession userSession = UserSession.getInstance();
        userSession.init(user);

            if (found) {
                boolean status = false;
                if(userSession.getPermissionLevel() == 1){
                    btnUser.setVisible(true);
                }
                HandleButtons(status);
            } else {
                txtWrong.setVisible(true);
            }
        }


    public void HandleButtons(boolean status) {

        if(!status){
            btnLogin.setVisible(false);
            txtId.setVisible(false);
            txtPassword.setVisible(false);
            txtLogging.setVisible(false);
            btnTreatment.setVisible(true);
            btnPatient.setVisible(true);
            btnLogout.setVisible(true);
            txtWrong.setVisible(false);
        }

        if (status) {
            txtPassword.clear();
            txtId.clear();
            btnLogin.setVisible(true);
            txtId.setVisible(true);
            txtPassword.setVisible(true);
            txtLogging.setVisible(true);
            btnTreatment.setVisible(false);
            btnUser.setVisible(false);
            btnPatient.setVisible(false);
            btnLogout.setVisible(false);
        }
    }
}
