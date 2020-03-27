package ui;
import java.util.ArrayList;
import java.util.List;

abstract class UIFormMenuBuilderAbstract {
	List<Pair> _menu;
	public UIFormMenuBuilderAbstract() {
		_menu = new ArrayList<Pair>();
	}
	
	public UIFormMenuConcrete toUIFormMenu(String heading) {
		if (null == heading)
			throw new IllegalArgumentException();
		if (_menu.size() < 1)
			throw new IllegalStateException();
		Pair[] array = new Pair[_menu.size()];
		for (int i = 0; i < _menu.size(); i++)
			array[i] = (_menu.get(i));
		return new UIFormMenuConcrete(heading, array);
	}

	

}
