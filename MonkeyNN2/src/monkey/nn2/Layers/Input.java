package monkey.nn2.Layers;

import monkey.nn2.Activators.Activator;
import monkey.nn2.Initializer.Constant;
import monkey.nn2.Utils.*;

public class Input extends Layer {
	private static final long serialVersionUID = 6829986169754535686L;
	
	Shape<Float> neurons;
	
	public Input(int[] size) {
		int len = size.length;
		
		if (len == 1)
			neurons = new Vector<Float>(size);
		if (len == 2)
			neurons = new Matrix<Float>(size);
		if (len == 3)
			neurons = new Matrix3D<Float>(size);
	}


	@Override
	public boolean hasWeights() {
		return false;
	}

	@Override
	public void compile(int[] previousSize, int[] currentSize) {
		// NO WEIGHTS
	}



	@Override
	public int[] weightSize() {
		return null;
	}
	
	@Override
	public String getName() {
		return "Input";
	}
	
	@Override
	public Activator getActivator() {
		return null;
	}


	@Override
	public Shape<Float> feed(Shape<Float> input) {
		return input;
	}


	@Override
	public Shape<Float> getNeurons() {
		return neurons;
	}


	@Override
	public Shape<Float> getWeights() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Shape<Float> getLoss() {
		return null;
	}


	@Override
	public Shape<Float> getBias() {
		return null;
	}

}
