package hw6Files.src.src.source.shop.ui;

/**
 * @see UIMenuBuilder
 */
final class UIMenu extends UICommonCommands implements UIMenuInterface, UIFactoryInterface{
  private final String _heading="";
  private final Pair[] _menu;

  UIMenu(String heading, Pair[] menu) {
    super(heading, menu);
    _menu= menu;
  }
  public int size() {
    return _menu.length;
  }
  public String getHeading() {
    return _heading;
  }
  public String getPrompt(int i) {
    return _menu[i].prompt;
  }
  public void runAction(int i) {
    _menu[i].action.run();
  }

}
