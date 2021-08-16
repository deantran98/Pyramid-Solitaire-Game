package cs3500.pyramidsolitaire.model.hw02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The class that implements the interface PyramidSolitaireModel.
 **/

public class BasicPyramidSolitaire implements PyramidSolitaireModel<Card> {

  protected boolean gameStarted;
  protected List<Card> currentPyramid;
  protected List<Card> stock;
  protected List<Card> drawCards;
  protected int rowsWhenStart;
  protected int drawSizeWhenStart;
  protected Random randomSeed;

  /**
   * The constructor of BasicPyramidSolitaire.
   **/
  public BasicPyramidSolitaire() {
    this.gameStarted = false;
    this.currentPyramid = new ArrayList<>();
    this.stock = new ArrayList<>();
    this.drawCards = new ArrayList<>();
    this.rowsWhenStart = -1;
    this.drawSizeWhenStart = -1;
    this.randomSeed = new Random(0);
  }

  /**
   * The constructor of BasicPyramidSolitaire with random seed.
   **/
  public BasicPyramidSolitaire(Random randomSeed) {
    if (randomSeed == null) {
      throw new IllegalArgumentException("Random seed can't be null.");
    }
    this.gameStarted = false;
    this.currentPyramid = new ArrayList<>();
    this.stock = new ArrayList<>();
    this.drawCards = new ArrayList<>();
    this.rowsWhenStart = -1;
    this.drawSizeWhenStart = -1;
    this.randomSeed = randomSeed;
  }

  @Override
  public List<Card> getDeck() {
    Integer[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    Suite[] suits = {Suite.CLUB, Suite.SPADE, Suite.DIAMOND, Suite.HEART};
    List<Card> completedDeck = new ArrayList<>();

    for (Integer value : values) {
      for (Suite suit : suits) {
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
    int countValidNumCards = 0;

    for (int i = 1; i <= numRows; i++) {
      countValidNumCards += i;
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

    if (deck.size() != 52) {
      throw new IllegalArgumentException("The game needs to start with a deck of 52 cards.");
    }

    if (numRows <= 0) {
      throw new IllegalArgumentException("The number of rows can't be negative.");
    }

    if (numDraw < 0) {
      throw new IllegalArgumentException("The number of draw cards can't be negative.");
    }

    if (countValidNumCards + numDraw > 52) {
      throw new IllegalArgumentException("Row and draw inputs exceed the number of 52 cards.");
    }

    if (deck.size() < numRows || deck.size() < numDraw) {
      throw new IllegalArgumentException("The number of rows and draw cards can't be larger "
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

  /**
   * The method helps to shuffle any given deck of card.
   **/
  public List<Card> shuffleGivenDeck(List<Card> givenDeck) {
    List<Card> shuffledDeck = new ArrayList<>(givenDeck);

    try {
      for (int i = 0; i < shuffledDeck.size(); i = i + 1) {
        int ranIndex = this.randomSeed.nextInt(givenDeck.size());
        shuffledDeck.set(i, givenDeck.get(ranIndex));
        givenDeck.remove(ranIndex);
      }
    } catch (NullPointerException e) {
      System.out.println("Random seed can't be null.");
    }

    return shuffledDeck;
  }

  /**
   * The method assigns the position (row, column) of each card in the pyramid and the position of
   * remaining cards to the stock and the draw pile.
   **/
  public void assignPosition(List<Card> givenDeck, int numRows, int numDraw) {
    int cardsInRow = 1;
    int currentIndexInGivenDeck = 0;

    for (int i = 0; i < numRows; i += 1) {
      if (cardsInRow <= numRows) {
        for (int j = 0; j < cardsInRow; j += 1) {
          if (currentIndexInGivenDeck < givenDeck.size()) {
            givenDeck.get(currentIndexInGivenDeck).setRowCol(i, j);
            Card cardToAdd = givenDeck.get(currentIndexInGivenDeck);
            currentPyramid.add(cardToAdd);
            currentIndexInGivenDeck += 1;
          }
        }

        cardsInRow += 1;
      }
    }

    givenDeck.removeAll(currentPyramid);
    stock.addAll(givenDeck);

    for (int i = 0; i < numDraw && i < stock.size(); i += 1) {
      drawCards.add(stock.get(i));
    }
    stock.removeAll(drawCards);

  }

  /**
   * The method returns the exposed list of cards.
   **/
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
          && this.getCardAt(rowBeneathRight, colBeneathRight) == null)
          || (rowBeneathLeft == this.getNumRows() && rowBeneathRight == this.getNumRows())) {
        exposedCards.add(card);
      }
    }

  }

  @Override
  public void remove(int row1, int card1, int row2, int card2)
      throws IllegalArgumentException, IllegalStateException {
    List<Card> exposedList = new ArrayList<>();
    this.exposedCards(exposedList);

    if (!gameStarted) {
      throw new IllegalStateException("You need to start the game first.");
    }
    if (row1 < 0 || card1 < 0 || row2 < 0 || card2 < 0) {
      throw new IllegalArgumentException("Position of cards can't be negative.");
    }

    if (row1 >= currentPyramid.size() || card1 >= currentPyramid.size() || row2 >= currentPyramid
        .size() || card2 >= currentPyramid.size()) {
      throw new IllegalArgumentException(
          "Position of cards can't be greater than size of pyramid.");
    }

    int index1 = 0;
    int index2 = 0;
    int value1 = 0;
    int value2 = 0;

    for (int i = 0; i < exposedList.size(); i++) {
      if (exposedList.get(i).getRow() == row1 && exposedList.get(i).getCol() == card1) {
        value1 = exposedList.get(i).getValue();
        index1 = i;
        break;
      }
    }
    for (int j = 0; j < exposedList.size(); j++) {
      if (exposedList.get(j).getRow() == row2 && exposedList.get(j).getCol() == card2) {
        value2 = exposedList.get(j).getValue();
        index2 = j;
        break;
      }
    }

    if (value1 != 13 && value2 != 13 && value1 + value2 == 13) {
      for (int i = 0; i < currentPyramid.size(); i++) {
        if (currentPyramid.get(i) == null) {
          continue;
        }
        if ((exposedList.get(index1).equals(currentPyramid.get(i))
            && exposedList.get(index1).hashCode() == currentPyramid.get(i).hashCode()) || (
            exposedList.get(index2)
                .equals(currentPyramid.get(i))
                && exposedList.get(index2).hashCode() == currentPyramid.get(i).hashCode())) {
          currentPyramid.set(i, null);
        }
      }
    } else {
      throw new IllegalArgumentException("Invalid attempted remove.");
    }

  }

  @Override
  public void remove(int row, int card) throws IllegalArgumentException, IllegalStateException {
    List<Card> exposedList = new ArrayList<>();
    this.exposedCards(exposedList);
    if (!gameStarted) {
      throw new IllegalStateException("You need to start the game first.");
    }
    if (row < 0 || card < 0) {
      throw new IllegalArgumentException("Position of cards can't be negative.");
    }

    if (row >= currentPyramid.size() || card >= currentPyramid.size()) {
      throw new IllegalArgumentException(
          "Position of cards can't be greater than size of pyramid.");
    }

    int index = 0;

    for (int i = 0; i < exposedList.size(); i++) {
      if (exposedList.get(i).getRow() == row && exposedList.get(i).getCol() == card) {
        index = i;
        break;
      }
    }

    for (int i = 0; i < currentPyramid.size(); i++) {
      if (currentPyramid.get(i) == null) {
        continue;
      }
      if (exposedList.get(index).equals(currentPyramid.get(i))
          && exposedList.get(index).hashCode() == currentPyramid.get(i).hashCode()
          && currentPyramid.get(i).getValue() == 13) {
        currentPyramid.set(i, null);
        break;
      }
      if (exposedList.get(index).equals(currentPyramid.get(i))
          && exposedList.get(index).hashCode() == currentPyramid.get(i).hashCode()
          && currentPyramid.get(i).getValue() != 13) {
        throw new IllegalArgumentException("Invalid attempted remove.");
      }
    }

  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card)
      throws IllegalArgumentException, IllegalStateException {
    List<Card> exposedList = new ArrayList<>();
    this.exposedCards(exposedList);

    if (!gameStarted) {
      throw new IllegalStateException("You need to start the game first.");
    }

    if (row < 0 || card < 0) {
      throw new IllegalArgumentException("Position of cards can't be negative.");
    }

    if (row >= currentPyramid.size() || card >= currentPyramid.size()) {
      throw new IllegalArgumentException(
          "Position of cards can't be greater than size of pyramid.");
    }

    if (drawIndex < 0 || drawIndex >= drawCards.size() || drawCards.get(drawIndex) == null) {
      throw new IllegalArgumentException(
          "Index of draw cards can't be negative or greater number of cards in draw pile.");
    }

    int index = -1;
    int value = 0;
    int valueDraw = drawCards.get(drawIndex).getValue();

    for (int i = 0; i < exposedList.size(); i++) {
      if (exposedList.get(i).getRow() == row && exposedList.get(i).getCol() == card) {
        value = exposedList.get(i).getValue();
        index = i;
        break;
      }
    }

    if (value != 13 && valueDraw != 13 && value + valueDraw == 13 && drawCards.size() > 0
        && index >= 0) {
      for (int i = 0; i < currentPyramid.size(); i++) {
        if (currentPyramid.get(i) == null) {
          continue;
        }
        if (exposedList.get(index).equals(currentPyramid.get(i))
            && exposedList.get(index).hashCode() == currentPyramid.get(i).hashCode()) {
          currentPyramid.set(i, null);
        }
      }
      this.discardDraw(drawIndex);
    } else {
      throw new IllegalArgumentException("Invalid attempted remove.");
    }

  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalArgumentException, IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("You need to start the game first.");
    }
    if (drawIndex < 0 || drawIndex >= drawCards.size()) {
      throw new IllegalArgumentException("Index of draw cards can't be negative.");
    }

    if (drawCards.get(drawIndex) == null) {
      throw new IllegalArgumentException("No card is present at the given index.");
    }

    if (stock.size() > 0) {
      drawCards.set(drawIndex, stock.get(0));
      stock.remove(0);
    } else {
      drawCards.remove(drawIndex);
    }

  }

  @Override
  public int getNumRows() {
    int numClone = rowsWhenStart;
    if (numClone <= 0) {
      return -1;
    } else {
      return numClone;
    }

  }

  @Override
  public int getNumDraw() {
    int numClone = drawSizeWhenStart;
    if (numClone < 0) {
      return -1;
    } else {
      return numClone;
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
    return row + 1;
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("You need to start the game first.");
    }

    List<Card> exposedList = new ArrayList<>();
    boolean checkPyramid = true;
    boolean checkDiscard = true;
    this.exposedCards(exposedList);

    for (Card c : exposedList) {
      if (c.removable(exposedList)) {
        checkPyramid = false;
        break;
      }
      if (c.removable(drawCards)) {
        checkPyramid = false;
        break;
      }
      if (c.getValue() == 13) {
        checkPyramid = false;
        break;
      }
    }

    for (Card c : drawCards) {
      if (c != null) {
        checkDiscard = false;
        break;
      }
    }

    boolean isWin = this.getScore() == 0;
    return isWin || (checkPyramid && checkDiscard);
  }

  @Override
  public int getScore() throws IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("You need to start the game first.");
    }
    int totalScore = 0;
    for (Card c : currentPyramid) {
      if (c == null) {
        totalScore += 0;
      } else {
        totalScore += c.getValue();
      }

    }
    return totalScore;
  }

  @Override
  public Card getCardAt(int row, int card) throws IllegalArgumentException, IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("You need to start the game first.");
    }
    if (row < 0 || card < 0) {
      throw new IllegalArgumentException("Row and card index can't be negative.");
    }

    int indexToGet = -1;
    for (int i = 0; i < currentPyramid.size(); i++) {
      if (currentPyramid.get(i) == null) {
        continue;
      }

      if (currentPyramid.get(i).getRow() == row && currentPyramid.get(i).getCol() == card) {
        indexToGet = i;
        break;
      }
    }

    if (indexToGet < 0) {
      return null;
    } else {
      return currentPyramid.get(indexToGet);
    }

  }

  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("You need to start the game first.");
    }
    return new ArrayList<>(drawCards);
  }

}
