package ui;

import javax.swing.JOptionPane;
//import java.io.IOException;

final class PopupUI implements UI, UIFactoryInterface {
  PopupUI() {}

  /**
   * @param message message to be displayed
   */
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(null,message);
  }

  /**
   * @param message message to be displayed
   */
  public void displayError(String message) {
    JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
  }
  
  /**
   * This method takes in an interface referenece: UIMenuInterface
   * UIMenuInterface is implemented by: UIMenu, the concrete implementation
   * iterates through the concrete menu and  displays choices through JOption
   * selection should be within the limits, otherwise, an 
   * exception is thrown. 
   * menu is ran based on the number selection.
   * @param menu to be proccessed
   */
  public void processMenu(UIFormMenuInterface menu) {
    StringBuffer b = new StringBuffer();
    b.append(menu.getHeading());
    b.append("\n");
    b.append("Enter choice by number:");
    b.append("\n");

    for (int i = 1; i < menu.size(); i++) {
      b.append("  " + i + ". " + menu.getPrompt(i));
      b.append("\n");
    }

    String response = JOptionPane.showInputDialog(b.toString());
    if(response == null) {
    	System.exit(0);
    }
    int selection;
    try {
      selection = Integer.parseInt(response, 10);
      if ((selection < 0) || (selection >= menu.size()))
        selection = 0;
    } catch (NumberFormatException e) {
      selection = 0;
    }
    menu.runAction(selection);
  }

  public String[] processForm(UIFormMenuInterface form) {
    String []msgArray= new String[form.size()];
    
    for(int i=0; i< form.size(); i++) {
    	String msgInput= JOptionPane.showInputDialog(form.getPrompt(i));
    	if(!form.checkInput(i, msgInput)) {
    		displayError("Invalid input");
    	}else {
    		msgArray[i]= msgInput;
    	}
    }
    return msgArray;
  }

}
