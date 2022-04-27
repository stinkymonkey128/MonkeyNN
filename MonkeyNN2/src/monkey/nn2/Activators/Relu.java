package monkey.nn2.Activators;

public class Relu extends Activator {

	@Override
	public Float calc(Float x) {
		return Math.max(x, 0); // Range [0, R]
	}

	@Override
	public Float prime(Float x) {
		return x > 0 ? 1f : 0;
	}
	
	@Override
	public String getName() {
		return "Relu";
	}
}
