package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * The class provides a textual controller for the game Pyramid Solitaire.
 **/
public class PyramidSolitaireTextualController implements PyramidSolitaireController {

  private final Readable in;
  private final Appendable out;

  /**
   * The constructor for PyramidSolitaireTextualController.
   **/
  public PyramidSolitaireTextualController(Readable in, Appendable out) {
    if (in == null) {
      throw new IllegalArgumentException("Input can't be null.");
    }
    if (out == null) {
      throw new IllegalArgumentException("Output can't be null.");
    }
    this.in = in;
    this.out = out;
  }

  /**
   * Append a given string to the current stream.
   **/
  private void append(Scanner scanner, String value) {
    Objects.requireNonNull(scanner);
    Objects.requireNonNull(value);
    try {
      out.append(value);
    } catch (IOException e) {
      throw new IllegalMonitorStateException("Couldn't write to appendable.");
    }
  }

  @Override
  public <K> void playGame(PyramidSolitaireModel<K> model, List<K> deck, boolean shuffle,
      int numRows, int numDraw) {
    if (model == null) {
      throw new IllegalArgumentException("Game model can't be null.");
    }

    if (deck == null) {
      throw new IllegalArgumentException("Deck of cards can't be null.");
    }

    /*if (numRows <= 0 || numRows > 52) {
      throw new IllegalStateException("Number of rows can't be less than 1 or greater than 52.");
    }

    if (numDraw < 0) {
      throw new IllegalStateException("Number of draw cards can't be less than 0.");
    }*/

    try {
      model.startGame(deck, shuffle, numRows, numDraw);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException("The game can't be started.");
    }

    PyramidSolitaireTextualView gameView = new PyramidSolitaireTextualView(model, out);
    try {
      gameView.render();
    } catch (IOException e) {
      System.err.println("Couldn't append to the stream.");
    }

    Scanner scanner = new Scanner(this.in);
    ICommand command = null;

    try {
      while (!model.isGameOver() && scanner.hasNext()) {

        String input = scanner.next();

        switch (input) {
          case "q":
          case "Q":
            throw new GameQuitException();
          case "rm1":
            command = new RemoveOneCommand(model, gameView, out);
            command.run(scanner);
            break;
          case "rm2":
            command = new RemoveTwoCommand(model, gameView, out);
            command.run(scanner);
            break;
          case "rmwd":
            command = new RemoveUsingDrawCommand(model, gameView, out);
            command.run(scanner);
            break;
          case "dd":
            command = new DiscardDrawCommand(model, gameView, out);
            command.run(scanner);
            break;
          default:
            append(scanner, "\nInvalid move. Play again.");
        }

      }
    } catch (GameQuitException e) {
      append(scanner, "\nGame quit!\n" + "State of game when quit:\n");
      try {
        gameView.render();
      } catch (IOException ge) {
        System.err.println("Couldn't append to the stream.");
      }
      append(scanner, "\nScore: " + model.getScore());
    }

  }
}
