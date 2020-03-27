package hw6Files.src.src.source.shop.main;

import hw6Files.src.src.source.shop.ui.UI;
import hw6Files.src.src.source.shop.ui.UIFactory;
import hw6Files.src.src.source.shop.ui.UIFactoryInterface;

import java.util.Scanner;

import hw6Files.src.src.source.shop.data.Data;

public class Main {
  private Main() {}
  /**
   * type in popupui to run popup
   * or textui to run textui
   * @param args
   */
  public static void main(String[] args) {
	UIFactory uiFactory = new UIFactory();
	Control control = new Control(Data.newInventory(), (UI) uiFactory.ui("popupui", null, null));
	control.run();
	

  }
}
