package hw6Files.src.src.source.shop.ui;

/**
 * @see UIFormBuilder
 */
final class UIForm extends UICommonCommands implements UIFormInterface, UIFactoryInterface{
  private final String _heading="";
  private final Pair[] _form;
  
  UIForm(String heading, Pair[] menu) {
    super(heading, menu);
    _form = menu;
  }
  public int size() {
    return _form.length;
  }
  public String getHeading() {
    return _heading;
  }
  public String getPrompt(int i) {
    return _form[i].prompt;
  }
  /**
   * returns true if input is empty,
   * if not, checks it with test and running that test
   */
  public boolean checkInput(int i, String input) {
    if (null == _form[i])
      return true;
    return _form[i].test.run(input);
  }
}
