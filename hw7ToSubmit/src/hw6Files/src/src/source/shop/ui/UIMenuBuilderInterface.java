package hw6Files.src.src.source.shop.ui;

public interface UIMenuBuilderInterface {
	UIMenu toUIMenu(String heading);
	void add(String prompt, UIMenuActionInterface action);

}
