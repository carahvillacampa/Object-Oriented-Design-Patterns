package src.source.shop.main;

import src.source.shop.ui.UIFactory;
import src.source.shop.data.Data;

public class Main {
  private Main() {}
  public static void main(String[] args) {
    Control control = new Control(Data.newInventory(), UIFactory.ui());
    control.run();
    
    
  }
}
