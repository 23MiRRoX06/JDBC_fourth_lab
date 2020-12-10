package ua.lviv.iot.data_access.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.data_access.AlbumDao;
import ua.lviv.iot.model.Album;
import ua.lviv.iot.persistant.ConnectionManager;
import ua.lviv.iot.transformer.Transformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AlbumDaoImpl implements AlbumDao {
  @Getter
  private static final AlbumDaoImpl INSTANCE = new AlbumDaoImpl();
  private ConnectionManager connectionManager = new ConnectionManager();
  private static final String FIND_ALL = "SELECT * FROM ostap_koziaryk_itunes.album";
  private static final String CREATE = "INSERT INTO ostap_koziaryk_itunes.album "
      + "(title, artist_id, release_date, price, genre_id, total_items) VALUES (?, ?, ?, ?, ?, ?)";
  private static final String UPDATE = "UPDATE ostap_koziaryk_itunes.album "
      + "SET title=?, artist_id=?, release_date=?, price=?, genre_id=?, total_items=? WHERE id=?";
  private static final String DELETE = "DELETE FROM ostap_koziaryk_itunes.album "
      + "WHERE id=?";
  private static final String FIND_BY_ID = "SELECT * FROM ostap_koziaryk_itunes.album "
      + "WHERE id=?";

  @Override
  public List<Album> findAll() throws SQLException {
    List<Album> albums = new ArrayList<Album>();
    Connection connection = connectionManager.getConnection();
    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
      while (resultSet.next()) {
        albums.add((Album) new Transformer(Album.class).fromResultSetToEntity(resultSet));
      }
    }
    return albums;
  }

  @Override
  public Album findById(Integer id) throws SQLException {
    Album album = null;
    Connection connection = connectionManager.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
      preparedStatement.setInt(1, id);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          album = (Album) new Transformer(Album.class).fromResultSetToEntity(resultSet);
          break;
        }
      }
    }
    return album;
  }

  @Override
  public int create(Album album) throws SQLException {
    Connection connection = connectionManager.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
      setValuesInStatement(preparedStatement, album);
      return preparedStatement.executeUpdate();
    }
  }

  @Override
  public int update(Album album) throws SQLException {
    Connection connection = connectionManager.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
      setValuesInStatement(preparedStatement, album);
      preparedStatement.setInt(7, album.getId());
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

  private void setValuesInStatement(PreparedStatement preparedStatement, Album album)
      throws SQLException {
    preparedStatement.setString(1, album.getTitle());
    preparedStatement.setInt(2, album.getArtistId());
    preparedStatement.setDate(3, album.getReleaseDate());
    preparedStatement.setBigDecimal(4, album.getPrice());
    preparedStatement.setInt(5, album.getGenreId());
    preparedStatement.setInt(6, album.getTotalItems());
  }
}
