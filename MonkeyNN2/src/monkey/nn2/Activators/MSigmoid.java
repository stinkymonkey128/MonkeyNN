package monkey.nn2.Activators;

// Modified Sigmoid activator covers negative and positive range
public class MSigmoid extends Activator{

	@Override
	public Float calc(Float x) {
		return 2f / (1f + (float) Math.exp(-x)) - 1f; // Range [-1, 1]
	}

	@Override
	public Float prime(Float x) {
		return -((x * x) - 1f) / 2f;
	}
	
	@Override
	public String getName() {
		return "MSigmoid";
	}
}
