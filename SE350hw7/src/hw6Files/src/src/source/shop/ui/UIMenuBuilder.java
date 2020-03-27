package hw6Files.src.src.source.shop.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * can only be accessed through the interfaces that it implements
 * @author carahvillacampa
 *
 */
final class UIMenuBuilder implements UIFactoryInterface,UIMenuBuilderInterface{
  private final List _menu;
  
  /**
   * returns an arraylist
   */
  public UIMenuBuilder() {
    _menu = new ArrayList();
  }
  
  /**
   * @param heading returns a new UI menu object
   * using the menu and the array of the contents of the menu
   */
  public UIMenu toUIMenu(String heading) {
    if (null == heading)
      throw new IllegalArgumentException();
    if (_menu.size() <= 1)
      throw new IllegalStateException();
    Pair[] array = new Pair[_menu.size()];
    for (int i = 0; i < _menu.size(); i++)
      array[i] = (Pair) (_menu.get(i));
    return new UIMenu(heading, array);
  }
  /**
   * 
   */
  public void add(String prompt, UIMenuActionInterface action) {
    if (null == action)
      throw new IllegalArgumentException();
    _menu.add(new Pair(prompt, action));
  }
}
