package monkey.nn2.Utils;

public enum Activators {
	// Activator Registry Most COMMON
	
	Identity("Identity"),
	Sigmoid("Sigmoid"),
	MSigmoid("MSigmoid"),
	TanH("TanH"),
	LeakyRelu("LeakyRelu"),
	Relu("Relu");
	
	private String NAME;
	
	Activators(String name) {
		NAME = name;
	}
	
	public String getName() {return NAME;}
	
	/*
	 * Returns a defined Activator from a String name
	 * 
	 * @param String name of the activator
	 */
	public static Activators fromName(String name) {
		for (Activators x : Activators.values())
			if (x.getName().equals(name))
				return x;
		return null;
	}
	
	/*
	 * Calculate using corresponding function
	 * 
	 * @param float input
	 */
	public Float calc(float x) {
		switch (this) {
		case Relu:
			return Math.max(x, 0); // Range [0, R]
		case Sigmoid:
			return 1f / (1f + (float) Math.exp(-x)); // Range [0, 1]
		case MSigmoid:
			return 2f / (1f + (float) Math.exp(-x)) - 1f; // Range [-1, 1]
		case TanH:
			return (float) Math.tanh(x); // range [0, 1]
		case LeakyRelu:
			return Math.max(.01f * x, x);
		}
		return x;
	}
}