package ui;

public interface UI {
  public void processMenu(UIFormMenuInterface menu);
  public String[] processForm(UIFormMenuInterface form);
  public void displayMessage(String message);
  public void displayError(String message);
}
