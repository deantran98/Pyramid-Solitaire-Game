package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireView;
import java.util.Scanner;

/**
 * Command to remove one card in the pyramid and one draw card.
 **/
public class RemoveUsingDrawCommand extends AbstractCommand {

  public RemoveUsingDrawCommand(PyramidSolitaireModel model, PyramidSolitaireView view,
      Appendable out) {
    super(model, view, out);
  }

  @Override
  public void run(Scanner scanner) throws GameQuitException {
    int draw = 0;
    int row = 0;
    int col = 0;

    for (int countInput = 1; countInput < 4; countInput++) {

      if (scanner.hasNext() && countInput == 1) {
        try {
          String input1 = scanner.next();
          super.checkQuit(input1);
          int checkInput = Integer.parseInt(input1);
          draw += checkInput;
        } catch (NumberFormatException e) {
          countInput -= 1;
          //System.out.println("Please re-enter a valid number for draw: ");
          append(scanner, "\nPlease re-enter a valid numbers for draw card: ");
        }
      } else if (scanner.hasNext() && countInput == 2) {
        try {
          String input2 = scanner.next();
          super.checkQuit(input2);
          int checkInput = Integer.parseInt(input2);
          row += checkInput;
        } catch (NumberFormatException e) {
          countInput -= 1;
          //System.out.println("Please re-enter a valid number for row: ");
          append(scanner, "\nPlease re-enter a valid numbers for row: ");
        }
      } else if (scanner.hasNext() && countInput == 3) {
        try {
          String input3 = scanner.next();
          super.checkQuit(input3);
          int checkInput = Integer.parseInt(input3);
          col += checkInput;
        } catch (NumberFormatException e) {
          countInput -= 1;
          //System.out.println("Please re-enter a valid number for column");
          append(scanner, "\nPlease re-enter a valid numbers for column: ");
        }
      }
    }

    try {
      model.removeUsingDraw(draw - 1, row - 1, col - 1);
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
