package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireView;
import java.util.Scanner;

/**
 * Command to discard a draw card.
 **/
public class DiscardDrawCommand extends AbstractCommand {

  public DiscardDrawCommand(PyramidSolitaireModel model, PyramidSolitaireView view,
      Appendable out) {
    super(model, view, out);
  }

  @Override
  public void run(Scanner scanner) throws GameQuitException {
    int inputValue = 0;
    while (scanner.hasNext()) {
      try {
        String input = scanner.next();
        super.checkQuit(input);
        int checkInput = Integer.parseInt(input);
        inputValue += checkInput;
        break;
      } catch (NumberFormatException e) {
        append(scanner, "\nPlease re-enter a valid number.");
        //System.out.println("Please re-enter a valid number.");
      }
    }

    try {
      model.discardDraw(inputValue - 1);
      append(scanner, "\n");
      this.updateAppendable();
    } catch (IllegalStateException e) {
      //System.out.println("Game is over.");
      append(scanner, "\nGame is over.");
    } catch (IllegalArgumentException e) {
      append(scanner, "\nInvalid move. Play again. *No card at a position to discard.*");
    }

  }
}
