package shop.main;

import ui.UI;
import ui.UIError;
import ui.UIFactory;
import ui.UIFormMenuBuilderInterface;
import ui.UIFormMenuBuilderInterface;
import ui.UIFormMenuInterface;
import data.Inventory;

class Control {
	private UIFormMenuInterface[] _menus;
	private static MenuOptions menuOptions;
	private static UIFormMenuInterface _getVideoForm;
	private static Inventory _inventory;
	private static UI _ui;
	private static States _STATE; //enums are static, they should be accessed in a static way
	private static UIFactory uiFactory = new UIFactory();
	private static UIFormTests _STR_Test;
	private static UIFormTests _YR_Test;
	private static UIFormTests _NUM_Test;
	
	Control(Inventory inventory, UI ui) {
	    _inventory = inventory;
	    _ui = ui;

	    _menus = new UIFormMenuInterface[States.NUMOFSTATES.getValue()];
	    _STATE = States.START;
	    startProgram(States.START);
	    exitProgram(States.EXIT);
	    //initialize enums in UIFormTests
	    _YR_Test= UIFormTests.YearTest;
	    _STR_Test= UIFormTests.StringTest;
	    _NUM_Test= UIFormTests.NumTest;
	    
	    UIFormMenuBuilderInterface uiformbuilder= (UIFormMenuBuilderInterface) uiFactory.ui("UIFMB", null, null); //UIFormBuilder uiformbuilder = new UIFormBuilder();
	    uiformbuilder.add("Title", UIFormTests.StringTest.getTest());
	    uiformbuilder.add("Year", UIFormTests.YearTest.getTest());
	    uiformbuilder.add("Director", UIFormTests.StringTest.getTest());
	    _getVideoForm = uiformbuilder.toUIFormMenu("Enter Video");
	    new MenuOptions.MenuOptionsConstructor(_getVideoForm,_inventory,_ui,_STATE);
	  	}
  	
		  void run() {
		    try {
		      while (_STATE != States.EXITED) {
		        _ui.processMenu(_menus[_STATE.getValue()]);
		        _STATE= MenuOptions.getState();
		      }
		    } catch (UIError e) {
		      _ui.displayError("UI is closed");
		    }
		  }
  
	    private void startProgram(States start) {
	    UIFormMenuBuilderInterface toMenuBuilder=(UIFormMenuBuilderInterface) uiFactory.ui("UIFMB", null, null);
	    for (MenuOptions options: MenuOptions.values()) {
	    	if(options.toString()== "YES"|| options.toString()== "NO") {
	    		continue;
	    	}else {
	    		toMenuBuilder.add(options.toString(), options.getMenuAction());
	    	}
	    }
	    _menus[start.getValue()] = toMenuBuilder.toUIFormMenu("Bob's Video");
	  }
  
	  private void exitProgram(States start) {
	    UIFormMenuBuilderInterface toMenuBuilder1=(UIFormMenuBuilderInterface) uiFactory.ui("UIFMB", null, null);
	   
	    toMenuBuilder1.add("Default", MenuOptions.DEFAULT_ENTRY.getMenuAction());
	    toMenuBuilder1.add("Yes", MenuOptions.YES.getMenuAction());
	    toMenuBuilder1.add("No", MenuOptions.NO.getMenuAction());
	    
	    
	    _menus[start.getValue()] = toMenuBuilder1.toUIFormMenu("Are you sure you want to exit?");
	  }
}
