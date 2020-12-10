package ua.lviv.iot.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.implementation.GenreServiceImpl;
import ua.lviv.iot.model.Genre;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreController {
  @Getter
  private static final GenreController INSTANCE = new GenreController();
  private final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private GenreServiceImpl genreService = GenreServiceImpl.getINSTANCE();

  public void createGenre() throws SQLException {
    Genre genre = createGenreInstanceFromUserInputs();
    int count = genreService.create(genre);
    System.out.println("There are " + count + " rows created");
  }

  public void updateGenre() throws SQLException {
    Genre genre = createGenreInstanceFromUserInputs();
    System.out.println("Input id(id) for Genre:  ");
    genre.setId(input.nextInt());
    int count = genreService.update(genre);
    System.out.println("There are " + count + " rows updated");
  }

  public void deleteGenre() throws SQLException {
    System.out.println("Input id(id) for Genre");
    Integer deleteId = input.nextInt();
    int count = genreService.delete(deleteId);
    System.out.println("There are " + count + " rows deleted");
  }

  public void selectAllFromGenre() throws SQLException {
    System.out.println("\nTable: Genre");
    List<Genre> genres = genreService.findAll();
    for (Genre genre : genres) {
      System.out.println(genre);
    }
  }

  public void selectFromGenreById() throws SQLException {
    System.out.println("Input id(id) for Genre");
    Integer selectId = input.nextInt();
    Genre genre = genreService.findById(selectId);
    System.out.println(genre);
  }

  private Genre createGenreInstanceFromUserInputs() {
    System.out.println("Input name(name) for Genre:  ");
    String name = input.nextLine();
    System.out.println("Input description(description) for Genre:  ");
    String description = input.nextLine();
    return new Genre()
        .setName(name)
        .setDescription(description);
  }
}
