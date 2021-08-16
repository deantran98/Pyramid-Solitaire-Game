import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Suite;
import cs3500.pyramidsolitaire.model.hw04.MultiPyramidSolitaire;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import java.io.StringReader;
import java.util.List;
import org.junit.Test;

/**
 * The class tests multi pyramid model.
 **/
public class MultiPyramidSolitaireTest {

  MultiPyramidSolitaire model;
  List<Card> validDeck;

  private void initialize() {
    model = new MultiPyramidSolitaire();
    validDeck = model.getDeck();
  }

  @Test
  public void testStartGameMulti() {
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

    controller.playGame(model, validDeck, true, 9, 3);
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

  @Test
  public void testGetRowWidth() {
    this.initialize();
    model.startGame(model.getDeck(), false, 4, 3);

    assertEquals(5, model.getRowWidth(0));
  }

  @Test
  public void testToStringMultiNoShuffle() {
    MultiPyramidSolitaire multiModel = new MultiPyramidSolitaire();
    multiModel.startGame(multiModel.getDeck(), false, 5, 3);
    PyramidSolitaireTextualView render = new PyramidSolitaireTextualView(multiModel);
    render.toString();

    assertEquals("        A♣  .   A♣  .   A♠\n"
        + "      A♠  A♦  A♦  A♥  A♥  2♣\n"
        + "    2♣  2♠  2♠  2♦  2♦  2♥  2♥\n"
        + "  3♣  3♣  3♠  3♠  3♦  3♦  3♥  3♥\n"
        + "4♣  4♣  4♠  4♠  4♦  4♦  4♥  4♥  5♣\n"
        + "Draw: 5♣, 5♠, 5♠", render.toString());
  }

  @Test
  public void testToStringMultiShuffle() {
    MultiPyramidSolitaire multiModel = new MultiPyramidSolitaire();
    multiModel.startGame(multiModel.getDeck(), true, 8, 3);
    PyramidSolitaireTextualView render = new PyramidSolitaireTextualView(multiModel);

    assertEquals("              K♣  .   .   .   3♦  .   .   .   5♥\n"
        + "            A♦  3♣  .   .   A♠  8♣  .   .   Q♥  9♦\n"
        + "          9♣  9♦  7♦  .   3♦  A♠  6♠  .   6♠  5♠  10♠\n"
        + "        Q♣  7♠  7♥  6♣  K♠  7♥  2♥  4♥  8♣  J♦  8♠  4♥\n"
        + "      A♣  J♦  2♣  A♦  7♣  Q♥  10♥ 9♣  10♦ 4♣  K♥  K♦  7♠\n"
        + "    6♦  K♦  5♣  8♥  10♠ Q♣  6♥  8♦  5♠  A♣  4♠  2♥  J♠  J♥\n"
        + "  Q♦  7♣  3♣  2♠  10♦ 4♦  8♦  3♥  6♥  A♥  5♥  K♠  2♦  J♥  3♠\n"
        + "2♠  6♦  Q♠  8♠  Q♦  A♥  4♠  J♣  J♣  8♥  Q♠  2♦  5♣  3♥  7♦  K♥\n"
        + "Draw: 4♦, 3♠, 5♦", render.toString());
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

    assertEquals("            K♣  .   .   3♦  .   .   5♥\n"
        + "          A♦  3♣  .   A♠  8♣  .   Q♥  9♦\n"
        + "        9♣  9♦  7♦  3♦  A♠  6♠  6♠  5♠  10♠\n"
        + "      Q♣  7♠  7♥  6♣  K♠  7♥  2♥  4♥  8♣  J♦\n"
        + "    8♠  4♥  A♣  J♦  2♣  A♦  7♣  Q♥  10♥ 9♣  10♦\n"
        + "  4♣  K♥  K♦  7♠  6♦  K♦  5♣  8♥  10♠ Q♣  6♥  8♦\n"
        + "5♠  A♣  4♠  2♥  J♠  J♥  Q♦  7♣  3♣  2♠  10♦ 4♦  8♦\n"
        + "Draw: 3♥\n"
        + "Invalid move. Play again. *Invalid attempted remove*", stringBuilder.toString());
  }

  @Test
  public void testInvalidRm2Command() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rm2 7 1 7 2");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, true, 7, 3);

    assertEquals("            K♣  .   .   3♦  .   .   5♥\n"
        + "          A♦  3♣  .   A♠  8♣  .   Q♥  9♦\n"
        + "        9♣  9♦  7♦  3♦  A♠  6♠  6♠  5♠  10♠\n"
        + "      Q♣  7♠  7♥  6♣  K♠  7♥  2♥  4♥  8♣  J♦\n"
        + "    8♠  4♥  A♣  J♦  2♣  A♦  7♣  Q♥  10♥ 9♣  10♦\n"
        + "  4♣  K♥  K♦  7♠  6♦  K♦  5♣  8♥  10♠ Q♣  6♥  8♦\n"
        + "5♠  A♣  4♠  2♥  J♠  J♥  Q♦  7♣  3♣  2♠  10♦ 4♦  8♦\n"
        + "Draw: 3♥, 6♥, A♥\n"
        + "Invalid move. Play again. *Invalid attempted remove*", stringBuilder.toString());
  }

  @Test
  public void testValidRm1Command() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rm1 4 7");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, true, 4, 3);

    assertEquals("      K♣  .   3♦  .   5♥\n"
        + "    A♦  3♣  A♠  8♣  Q♥  9♦\n"
        + "  9♣  9♦  7♦  3♦  A♠  6♠  6♠\n"
        + "5♠  10♠ Q♣  7♠  7♥  6♣  K♠  7♥\n"
        + "Draw: 2♥, 4♥, 8♣\n"
        + "      K♣  .   3♦  .   5♥\n"
        + "    A♦  3♣  A♠  8♣  Q♥  9♦\n"
        + "  9♣  9♦  7♦  3♦  A♠  6♠  6♠\n"
        + "5♠  10♠ Q♣  7♠  7♥  6♣  .   7♥\n"
        + "Draw: 2♥, 4♥, 8♣", stringBuilder.toString());
  }

  @Test
  public void testValidRm2Command() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rm2 6 7 6 8");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, true, 6, 3);

    assertEquals("          K♣  .   .   3♦  .   .   5♥\n"
        + "        A♦  3♣  .   A♠  8♣  .   Q♥  9♦\n"
        + "      9♣  9♦  7♦  3♦  A♠  6♠  6♠  5♠  10♠\n"
        + "    Q♣  7♠  7♥  6♣  K♠  7♥  2♥  4♥  8♣  J♦\n"
        + "  8♠  4♥  A♣  J♦  2♣  A♦  7♣  Q♥  10♥ 9♣  10♦\n"
        + "4♣  K♥  K♦  7♠  6♦  K♦  5♣  8♥  10♠ Q♣  6♥  8♦\n"
        + "Draw: 5♠, A♣, 4♠\n"
        + "          K♣  .   .   3♦  .   .   5♥\n"
        + "        A♦  3♣  .   A♠  8♣  .   Q♥  9♦\n"
        + "      9♣  9♦  7♦  3♦  A♠  6♠  6♠  5♠  10♠\n"
        + "    Q♣  7♠  7♥  6♣  K♠  7♥  2♥  4♥  8♣  J♦\n"
        + "  8♠  4♥  A♣  J♦  2♣  A♦  7♣  Q♥  10♥ 9♣  10♦\n"
        + "4♣  K♥  K♦  7♠  6♦  K♦  .   .   10♠ Q♣  6♥  8♦\n"
        + "Draw: 5♠, A♣, 4♠", stringBuilder.toString());
  }

  @Test
  public void testInvalidRmwdCommand() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rmwd 1 7 7");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, true, 4, 3);

    assertEquals("      K♣  .   3♦  .   5♥\n"
        + "    A♦  3♣  A♠  8♣  Q♥  9♦\n"
        + "  9♣  9♦  7♦  3♦  A♠  6♠  6♠\n"
        + "5♠  10♠ Q♣  7♠  7♥  6♣  K♠  7♥\n"
        + "Draw: 2♥, 4♥, 8♣\n"
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

    assertEquals("      K♣  .   3♦  .   5♥\n"
            + "    A♦  3♣  A♠  8♣  Q♥  9♦\n"
            + "  9♣  9♦  7♦  3♦  A♠  6♠  6♠\n"
            + "5♠  10♠ Q♣  7♠  7♥  6♣  K♠  7♥\n"
            + "Draw: 2♥, 4♥, 8♣\n"
            + "Invalid move. Play again. *No card at a position to discard.*",
        stringBuilder.toString());
  }

  @Test
  public void testValidRmwdCommand() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("rmwd 1 6 8");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, true, 6, 3);

    assertEquals("          K♣  .   .   3♦  .   .   5♥\n"
        + "        A♦  3♣  .   A♠  8♣  .   Q♥  9♦\n"
        + "      9♣  9♦  7♦  3♦  A♠  6♠  6♠  5♠  10♠\n"
        + "    Q♣  7♠  7♥  6♣  K♠  7♥  2♥  4♥  8♣  J♦\n"
        + "  8♠  4♥  A♣  J♦  2♣  A♦  7♣  Q♥  10♥ 9♣  10♦\n"
        + "4♣  K♥  K♦  7♠  6♦  K♦  5♣  8♥  10♠ Q♣  6♥  8♦\n"
        + "Draw: 5♠, A♣, 4♠\n"
        + "          K♣  .   .   3♦  .   .   5♥\n"
        + "        A♦  3♣  .   A♠  8♣  .   Q♥  9♦\n"
        + "      9♣  9♦  7♦  3♦  A♠  6♠  6♠  5♠  10♠\n"
        + "    Q♣  7♠  7♥  6♣  K♠  7♥  2♥  4♥  8♣  J♦\n"
        + "  8♠  4♥  A♣  J♦  2♣  A♦  7♣  Q♥  10♥ 9♣  10♦\n"
        + "4♣  K♥  K♦  7♠  6♦  K♦  5♣  .   10♠ Q♣  6♥  8♦\n"
        + "Draw: 2♥, A♣, 4♠", stringBuilder.toString());
  }

  @Test
  public void testValidDdCommand() {
    this.initialize();
    Appendable stringBuilder = new StringBuilder();
    Readable stringReader = new StringReader("dd 1");
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        stringReader, stringBuilder);

    controller.playGame(model, validDeck, true, 4, 3);

    assertEquals("      K♣  .   3♦  .   5♥\n"
        + "    A♦  3♣  A♠  8♣  Q♥  9♦\n"
        + "  9♣  9♦  7♦  3♦  A♠  6♠  6♠\n"
        + "5♠  10♠ Q♣  7♠  7♥  6♣  K♠  7♥\n"
        + "Draw: 2♥, 4♥, 8♣\n"
        + "      K♣  .   3♦  .   5♥\n"
        + "    A♦  3♣  A♠  8♣  Q♥  9♦\n"
        + "  9♣  9♦  7♦  3♦  A♠  6♠  6♠\n"
        + "5♠  10♠ Q♣  7♠  7♥  6♣  K♠  7♥\n"
        + "Draw: J♦, 4♥, 8♣", stringBuilder.toString());
  }

}