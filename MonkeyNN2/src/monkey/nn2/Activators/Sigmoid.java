package monkey.nn2.Activators;

public class Sigmoid extends Activator {

	@Override
	public Float calc(Float x) {
		return 1f / (1f + (float) Math.exp(-x)); // Range [0, 1]
	}

	@Override
	public Float prime(Float x) {
		return x * (x - 1);
	}
	
	@Override
	public String getName() {
		return "Sigmoid";
	}
}
