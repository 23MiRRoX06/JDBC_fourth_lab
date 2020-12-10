package ua.lviv.iot.data_access.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.data_access.MusicVideoDao;
import ua.lviv.iot.model.MusicVideo;
import ua.lviv.iot.persistant.ConnectionManager;
import ua.lviv.iot.transformer.Transformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MusicVideoDaoImpl implements MusicVideoDao {
  @Getter
  private static final MusicVideoDaoImpl INSTANCE = new MusicVideoDaoImpl();
  private ConnectionManager connectionManager = new ConnectionManager();
  private static final String FIND_ALL = "SELECT * FROM ostap_koziaryk_itunes.music_video";
  private static final String CREATE = "INSERT INTO ostap_koziaryk_itunes.music_video "
      + "(size_in_megabytes, release_date) VALUES (?, ?)";
  private static final String UPDATE = "UPDATE ostap_koziaryk_itunes.music_video "
      + "SET size_in_megabytes=?, release_date=? WHERE id=?";
  private static final String DELETE = "DELETE FROM ostap_koziaryk_itunes.music_video "
      + "WHERE id=?";
  private static final String FIND_BY_ID = "SELECT * FROM ostap_koziaryk_itunes.music_video "
      + "WHERE id=?";

  @Override
  public List<MusicVideo> findAll() throws SQLException {
    List<MusicVideo> musicVideos = new ArrayList<MusicVideo>();
    Connection connection = connectionManager.getConnection();
    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
      while (resultSet.next()) {
        musicVideos.add((MusicVideo) new Transformer(MusicVideo.class).fromResultSetToEntity(resultSet));
      }
    }
    return musicVideos;
  }

  @Override
  public MusicVideo findById(Integer id) throws SQLException {
    MusicVideo musicVideo = null;
    Connection connection = connectionManager.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
      preparedStatement.setInt(1, id);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          musicVideo = (MusicVideo) new Transformer(MusicVideo.class).fromResultSetToEntity(resultSet);
          break;
        }
      }
    }
    return musicVideo;
  }

  @Override
  public int create(MusicVideo musicVideo) throws SQLException {
    Connection connection = connectionManager.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
      setValuesInStatement(preparedStatement, musicVideo);
      return preparedStatement.executeUpdate();
    }
  }

  @Override
  public int update(MusicVideo musicVideo) throws SQLException {
    Connection connection = connectionManager.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
      setValuesInStatement(preparedStatement, musicVideo);
      preparedStatement.setInt(3, musicVideo.getId());
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

  private void setValuesInStatement(PreparedStatement preparedStatement, MusicVideo musicVideo)
      throws SQLException {
    preparedStatement.setDouble(1, musicVideo.getSizeInMegabytes());
    preparedStatement.setDate(2, musicVideo.getReleaseDate());
  }
}
