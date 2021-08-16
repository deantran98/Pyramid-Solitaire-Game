package cs3500.pyramidsolitaire;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireController;
import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator.GameType;
import java.io.InputStreamReader;

/**
 * The class represents the main entry point of the program. Take in a command to define the type of
 * pyramid solitaire model.
 */
public final class PyramidSolitaire {

  /**
   * The main class to run the program.
   */

  public static void main(String[] args) {
    // FILL IN HERE
    PyramidSolitaireModel model = null;
    PyramidSolitaireCreator creator = new PyramidSolitaireCreator();
    int rowInput = 7;
    int drawInput = 3;

    if (args.length < 1) {
      System.out.println("No command entered.");
    }

    switch (args[0]) {
      case "basic":
        if (args.length > 1) {
          try {
            rowInput = Integer.parseInt(args[1]);
            drawInput = Integer.parseInt(args[2]);
            model = creator.create(GameType.BASIC);
          } catch (NumberFormatException e) {
            System.err.println("Row and draw cards must be valid numbers.");
          }
        } else {
          model = creator.create(GameType.BASIC);
        }

        break;
      case "relaxed":
        if (args.length > 1) {
          try {
            rowInput = Integer.parseInt(args[1]);
            drawInput = Integer.parseInt(args[2]);
            model = creator.create(GameType.RELAXED);
          } catch (NumberFormatException e) {
            System.err.println("Row and draw cards must be valid numbers.");
          }
        } else {
          model = creator.create(GameType.RELAXED);
        }

        break;
      case "multipyramid":
        if (args.length > 1) {
          try {
            rowInput = Integer.parseInt(args[1]);
            drawInput = Integer.parseInt(args[2]);
            model = creator.create(GameType.MULTIPYRAMID);
          } catch (NumberFormatException e) {
            System.err.println("Row and draw cards must be valid numbers.");
          }
        } else {
          model = creator.create(GameType.MULTIPYRAMID);
        }

        break;
      default:
        model = creator.create(GameType.BASIC);
        break;
    }

    PyramidSolitaireController controller = new PyramidSolitaireTextualController(
        new InputStreamReader(System.in)
        , System.out);
    controller.playGame(model, model.getDeck(), true, rowInput, drawInput);
  }

}
