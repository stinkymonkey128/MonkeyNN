package monkey.nn2.LossFunction;

import monkey.nn2.Exceptions.IllegalLength;

public class MSE extends LossFunction {
	private static final long serialVersionUID = 4596033278273889594L;

	/* 
	 * Mean Squared Error
	 * 
	 * @param Result from propagation
	 * @param Expected from goal hidden = goal + optimizer
	 */
	@Override
	public Float calc(Float[] actual, Float[] expected) throws IllegalLength {
		if (actual.length != expected.length)
			throw new IllegalLength("Arrays are unequal " + actual.length + " != " + expected.length);
		Float out = 0f;
		for (int i = 0; i < actual.length; i++)
			out += (float) Math.pow(actual[i] - expected[i], 2);
		return out;
	}
	
	@Override
	public Float calc(Float actual, Float expected) {
		return (float) Math.pow(actual - expected, 2);
	}

	@Override
	public String getName() {
		return "Mean Squared Error";
	}

	@Override
	public Float prime(Float actual, Float expected) {
		return actual - expected;
	}
}
