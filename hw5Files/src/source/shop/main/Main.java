package source.shop.main;

import source.shop.ui.UIFactory;
import source.shop.data.Data;

public class Main {
  private Main() {}
  public static void main(String[] args) {
    Control control = new Control(Data.newInventory(), UIFactory.ui());
    control.run();
  }
}
