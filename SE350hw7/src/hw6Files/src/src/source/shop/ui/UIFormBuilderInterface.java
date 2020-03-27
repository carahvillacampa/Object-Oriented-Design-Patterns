package hw6Files.src.src.source.shop.ui;

public interface UIFormBuilderInterface {
	UIForm toUIForm(String heading);
	void add(String prompt, UIFormTest action);

}
