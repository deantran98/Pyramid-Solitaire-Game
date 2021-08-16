package cs3500.pyramidsolitaire.model.hw02;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireController;
import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw04.MultiPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * The class runs the game on console.
 **/
public class Main {

  /**
   * The method to run a game on console.
   **/
  public static void main(String[] args) {
    /*PyramidSolitaireModel model = new BasicPyramidSolitaire(new Random(3));
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(
        new InputStreamReader(System.in)
        , System.out);
    controller.playGame(model, model.getDeck(), true, 2, 7);*/
    PyramidSolitaireModel relaxModel = new RelaxedPyramidSolitaire(new Random(4));
    PyramidSolitaireModel multiModel = new MultiPyramidSolitaire(new Random(5));
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(
        new InputStreamReader(System.in)
        , System.out);
    controller.playGame(relaxModel, relaxModel.getDeck(), true, 7, 3);
  }
}
