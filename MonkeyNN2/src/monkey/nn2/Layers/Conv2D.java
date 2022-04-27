package monkey.nn2.Layers;

import monkey.nn2.Activators.Activator;
public class Conv2D implements Layer {
	float[][][] filters;
	float[] bias;
	Activator activator;
	
	/*
	 * 
	 */
	public Conv2D(int filters, int kernelSize, int[] strides, String paddingStyle, Activator activator, String kernelInit, String biasInit) {
		
	}

	@Override
	public Float[] feed(Float[] input) {
		// TODO Auto-generated method stub
		return null;
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
	public Float[] getNeurons() {
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
	public Float[][] getWeights() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Activator getActivator() {
		return activator;
	}

	@Override
	public Float[] getLoss() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float[] getBias() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
