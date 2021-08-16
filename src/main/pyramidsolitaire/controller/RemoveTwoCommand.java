package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireView;
import java.util.Scanner;

/**
 * Command to remove two cards in the pyramid.
 **/
public class RemoveTwoCommand extends AbstractCommand {

  public RemoveTwoCommand(PyramidSolitaireModel model, PyramidSolitaireView view, Appendable out) {
    super(model, view, out);
  }

  @Override
  public void run(Scanner scanner) throws GameQuitException {
    int row1 = 0;
    int col1 = 0;
    int row2 = 0;
    int col2 = 0;

    for (int countInput = 1; countInput < 5; countInput++) {

      if (scanner.hasNext() && countInput == 1) {
        try {
          String input1 = scanner.next();
          super.checkQuit(input1);
          int checkInput = Integer.parseInt(input1);
          row1 += checkInput;
        } catch (NumberFormatException e) {
          countInput -= 1;
          //System.out.println("\Please re-enter a valid numbers for fist row: ");
          append(scanner, "\nPlease re-enter a valid numbers for fist row: ");
        }
      } else if (scanner.hasNext() && countInput == 2) {
        try {
          String input2 = scanner.next();
          super.checkQuit(input2);
          int checkInput = Integer.parseInt(input2);
          col1 += checkInput;
        } catch (NumberFormatException e) {
          countInput -= 1;
          //System.out.println("\nPlease re-enter a valid numbers for first column: ");
          append(scanner, "\nPlease re-enter a valid numbers for fist column: ");
        }
      } else if (scanner.hasNext() && countInput == 3) {
        try {
          String input3 = scanner.next();
          super.checkQuit(input3);
          int checkInput = Integer.parseInt(input3);
          row2 += checkInput;
        } catch (NumberFormatException e) {
          countInput -= 1;
          //System.out.println("\nPlease re-enter a valid numbers for second row: ");
          append(scanner, "\nPlease re-enter a valid numbers for second row: ");
        }
      } else if (scanner.hasNext() && countInput == 4) {
        try {
          String input4 = scanner.next();
          super.checkQuit(input4);
          int checkInput = Integer.parseInt(input4);
          col2 += checkInput;
        } catch (NumberFormatException e) {
          countInput -= 1;
          //System.out.println("\nPlease re-enter a valid numbers for second column: ");
          append(scanner, "\nPlease re-enter a valid numbers for second column: ");
        }
      }
    }

    try {
      model.remove(row1 - 1, col1 - 1, row2 - 1, col2 - 1);
      append(scanner, "\n");
      this.updateAppendable();
    } catch (IllegalStateException e) {
      //System.out.println("Game is over.");
      append(scanner, "\nGame is over.");
    } catch (IllegalArgumentException e) {
      append(scanner, "\nInvalid move. Play again. *Invalid attempted remove*");
    }
  }
}
