package hw6Files.src.src.source.shop.ui;

import java.util.Scanner;

import hw6Files.src.src.source.shop.ui.PopupUI;
import hw6Files.src.src.source.shop.ui.TextUI;
import hw6Files.src.src.source.shop.ui.UI;

/** 
 * Returns a new object of the available concrete classes.
 * Relies on the String i passed as a parameter to the UIFactoryInterface
 * to create the type of concrete class and access its methods
 * 
 * @author carahvillacampa
 *
 */
public class UIFactory {
  public UIFactory() {}
  //static private UI _UI = new PopupUI();
  //static private UI _UI = new TextUI();
  static public UIFactoryInterface ui (String i, String head, Pair[] t) {
	  switch(i){
      case UIFactoryInterface.popup:
          return new PopupUI();

      case UIFactoryInterface.textui:
          return new TextUI();

      case UIFactoryInterface.UIFormBuilder:
          return new UIFormBuilder();

      case UIFactoryInterface.UIMenu:
          return new UIMenu(head, t);

      case UIFactoryInterface.UIMenuBuilder:
          return new UIMenuBuilder();

      default:
          return null;
	  }
  }
}
