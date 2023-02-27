package monkey.nn2.Activators;

// Identity activator passes through without any modifications
public class Identity extends Activator {
	
	@Override
	public Float calc(Float x) {
		return x; // Range [-R, R]
	}

	@Override
	public Float prime(Float x) {
		return x;
	}

	@Override
	public String getName() {
		return "Identity";
	}
	
}
