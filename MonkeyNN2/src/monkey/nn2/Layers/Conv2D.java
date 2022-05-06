package monkey.nn2.Layers;

import monkey.nn2.Activators.Activator;
import monkey.nn2.Utils.*;

public class Conv2D extends Layer {
	private static final long serialVersionUID = -866424006190212993L;
	
	float[][][] filters;
	float[] bias;
	Activator activator;
	
	/*
	 * 
	 */
	public Conv2D(int filters, int kernelSize, int[] strides, String paddingStyle, Activator activator, String kernelInit, String biasInit) {
		
	}

	
	
	@Override
	public boolean hasWeights() {
		return true;
	}

	@Override
	public void compile(int[] weightShape) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Float> getNeurons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] weightSize() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getName() {
		return "Conv2D";
	}

	@Override
	public Matrix<Float> getWeights() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Activator getActivator() {
		return activator;
	}

	@Override
	public Vector<Float> getLoss() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Float> getBias() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Shape<Float> feed(Shape<Float> input) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
