package ui;

import java.util.Scanner;
import ui.PopupUI;
import ui.TextUI;
import ui.UI;
import ui.UIFormMenuBuilderConcrete;

public class UIFactory {
  public UIFactory() {}
  //static private UI _UI = new PopupUI();
  //static private UI _UI = new TextUI();
  
  /**
   * this is the factory method that returns instances of classes based
   * on the string pulled in from UIFactoryInterface
   * 
   * @param i string to pull from UIFactoryInterface
   * @param head  
   * @param t
   * @return
   */
  static public UIFactoryInterface ui (String i, String head, Pair[] t) {
	  switch(i){
	  case UIFactoryInterface.uiFormMenuConcrete:
          return new UIFormMenuConcrete(head, t);

      case UIFactoryInterface.popup:
          return new PopupUI();

      case UIFactoryInterface.textui:
          return new TextUI();

      case UIFactoryInterface.uiFormMenuBuilder:
          return new UIFormMenuBuilderConcrete();

      default:
          return null;
	  }
  }
}
