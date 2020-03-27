package source.shop.command;

public class CommandHistoryFactory {
  //static CommandHistory comHistObj;
  
  private CommandHistoryFactory() {}
  
  static public CommandHistory newCommandHistory() { 
	  //comHistObj= new CommandHistoryObj();
	  //return comHistObj;
	  
	  return new CommandHistoryObj();
  }
}
