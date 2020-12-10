package ua.lviv.iot.data_access.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.data_access.ArtistDao;
import ua.lviv.iot.model.Artist;
import ua.lviv.iot.persistant.ConnectionManager;
import ua.lviv.iot.transformer.Transformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArtistDaoImpl implements ArtistDao {
  @Getter
  private static final ArtistDaoImpl INSTANCE = new ArtistDaoImpl();
  private ConnectionManager connectionManager = new ConnectionManager();
  private static final String FIND_ALL = "SELECT * FROM ostap_koziaryk_itunes.artist";
  private static final String CREATE = "INSERT INTO ostap_koziaryk_itunes.artist "
      + "(title, total_songs, total_albums) VALUES (?, ?, ?)";
  private static final String UPDATE = "UPDATE ostap_koziaryk_itunes.artist "
      + "SET title=?, total_songs=?, total_albums=? WHERE id=?";
  private static final String DELETE = "DELETE FROM ostap_koziaryk_itunes.artist "
      + "WHERE id=?";
  private static final String FIND_BY_ID = "SELECT * FROM ostap_koziaryk_itunes.artist "
      + "WHERE id=?";

  @Override
  public List<Artist> findAll() throws SQLException {
    List<Artist> artists = new ArrayList<Artist>();
    Connection connection = connectionManager.getConnection();
    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
      while (resultSet.next()) {
        artists.add((Artist) new Transformer(Artist.class).fromResultSetToEntity(resultSet));
      }
    }
    return artists;
  }

  @Override
  public Artist findById(Integer id) throws SQLException {
    Artist artist = null;
    Connection connection = connectionManager.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
      preparedStatement.setInt(1, id);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          artist = (Artist) new Transformer(Artist.class).fromResultSetToEntity(resultSet);
          break;
        }
      }
    }
    return artist;
  }

  @Override
  public int create(Artist artist) throws SQLException {
    Connection connection = connectionManager.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
      setValuesInStatement(preparedStatement, artist);
      return preparedStatement.executeUpdate();
    }
  }

  @Override
  public int update(Artist artist) throws SQLException {
    Connection connection = connectionManager.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
      setValuesInStatement(preparedStatement, artist);
      preparedStatement.setInt(4, artist.getId());
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

  private void setValuesInStatement(PreparedStatement preparedStatement, Artist artist)
      throws SQLException {
    preparedStatement.setString(1, artist.getTitle());
    preparedStatement.setInt(2, artist.getTotalSongs());
    preparedStatement.setInt(3, artist.getTotalAlbums());
  }
}
