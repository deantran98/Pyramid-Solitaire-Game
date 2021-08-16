package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.Card;
import java.util.List;

/**
 * Assign positions on the 1st row command.
 */
public class OneRowAssign implements IAssignPosition {

  @Override
  public void assign(List<Card> deck, List<Card> pyramid, int numRows, int index, int cardInRow) {
    deck.get(index).setRowCol(0, 0);
    Card cardToAdd = deck.get(index);
    pyramid.add(cardToAdd);
  }
}
