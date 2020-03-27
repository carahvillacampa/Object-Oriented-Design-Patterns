package ui;

final class UIFormMenuBuilderConcrete extends UIFormMenuBuilderAbstract implements UIFactoryInterface, UIFormMenuBuilderInterface{

	public UIFormMenuBuilderConcrete() {
		super();
	}

	@Override
	public void add(String prompt, UIMenuActionInterface action) {
		// TODO Auto-generated method stub
		if (null == action)
			throw new IllegalArgumentException();
		_menu.add(new Pair(prompt, action));
		
	}

	@Override
	public void add(String prompt, UIFormTest test) {
		// TODO Auto-generated method stub
		_menu.add(new Pair(prompt, test));
		
	}

}
