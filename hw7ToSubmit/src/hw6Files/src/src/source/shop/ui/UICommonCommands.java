package hw6Files.src.src.source.shop.ui;

abstract class UICommonCommands {
	String heading;
	Pair[] pair;

	UICommonCommands(String _heading, Pair[] pair) {
		this.heading = _heading;
		this.pair = pair;
	}

	public int size() {
		return pair.length;
	}

	public String getPrompt(int i) {
		return pair[i].prompt;
	}

	public String getHeading() {
		return heading;
	}

}
