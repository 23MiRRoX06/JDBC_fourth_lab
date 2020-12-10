package ua.lviv.iot.view;

import lombok.Getter;
import ua.lviv.iot.controller.*;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleInterface {
  @Getter
  private static final ConsoleInterface INSTANCE = new ConsoleInterface();
  private ArtistController artistController = ArtistController.getINSTANCE();
  private GenreController genreController = GenreController.getINSTANCE();
  private MusicVideoController musicVideoController = MusicVideoController.getINSTANCE();
  private SongController songController = SongController.getINSTANCE();
  private AlbumController albumController = AlbumController.getINSTANCE();
  private Map<String, String> menu;
  private Map<String, Printable> menuMethods;
  private Scanner input = new Scanner(System.in, "UTF-8");

  private ConsoleInterface() {
    menu = new LinkedHashMap<String, String>();
    menuMethods = new LinkedHashMap<String, Printable>();

    menu.put("A", "  A - select all tables");

    menu.put("1", "  1 - Table: Artist");
    menu.put("11", "  11 - Create Artist");
    menu.put("12", "  12 - Update Artist");
    menu.put("13", "  13 - Delete from Artist");
    menu.put("14", "  14 - Select all from Artist");
    menu.put("15", "  15 - Select by ID from Artist");

    menu.put("2", "  2 - Table: Genre");
    menu.put("21", "  21 - Create Genre");
    menu.put("22", "  22 - Update Genre");
    menu.put("23", "  23 - Delete from Genre");
    menu.put("24", "  24 - Select all from Genre");
    menu.put("25", "  25 - Select by ID from Genre");

    menu.put("3", "  3 - Table: MusicVideo");
    menu.put("31", "  31 - Create MusicVideo");
    menu.put("32", "  32 - Update MusicVideo");
    menu.put("33", "  33 - Delete from MusicVideo");
    menu.put("34", "  34 - Select all from MusicVideo");
    menu.put("35", "  35 - Select by ID from MusicVideo");

    menu.put("4", "  4 - Table: Song");
    menu.put("41", "  41 - Create Song");
    menu.put("42", "  42 - Update Song");
    menu.put("43", "  43 - Delete from Song");
    menu.put("44", "  44 - Select all from Song");
    menu.put("45", "  45 - Select by ID from Song");

    menu.put("5", "  5 - Table: Album");
    menu.put("51", "  51 - Create Album");
    menu.put("52", "  52 - Update Album");
    menu.put("53", "  53 - Delete from Album");
    menu.put("54", "  54 - Select all from Album");
    menu.put("55", "  55 - Select by ID from Album");

    menu.put("Q", "  Q - exit");

    menuMethods.put("A", this::selectAllTables);

    menuMethods.put("11", artistController::createArtist);
    menuMethods.put("12", artistController::updateArtist);
    menuMethods.put("13", artistController::deleteArtist);
    menuMethods.put("14", artistController::selectAllFromArtist);
    menuMethods.put("15", artistController::selectFromArtistById);

    menuMethods.put("21", genreController::createGenre);
    menuMethods.put("22", genreController::updateGenre);
    menuMethods.put("23", genreController::deleteGenre);
    menuMethods.put("24", genreController::selectAllFromGenre);
    menuMethods.put("25", genreController::selectFromGenreById);

    menuMethods.put("31", musicVideoController::createMusicVideo);
    menuMethods.put("32", musicVideoController::updateMusicVideo);
    menuMethods.put("33", musicVideoController::deleteMusicVideo);
    menuMethods.put("34", musicVideoController::selectAllFromMusicVideo);
    menuMethods.put("35", musicVideoController::selectFromMusicVideoById);

    menuMethods.put("41", songController::createSong);
    menuMethods.put("42", songController::updateSong);
    menuMethods.put("43", songController::deleteSong);
    menuMethods.put("44", songController::selectAllFromSong);
    menuMethods.put("45", songController::selectFromSongById);

    menuMethods.put("51", albumController::createAlbum);
    menuMethods.put("52", albumController::updateAlbum);
    menuMethods.put("53", albumController::deleteAlbum);
    menuMethods.put("54", albumController::selectAllFromAlbum);
    menuMethods.put("55", albumController::selectFromAlbumById);

    menuMethods.put("Q", this::exitOutput);
  }

  private void selectAllTables() throws SQLException {
    artistController.selectAllFromArtist();
    genreController.selectAllFromGenre();
    musicVideoController.selectAllFromMusicVideo();
    songController.selectAllFromSong();
    albumController.selectAllFromAlbum();
  }

  private void exitOutput() {
    System.out.println("Exiting program....");
  }

  private void outputMenu() {
    System.out.println("\nMENU:");
    for (String key : menu.keySet()) {
      if (key.length() == 1) {
        System.out.println(menu.get(key));
      }
    }
  }

  private void outputSubMenu(String subMenuKey) {
    System.out.println("\nSUB_MENU:");
    for (String key : menu.keySet()) {
      if (key.length() != 1 && key.substring(0, 1).equals(subMenuKey)) {
        System.out.println(menu.get(key));
      }
    }
  }

  public void show() {
    String menuKey;
    do {
      outputMenu();
      System.out.println("Please, select menu point.");
      menuKey = input.nextLine().toUpperCase();

      if (menuKey.matches("^\\d")) {
        outputSubMenu(menuKey);
        System.out.println("Please, select menu point.");
        menuKey = input.nextLine().toUpperCase();

      }

      try {
        menuMethods.get(menuKey).print();
      } catch (SQLException exception) {
        exception.printStackTrace();
      }
    } while (!menuKey.equals("Q"));
  }
}
