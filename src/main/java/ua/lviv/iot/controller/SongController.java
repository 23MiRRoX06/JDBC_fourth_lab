package ua.lviv.iot.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.implementation.SongServiceImpl;
import ua.lviv.iot.model.Song;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SongController {
  @Getter
  private static final SongController INSTANCE = new SongController();
  private final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private SongServiceImpl songService = SongServiceImpl.getINSTANCE();

  public void createSong() throws SQLException {
    System.out.println("You should have already created artist, album, genre and music video");
    Song song = createSongInstanceFromUserInputs();
    System.out.println("Please enter artistId");
    Integer artistId = input.nextInt();
    input.nextLine();
    song.setArtistId(artistId);
    System.out.println("Please enter albumId");
    Integer albumId = input.nextInt();
    input.nextLine();
    song.setAlbumId(albumId);
    System.out.println("Please enter genreId");
    Integer genreId = input.nextInt();
    input.nextLine();
    song.setGenreId(genreId);
    System.out.println("Please enter musicVideoId");
    Integer musicVideoId = input.nextInt();
    input.nextLine();
    song.setMusicVideoId(musicVideoId);
    songService.create(song);
    System.out.println("There are 1 row created");
  }

  public void updateSong() throws SQLException {
    System.out.println("You should have already created artist, album, genre and music video");
    Song song = createSongInstanceFromUserInputs();
    System.out.println("Please enter artistId");
    Integer artistId = input.nextInt();
    input.nextLine();
    song.setArtistId(artistId);
    System.out.println("Please enter albumId");
    Integer albumId = input.nextInt();
    input.nextLine();
    song.setAlbumId(albumId);
    System.out.println("Please enter genreId");
    Integer genreId = input.nextInt();
    input.nextLine();
    song.setGenreId(genreId);
    System.out.println("Please enter musicVideoId");
    Integer musicVideoId = input.nextInt();
    input.nextLine();
    song.setMusicVideoId(musicVideoId);
    System.out.println("Input id(id) for Song:  ");
    song.setId(input.nextInt());
    songService.update(song);
    System.out.println("There are 1 row updated");
  }

  public void deleteSong() throws SQLException {
    System.out.println("Input id(id) for Song");
    Integer deleteId = input.nextInt();
    int count = songService.delete(deleteId);
    System.out.println("There are " + count + " rows deleted");
  }

  public void selectAllFromSong() throws SQLException {
    System.out.println("\nTable: Song");
    List<Song> songs = songService.findAll();
    for (Song song : songs) {
      System.out.println(song);
    }
  }

  public void selectFromSongById() throws SQLException {
    System.out.println("Input id(id) for Song");
    Integer selectId = input.nextInt();
    Song song = songService.findById(selectId);
    System.out.println(song);
  }

  private Song createSongInstanceFromUserInputs() {
    System.out.println("Input title(title) for Song:  ");
    String title = input.nextLine();
    System.out.println("Input price(price) for Song:  ");
    BigDecimal price = input.nextBigDecimal();
    System.out.println("Input durationInMinutes(duration_in_minutes) for Song:  ");
    Double durationInMinutes = input.nextDouble();
    input.nextLine();
    System.out.println("Input releaseDate(release_date) for Song:  ");
    Date releaseDate = Date.valueOf(input.nextLine());
    System.out.println("Input popularity(popularity) for Song(1-100):  ");
    Integer popularity = input.nextInt();
    System.out.println("Input withParentalAdvisoryLogo(with_parental_advisory_logo) for Song:  ");
    Boolean withParentalAdvisoryLogo = input.nextBoolean();
    return new Song()
        .setTitle(title)
        .setPrice(price)
        .setDurationInMinutes(durationInMinutes)
        .setReleaseDate(releaseDate)
        .setPopularity(popularity)
        .setWithParentalAdvisoryLogo(withParentalAdvisoryLogo);
  }
}
