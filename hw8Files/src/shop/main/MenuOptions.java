package shop.main;
import java.util.Iterator;
import command.Command;
import data.Data;
import data.Inventory;
import data.Record;
import data.Video;
import ui.UI;
import ui.UIFactory;
import ui.UIFactoryInterface;
import ui.UIFormMenuBuilderInterface;
import ui.UIFormMenuInterface;
import ui.UIMenuActionInterface;

public enum MenuOptions {

	 DEFAULT_ENTRY(new UIMenuActionInterface() {
			  public void run() {
		          _ui.displayError("doh!");
		      }
	  }),
	  ADD_OR_REMOVE_VIDEOS(new UIMenuActionInterface() {
			        public void run() {
			        	String[] res = _ui.processForm(_getVideoForm);
			            Video v = Data.newVideo(res[0], Integer.parseInt(res[1]), res[2]);

			            UIFormMenuBuilderInterface f=(UIFormMenuBuilderInterface) UIFactory.ui("UIFMB", null, null);
			            f.add("Number of copies to add/remove", UIFormTests.NumTest.getTest());
			            String[] res2 = _ui.processForm(f.toUIFormMenu(""));
			                                               
			            Command c = Data.newAddCmd(_inventory, v, Integer.parseInt(res2[0]));
			            if (! c.run()) {
			              _ui.displayError("Command failed");
			            }
			            else {
			            	_ui.displayMessage("Video successfully added/ removed");
			            }
			        }
			      }
	  ),
	  CHECKIN_VIDEO (new UIMenuActionInterface() {
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
	  CHECKOUT_VIDEO(new UIMenuActionInterface() {
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
	  PRINT_THE_INVENTORY(new UIMenuActionInterface() {
		  public void run() {
	          _ui.displayMessage(_inventory.toString());
	        }
	  }),
	  CLEAR_INVENTORY(new UIMenuActionInterface() {
		  public void run() {
	          if (!Data.newClearCmd(_inventory).run()) {
	            _ui.displayError("Command failed");
	          }else {
	        	  _ui.displayMessage("Inventory Cleared");
	          }
	        }
	  }),
	  UNDO_COMMAND(new UIMenuActionInterface() {
		  public void run() {
	          if (!Data.newUndoCmd(_inventory).run()) {
	            _ui.displayError("Command failed");
	          }else {
	        	  _ui.displayMessage("undone sucessful");
	          }
	        }
	  }),
	  REDO_COMMAND(new UIMenuActionInterface() {
		  public void run() {
	          if (!Data.newRedoCmd(_inventory).run()) {
	            _ui.displayError("Command failed");
	          }
	          else {
	        	  _ui.displayMessage("redo sucessful");
	          }
	        }
	  }),
	  PRINT_TOPTEN_ALL_TIME_RENTALS(new UIMenuActionInterface() {
		  public void run() {
			  if (_inventory.size() > 0){
	                Iterator<Record> iter = _inventory.iterator(new java.util.Comparator<Record>(){
	                    public int compare(Record rec1, Record rec2){
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
	  EXIT(new UIMenuActionInterface() {
		  public void run() {
	          states = States.EXIT;
	        }
	  }),
	  FILL_CONTENTS (new UIMenuActionInterface() {
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
	          
	          _ui.displayMessage("inventory filled with contents");
	        }
		  
	  }),
	
	  YES(new UIMenuActionInterface() {
	        public void run() {
	        	  _ui.displayMessage("Program is closed");
		          states = States.EXITED;
	        }
	  }),
	  NO(new UIMenuActionInterface() {
	        public void run() {
	        	  _ui.displayMessage("Program will restart");
		          states = States.START;
	        }
	  });
	  
		      
		private static UI _ui;
		private static UIFormMenuInterface _getVideoForm;
		private static Inventory _inventory;
		private static States states;
		private static UIFactory  uiFactory= new UIFactory();
	    private UIMenuActionInterface optionChosen;
	  
	  
	  MenuOptions(UIMenuActionInterface menuAction){
		  this.optionChosen= menuAction;
	  }
	  
	  public static States getState() {
		  return states;
	  }
	  
	  public void setState(States something) {
		  this.states= something;
	  }
	  
	  public UI setUI() {
		  return _ui;
	  }
	  
	  private UIFormMenuInterface getVidForm() {
		  return _getVideoForm;
	  }
	  
	  private Inventory getInventory() {
		  return _inventory;
	  }
	  
	  public UIMenuActionInterface getMenuAction() {
		  return optionChosen;
	  }

	  static class MenuOptionsConstructor{
		  public MenuOptionsConstructor( UIFormMenuInterface menuInterface,Inventory someInventory,  UI someUI,States someState) {
			  _ui= someUI;
			  _inventory= someInventory;
			  _getVideoForm= menuInterface;
			  states= someState;
		  }
	  }
	  
   
	  
}
