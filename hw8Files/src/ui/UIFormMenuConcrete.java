package ui;

final class UIFormMenuConcrete extends CommonCommandsAbstract implements UIFormMenuInterface,UIFactoryInterface{
	private final String _heading="";
	private final Pair[] _pair;
	
	UIFormMenuConcrete(String heading, Pair[] pair){
		super(heading, pair);
		this._pair= pair;
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return _pair.length;
	}

	@Override
	public String getHeading() {
		// TODO Auto-generated method stub
		return _heading;
	}

	@Override
	public String getPrompt(int i) {
		// TODO Auto-generated method stub
		return _pair[i].prompt;
	}

	@Override
	public boolean checkInput(int i, String input) {
		// TODO Auto-generated method stub
		if( _pair[i]== null) {
			return true;
		}
		return _pair[i].test.run(input);
	}

	@Override
	public void runAction(int i) {
		// TODO Auto-generated method stub
		_pair[i].action.run();
	}

}
