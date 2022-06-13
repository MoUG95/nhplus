package model;

import utils.PBKDF2WithHmacSHA1;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 *Users are Employees (Nurses, IT,...)
 * Every Group of Employees has their own Permissions
*/
public class User extends Person{

    private long uid;
    private int  permissionLevel;
    private String password;

    /**
     * constructs a user from the given params.
     * @param firstName
     * @param surname
     * @param uid
     * @param permissionLevel
     * @param password
     */
    public User(long uid,String firstName, String surname, int permissionLevel, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        super(firstName, surname);
        this.uid = uid;
        this.permissionLevel = permissionLevel;
        this.password = PBKDF2WithHmacSHA1.generateStorngPasswordHash(password);
    }

    public User(String firstname, String surname, int permissionLevel, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        super(firstname,surname);
        this.permissionLevel = permissionLevel;
        this.password = PBKDF2WithHmacSHA1.generateStorngPasswordHash(password);
    }

    public User() {
        super();
    }

    /**
     * Return User Id
     */
    public long getUid() {return this.uid;}

    /**
     * Return Permission Level
     */
    public int getPermissionLevel() {return this.permissionLevel;}

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    /**
     * Return User Password
     */
    public String getPassword() throws NoSuchAlgorithmException, InvalidKeySpecException {

        return PBKDF2WithHmacSHA1.generateStorngPasswordHash(this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return string-representation of the user
     */
    public String toString() {
        return "User" +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nID: " + this.uid +
                "\nPermission Level: " + this.permissionLevel +
                "\n";
}}
