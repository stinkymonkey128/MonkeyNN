package monkey.nn2.Activators;

public class TanH extends Activator {

	@Override
	public Float calc(Float x) {
		return (float) Math.tanh(x); // range [0, 1]
	}

	@Override
	public Float prime(Float x) {
		return 1 - x * x;
	}
	
	@Override
	public String getName() {
		return "TanH";
	}
}
