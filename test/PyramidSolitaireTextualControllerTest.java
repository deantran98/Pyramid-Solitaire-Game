import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import java.io.StringReader;
import java.util.List;
import java.util.Random;
import org.junit.Test;

/**
 * The class tests all method in PyramidSolitaireTextualController.
 **/
public class PyramidSolitaireTextualControllerTest {

  BasicPyramidSolitaire model;
  List<Card> validDeck;
  BasicPyramidSolitaire randomModel;
  BasicPyramidSolitaire randomModel1;
  Random randomSeed;
  Random randomSeed1;

  private void initialize() {
    model = new BasicPyramidSolitaire();
    validDeck = model.getDeck();
    randomSeed = new Random(6);
    randomSeed1 = new Random(3);
    randomModel = new BasicPyramidSolitaire(randomSeed);
    randomModel1 = new BasicPyramidSolitaire(randomSeed1);
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
  public void testQuitCommand() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("Q");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(randomModel1, validDeck, true, 7, 1);

    assertEquals("            5♦\n"
        + "          6♠  3♦\n"
        + "        2♣  8♣  10♠\n"
        + "      7♠  J♠  J♥  10♥\n"
        + "    K♠  A♦  K♣  9♥  6♦\n"
        + "  2♥  9♠  7♣  7♥  K♦  2♦\n"
        + "5♣  Q♣  A♠  2♠  5♥  4♦  6♣\n"
        + "Draw: 3♣\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "            5♦\n"
        + "          6♠  3♦\n"
        + "        2♣  8♣  10♠\n"
        + "      7♠  J♠  J♥  10♥\n"
        + "    K♠  A♦  K♣  9♥  6♦\n"
        + "  2♥  9♠  7♣  7♥  K♦  2♦\n"
        + "5♣  Q♣  A♠  2♠  5♥  4♦  6♣\n"
        + "Draw: 3♣\n"
        + "Score: 190", stringBuilder.toString());
  }

  @Test
  public void testInvalidQuitCommand() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("out");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(randomModel1, validDeck, true, 7, 1);

    assertEquals("            5♦\n"
        + "          6♠  3♦\n"
        + "        2♣  8♣  10♠\n"
        + "      7♠  J♠  J♥  10♥\n"
        + "    K♠  A♦  K♣  9♥  6♦\n"
        + "  2♥  9♠  7♣  7♥  K♦  2♦\n"
        + "5♣  Q♣  A♠  2♠  5♥  4♦  6♣\n"
        + "Draw: 3♣\n"
        + "Invalid move. Play again.", stringBuilder.toString());
  }

  @Test
  public void testRm1Command() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rm1 7 1");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(randomModel, validDeck, true, 7, 1);

    assertEquals("            J♥\n"
        + "          5♦  5♣\n"
        + "        K♠  A♠  7♦\n"
        + "      6♦  10♥ 3♥  5♥\n"
        + "    4♣  2♠  J♦  7♥  6♣\n"
        + "  10♦ 8♣  10♠ 3♠  2♥  8♦\n"
        + "K♦  4♠  3♣  J♣  9♣  Q♦  A♥\n"
        + "Draw: 9♦\n"
        + "            J♥\n"
        + "          5♦  5♣\n"
        + "        K♠  A♠  7♦\n"
        + "      6♦  10♥ 3♥  5♥\n"
        + "    4♣  2♠  J♦  7♥  6♣\n"
        + "  10♦ 8♣  10♠ 3♠  2♥  8♦\n"
        + ".   4♠  3♣  J♣  9♣  Q♦  A♥\n"
        + "Draw: 9♦", stringBuilder.toString());
  }

  @Test
  public void testInvalidRm1Command() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rm1 7 a");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(randomModel, validDeck, true, 7, 1);

    assertEquals("            J♥\n"
        + "          5♦  5♣\n"
        + "        K♠  A♠  7♦\n"
        + "      6♦  10♥ 3♥  5♥\n"
        + "    4♣  2♠  J♦  7♥  6♣\n"
        + "  10♦ 8♣  10♠ 3♠  2♥  8♦\n"
        + "K♦  4♠  3♣  J♣  9♣  Q♦  A♥\n"
        + "Draw: 9♦\n"
        + "Please re-enter a valid number for column: \n"
        + "Invalid move. Play again. *Invalid attempted remove*", stringBuilder.toString());
  }

  @Test
  public void testRm2Command() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rm2 7 6 7 7");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(randomModel, validDeck, true, 7, 3);

    assertEquals("            J♥\n"
        + "          5♦  5♣\n"
        + "        K♠  A♠  7♦\n"
        + "      6♦  10♥ 3♥  5♥\n"
        + "    4♣  2♠  J♦  7♥  6♣\n"
        + "  10♦ 8♣  10♠ 3♠  2♥  8♦\n"
        + "K♦  4♠  3♣  J♣  9♣  Q♦  A♥\n"
        + "Draw: 9♦, Q♥, 2♣\n"
        + "            J♥\n"
        + "          5♦  5♣\n"
        + "        K♠  A♠  7♦\n"
        + "      6♦  10♥ 3♥  5♥\n"
        + "    4♣  2♠  J♦  7♥  6♣\n"
        + "  10♦ 8♣  10♠ 3♠  2♥  8♦\n"
        + "K♦  4♠  3♣  J♣  9♣  .   .\n"
        + "Draw: 9♦, Q♥, 2♣", stringBuilder.toString());
  }

  @Test
  public void testInvalidRm2Command() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rm2 8 9 12 55");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(randomModel, validDeck, true, 7, 3);

    assertEquals("            J♥\n"
        + "          5♦  5♣\n"
        + "        K♠  A♠  7♦\n"
        + "      6♦  10♥ 3♥  5♥\n"
        + "    4♣  2♠  J♦  7♥  6♣\n"
        + "  10♦ 8♣  10♠ 3♠  2♥  8♦\n"
        + "K♦  4♠  3♣  J♣  9♣  Q♦  A♥\n"
        + "Draw: 9♦, Q♥, 2♣\n"
        + "Invalid move. Play again. *Invalid attempted remove*", stringBuilder.toString());
  }

  @Test
  public void testInvalidRmwdCommand() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rmwd 1 d 2");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(randomModel, validDeck, true, 7, 3);

    assertEquals("            J♥\n"
        + "          5♦  5♣\n"
        + "        K♠  A♠  7♦\n"
        + "      6♦  10♥ 3♥  5♥\n"
        + "    4♣  2♠  J♦  7♥  6♣\n"
        + "  10♦ 8♣  10♠ 3♠  2♥  8♦\n"
        + "K♦  4♠  3♣  J♣  9♣  Q♦  A♥\n"
        + "Draw: 9♦, Q♥, 2♣\n"
        + "Please re-enter a valid numbers for row: \n"
        + "Invalid move. Play again. *Invalid attempted remove*", stringBuilder.toString());
  }

  @Test
  public void testRmwdCommand() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rmwd 1 7 2");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(randomModel, validDeck, true, 7, 3);

    assertEquals("            J♥\n"
        + "          5♦  5♣\n"
        + "        K♠  A♠  7♦\n"
        + "      6♦  10♥ 3♥  5♥\n"
        + "    4♣  2♠  J♦  7♥  6♣\n"
        + "  10♦ 8♣  10♠ 3♠  2♥  8♦\n"
        + "K♦  4♠  3♣  J♣  9♣  Q♦  A♥\n"
        + "Draw: 9♦, Q♥, 2♣\n"
        + "            J♥\n"
        + "          5♦  5♣\n"
        + "        K♠  A♠  7♦\n"
        + "      6♦  10♥ 3♥  5♥\n"
        + "    4♣  2♠  J♦  7♥  6♣\n"
        + "  10♦ 8♣  10♠ 3♠  2♥  8♦\n"
        + "K♦  .   3♣  J♣  9♣  Q♦  A♥\n"
        + "Draw: 7♠, Q♥, 2♣", stringBuilder.toString());
  }

  @Test
  public void testDdCommand() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("dd 2");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, false, 3, 3);
    assertEquals("    A♣\n"
        + "  A♠  A♦\n"
        + "A♥  2♣  2♠\n"
        + "Draw: 2♦, 2♥, 3♣\n"
        + "    A♣\n"
        + "  A♠  A♦\n"
        + "A♥  2♣  2♠\n"
        + "Draw: 2♦, 3♠, 3♣", stringBuilder.toString());
  }

  @Test
  public void testInvalidDdCommand() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("dd 4");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, false, 3, 3);
    assertEquals("    A♣\n"
            + "  A♠  A♦\n"
            + "A♥  2♣  2♠\n"
            + "Draw: 2♦, 2♥, 3♣\n"
            + "Invalid move. Play again. *No card at a position to discard.*",
        stringBuilder.toString());
  }

}