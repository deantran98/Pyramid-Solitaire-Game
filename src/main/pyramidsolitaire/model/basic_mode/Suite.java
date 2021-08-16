package cs3500.pyramidsolitaire.model.hw02;

/**
 * The enum represents all suites for deck of cards.
 **/
public enum Suite {
  HEART("♥"), DIAMOND("♦"), CLUB("♣"), SPADE("♠");
  String symbol;

  private Suite(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return this.symbol;
  }
}
