package ua.lviv.iot.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.implementation.ArtistServiceImpl;
import ua.lviv.iot.model.Artist;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArtistController {
  @Getter
  private static final ArtistController INSTANCE = new ArtistController();
  private final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private ArtistServiceImpl artistService = ArtistServiceImpl.getINSTANCE();

  public void createArtist() throws SQLException {
    Artist artist = createArtistInstanceFromUserInputs();
    int count = artistService.create(artist);
    System.out.println("There are " + count + " rows created");
  }

  public void updateArtist() throws SQLException {
    Artist artist = createArtistInstanceFromUserInputs();
    System.out.println("Input id(id) for Artist:  ");
    artist.setId(input.nextInt());
    int count = artistService.update(artist);
    System.out.println("There are " + count + " rows updated");
  }

  public void deleteArtist() throws SQLException {
    System.out.println("Input id(id) for Artist");
    Integer deleteId = input.nextInt();
    int count = artistService.delete(deleteId);
    System.out.println("There are " + count + " rows deleted");
  }

  public void selectAllFromArtist() throws SQLException {
    System.out.println("\nTable: Artist");
    List<Artist> artists = artistService.findAll();
    for (Artist artist : artists) {
      System.out.println(artist);
    }
  }

  public void selectFromArtistById() throws SQLException {
    System.out.println("Input id(id) for Artist");
    Integer selectId = input.nextInt();
    Artist artist = artistService.findById(selectId);
    System.out.println(artist);
  }

  private Artist createArtistInstanceFromUserInputs() {
    System.out.println("Input title(title) for Artist:  ");
    String title = input.nextLine();
    System.out.println("Input totalSongs(total_songs) for Artist:  ");
    Integer totalSongs = input.nextInt();
    input.nextLine();
    System.out.println("Input totalAlbums(total_albums) for Artist:  ");
    Integer totalAlbums = input.nextInt();
    return new Artist()
        .setTitle(title)
        .setTotalSongs(totalSongs)
        .setTotalAlbums(totalAlbums);
  }
}
