package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.Card;
import java.util.List;

/**
 * Assign positions on the 2nd and 3rd rows command.
 */
public class TwoOrThreeRowAssign implements IAssignPosition {

  @Override
  public void assign(List<Card> deck, List<Card> pyramid, int numRows, int index, int cardInRow) {
    for (int i = 0; i < numRows; i += 1) {
      for (int j = 0; j < cardInRow; j += 1) {
        deck.get(index).setRowCol(i, j);
        Card cardToAdd0 = deck.get(index);
        pyramid.add(cardToAdd0);
        index += 1;
      }

      cardInRow += 1;
    }
  }
}
