package ua.lviv.iot.business.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import ua.lviv.iot.business.AlbumService;
import ua.lviv.iot.data_access.implementation.AlbumDaoImpl;
import ua.lviv.iot.model.Album;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AlbumServiceImpl implements AlbumService {
  @Getter
  private static final AlbumServiceImpl INSTANCE = new AlbumServiceImpl();
  private AlbumDaoImpl albumDao = AlbumDaoImpl.getINSTANCE();

  @Override
  public List<Album> findAll() throws SQLException {
    return albumDao.findAll();
  }

  @Override
  public Album findById(Integer id) throws SQLException {
    return albumDao.findById(id);
  }

  @Override
  public int create(Album album) throws SQLException {
    return albumDao.create(album);
  }

  @Override
  public int update(Album album) throws SQLException {
    return albumDao.update(album);
  }

  @Override
  public int delete(Integer id) throws SQLException {
    return albumDao.delete(id);
  }
}
