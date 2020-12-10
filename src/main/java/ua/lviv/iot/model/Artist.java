package ua.lviv.iot.model;

import lombok.*;
import lombok.experimental.Accessors;
import ua.lviv.iot.model.anotation.Column;
import ua.lviv.iot.model.anotation.PrimaryKey;
import ua.lviv.iot.model.anotation.Table;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Accessors(chain = true)
@Table(name = "artist")
public class Artist {

  @PrimaryKey
  @Column(name = "id")
  @EqualsAndHashCode.Include
  private Integer id;

  @Column(name = "title")
  private String title;

  @Column(name = "total_songs")
  private Integer totalSongs;

  @Column(name = "total_albums")
  private Integer totalAlbums;
}
