package monkey.nn2.Utils;

import monkey.nn2.Exceptions.IllegalLength;

public class Basic {
	
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
	public static Matrix<Float> dot(Matrix<Float> a, Matrix<Float> b) {
		
		int aColumn = a.getSize()[1], aRow = a.getSize()[0];
		int bRow = b.getSize()[0], bColumn = b.getSize()[1];
		
		if (aColumn !=bRow)
			throw new IllegalLength("Invalid Matrices " + aColumn + " != " + bRow);
		
		
		Matrix<Float> out = new Matrix<Float>(new int[] {aRow, bColumn});
		
		for (int i = 0; i < aRow; i++) 
		    for (int j = 0; j < bColumn; j++) {
		    	Float oV = out.get(new int[] {i, j});
		    	
		    	oV = 0f;
		        for (int k = 0; k < bRow; k++) {
		        	Float aV = a.get(new int[] {i, k});
		        	Float bV = b.get(new int[] {k, j});
		        	
		            oV += aV * bV;
		        }
		        
		        out.set(new int[] {i, j}, oV);
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
	public static Vector<Float> converge(Vector<Float> a, Vector<Float> b) {
		int aLen = a.getSize()[0];
		int bLen = b.getSize()[0];
		
		if (aLen != bLen)
			throw new IllegalLength("Invalid Vectors " + aLen + " != " + bLen);
		
		Vector<Float> out = a;
		
		for (int i = 0; i < a.getSize()[0]; i++) {
			Float oV = out.get(new int[] {i});
			Float bV = b.get(new int[] {i});
			
			oV += bV;
		}
		return out;
	}
	
	
	//
	//
	
	public static void printArray(Vector<Float> x) {
		Float[] a = x.dump();
		
		for (int i = 0; i < a.length; i++) {
				System.out.print(a[i] + " ");
		}
		System.out.println();
	}
}
