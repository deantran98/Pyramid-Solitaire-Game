package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireView;
import java.util.Scanner;

/**
 * Command to remove a card in the pyramid.
 **/
public class RemoveOneCommand extends AbstractCommand {

  public RemoveOneCommand(PyramidSolitaireModel model, PyramidSolitaireView view, Appendable out) {
    super(model, view, out);
  }

  @Override
  public void run(Scanner scanner) throws GameQuitException {
    int rowCard = 0;
    int colCard = 0;

    for (int countInput = 1; countInput < 3; countInput++) {

      if (scanner.hasNext() && countInput == 1) {
        try {
          String input1 = scanner.next();
          super.checkQuit(input1);
          int checkInput = Integer.parseInt(input1);
          rowCard += checkInput;
        } catch (NumberFormatException e) {
          countInput -= 1;
          //System.out.println("Please re-enter a valid number for row: ");
          append(scanner, "\nPlease re-enter a valid number for row: ");
        }
      } else if (scanner.hasNext() && countInput == 2) {
        try {
          String input2 = scanner.next();
          super.checkQuit(input2);
          int checkInput = Integer.parseInt(input2);
          colCard += checkInput;
        } catch (NumberFormatException e) {
          countInput -= 1;
          //System.out.println("Please re-enter a valid number for column: ");
          append(scanner, "\nPlease re-enter a valid number for column: ");
        }
      }
    }

    try {
      model.remove(rowCard - 1, colCard - 1);
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
