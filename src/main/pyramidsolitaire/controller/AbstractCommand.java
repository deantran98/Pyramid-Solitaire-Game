package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireView;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Provide all the commands for the game.
 **/
public class AbstractCommand implements ICommand {

  protected final PyramidSolitaireModel model;
  protected final PyramidSolitaireView view;
  protected final Appendable out;

  /**
   * The constructor of command.
   **/
  public AbstractCommand(PyramidSolitaireModel model, PyramidSolitaireView view, Appendable out) {
    this.model = model;
    this.view = view;
    this.out = out;
  }

  /**
   * Append a given string to the stream.
   **/
  protected void append(Scanner scanner, String value) {
    Objects.requireNonNull(scanner);
    Objects.requireNonNull(value);
    try {
      out.append(value);
    } catch (IOException e) {
      throw new IllegalMonitorStateException("Couldn't write to appendable.");
    }
  }

  /**
   * Update the current appendable output.
   **/
  protected void updateAppendable() {
    try {
      this.view.render();
    } catch (IOException e) {
      System.err.println("Couldn't append to the stream.");
    }
  }

  @Override
  public void run(Scanner scanner) throws GameQuitException {
    return;
  }

  protected void checkQuit(String input) throws GameQuitException {
    if (input.equalsIgnoreCase("Q")) {
      throw new GameQuitException();
    }
  }
}
