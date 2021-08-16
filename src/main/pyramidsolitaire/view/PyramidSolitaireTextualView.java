package cs3500.pyramidsolitaire.view;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class render the game based on given model.
 **/
public class PyramidSolitaireTextualView implements PyramidSolitaireView {

  private final PyramidSolitaireModel<?> model;
  private Appendable out;
  // ... any other fields you need

  /**
   * The constructor for PyramidSolitaireTextualView.
   **/
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model) {
    this.model = model;
  }

  /**
   * The constructor for PyramidSolitaireTextualView that has both the model and appendable.
   **/
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model, Appendable out) {
    this.model = model;
    this.out = Objects.requireNonNull(out);
  }

  @Override
  public String toString() {
    boolean gameOverWin = true;
    for (int row = 0; row < model.getNumRows(); row++) {
      for (int col = 0; col < model.getRowWidth(row); col++) {
        if (model.getCardAt(row, col) != null) {
          gameOverWin = false;
        }
        break;
      }

      if (!gameOverWin) {
        break;
      }
    }

    if (model.getNumRows() <= 0) {
      return "";
    } else if (gameOverWin) {
      return "You win!";
    } else if (model.isGameOver()) {
      return "Game over. Score: " + model.getScore();
    } else {
      int numDraws = model.getDrawCards().size();
      String rowsRender = "";
      String space = "";
      String drawRender = "Draw: ";
      List<String> rows = new ArrayList<>();
      List<?> drawCards = model.getDrawCards();

      if (numDraws == 0) {
        drawRender = "Draw:";
      } else {
        for (int i = 0; i < numDraws; i++) {
          if (i == numDraws - 1) {
            drawRender += drawCards.get(i).toString();
            break;
          }
          drawRender += drawCards.get(i).toString() + ", ";
        }
      }

      for (int r = model.getNumRows() - 1; r >= 0; r--) {
        String lineByRow = "";
        int rowWidth = model.getRowWidth(r);
        int checkLength3 = 0;

        for (int c = 0; c < rowWidth; c++) {
          if (model.getCardAt(r, c) == null) {
            if (c == rowWidth - 1) {
              lineByRow += ".";
            } else {
              //lineByRow += " .  ";
              lineByRow += ".   ";
            }
          } else if (c == rowWidth - 1) {
            lineByRow += model.getCardAt(r, c).toString();
            break;
          } else {
            checkLength3 = model.getCardAt(r, c).toString().length();
            if (checkLength3 == 3) {
              lineByRow += model.getCardAt(r, c).toString() + " ";
            } else {
              lineByRow += model.getCardAt(r, c).toString() + "  ";
            }
          }

        }

        rows.add(lineByRow);
      }

      for (int ii = 1; ii < model.getNumRows(); ii++) {
        space += "  ";
      }
      for (int j = rows.size() - 1; j >= 0; j--) {
        rowsRender = rowsRender + space + rows.get(j) + "\n";
        if (space.length() >= 2) {
          space = space.substring(0, space.length() - 2);
        }
      }

      return rowsRender + drawRender;
    }

  }

  @Override
  public void render() throws IOException {
    try {
      this.out.append(this.toString());
    } catch (IOException e) {
      System.err.println("Couldn't append to the stream.");
    }
  }
}
