package shop.main;

import ui.UIFormTest;

/**
 * This is using lambda expressions 
 * @author carahvillacampa
 *
 */
public enum UIFormTests{
	/**
	 * returns true if parsed integer using input is between 1800 and 5000
	 * else, it throws a number format exception
	 */
	    YearTest( new UIFormTest() {

			@Override
			public boolean run(String input) {
				// TODO Auto-generated method stub
				try {
					int x= Integer.parseInt(input);
					return x > 1800 && x < 5000;
				}catch(NumberFormatException e){
					return false;
				}
				
			}
	    	
	    }),
	    /**
	     * returns true of input is actually a number.
	     * parseInt is from the Integer built in library,
	     * otherwise, it'll thow a numberformatexception
	     */
	    NumTest (new UIFormTest() {

			@Override
			public boolean run(String input) {
				// TODO Auto-generated method stub
				try {
					int x= Integer.parseInt(input);
					return true;
				}catch(NumberFormatException e) {
					return false;
				}
				
			
			}
	    	
	    }
	    ),
	    StringTest (new UIFormTest() {

			@Override
			public boolean run(String input) {
				// TODO Auto-generated method stub
				return ! "".equals(input.trim());
			}
	    	
	    }
	    
	    );
	  
	    private final UIFormTest testToDo;
	    public UIFormTest getTest(){
	    	return testToDo;
	    	}
	    UIFormTests(UIFormTest startTest){
	      this.testToDo = startTest;
	    }
}