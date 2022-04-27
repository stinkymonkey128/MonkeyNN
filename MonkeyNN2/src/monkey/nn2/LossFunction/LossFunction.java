package monkey.nn2.LossFunction;

import monkey.nn2.Exceptions.IllegalLength;

public interface LossFunction {
	public abstract Float calc(Float[] actual, Float[] expected) throws IllegalLength;
	public abstract Float calc(Float actual, Float expected);
	
	public abstract Float prime(Float actual, Float expected);
	
	public abstract String getName();
}	
