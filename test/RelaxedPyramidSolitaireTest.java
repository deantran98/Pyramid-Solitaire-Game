import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Suite;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import java.io.StringReader;
import java.util.List;
import org.junit.Test;

/**
 * The tests of functionality of relaxed model.
 **/
public class RelaxedPyramidSolitaireTest {

  RelaxedPyramidSolitaire model;
  List<Card> validDeck;

  private void initialize() {
    model = new RelaxedPyramidSolitaire();
    validDeck = model.getDeck();
  }

  @Test
  public void testStartGameRelax() {
    this.initialize();
    model.startGame(model.getDeck(), false, 5, 3);

    assertEquals(new Card(1, Suite.CLUB), model.getCardAt(0, 0));
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidRowsStartGameMulti() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rm1 7 1");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, true, -1, 3);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidTotalCardsStartGameMulti() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rm1 7 1");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, true, 8, 17);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidReader() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        null, stringBuilder);

    controller.playGame(model, validDeck, true, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAppendable() {
    this.initialize();
    Readable stringReader = new StringReader("rm1 7 1");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, null);

    controller.playGame(model, validDeck, true, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rm1 7 1");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(null, validDeck, true, 7, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDeck() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rm1 7 1");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, null, true, 7, 1);
  }

  @Test
  public void testInvalidRm1Command() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rm1 7 1");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, true, 7, 1);

    assertEquals("            Q♣\n"
        + "          8♣  8♦\n"
        + "        A♦  4♣  7♣\n"
        + "      J♣  6♥  J♦  9♣\n"
        + "    10♣ 5♦  Q♠  10♠ 6♣\n"
        + "  10♦ 2♠  A♣  2♦  K♥  4♠\n"
        + "3♦  7♠  9♦  8♠  10♥ 6♦  4♦\n"
        + "Draw: 7♦\n"
        + "Invalid move. Play again. *Invalid attempted remove*", stringBuilder.toString());
  }

  @Test
  public void testInvalidRm2Command() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rm2 7 1 7 2");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, true, 7, 1);

    assertEquals("            Q♣\n"
        + "          8♣  8♦\n"
        + "        A♦  4♣  7♣\n"
        + "      J♣  6♥  J♦  9♣\n"
        + "    10♣ 5♦  Q♠  10♠ 6♣\n"
        + "  10♦ 2♠  A♣  2♦  K♥  4♠\n"
        + "3♦  7♠  9♦  8♠  10♥ 6♦  4♦\n"
        + "Draw: 7♦\n"
        + "Invalid move. Play again. *Invalid attempted remove*", stringBuilder.toString());
  }

  @Test
  public void testValidRm1Command() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rm1 6 5");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, true, 6, 1);

    assertEquals("          Q♣\n"
        + "        8♣  8♦\n"
        + "      A♦  4♣  7♣\n"
        + "    J♣  6♥  J♦  9♣\n"
        + "  10♣ 5♦  Q♠  10♠ 6♣\n"
        + "10♦ 2♠  A♣  2♦  K♥  4♠\n"
        + "Draw: 3♦\n"
        + "          Q♣\n"
        + "        8♣  8♦\n"
        + "      A♦  4♣  7♣\n"
        + "    J♣  6♥  J♦  9♣\n"
        + "  10♣ 5♦  Q♠  10♠ 6♣\n"
        + "10♦ 2♠  A♣  2♦  .   4♠\n"
        + "Draw: 3♦", stringBuilder.toString());
  }

  @Test
  public void testValidRm2Command() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rm2 7 1 7 5");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, true, 7, 3);

    assertEquals("            Q♣\n"
        + "          8♣  8♦\n"
        + "        A♦  4♣  7♣\n"
        + "      J♣  6♥  J♦  9♣\n"
        + "    10♣ 5♦  Q♠  10♠ 6♣\n"
        + "  10♦ 2♠  A♣  2♦  K♥  4♠\n"
        + "3♦  7♠  9♦  8♠  10♥ 6♦  4♦\n"
        + "Draw: 7♦, A♥, 2♣\n"
        + "            Q♣\n"
        + "          8♣  8♦\n"
        + "        A♦  4♣  7♣\n"
        + "      J♣  6♥  J♦  9♣\n"
        + "    10♣ 5♦  Q♠  10♠ 6♣\n"
        + "  10♦ 2♠  A♣  2♦  K♥  4♠\n"
        + ".   7♠  9♦  8♠  .   6♦  4♦\n"
        + "Draw: 7♦, A♥, 2♣", stringBuilder.toString());
  }

  @Test
  public void testInvalidRmwdCommand() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rmwd 1 7 7");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, true, 4, 3);

    assertEquals("      Q♣\n"
        + "    8♣  8♦\n"
        + "  A♦  4♣  7♣\n"
        + "J♣  6♥  J♦  9♣\n"
        + "Draw: 10♣, 5♦, Q♠\n"
        + "Invalid move. Play again. *Invalid attempted remove*", stringBuilder.toString());
  }

  @Test
  public void testInvalidDdCommand() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("dd 4");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, true, 4, 3);

    assertEquals("      Q♣\n"
            + "    8♣  8♦\n"
            + "  A♦  4♣  7♣\n"
            + "J♣  6♥  J♦  9♣\n"
            + "Draw: 10♣, 5♦, Q♠\n"
            + "Invalid move. Play again. *No card at a position to discard.*",
        stringBuilder.toString());
  }

  @Test
  public void testValidRmwdCommand() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rmwd 1 6 1");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, true, 6, 3);

    assertEquals("          Q♣\n"
        + "        8♣  8♦\n"
        + "      A♦  4♣  7♣\n"
        + "    J♣  6♥  J♦  9♣\n"
        + "  10♣ 5♦  Q♠  10♠ 6♣\n"
        + "10♦ 2♠  A♣  2♦  K♥  4♠\n"
        + "Draw: 3♦, 7♠, 9♦\n"
        + "          Q♣\n"
        + "        8♣  8♦\n"
        + "      A♦  4♣  7♣\n"
        + "    J♣  6♥  J♦  9♣\n"
        + "  10♣ 5♦  Q♠  10♠ 6♣\n"
        + ".   2♠  A♣  2♦  K♥  4♠\n"
        + "Draw: 8♠, 7♠, 9♦", stringBuilder.toString());
  }

  @Test
  public void testValidDdCommand() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("dd 1");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, true, 4, 3);

    assertEquals("      Q♣\n"
        + "    8♣  8♦\n"
        + "  A♦  4♣  7♣\n"
        + "J♣  6♥  J♦  9♣\n"
        + "Draw: 10♣, 5♦, Q♠\n"
        + "      Q♣\n"
        + "    8♣  8♦\n"
        + "  A♦  4♣  7♣\n"
        + "J♣  6♥  J♦  9♣\n"
        + "Draw: 10♠, 5♦, Q♠", stringBuilder.toString());
  }

}