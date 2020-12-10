package ua.lviv.iot.data_access;

import java.sql.SQLException;
import java.util.List;

public interface GeneralDao<T, Id> {

  List<T> findAll() throws SQLException;

  T findById(Id id) throws SQLException;

  int create(T entity) throws SQLException;

  int update(T entity) throws SQLException;

  int delete(Id id) throws SQLException;
}
