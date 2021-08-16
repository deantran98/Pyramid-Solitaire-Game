package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.Card;
import java.util.List;

/**
 * Assign positions on the 8th row command.
 */
public class EightRowAssign implements IAssignPosition {

  @Override
  public void assign(List<Card> deck, List<Card> pyramid, int numRows, int index, int cardInRow) {
    for (int i = 0; i < numRows; i += 1) {
      for (int j = 0; j < cardInRow; j += 1) {
        if (i == 0) {
          if (j == cardInRow - 1) {
            deck.get(index).setRowCol(i, j + 6);
            Card cardToAdd3 = deck.get(index);
            pyramid.add(cardToAdd3);
          } else {
            if (j == 0) {
              deck.get(index).setRowCol(i, j);
            } else {
              deck.get(index).setRowCol(i, j + 3);
            }
            Card cardToAdd3 = deck.get(index);
            pyramid.add(cardToAdd3);
            //pyramid.add(null);
            //pyramid.add(null);
            //pyramid.add(null);
          }
        } else if (i == 1) {
          if (j == cardInRow - 1 || j == 4) {
            deck.get(index).setRowCol(i, j + 4);
            Card cardToAdd3 = deck.get(index);
            pyramid.add(cardToAdd3);
          } else if (j == 2 || j == 3) {
            deck.get(index).setRowCol(i, j + 2);
            Card cardToAdd3 = deck.get(index);
            pyramid.add(cardToAdd3);
          } else {
            deck.get(index).setRowCol(i, j);
            Card cardToAdd3 = deck.get(index);
            pyramid.add(cardToAdd3);
            //pyramid.add(null);
            //pyramid.add(null);
          }
        } else if (i == 2) {
          if (j == cardInRow - 1 || j == 6 || j == 7) {
            deck.get(index).setRowCol(i, j + 2);
            Card cardToAdd3 = deck.get(index);
            pyramid.add(cardToAdd3);
          } else if (j == 3 || j == 4 || j == 5) {
            deck.get(index).setRowCol(i, j + 1);
            Card cardToAdd3 = deck.get(index);
            pyramid.add(cardToAdd3);
          } else {
            deck.get(index).setRowCol(i, j);
            Card cardToAdd3 = deck.get(index);
            pyramid.add(cardToAdd3);
            //pyramid.add(null);
          }
        } else {
          deck.get(index).setRowCol(i, j);
          Card cardToAdd3 = deck.get(index);
          pyramid.add(cardToAdd3);
        }
        index += 1;

      }

      if (i == 0 || i == 1 || i == 2) {
        cardInRow += 3;
      } else {
        cardInRow += 1;
      }

    }

  }

}
