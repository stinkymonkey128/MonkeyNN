package monkey.nn2.LossFunction;

import monkey.nn2.Exceptions.IllegalLength;

public class MAE implements LossFunction {
	/*
	 * Mean Absolute Error
	 * 
	 * @param Result from propagation
	 * @param Expected from goal hidden = goal + optimizer
	 */
	@Override
	public Float calc(Float[] actual, Float[] expected) throws IllegalLength {
		if (actual.length != expected.length)
			throw new IllegalLength("Arrays are unequal (MAE)");
		Float out = 0f;
		for (int i = 0; i < actual.length; i++)
			out += (float) Math.abs(actual[i] - expected[i]);
		return out;
	}

	@Override
	public Float calc(Float actual, Float expected) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "Mean Absolute Error";
	}

	@Override
	public Float prime(Float actual, Float expected) {
		return null;
	}

}
