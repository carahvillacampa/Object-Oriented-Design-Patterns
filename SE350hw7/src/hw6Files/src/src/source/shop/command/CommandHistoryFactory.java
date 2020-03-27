package hw6Files.src.src.source.shop.command;

public class CommandHistoryFactory {
  //static CommandHistory comHistObj;
  
  private CommandHistoryFactory() {}
  
  /**
   * @return a new CommandHistoryObject
   */
  static public CommandHistory newCommandHistory() { 
	  //comHistObj= new CommandHistoryObj();
	  //return comHistObj;
	  
	  return new CommandHistoryObj();
  }
}
