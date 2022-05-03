package monkey.nn2.Layers;

import monkey.nn2.Activators.Activator;
import monkey.nn2.Initializer.Constant;
import monkey.nn2.Utils.*;

public class Input implements Layer {
	
	Vector<Float> neurons;
	
	public Input(int neurons) {
		this.neurons = new Vector<Float>((new Constant(0f)).generate(new int[] {1, neurons})[0]);
	}


	@Override
	public boolean hasWeights() {
		return false;
	}

	@Override
	public void compile(int[] weightShape) {
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
