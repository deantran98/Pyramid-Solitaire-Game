import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Suite;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Test;

/**
 * The class tests all method in BasicPyramidSolitaire.
 **/
public class BasicPyramidSolitaireTest {

  BasicPyramidSolitaire model;
  List<Card> validDeck;

  private void initialize() {
    model = new BasicPyramidSolitaire();
    validDeck = model.getDeck();
  }

  @Test
  public void testGetDeck() {
    this.initialize();

    assertEquals(validDeck.get(0), new Card(1, Suite.CLUB));
    assertEquals(validDeck.get(1), new Card(1, Suite.SPADE));
    assertEquals(validDeck.get(2), new Card(1, Suite.DIAMOND));
    assertEquals(validDeck.get(3), new Card(1, Suite.HEART));
    assertEquals(validDeck.size(), 52);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidState() {
    this.initialize();

    model.remove(1, 2, 4, 5);
    model.remove(1, 2);
    model.removeUsingDraw(1, 2, 4);
    model.discardDraw(1);
    model.getRowWidth(4);
    model.isGameOver();
    model.getScore();
    model.getCardAt(1,1);
    model.getDrawCards();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameInvalid() {
    this.initialize();
    List<Card> invalidDeck = new ArrayList<>();

    new BasicPyramidSolitaire().startGame(invalidDeck, false, 4, 3);
    new BasicPyramidSolitaire().startGame(validDeck, false, -2, 3);
    new BasicPyramidSolitaire().startGame(validDeck, false, 4, -5);
    new BasicPyramidSolitaire().startGame(validDeck, true, 54, 3);
    new BasicPyramidSolitaire().startGame(validDeck, true, 2, 100);

    validDeck.set(1, new Card(1,Suite.CLUB));
    new BasicPyramidSolitaire().startGame(validDeck, false, 7, 3);
  }

  @Test
  public void testStartGame() {
    this.initialize();
    model.startGame(model.getDeck(), false, 3, 3);

    assertEquals(model.getCardAt(0, 0), model.getDeck().get(0));
  }

  @Test
  public void testAssignPosition() {
    this.initialize();
    model.startGame(model.getDeck(), false, 2, 3);

    assertEquals(model.getCardAt(0, 0), model.getDeck().get(0));
    assertEquals(model.getDrawCards().get(0), model.getDeck().get(3));
    assertEquals(model.getDrawCards().get(1), model.getDeck().get(4));
    assertEquals(model.getDrawCards().get(2), model.getDeck().get(5));
  }

  /*@Test
  public void testExposedCards() {
    this.initialize();
    List<Card> exposedCards = new ArrayList<>();
    model.startGame(model.getDeck(), false, 2, 3);
    model.exposedCards(exposedCards);

    assertEquals(exposedCards.get(1), new Card(1, Suite.DIAMOND));
  }*/

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRemove() {
    this.initialize();

    model.startGame(model.getDeck(), false, 7, 4);
    model.remove(-1, 2, 4, -5);
    model.remove(-1, -2);
    model.remove(0, 0, 1, 1);
    model.remove(0, 0);
  }

  @Test
  public void testRemove() {
    this.initialize();
    model.startGame(validDeck, false, 7, 3);

    model.remove(6, 0, 6, 5);
    assertEquals(model.getCardAt(6, 0), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRemoveUsingDraw() {
    this.initialize();

    model.startGame(model.getDeck(), false, 7, 3);
    model.removeUsingDraw(-2, 1, 1);
    model.removeUsingDraw(1, -1, -1);
    model.removeUsingDraw(5, 1, 1);
    model.removeUsingDraw(0, 1, 1);
  }

  @Test
  public void testRemoveUsingDraw() {
    this.initialize();
    model.startGame(validDeck, false, 7, 3);
    model.remove(6,1,6,3);
    model.remove(6,2,6,4);
    model.removeUsingDraw(0, 5, 1);

    assertEquals(model.getCardAt(5, 1), null);
    assertEquals(model.getDrawCards().get(0), new Card(8, Suite.HEART));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDiscardDraw() {
    this.initialize();

    model.startGame(model.getDeck(), false, 7, 4);
    model.discardDraw(4);
    model.discardDraw(-2);
  }

  @Test
  public void testDiscardDraw() {
    this.initialize();
    model.startGame(model.getDeck(), false, 2, 3);
    model.discardDraw(0);

    assertEquals(model.getDrawCards().get(0), model.getDeck().get(6));
  }

  @Test
  public void testNumRows() {
    this.initialize();
    assertEquals(model.getNumRows(), -1);

    model.startGame(model.getDeck(), false, 2, 3);
    assertEquals(model.getNumRows(), 2);
  }

  @Test
  public void testNumDraw() {
    this.initialize();
    assertEquals(model.getNumDraw(), -1);

    model.startGame(model.getDeck(), false, 2, 3);
    assertEquals(model.getNumDraw(), 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetRowWidth() {
    this.initialize();

    model.startGame(model.getDeck(), false, 2, 3);
    model.getRowWidth(-2);
  }

  @Test
  public void testGetRowWidth() {
    this.initialize();
    model.startGame(model.getDeck(), false, 2, 3);

    assertEquals(model.getRowWidth(3), 4);
  }

  @Test
  public void testIsGameOver() {
    BasicPyramidSolitaire model = new BasicPyramidSolitaire();
    model.startGame(model.getDeck(), false, 7, 3);

    assertEquals(model.isGameOver(), false);

    model.startGame(model.getDeck(), false, 2, 3);
    assertEquals(model.isGameOver(), false);
  }

  @Test
  public void testGetScore() {
    this.initialize();
    model.startGame(model.getDeck(), false, 2, 3);

    assertEquals(model.getScore(), 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetCardAt() {
    this.initialize();

    model.startGame(model.getDeck(), false, 2, 3);
    model.getCardAt(-1, -2);
  }

  @Test
  public void testGetCardAt() {
    this.initialize();
    model.startGame(model.getDeck(), false, 7, 3);

    assertEquals(model.getCardAt(0, 0), model.getDeck().get(0));
  }

  @Test
  public void testGetDrawCards() {
    this.initialize();
    model.startGame(model.getDeck(), false, 2, 3);

    assertEquals(model.getDrawCards().get(0), new Card(1, Suite.HEART));
    assertEquals(model.getDrawCards().get(1), new Card(2, Suite.CLUB));
    assertEquals(model.getDrawCards().get(2), new Card(2, Suite.SPADE));
  }

  @Test
  public void testShuffleDeck() {
    BasicPyramidSolitaire randomModel = new BasicPyramidSolitaire(new Random(4));
    randomModel.startGame(randomModel.getDeck(), true, 2, 3);

    assertNotEquals(randomModel.getDrawCards().get(0), new Card(1, Suite.HEART));
  }

}