package ua.lviv.iot.model;

import lombok.*;
import lombok.experimental.Accessors;
import ua.lviv.iot.model.anotation.Column;
import ua.lviv.iot.model.anotation.PrimaryKey;
import ua.lviv.iot.model.anotation.Table;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Accessors(chain = true)
@Table(name = "artist")
public class MusicVideo {

  @PrimaryKey
  @Column(name = "id")
  @EqualsAndHashCode.Include
  private Integer id;

  @Column(name = "size_in_megabytes")
  private Double sizeInMegabytes;

  @Column(name = "release_date")
  private Date releaseDate;
}
