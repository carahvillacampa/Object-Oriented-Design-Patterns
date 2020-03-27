package ui;

public interface UIFormMenuInterface {
	public int size();
	  public String getHeading();
	  public String getPrompt(int i);
	  public boolean checkInput(int i, String input) ;
	  public void runAction(int i);

}
