package ua.lviv.iot.business.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.GenreService;
import ua.lviv.iot.data_access.implementation.GenreDaoImpl;
import java.sql.SQLException;
import java.util.List;
import ua.lviv.iot.model.Genre;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreServiceImpl implements GenreService {
  @Getter
  private static final GenreServiceImpl INSTANCE = new GenreServiceImpl();
  private GenreDaoImpl genreDao = GenreDaoImpl.getINSTANCE();

  @Override
  public List<Genre> findAll() throws SQLException {
    return genreDao.findAll();
  }

  @Override
  public Genre findById(Integer id) throws SQLException {
    return genreDao.findById(id);
  }

  @Override
  public int create(Genre genre) throws SQLException {
    return genreDao.create(genre);
  }

  @Override
  public int update(Genre genre) throws SQLException {
    return genreDao.update(genre);
  }

  @Override
  public int delete(Integer id) throws SQLException {
    return genreDao.delete(id);
  }
}
