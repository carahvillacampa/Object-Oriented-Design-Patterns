package ui;

import java.io.IOException;

import ui.Pair;

abstract class CommonCommandsAbstract {
	  String heading;
	  Pair[] _Pair;
	  public CommonCommandsAbstract(String _heading, Pair[] pair) {
		this.heading = _heading;
		this._Pair = pair;
	  }
	  
	  public int size() {
		  return _Pair.length;
	  }
	  public String getPrompt(int i) {
		  return _Pair[i].prompt;
	  }
	  public String getHeading() {
		  return heading;
	  }
	

}
