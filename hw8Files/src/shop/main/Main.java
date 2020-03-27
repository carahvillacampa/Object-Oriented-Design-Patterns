package shop.main;

import ui.UI;
import ui.UIFactory;
import ui.UIFactoryInterface;

import java.util.Scanner;

import data.Data;

public class Main {
  private Main() {}
  /**
   * type in popupui to run popup
   * or textui to run textui
   * @param args
   */
  public static void main(String[] args) {
	UIFactory uiFactory = new UIFactory();
	Control control = new Control(Data.newInventory(), (UI) UIFactory.ui("textui", null, null));
	control.run();
	

  }
}
