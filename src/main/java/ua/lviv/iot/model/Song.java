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
@Table(name = "song")
public class Song {
  @PrimaryKey
  @Column(name = "id")
  @EqualsAndHashCode.Include
  private Integer id;

  @Column(name = "title")
  private String title;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "duration_in_minutes")
  private Double durationInMinutes;

  @Column(name = "release_date")
  private Date releaseDate;

  @Column(name = "popularity")
  private Integer popularity;

  @Column(name = "with_parental_advisory_logo")
  private Boolean withParentalAdvisoryLogo;

  @Column(name = "artist_id")
  private Integer artistId;

  @Column(name = "album_id")
  private Integer albumId;

  @Column(name = "genre_id")
  private Integer genreId;

  @Column(name = "music_video_id")
  private Integer musicVideoId;
}
