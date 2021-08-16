package cs3500.pyramidsolitaire.model.hw02;

/**
 * The card interface.
 **/
public interface ICard {

  int getValue();

  Suite getSuite();

  int getRow();

  int getCol();
}
