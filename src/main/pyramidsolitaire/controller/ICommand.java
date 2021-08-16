package cs3500.pyramidsolitaire.controller;

import java.util.Scanner;

/**
 * Provide a method to run all commands.
 **/
public interface ICommand {

  /**
   * Primary method that takes in a scanner and run a command.
   * @param scanner the scanner that reads in an input command from users.
   **/
  void run(Scanner scanner) throws GameQuitException;

}
