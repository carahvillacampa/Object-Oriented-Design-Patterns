package command;
import java.util.EmptyStackException;
import java.util.Stack;


final class CommandHistoryObj implements CommandHistory {
  Stack<UndoableCommand> _undoStack = new Stack<UndoableCommand>();
  Stack<UndoableCommand> _redoStack = new Stack<UndoableCommand>();
  
  RerunnableCommand _undoCmd = new RerunnableCommand () {
      public boolean run () {
          boolean result = !_undoStack.empty();
          try {
        	  if (result) {
            	  UndoableCommand undone=_undoStack.pop();
    			  //undone.undo();
    			  _redoStack.push(undone).undo();; 
            	  return true;
              }
          }
          catch(EmptyStackException e) {};
          return result;
      }
    };
  /**
   * creates a new rerunnable command and implements run method
   */
  RerunnableCommand _redoCmd = new RerunnableCommand () {
	  /**
	   * @return boolean if the stack that was added was popped,
	   * pushed to the redone stack and redid
	   */
      public boolean run () {
    	  boolean result = !_redoStack.empty();
    	  try {
    		  if (result) {
            	  UndoableCommand redone=_redoStack.pop();
    			  //redone.redo();
    			  _undoStack.push(redone).redo(); 
    			  return true;
              }
    	  }
    	  catch(EmptyStackException e) {};
    	  return false;
          
       }
    };

    /**
     * Add command <code>undoable</code> and clear <code>redoable</code>.
     * @param cmd the command to be run.
     */
  public void add(UndoableCommand cmd) {
	 _undoStack.add(cmd);
	 _redoStack.clear();
  }
  
  /**
   * Returns a <code>RerunnableCommand</code> that, when run does the following:
   * Pop command from <code>undoable</code>, undo it, then push it
   * onto <code>redoable</code>.
   * @throws EmptyStackException if there is no undoable command.
   */
  public RerunnableCommand getUndo() {
	  return _undoCmd;	  
	  
  }
  
  /**
   * Returns a <code>RerunnableCommand</code> that, when run does the following:
   * Pop command from <code>redoable</code>, redo it, then push it
   * onto <code>undoable</code>.
   * @throws EmptyStackException if there is no redoable command.
   */
  public RerunnableCommand getRedo() {
	  return _redoCmd;
    
  }
  
  // For testing
  UndoableCommand topUndoCommand() {
    if (_undoStack.empty())
      return null;
    else
      return _undoStack.peek();
  }
  // For testing
  UndoableCommand topRedoCommand() {
    if (_redoStack.empty())
      return null;
    else
      return _redoStack.peek();
  }
}
