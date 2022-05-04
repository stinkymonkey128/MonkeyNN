package monkey.nn2.Activators;

public abstract class Activator {
	public abstract Float calc(Float x);
	
	/*
	 * Derivative calculation using already calculated product
	 * 
	 * @param calculated value
	 */
	public abstract Float prime(Float x);
	
	public Float[][] calc(Float[][] x) {
		Float[][] out = new Float[x.length][x[0].length];
		
		for (int i = 0; i < x.length; i++)
			for (int j = 0; j < x[0].length; j++)
				out[i][j] = calc(x[i][j]);
		
		return out;
	}
	
	public Float[] calc(Float[] x) {
		Float[] out = new Float[x.length];
		
		for (int i = 0; i < x.length; i++)
				out[i]= calc(x[i]);
		
		return out;
	}
	
	public abstract String getName();
}
