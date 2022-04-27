package monkey.nn2.Activators;

public class LeakyRelu extends Activator {

	@Override
	public Float calc(Float x) {
		return Math.max(.01f * x, x);
	}

	@Override
	public Float prime(Float x) {
		return x > 0 ? 1 : .01f;
	}
	
	@Override
	public String getName() {
		return "LeakyRelu";
	}
}
