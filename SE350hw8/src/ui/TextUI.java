package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

final class TextUI implements UI, UIFactoryInterface  {
  final BufferedReader _in;
  final PrintStream _out;

  TextUI() {
    _in = new BufferedReader(new InputStreamReader(System.in));
    _out = System.out;
  }

  public void displayMessage(String message) {
    _out.println(message);
  }

  public void displayError(String message) {
    _out.println(message);
  }

  private String getResponse() {
    String result;
    try {
      result = _in.readLine();
    } catch (IOException e) {
      throw new UIError();
    }
    if (result == null) {
      throw new UIError(); 
    }
    return result;
  }

  public void processMenu(UIFormMenuInterface menu) {
    _out.println(menu.getHeading());
    _out.println("Enter choice by number:");

    for (int i = 1; i < menu.size(); i++) {
      _out.println("  " + i + ". " + menu.getPrompt(i));
    }

    String response = getResponse();
    if(response== null) {
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
	
    String [] msg= new String [form.size()];
    
    for(int i=0; i< form.size(); i++) {
    	displayMessage(form.getPrompt(i));
    	String msgInput= getResponse();
    	if(!form.checkInput(i, msgInput)) {
    		displayError("Invalid input");
    	}else {
    		msg[i]= msgInput;
    	}
    }
    return msg;
  }

}
