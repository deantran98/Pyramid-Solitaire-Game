package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.Card;
import java.util.List;

/**
 * Assign positions on the 6th and 7th rows command.
 */
public class SixOrSevenRowAssign implements IAssignPosition {

  @Override
  public void assign(List<Card> deck, List<Card> pyramid, int numRows, int index, int cardInRow) {
    for (int i = 0; i < numRows; i += 1) {
      for (int j = 0; j < cardInRow; j += 1) {
        if (i == 0) {
          if (j == cardInRow - 1) {
            deck.get(index).setRowCol(i, j + 4);
            Card cardToAdd2 = deck.get(index);
            pyramid.add(cardToAdd2);
          } else {
            if (j == 0) {
              deck.get(index).setRowCol(i, j);
            } else {
              deck.get(index).setRowCol(i, j + 2);
            }
            Card cardToAdd2 = deck.get(index);
            pyramid.add(cardToAdd2);
            //pyramid.add(null);
            //pyramid.add(null);
          }
        } else if (i == 1) {
          if (j == cardInRow - 1 || j == 4) {
            deck.get(index).setRowCol(i, j + 2);
            Card cardToAdd2 = deck.get(index);
            pyramid.add(cardToAdd2);
          } else if (j == 2 || j == 3) {
            deck.get(index).setRowCol(i, j + 1);
            Card cardToAdd2 = deck.get(index);
            pyramid.add(cardToAdd2);
          } else {
            deck.get(index).setRowCol(i, j);
            Card cardToAdd2 = deck.get(index);
            pyramid.add(cardToAdd2);
            //pyramid.add(null);
          }
        } else {
          deck.get(index).setRowCol(i, j);
          Card cardToAdd2 = deck.get(index);
          pyramid.add(cardToAdd2);
        }
        index += 1;

      }

      if (i == 0 || i == 1) {
        cardInRow += 3;
      } else {
        cardInRow += 1;
      }

    }

  }

}
