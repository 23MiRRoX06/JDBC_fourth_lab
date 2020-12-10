package ua.lviv.iot.data_access.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.data_access.GenreDao;
import ua.lviv.iot.model.Genre;
import ua.lviv.iot.persistant.ConnectionManager;
import ua.lviv.iot.transformer.Transformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreDaoImpl implements GenreDao {
  @Getter
  private static final GenreDaoImpl INSTANCE = new GenreDaoImpl();
  private ConnectionManager connectionManager = new ConnectionManager();
  private static final String FIND_ALL = "SELECT * FROM ostap_koziaryk_itunes.genre";
  private static final String CREATE = "INSERT INTO ostap_koziaryk_itunes.genre "
      + "(name, description) VALUES (?, ?)";
  private static final String UPDATE = "UPDATE ostap_koziaryk_itunes.genre "
      + "SET name=?, description=? WHERE id=?";
  private static final String DELETE = "DELETE FROM ostap_koziaryk_itunes.genre "
      + "WHERE id=?";
  private static final String FIND_BY_ID = "SELECT * FROM ostap_koziaryk_itunes.genre "
      + "WHERE id=?";

  @Override
  public List<Genre> findAll() throws SQLException {
    List<Genre> genres = new ArrayList<Genre>();
    Connection connection = connectionManager.getConnection();
    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
      while (resultSet.next()) {
        genres.add((Genre) new Transformer(Genre.class).fromResultSetToEntity(resultSet));
      }
    }
    return genres;
  }

  @Override
  public Genre findById(Integer id) throws SQLException {
    Genre genre = null;
    Connection connection = connectionManager.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
      preparedStatement.setInt(1, id);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          genre = (Genre) new Transformer(Genre.class).fromResultSetToEntity(resultSet);
          break;
        }
      }
    }
    return genre;
  }

  @Override
  public int create(Genre genre) throws SQLException {
    Connection connection = connectionManager.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
      setValuesInStatement(preparedStatement, genre);
      return preparedStatement.executeUpdate();
    }
  }

  @Override
  public int update(Genre genre) throws SQLException {
    Connection connection = connectionManager.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
      setValuesInStatement(preparedStatement, genre);
      preparedStatement.setInt(3, genre.getId());
      return preparedStatement.executeUpdate();
    }
  }

  @Override
  public int delete(Integer id) throws SQLException {
    Connection connection = connectionManager.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
      preparedStatement.setInt(1, id);
      return preparedStatement.executeUpdate();
    }
  }

  private void setValuesInStatement(PreparedStatement preparedStatement, Genre genre)
      throws SQLException {
    preparedStatement.setString(1, genre.getName());
    preparedStatement.setString(2, genre.getDescription());
  }
}
