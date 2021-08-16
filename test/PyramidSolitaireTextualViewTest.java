import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import org.junit.Test;

/**
 * The class tests rendering model.
 **/
public class PyramidSolitaireTextualViewTest {
  @Test
  public void testToString() {
    BasicPyramidSolitaire model = new BasicPyramidSolitaire();
    model.startGame(model.getDeck(), false, 2, 2);
    PyramidSolitaireTextualView render = new PyramidSolitaireTextualView(model);

    assertEquals(render.toString(), "  A♣\n"
        + "A♠  A♦\n"
        + "Draw: A♥, 2♣");
  }

  @Test
  public void testToStringRemove() {
    BasicPyramidSolitaire model = new BasicPyramidSolitaire();
    model.startGame(model.getDeck(), false, 7, 3);
    model.remove(6, 0, 6, 4);
    PyramidSolitaireTextualView render = new PyramidSolitaireTextualView(model);
    String view = render.toString();

    assertEquals(view, "            A♣\n"
        + "          A♠  A♦\n"
        + "        A♥  2♣  2♠\n"
        + "      2♦  2♥  3♣  3♠\n"
        + "    3♦  3♥  4♣  4♠  4♦\n"
        + "  4♥  5♣  5♠  5♦  5♥  6♣\n"
        + ".   6♦  6♥  7♣  .   7♦  7♥\n"
        + "Draw: 8♣, 8♠, 8♦");
  }

  @Test
  public void testToStringIfTheGameHaveNotStarted() {
    BasicPyramidSolitaire model = new BasicPyramidSolitaire();
    PyramidSolitaireTextualView render = new PyramidSolitaireTextualView(model);

    assertEquals(render.toString(), "");
  }

}