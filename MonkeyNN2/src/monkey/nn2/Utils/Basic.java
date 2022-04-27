package monkey.nn2.Utils;

import monkey.nn2.Exceptions.IllegalLength;

public class Basic {
	
	/*
	 * Derivative of da/dc * dw/da * da/db
	 * 
	 * @param Calculated data of w * n + b
	 */
	public static Float deriv(Float x) {
		return x * (1 - x);
	}
	
	/*
	 * Rotate vector
	 * 
	 * @param 2d vector to turn into 3d one cell matrix
	 */
	public static Float[][] transpose(Float[] x) {
		Float[][] out = new Float[x.length][1];
		
		for (int i = 0; i < x.length; i++)
			out[i][1] = x[i];
		
		return out;
	}
	
	/*
	 * Rotate Matrix
	 * 
	 * @param 2d vector to turn into 3d one cell matrix
	 */
	public static Float[][] transpose(Float[][] x) {
		Float[][] out = new Float[x[0].length][x.length];
		
		for (int i = 0; i < x.length; i++)
			for (int j = 0; j < x[0].length; j++)
				out[i][j] = x[j][i];
		
		return out;
	}
	
	/*
	 * Matrices Dot Product
	 * 
	 * @param First Matrix
	 * @param Second Matrix
	 */
	public static Float[][] dot(Float[][] a, Float[][] b) {
		if (a[0].length != b.length)
			throw new IllegalLength("Invalid Matrices " + a[0].length + " != " + b.length);
		
		Float[][] out = new Float[a.length][b[0].length];
		
		for (int i = 0; i < out.length; i++) 
		    for (int j = 0; j < out[0].length; j++) {
		    	out[i][j] = 0f;
		        for (int k = 0; k < b.length; k++) 
		            out[i][j] += a[i][k] * b[k][j];
		    }

		return out;
	}
	
	public static String iAtoS(int[] x) {
		if (x == null)
			return "None";
		String out = "{";
		for (int i = 0; i < x.length - 1; i++)
			out += x[i] + ",";
		return out + " " + x[x.length - 1] + "}";
	}
	
	
	/*
	 * Add two arrays
	 * 
	 * @param Array A
	 * @param Array B
	 */
	public static Float[] converge(Float[] a, Float[] b) {
		if (a.length != b.length)
			throw new IllegalLength("Invalid Vectors " + a.length + " != " + b.length);
		Float[] out = a;
		for (int i = 0; i < a.length; i++)
			out[i] += b[i];
		return out;
	}
	
	
	//
	//
	
	public static void printArray(Float[] x) {
		for (int i = 0; i < x.length; i++) {
				System.out.print(x[i] + " ");
		}
		System.out.println();
	}
}
