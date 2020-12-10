package ua.lviv.iot.business.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.SongService;
import ua.lviv.iot.data_access.implementation.SongDaoImpl;
import java.sql.SQLException;
import java.util.List;
import ua.lviv.iot.model.Song;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SongServiceImpl implements SongService {
  @Getter
  private static final SongServiceImpl INSTANCE = new SongServiceImpl();
  private SongDaoImpl songDao = SongDaoImpl.getINSTANCE();

  @Override
  public List<Song> findAll() throws SQLException {
    return songDao.findAll();
  }

  @Override
  public Song findById(Integer id) throws SQLException {
    return songDao.findById(id);
  }

  @Override
  public int create(Song song) throws SQLException {
    return songDao.create(song);
  }

  @Override
  public int update(Song song) throws SQLException {
    return songDao.update(song);
  }

  @Override
  public int delete(Integer id) throws SQLException {
    return songDao.delete(id);
  }
}
