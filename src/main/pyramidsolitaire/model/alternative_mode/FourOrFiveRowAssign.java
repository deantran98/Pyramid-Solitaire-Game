package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.Card;
import java.util.List;

/**
 * Assign positions on the 4th and 5th rows command.
 */
public class FourOrFiveRowAssign implements IAssignPosition {

  @Override
  public void assign(List<Card> deck, List<Card> pyramid, int numRows, int index, int cardInRow) {
    for (int i = 0; i < numRows; i += 1) {
      for (int j = 0; j < cardInRow; j += 1) {
        if (i == 0) {
          if (j == cardInRow - 1) {
            deck.get(index).setRowCol(i, j + 2);
            Card cardToAdd1 = deck.get(index);
            pyramid.add(cardToAdd1);
          } else {
            if (j == 0) {
              deck.get(index).setRowCol(i, j);
            } else {
              deck.get(index).setRowCol(i, j + 1);
            }
            Card cardToAdd1 = deck.get(index);
            pyramid.add(cardToAdd1);
          }
        } else {
          deck.get(index).setRowCol(i, j);
          Card cardToAdd1 = deck.get(index);
          pyramid.add(cardToAdd1);
          //pyramid.add(null);
        }
        index += 1;

      }

      if (i == 0) {
        cardInRow += 3;
      } else {
        cardInRow += 1;
      }

    }

  }

}
