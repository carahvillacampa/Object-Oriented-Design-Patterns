package shop.main;

public enum States{
	  EXITED(0), EXIT(1),START(2),NUMOFSTATES(10);
	
	  private int value;
	  private States(final int value) {
	        this.value = value;
	    }
	  public int getValue() {
	       return this.value;
	  }
}
