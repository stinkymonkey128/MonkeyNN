package monkey.nn2.Activators;

import monkey.nn2.Utils.*;

public abstract class Activator {
	public abstract Float calc(Float x);
	
	/*
	 * Derivative calculation using already calculated product
	 * 
	 * @param calculated value
	 */
	public abstract Float prime(Float x);
	
	public Matrix<Float> calc(Matrix<Float> x) {
		int xRow = x.getSize()[0];
		int xCol = x.getSize()[1];
		
		Matrix<Float> out = x;
		
		for (int i = 0; i < xRow; i++)
			for (int j = 0; j < xCol; j++)
				out.dump()[i][j] = calc(out.dump()[i][j]);
		
		return out;
	}
	
	public Vector<Float> calc(Vector<Float> x) {
		Vector<Float> out = x;
		
		for (int i = 0; i < out.getSize()[0]; i++) {
			Float outX = out.get(new int[] {i});
			
			outX = calc(outX);
		}
			
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
