package cs3500.pyramidsolitaire.model.hw02;

import java.util.List;
import java.util.Objects;

/**
 * The class represents a card in the desk of 52 cards. value - the value of a card from 1 to 13.
 * suit - the suit of a card: club, spade, diamond or heart. row - index of row when a card is
 * placed in the pyramid. col - index of column when a card is placed in the pyramid.
 **/

public class Card implements ICard {

  private final int value;
  private final Suite suit;
  private int row;
  private int col;

  /**
   * The constructor for card.
   **/
  public Card(int value, Suite suit) {

    if (value <= 0 || value > 13) {
      throw new IllegalArgumentException("Card Value must be in the range 1 to 13.");
    }

    if (suit == null) {
      throw new IllegalArgumentException("Card suit can't be null.");
    }

    this.value = value;
    this.suit = suit;
  }

  @Override
  public String toString() {
    String str = "";

    switch (value) {
      case 1:
        str += "A";
        break;
      case 11:
        str += "J";
        break;
      case 12:
        str += "Q";
        break;
      case 13:
        str += "K";
        break;
      default:
        str += value;
        break;
    }
    if (this == null) {
      return ".";
    } else {
      return str + suit.toString();
    }

  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Card)) {
      return false;
    }

    Card otherCard = (Card) obj;

    boolean result = (this.value == otherCard.value && this.suit == otherCard.suit
        && this.row == otherCard.row && this.col == otherCard.col);
    return result;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, suit);
  }

  /**
   * The method assigns index of row and column to a card when it's placed in te pyramid.
   **/
  public void setRowCol(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * The method check if the sum of values of 2 cards is equal to 13.
   **/
  public boolean removable(List<Card> cards) {
    boolean result = false;
    for (Card c : cards) {
      if (this.getValue() + c.getValue() == 13) {
        result = true;
      }
    }

    return result;
  }

  @Override
  public int getValue() {
    return this.value;
  }

  @Override
  public Suite getSuite() {
    return this.suit;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getCol() {
    return this.col;
  }
}
