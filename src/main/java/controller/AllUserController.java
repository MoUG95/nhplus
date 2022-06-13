package controller;

import com.sun.javafx.scene.control.IntegerField;
import datastorage.DAOFactory;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import datastorage.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.User;
import model.UserSession;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;

public class AllUserController {

    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, Integer> colID;
    @FXML
    private TableColumn<User, String> colFirstName;
    @FXML
    private TableColumn<User, String> colSurname;
    @FXML
    private TableColumn<User, Integer> colPermissionlevel;
    @FXML
    private TableColumn<User, String> colPassword;

    @FXML
    Button btnDelete;
    @FXML
    Button btnAdd;
    @FXML
    TextField txtSurname;
    @FXML
    TextField txtFirstname;
    @FXML
    IntegerField intPermissionlevel;
    @FXML
    TextField txtPassword;

    private ObservableList<User> tableviewContent = FXCollections.observableArrayList();
    private UserDAO dao;

    public void initialize(){
        readAllAndShowInTableView();

        this.colID.setCellValueFactory(new PropertyValueFactory<User, Integer>("uid"));

        //CellValuefactory zum Anzeigen der Daten in der TableView
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        //CellFactory zum Schreiben innerhalb der Tabelle
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colPermissionlevel.setCellValueFactory(new PropertyValueFactory<User, Integer>("permissionLevel"));
        this.colPermissionlevel.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        this.colPassword.setCellValueFactory(new PropertyValueFactory<User, String>("password"));

        //Anzeigen der Daten
        this.tableView.setItems(this.tableviewContent);
    }

    /**
     * handles new firstname value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditFirstname(TableColumn.CellEditEvent<User, String> event){
        event.getRowValue().setFirstName(event.getNewValue());
        doStringUpdate(event);
    }

    /**
     * handles new surname value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditSurname(TableColumn.CellEditEvent<User, String> event){
        event.getRowValue().setSurname(event.getNewValue());
        doStringUpdate(event);
    }

    /**
     * handles new birthdate value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditPermissionLevel(TableColumn.CellEditEvent<User, Integer> event){
        event.getRowValue().setPermissionLevel(event.getNewValue());
        doIntUpdate(event);
    }

    /**
     * handles new carelevel value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditPassword(TableColumn.CellEditEvent<User, String> event){
        event.getRowValue().setPassword(event.getNewValue());
        doStringUpdate(event);
    }

    /**
     * updates a patient by calling the update-Method in the {@link PatientDAO}
     * @param t row to be updated by the user (includes the patient)
     */
    private void doStringUpdate(TableColumn.CellEditEvent<User, String> t) {
        try {
            dao.update(t.getRowValue());
        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    private void doIntUpdate(TableColumn.CellEditEvent<User, Integer> t) {
        try {
            dao.update(t.getRowValue());
        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    /**
     * calls readAll in {@link PatientDAO} and shows patients in the table
     */
    private void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createUserDAO();
        List<User> allUsers;
        try {
            allUsers = dao.readAll();
            this.tableviewContent.addAll(allUsers);
        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a delete-click-event. Calls the delete methods in the {@link PatientDAO} and {@link TreatmentDAO}
     */
    @FXML
    public void handleDeleteRow() {
        TreatmentDAO tDao = DAOFactory.getDAOFactory().createTreatmentDAO();
        User selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        try {
            tDao.deleteByPid(selectedItem.getUid());
            dao.deleteById(selectedItem.getUid());
            this.tableView.getItems().remove(selectedItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a add-click-event. Creates a patient and calls the create method in the {@link PatientDAO}
     */
    @FXML
    public void handleAdd(){
        String surname = this.txtSurname.getText();
        String firstname = this.txtFirstname.getText();
        int permissionLevel = this.intPermissionlevel.getValue();
        String password = this.txtPassword.getText();
        try {
            User u = new User(firstname, surname, permissionLevel, password);
            dao.create(u);
        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();

        }
        readAllAndShowInTableView();
        clearTextfields();
    }

    /**
     * removes content from all textfields
     */
    private void clearTextfields() {
        this.txtFirstname.clear();
        this.txtSurname.clear();
        this.txtPassword.clear();
    }
}
