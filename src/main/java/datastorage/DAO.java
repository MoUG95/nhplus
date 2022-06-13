package datastorage;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    void create(T t) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException;

    T read(long key) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException;

    List<T> readAll() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException;

    void update(T t) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException;

    void deleteById(long key) throws SQLException;
}
