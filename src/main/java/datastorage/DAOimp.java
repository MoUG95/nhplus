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
    protected static final String readAllNotLocked = " WHERE delflag is null";
    protected static final String readSingleNotLocked = " AND delflag is null";

    public DAOimp(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void create(T t) throws SQLException{
        Statement st = conn.createStatement();
        st.executeUpdate(getCreateStatementString(t));
    }

    @Override
    public T read(long key) throws SQLException{
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadByIDStatementString(key) + readSingleNotLocked);
        if (result.next()) {
            object = getInstanceFromResultSet(result);
        }
        return object;
    }

    @Override
    public List<T> readAll() throws SQLException{
        ArrayList<T> list = new ArrayList<T>();
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllStatementString() + readAllNotLocked);
        list = getListFromResultSet(result);
        return list;
    }

    @Override
    public void update(T t) throws SQLException{
        Statement st = conn.createStatement();
        st.executeUpdate(getUpdateStatementString(t));
    }

    @Override
    public void lockById(long key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getLockStatementString(key));
    }

    @Override
    public void deleteById(long key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getDeleteStatementString(key));
    }

    protected abstract String getCreateStatementString(T t);

    protected abstract String getReadByIDStatementString(long key);

    protected abstract T getInstanceFromResultSet(ResultSet set) throws SQLException;

    protected abstract String getReadAllStatementString();

    protected abstract ArrayList<T> getListFromResultSet(ResultSet set) throws SQLException;

    protected abstract String getUpdateStatementString(T t);

    protected abstract String getLockStatementString(long key);

    protected abstract String getDeleteStatementString(long key);
}
