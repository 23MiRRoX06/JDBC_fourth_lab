package ua.lviv.iot.model;

import lombok.*;
import lombok.experimental.Accessors;
import ua.lviv.iot.model.anotation.Column;
import ua.lviv.iot.model.anotation.PrimaryKey;
import ua.lviv.iot.model.anotation.Table;

import java.math.BigDecimal;
import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Accessors(chain = true)
@Table(name = "album")
public class Album {

  @PrimaryKey
  @Column(name = "id")
  @EqualsAndHashCode.Include
  private Integer id;

  @Column(name = "title")
  private String title;

  @Column(name = "release_date")
  private Date releaseDate;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "total_items")
  private Integer totalItems;

  @Column(name = "artist_id")
  private Integer artistId;

  @Column(name = "genre_id")
  private Integer genreId;
}
