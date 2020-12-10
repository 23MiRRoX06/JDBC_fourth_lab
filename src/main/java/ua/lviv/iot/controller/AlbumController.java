package ua.lviv.iot.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.implementation.AlbumServiceImpl;
import ua.lviv.iot.model.Album;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AlbumController {
  @Getter
  private static final AlbumController INSTANCE = new AlbumController();
  private final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private AlbumServiceImpl albumService = AlbumServiceImpl.getINSTANCE();

  public void createAlbum() throws SQLException {
    System.out.println("You should have already created artist, genre");
    Album album = createAlbumInstanceFromUserInputs();
    System.out.println("Please enter artistId");
    Integer artistId = input.nextInt();
    input.nextLine();
    album.setArtistId(artistId);
    System.out.println("Please enter genreId");
    Integer genreId = input.nextInt();
    input.nextLine();
    album.setGenreId(genreId);
    albumService.create(album);
    System.out.println("There are 1 row created");
  }

  public void updateAlbum() throws SQLException {
    System.out.println("You should have already created artist, genre");
    Album album = createAlbumInstanceFromUserInputs();
    System.out.println("Please enter artistId");
    Integer artistId = input.nextInt();
    input.nextLine();
    album.setArtistId(artistId);
    System.out.println("Please enter genreId");
    Integer genreId = input.nextInt();
    input.nextLine();
    album.setGenreId(genreId);
    System.out.println("Input id(id) for Album:  ");
    album.setId(input.nextInt());
    albumService.update(album);
    System.out.println("There are 1 row updated");
  }

  public void deleteAlbum() throws SQLException {
    System.out.println("Input id(id) for Album");
    Integer deleteId = input.nextInt();
    int count = albumService.delete(deleteId);
    System.out.println("There are " + count + " rows deleted");
  }

  public void selectAllFromAlbum() throws SQLException {
    System.out.println("\nTable: Album");
    List<Album> albums = albumService.findAll();
    for (Album album : albums) {
      System.out.println(album);
    }
  }

  public void selectFromAlbumById() throws SQLException {
    System.out.println("Input id(id) for Album");
    Integer selectId = input.nextInt();
    Album album = albumService.findById(selectId);
    System.out.println(album);
  }

  private Album createAlbumInstanceFromUserInputs() {
    input.nextLine();
    System.out.println("Input title(title) for Album:  ");
    String title = input.nextLine();
    System.out.println("Input price(price) for Album:  ");
    BigDecimal price = input.nextBigDecimal();
    input.nextLine();
    System.out.println("Input releaseDate(release_date) for Album:  ");
    Date releaseDate = Date.valueOf(input.nextLine());
    System.out.println("Input totalItems(total_items) for Album:  ");
    Integer totalItems = input.nextInt();
    return new Album()
        .setTitle(title)
        .setReleaseDate(releaseDate)
        .setPrice(price)
        .setTotalItems(totalItems);
  }
}
