package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * The class represents the creator for different types ofpyramid model.
 */
public class PyramidSolitaireCreator {

  /**
   * The enum class represents different types of model.
   */
  public enum GameType { BASIC, RELAXED, MULTIPYRAMID }

  /**
   * The method returns an instance of model's type based on the given game type.
   */
  public static PyramidSolitaireModel create(GameType type) {
    switch (type) {
      case BASIC:
        return new BasicPyramidSolitaire();
      case RELAXED:
        return new RelaxedPyramidSolitaire();
      case MULTIPYRAMID:
        return new MultiPyramidSolitaire();
      default:
        break;
    }

    return new BasicPyramidSolitaire();
  }

}
