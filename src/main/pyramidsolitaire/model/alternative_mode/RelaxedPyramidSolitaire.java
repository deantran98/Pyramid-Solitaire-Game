package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import java.util.List;
import java.util.Random;

/**
 * The class represents the relaxed pyramid version of the game.
 */
public class RelaxedPyramidSolitaire extends BasicPyramidSolitaire {

  /**
   * The constructor for the multi pyramid version of the game.
   */
  public RelaxedPyramidSolitaire() {
    super();
  }

  /**
   * The constructor for the multi pyramid version of the game.
   */
  public RelaxedPyramidSolitaire(Random randomSeed) {
    super(randomSeed);
  }

  /**
   * The new version to find all exposed cards (cover by at least 1 card) in the current pyramid.
   */
  @Override
  public void exposedCards(List<Card> exposedCards) {

    for (Card card : currentPyramid) {
      if (card == null) {
        continue;
      }
      int rowBeneathLeft = card.getRow() + 1;
      int colBeneathLeft = card.getCol();
      int rowBeneathRight = card.getRow() + 1;
      int colBeneathRight = card.getCol() + 1;

      if ((this.getCardAt(rowBeneathLeft, colBeneathLeft) == null
          || this.getCardAt(rowBeneathRight, colBeneathRight) == null)
          || (rowBeneathLeft == this.getNumRows() && rowBeneathRight == this.getNumRows())) {
        exposedCards.add(card);
      }
    }

  }


}
