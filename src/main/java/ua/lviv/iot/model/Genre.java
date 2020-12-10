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
public class Genre {

  @PrimaryKey
  @Column(name = "id")
  @EqualsAndHashCode.Include
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;
}
