package ua.lviv.iot.business.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.ArtistService;
import ua.lviv.iot.data_access.implementation.ArtistDaoImpl;
import java.sql.SQLException;
import java.util.List;
import ua.lviv.iot.model.Artist;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArtistServiceImpl implements ArtistService {
  @Getter
  private static final ArtistServiceImpl INSTANCE = new ArtistServiceImpl();
  private ArtistDaoImpl artistDao = ArtistDaoImpl.getINSTANCE();

  @Override
  public List<Artist> findAll() throws SQLException {
    return artistDao.findAll();
  }

  @Override
  public Artist findById(Integer id) throws SQLException {
    return artistDao.findById(id);
  }

  @Override
  public int create(Artist artist) throws SQLException {
    return artistDao.create(artist);
  }

  @Override
  public int update(Artist artist) throws SQLException {
    return artistDao.update(artist);
  }

  @Override
  public int delete(Integer id) throws SQLException {
    return artistDao.delete(id);
  }
}
