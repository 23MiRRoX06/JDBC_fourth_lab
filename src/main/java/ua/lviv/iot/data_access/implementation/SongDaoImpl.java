package ua.lviv.iot.data_access.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.data_access.SongDao;
import ua.lviv.iot.model.Song;
import ua.lviv.iot.persistant.ConnectionManager;
import ua.lviv.iot.transformer.Transformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SongDaoImpl implements SongDao {
  @Getter
  private static final SongDaoImpl INSTANCE = new SongDaoImpl();
  private ConnectionManager connectionManager = new ConnectionManager();
  private static final String FIND_ALL = "SELECT * FROM ostap_koziaryk_itunes.song";
  private static final String CREATE = "INSERT INTO ostap_koziaryk_itunes.song "
      + "(title, artist_id, album_id, release_date, price, " +
      "duration_in_minutes, genre_id, popularity, music_video_id, " +
      "with_parental_advisory_logo) " +
      "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
  private static final String UPDATE = "UPDATE ostap_koziaryk_itunes.song "
      + "SET title=?, artist_id=?, album_id=?, release_date=?, price=?, "
      + "duration_in_minutes=?, genre_id=?, popularity=?, music_video_id=?, "
      + "with_parental_advisory_logo=? WHERE id=?";
  private static final String DELETE = "DELETE FROM ostap_koziaryk_itunes.song "
      + "WHERE id=?";
  private static final String FIND_BY_ID = "SELECT * FROM ostap_koziaryk_itunes.song "
      + "WHERE id=?";

  @Override
  public List<Song> findAll() throws SQLException {
    List<Song> songs = new ArrayList<Song>();
    Connection connection = connectionManager.getConnection();
    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
      while (resultSet.next()) {
        songs.add((Song) new Transformer(Song.class).fromResultSetToEntity(resultSet));
      }
    }
    return songs;
  }

  @Override
  public Song findById(Integer id) throws SQLException {
    Song song = null;
    Connection connection = connectionManager.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
      preparedStatement.setInt(1, id);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          song = (Song) new Transformer(Song.class).fromResultSetToEntity(resultSet);
          break;
        }
      }
    }
    return song;
  }

  @Override
  public int create(Song song) throws SQLException {
    Connection connection = connectionManager.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
      setValuesInStatement(preparedStatement, song);
      return preparedStatement.executeUpdate();
    }
  }

  @Override
  public int update(Song song) throws SQLException {
    Connection connection = connectionManager.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
      setValuesInStatement(preparedStatement, song);
      preparedStatement.setInt(11, song.getId());
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

  private void setValuesInStatement(PreparedStatement preparedStatement, Song song)
      throws SQLException {
    preparedStatement.setString(1, song.getTitle());
    preparedStatement.setInt(2, song.getArtistId());
    preparedStatement.setInt(3, song.getAlbumId());
    preparedStatement.setDate(4, song.getReleaseDate());
    preparedStatement.setBigDecimal(5, song.getPrice());
    preparedStatement.setDouble(6, song.getDurationInMinutes());
    preparedStatement.setInt(7, song.getGenreId());
    preparedStatement.setInt(8, song.getPopularity());
    preparedStatement.setInt(9, song.getMusicVideoId());
    preparedStatement.setBoolean(10, song.getWithParentalAdvisoryLogo());
  }
}