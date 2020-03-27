package ui;

public interface UIFormMenuBuilderInterface {
	UIFormMenuConcrete toUIFormMenu(String heading);
	void add(String prompt, UIMenuActionInterface action);
	void add(String prompt, UIFormTest test);
	
	
	

}
