package ua.lviv.iot.business.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.MusicVideoService;
import ua.lviv.iot.data_access.implementation.MusicVideoDaoImpl;
import java.sql.SQLException;
import java.util.List;
import ua.lviv.iot.model.MusicVideo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MusicVideoServiceImpl implements MusicVideoService {
  @Getter
  private static final MusicVideoServiceImpl INSTANCE = new MusicVideoServiceImpl();
  private MusicVideoDaoImpl musicVideoDao = MusicVideoDaoImpl.getINSTANCE();

  @Override
  public List<MusicVideo> findAll() throws SQLException {
    return musicVideoDao.findAll();
  }

  @Override
  public MusicVideo findById(Integer id) throws SQLException {
    return musicVideoDao.findById(id);
  }

  @Override
  public int create(MusicVideo musicVideo) throws SQLException {
    return musicVideoDao.create(musicVideo);
  }

  @Override
  public int update(MusicVideo musicVideo) throws SQLException {
    return musicVideoDao.update(musicVideo);
  }

  @Override
  public int delete(Integer id) throws SQLException {
    return musicVideoDao.delete(id);
  }
}
