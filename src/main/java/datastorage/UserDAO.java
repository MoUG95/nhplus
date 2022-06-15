package datastorage;

import model.User;
import utils.BCryptHashing;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;

/**
 * * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific user-SQL-queries.
 * */

public class UserDAO extends DAOimp<User>{

    /**
     * constructs Onbject. Calls the Constructor from <code>DAOImp</code> to store the connection.
     * @param conn
     */

    public UserDAO(Connection conn) {
        super(conn);
    }

    /**
     * generates a <code>INSERT INTO</code>-Statement for a given user
     * @param user for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(User user) {
        return String.format("INSERT INTO user ( firstname, surname, permissionLevel, password) VALUES ('%s', '%s', '%s', '%s')",
                user.getFirstName(), user.getSurname(), user.getPermissionLevel(), BCryptHashing.hashPassword(user.getPassword()));
    }

    /**
     * generates a <code>select</code>-Statement for a given key
     * @param key for which a specific SELECTis to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM user WHERE uid = %d", key);
    }

    /**
     * generates a prepared statement to select the password for the user
     * @param key
     * @return PreparedStatement
     * @throws SQLException
     */
    public PreparedStatement getPasswordByUidStatementString(String key) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select password from User where uid = ? AND delflag is null");
        ps.setString(1, key);
        return ps;
    }

    /**
     * maps a <code>ResultSet</code> to a <code>User</code>
     * @param result ResultSet with a single row. Columns will be mapped to a user-object.
     * @return user with the data from the resultSet.
     */
    @Override
    protected User getInstanceFromResultSet(ResultSet result) throws SQLException {
        return new User(result.getLong(1), result.getString(2), result.getString(3),
                result.getInt(4), result.getString(5));
    }

    /**
     * generates a <code>SELECT</code>-Statement for all users.
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM user";
    }

    /**
     * maps a <code>ResultSet</code> to a <code>User-List</code>
     * @param result ResultSet with a multiple rows. Data will be mapped to user-object.
     * @return ArrayList with users from the resultSet.
     */
    @Override
    protected ArrayList<User> getListFromResultSet(ResultSet result) throws SQLException{
        ArrayList<User> list = new ArrayList<User>();
        User u = null;
        while (result.next()) {
            u = new User(result.getLong(1), result.getString(2), result.getString(3),
                     result.getInt(4), result.getString(5));
            list.add(u);
        }
        return list;
    }

    /**
     * generates a <code>UPDATE</code>-Statement for a given user
     * @param user for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(User user) {
        return String.format("UPDATE user SET firstname = '%s', surname = '%s',  permissionLevel = '%s', " +
                "password = '%s' WHERE uid = %s", user.getFirstName(), user.getSurname(), user.getPermissionLevel(), user.getPassword(), user.getUid());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM user WHERE uid=%d", key);
    }

    /**
     * generates a <code>lock</code>-statement for a given key
     * @param key for which a specific UPDATE (set flag for locked data) is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getLockStatementString(long key) {
        return String.format("UPDATE user SET delflag = 'x' WHERE uid=%d", key);
    }

    public User readByUid(String uid) throws SQLException {
        User object = null;
        Statement st = conn.createStatement();
        ResultSet result = getReadByUid(uid).executeQuery();
        if (result.next()) {
            object = getInstanceFromResultSet(result);
        }
        return object;
    }

    private PreparedStatement getReadByUid(String uid) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE uid = ? AND delflag is null");
        ps.setString(1, uid);
        return ps;
    }


}
