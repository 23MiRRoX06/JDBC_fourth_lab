package ua.lviv.iot.business;

import java.sql.SQLException;
import java.util.List;

public interface GeneralService<T, Id> {
  List<T> findAll() throws SQLException;

  T findById(Id id) throws SQLException;

  int create(T entity) throws SQLException;

  int update(T entity) throws SQLException;

  int delete(Id id) throws SQLException;
}
