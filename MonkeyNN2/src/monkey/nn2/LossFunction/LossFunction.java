package monkey.nn2.LossFunction;

import java.io.Serializable;

import monkey.nn2.Exceptions.IllegalLength;

public abstract class LossFunction implements Serializable {
	private static final long serialVersionUID = 8314133331079874530L;

	public abstract Float calc(Float[] actual, Float[] expected) throws IllegalLength;
	public abstract Float calc(Float actual, Float expected);
	
	public abstract Float prime(Float actual, Float expected);
	
	public abstract String getName();
}	
