package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Suite;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The class represents the multi pyramid version of the game.
 */

public class MultiPyramidSolitaire extends BasicPyramidSolitaire {

  /**
   * The constructor for the multi pyramid version of the game.
   */
  public MultiPyramidSolitaire() {
    super();
  }

  /**
   * The constructor with random seed for the multi pyramid version of the game.
   */
  public MultiPyramidSolitaire(Random randomSeed) {
    super(randomSeed);
  }

  @Override
  public List<Card> getDeck() {
    Integer[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    Suite[] suits = {Suite.CLUB, Suite.SPADE, Suite.DIAMOND, Suite.HEART};
    List<Card> completedDeck = new ArrayList<>();

    for (Integer value : values) {
      for (Suite suit : suits) {
        completedDeck.add(new Card(value, suit));
        completedDeck.add(new Card(value, suit));
      }
    }

    return completedDeck;
  }

  @Override
  public void startGame(List<Card> deck, boolean shuffle, int numRows, int numDraw)
      throws IllegalArgumentException {
    List<Card> validDeck = this.getDeck();
    List<Card> cloneGivenDeck = new ArrayList<>();

    if (!checkRowAndDraw(numRows, numDraw)) {
      throw new IllegalArgumentException("Invalid rows or draw. Exceed 104 cards.");
    }

    if (!deck.containsAll(validDeck)) {
      throw new IllegalArgumentException("Deck of cards is invalid.");
    }

    if (deck == null) {
      throw new IllegalArgumentException("Deck of cards can't be null.");
    }

    if (deck == new ArrayList<Card>()) {
      throw new IllegalArgumentException("Can't start a game with an empty deck.");
    }

    if (deck.size() != 104) {
      throw new IllegalArgumentException("The game needs to start with a deck of 104 cards.");
    }

    if (numRows <= 0 || numRows >= 9) {
      throw new IllegalArgumentException("The number of rows can't be negative or greater than 8.");
    }

    if (numDraw < 0) {
      throw new IllegalArgumentException("The number of draw cards can't be negative.");
    }

    if (deck.size() < numDraw) {
      throw new IllegalArgumentException("The number of draw cards can't be larger "
          + "than the number of cards in a deck.");
    }

    cloneGivenDeck.addAll(deck);
    if (!shuffle) {
      assignPosition(cloneGivenDeck, numRows, numDraw);
    } else {
      assignPosition(shuffleGivenDeck(cloneGivenDeck), numRows, numDraw);
    }

    rowsWhenStart = numRows;
    drawSizeWhenStart = numDraw;
    this.gameStarted = true;

  }

  @Override
  public void assignPosition(List<Card> givenDeck, int numRows, int numDraw) {
    int cardsInRow;
    int currentIndexInGivenDeck = 0;
    if (numRows == 1) {
      cardsInRow = 1;
    } else {
      cardsInRow = 3;
    }
    IAssignPosition assignPos = null;

    switch (numRows) {
      case 1:
        assignPos = new OneRowAssign();
        assignPos.assign(givenDeck, currentPyramid, numRows, currentIndexInGivenDeck, cardsInRow);
        break;
      case 2:
      case 3:
        assignPos = new TwoOrThreeRowAssign();
        assignPos.assign(givenDeck, currentPyramid, numRows, currentIndexInGivenDeck, cardsInRow);
        break;
      case 4:
      case 5:
        assignPos = new FourOrFiveRowAssign();
        assignPos.assign(givenDeck, currentPyramid, numRows, currentIndexInGivenDeck, cardsInRow);
        break;
      case 6:
      case 7:
        assignPos = new SixOrSevenRowAssign();
        assignPos.assign(givenDeck, currentPyramid, numRows, currentIndexInGivenDeck, cardsInRow);
        break;
      case 8:
        assignPos = new EightRowAssign();
        assignPos.assign(givenDeck, currentPyramid, numRows, currentIndexInGivenDeck, cardsInRow);
        break;
      default:
        return;
    }

    for (Card c : currentPyramid) {
      givenDeck.remove(c);
    }
    stock.addAll(givenDeck);

    for (int i = 0; i < numDraw && i < stock.size(); i += 1) {
      drawCards.add(stock.get(i));
    }

    for (Card c : drawCards) {
      stock.remove(c);
    }

  }

  @Override
  public int getRowWidth(int row) throws IllegalArgumentException, IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("You need to start the game first.");
    }
    if (row < 0) {
      throw new IllegalArgumentException("Row index can't be negative.");
    }
    int startedWidth = -1;
    int totalRow = this.getNumRows();

    switch (totalRow) {
      case 1:
        startedWidth = 1;
        break;
      case 2:
      case 3:
        startedWidth = 3;
        break;
      case 4:
      case 5:
        startedWidth = 5;
        break;
      case 6:
      case 7:
        startedWidth = 7;
        break;
      case 8:
        startedWidth = 9;
        break;
      default:
        startedWidth = 0;
    }

    return row + startedWidth;
  }

  //Helper method to check valid rows and draw input
  private boolean checkRowAndDraw(int rows, int draw) {
    boolean isValid;
    switch (rows) {
      case 1:
        isValid = draw <= 103;
        break;
      case 2:
        isValid = draw <= 97;
        break;
      case 3:
        isValid = draw <= 92;
        break;
      case 4:
        isValid = draw <= 80;
        break;
      case 5:
        isValid = draw <= 71;
        break;
      case 6:
        isValid = draw <= 53;
        break;
      case 7:
        isValid = draw <= 40;
        break;
      case 8:
        isValid = draw <= 16;
        break;
      default:
        isValid = false;
    }

    return isValid;
  }

}
