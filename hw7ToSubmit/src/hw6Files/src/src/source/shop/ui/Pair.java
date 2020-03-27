package hw6Files.src.src.source.shop.ui;

/**
 * Generic class pair which is used in the classes UIMenu and UIForm
 * 
 * @author carahvillacampa
 */
final class Pair<T>{
    String prompt;
    UIFormTest test;
    UIMenuActionInterface action;

    /**
     * pair class constructor 
     * @param thePrompt
     * @param theTest
     */
    Pair(String thePrompt, T theTest) {
      this.prompt = thePrompt;
      if(theTest instanceof UIMenuActionInterface) {
    	  action = (UIMenuActionInterface) theTest;
      }
      else if(theTest instanceof UIFormTest) {
    	  test= (UIFormTest) theTest;
      }
      else 
    	  throw new IllegalArgumentException();
        
    }
    
  }
