package hw6Files.src.src.source.shop.main;

import hw6Files.src.src.source.shop.ui.UI;
import hw6Files.src.src.source.shop.ui.UIMenuActionInterface;
import hw6Files.src.src.source.shop.ui.UIMenuBuilderInterface;
import hw6Files.src.src.source.shop.ui.UIMenuInterface;
import hw6Files.src.src.source.shop.ui.UIError;
import hw6Files.src.src.source.shop.ui.UIFactory;
import hw6Files.src.src.source.shop.ui.UIFormBuilderInterface;
import hw6Files.src.src.source.shop.ui.UIFormTest;
import hw6Files.src.src.source.shop.ui.UIFormInterface;
import hw6Files.src.src.source.shop.data.Data;
import hw6Files.src.src.source.shop.data.Inventory;
import hw6Files.src.src.source.shop.data.Video;
import hw6Files.src.src.source.shop.data.Record;
import hw6Files.src.src.source.shop.command.Command;
import java.util.Iterator;
import java.util.Comparator;

class Control {
	private UIMenuInterface[] _menus;
	private static int _state;

	private static UIFormInterface _getVideoForm;
	private static Inventory _inventory;
	private static UI _ui;
	//private static UIFactory uiFactory = new UIFactory();
	
	Control(Inventory inventory, UI ui) {
	    _inventory = inventory;
	    _ui = ui;

	    _menus = new UIMenuInterface[states.NUMSTATES.getValue()];
	    _state = states.START.getValue();
	    addSTART(states.START.getValue());
	    addEXIT(states.EXIT.getValue());
	    
	    UIFormBuilderInterface uiformbuilder= (UIFormBuilderInterface) UIFactory.ui("UIFB", null, null); //UIFormBuilder uiformbuilder = new UIFormBuilder();
	    uiformbuilder.add("Title", UIFormTests.StringTest.getTest());
	    uiformbuilder.add("Year", UIFormTests.YearTest.getTest());
	    uiformbuilder.add("Director", UIFormTests.StringTest.getTest());
	    _getVideoForm = uiformbuilder.toUIForm("Enter Video");
	  }
	/**
	 * - enums aren't objects, they can't be created. They can only be accessed 
	 * through the enum name. Enums also have built in arrays that users could use
	 * @author carahvillacampa
	 *
	 */
  public enum states{
	  EXITED(0),
	  EXIT(1),
	  START(2),
	  NUMSTATES(10);
	  private int value;
	  
	  /**
	   * private constructor of an enum
	   * @param value
	   */
	  private states(final int value) {
	        this.value = value;
	    }
	  public int getValue() {
	       return this.value;
	  }
  }
  
  /**
   * Another type of enum but using lambda expressions
   * @author carahvillacampa
   *
   */
  public enum UIFormTests{
	    YearTest( startTest->{
	        try {
	          int year = Integer.parseInt(startTest);
	          return year > 1800 && year < 5000;
	        } catch (NumberFormatException e) {
	          return false;
	        }
	      }
	    ),
	    NumTest (startTest ->{
	    	try {
	            Integer.parseInt(startTest);
	            return true;
	          } catch (NumberFormatException e) {
	            return false;
	          }
	    }
	    ),
	    StringTest (startTest ->
	    ! "".equals(startTest.trim())
	    );
	  
	    private final UIFormTest testToDo;
	    public UIFormTest getTest(){
	    	return testToDo;
	    	}
	    UIFormTests(UIFormTest startTest){
	      this.testToDo = startTest;
	    }
  }
  	
  void run() {
    try {
      while (_state != states.EXITED.getValue()) {
        _ui.processMenu(_menus[_state]);
      }
    } catch (UIError e) {
      _ui.displayError("UI is closed");
    }
  }
  
  public enum MenuOptions {
	  Default(new UIMenuActionInterface() {
			  public void run() {
		          _ui.displayError("doh!");
		        }
	  }),
	  AddOrRemoveVideos(new UIMenuActionInterface() {
			        public void run() {
			        	String[] result1 = _ui.processForm(_getVideoForm);
			            Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

			            UIFormBuilderInterface f=(UIFormBuilderInterface) UIFactory.ui("UIFB", null, null);
			            f.add("Number of copies to add/remove", UIFormTests.NumTest.getTest());
			            String[] result2 = _ui.processForm(f.toUIForm(""));
			                                               
			            Command c = Data.newAddCmd(_inventory, v, Integer.parseInt(result2[0]));
			            if (! c.run()) {
			              _ui.displayError("Command failed");
			            }
			            else {
			            	_ui.displayMessage("Video successfully added/ removed");
			            }
			        }
			      }
		  ),
	  CheckIn (new UIMenuActionInterface() {
		  public void run() {
        	String[] result1 = _ui.processForm(_getVideoForm);
            Video video = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
            Command c = Data.newInCmd(_inventory, video);
            if (! c.run()) {
              _ui.displayError("Failed command");
            }
            else {
          	  _ui.displayMessage("Sucessfully checked in video");
            }
		  }
	  	}
		  ),
	  CheckOut(new UIMenuActionInterface() {
		  public void run() {
	        	String[] result1 = _ui.processForm(_getVideoForm);
	        	Video v1 = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
	        	Command c = Data.newOutCmd(_inventory, v1);
	        	if(!c.run()) {
	        		_ui.displayError("Failed command");
	        	}else {
	            	  _ui.displayMessage("Sucessfully checked out video");
	            }
	        }
	      }
	  ),
	  PrintInventory(new UIMenuActionInterface() {
		  public void run() {
	          _ui.displayMessage(_inventory.toString());
	        }
	  }),
	  ClearInventory(new UIMenuActionInterface() {
		  public void run() {
	          if (!Data.newClearCmd(_inventory).run()) {
	            _ui.displayError("Command failed");
	          }
	        }
	  }),
	  Undo(new UIMenuActionInterface() {
		  public void run() {
	          if (!Data.newUndoCmd(_inventory).run()) {
	            _ui.displayError("Command failed");
	          }
	        }
	  }),
	  Redo(new UIMenuActionInterface() {
		  public void run() {
	          if (!Data.newRedoCmd(_inventory).run()) {
	            _ui.displayError("Command failed");
	          }
	        }
	  }),
	  PrintTopTen(new UIMenuActionInterface() {
		  public void run() {
			  if (_inventory.size() > 0){
	                Iterator<Record> iter = _inventory.iterator(new java.util.Comparator<Record>(){
	                    public int compare(Record rec1, Record rec2)
	                    {
	                        return rec2.numOut() - rec1.numOut();
	                    }
	                });

	                StringBuilder b = new StringBuilder();
	                int counter = 1;
	                b.append("top ten all time rentals in order: \n");
	                while (iter.hasNext() && counter < 11){
	                	Record r = iter.next();
	                    b.append(" " + r.video().title() + " [" + r.numOwned() + "]\n");
	                    counter++;
	                }
	                _ui.displayMessage(b.toString());
	            } else{
	                _ui.displayError("Empty inventory");
	            }    
	        }
	  }),
	  Exit(new UIMenuActionInterface() {
		  public void run() {
	          _state = states.EXIT.getValue();
	        }
	  }),
	  Fill_With_Contents (new UIMenuActionInterface() {
		  public void run() {
	          Data.newAddCmd(_inventory, Data.newVideo("a", 2000, "m"), 1).run();
	          Data.newAddCmd(_inventory, Data.newVideo("b", 2000, "m"), 2).run();
	          Data.newAddCmd(_inventory, Data.newVideo("c", 2000, "m"), 3).run();
	          Data.newAddCmd(_inventory, Data.newVideo("d", 2000, "m"), 4).run();
	          Data.newAddCmd(_inventory, Data.newVideo("e", 2000, "m"), 5).run();
	          Data.newAddCmd(_inventory, Data.newVideo("f", 2000, "m"), 6).run();
	          Data.newAddCmd(_inventory, Data.newVideo("g", 2000, "m"), 7).run();
	          Data.newAddCmd(_inventory, Data.newVideo("h", 2000, "m"), 8).run();
	          Data.newAddCmd(_inventory, Data.newVideo("i", 2000, "m"), 9).run();
	          Data.newAddCmd(_inventory, Data.newVideo("j", 2000, "m"), 10).run();
	          Data.newAddCmd(_inventory, Data.newVideo("k", 2000, "m"), 11).run();
	          Data.newAddCmd(_inventory, Data.newVideo("l", 2000, "m"), 12).run();
	          Data.newAddCmd(_inventory, Data.newVideo("m", 2000, "m"), 13).run();
	          Data.newAddCmd(_inventory, Data.newVideo("n", 2000, "m"), 14).run();
	          Data.newAddCmd(_inventory, Data.newVideo("o", 2000, "m"), 15).run();
	          Data.newAddCmd(_inventory, Data.newVideo("p", 2000, "m"), 16).run();
	          Data.newAddCmd(_inventory, Data.newVideo("q", 2000, "m"), 17).run();
	          Data.newAddCmd(_inventory, Data.newVideo("r", 2000, "m"), 18).run();
	          Data.newAddCmd(_inventory, Data.newVideo("s", 2000, "m"), 19).run();
	          Data.newAddCmd(_inventory, Data.newVideo("t", 2000, "m"), 20).run();
	          Data.newAddCmd(_inventory, Data.newVideo("u", 2000, "m"), 21).run();
	          Data.newAddCmd(_inventory, Data.newVideo("v", 2000, "m"), 22).run();
	          Data.newAddCmd(_inventory, Data.newVideo("w", 2000, "m"), 23).run();
	          Data.newAddCmd(_inventory, Data.newVideo("x", 2000, "m"), 24).run();
	          Data.newAddCmd(_inventory, Data.newVideo("y", 2000, "m"), 25).run();
	          Data.newAddCmd(_inventory, Data.newVideo("z", 2000, "m"), 26).run();
	        }
	  });
	  private UIMenuActionInterface optionChosen;
	  private MenuOptions(final UIMenuActionInterface optionChosen) {
		  this.optionChosen = optionChosen;
	  }
	  public UIMenuActionInterface getOption () {
		  return this.optionChosen;
	  }
	  
  }
  
  	/**
  	 * addSTART is a class that creates a menu 
  	 * How does this class create menus:
  	 * @param stateNum takes in stateNum and feeds it in the interface UIMenuInterface
  	 * UIMenuInterface has the access to the concrete class UIMenu.
  	 * The Builder has the capability of adding stuff to the actual menu through the
  	 * interface's add method.
  	 * 
  	 * Add method used is a built in method from UIMenuBuilder. UIMB uses it to build an actual menu
  	 * You type in a string of the prompt, and the type of enum from MenuOptions
  	 */
    private void addSTART(int stateNum) {
    UIMenuBuilderInterface m=(UIMenuBuilderInterface) UIFactory.ui("UIMB", null, null);
    m.add("Default", MenuOptions.Default.getOption());
    m.add("Add/Remove copies of a video", MenuOptions.AddOrRemoveVideos.getOption());
    m.add("Check in a video", MenuOptions.CheckIn.getOption());
    m.add("Check out a video", MenuOptions.CheckOut.getOption());
    m.add("Print the inventory", MenuOptions.PrintInventory.getOption());
    m.add("Clear the inventory", MenuOptions.ClearInventory.getOption());
    m.add("Undo", MenuOptions.Undo.getOption());
    m.add("Redo", MenuOptions.Redo.getOption());
    m.add("Print top ten all time rentals in order", MenuOptions.PrintTopTen.getOption());
    m.add("Exit", MenuOptions.Exit.getOption());
    m.add("Initialize with bogus contents", MenuOptions.Fill_With_Contents.getOption());
    _menus[stateNum] = m.toUIMenu("Bob's Video");
    }
  
   private void addEXIT(int stateNum) {
    UIMenuBuilderInterface m=(UIMenuBuilderInterface) UIFactory.ui("UIMB", null, null);  
    m.add("Default", new UIMenuActionInterface() { public void run() {} });
    m.add("Yes",
      new UIMenuActionInterface() {
    	/** 
    	 * this method has the variable _state which is part of the 
    	 * constructor of control. It gets that value based on the enum states and passes it
    	 * on to the variable _state
    	 */
        public void run() {
          _state = states.EXITED.getValue();
        }
      });
    m.add("No",
      new UIMenuActionInterface() {
        public void run() {
          _state = states.START.getValue();
        }
      });
    
      _menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
  }
}
