package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.Card;
import java.util.List;

/**
 * The interface represents different command of assign position for multi pyramid.
 */
public interface IAssignPosition {

  /**
   * Assign the position for cards in the pyramid for multi pyramid model.
   * @param deck the given deck for the game
   * @param pyramid the current pyramid
   * @param numRows number of rows in pyramid
   * @param index the current index to keep track on cards in given deck
   * @param cardInRow the number of cards in each row
   */
  void assign(List<Card> deck, List<Card> pyramid, int numRows, int index, int cardInRow);

}
