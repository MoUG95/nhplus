package datastorage;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOimp<T> implements DAO<T>{
    protected Connection conn;

    public DAOimp(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void create(T t) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        Statement st = conn.createStatement();
        st.executeUpdate(getCreateStatementString(t));
    }

    @Override
    public T read(long key) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadByIDStatementString(key));
        if (result.next()) {
            object = getInstanceFromResultSet(result);
        }
        return object;
    }

    @Override
    public List<T> readAll() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        ArrayList<T> list = new ArrayList<T>();
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllStatementString());
        list = getListFromResultSet(result);
        return list;
    }

    @Override
    public void update(T t) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        Statement st = conn.createStatement();
        st.executeUpdate(getUpdateStatementString(t));
    }

    @Override
    public void deleteById(long key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getDeleteStatementString(key));
    }

    protected abstract String getCreateStatementString(T t) throws NoSuchAlgorithmException, InvalidKeySpecException;

    protected abstract String getReadByIDStatementString(long key);

    protected abstract T getInstanceFromResultSet(ResultSet set) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException;

    protected abstract String getReadAllStatementString();

    protected abstract ArrayList<T> getListFromResultSet(ResultSet set) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException;

    protected abstract String getUpdateStatementString(T t) throws NoSuchAlgorithmException, InvalidKeySpecException;

    protected abstract String getDeleteStatementString(long key);
}
