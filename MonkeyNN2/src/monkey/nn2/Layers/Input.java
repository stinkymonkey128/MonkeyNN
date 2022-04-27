package monkey.nn2.Layers;

import monkey.nn2.Activators.Activator;
import monkey.nn2.Initializer.Constant;

public class Input implements Layer {
	
	Float[] neurons;
	
	public Input(int neurons) {
		this.neurons = (new Constant(0f)).generate(new int[] {1, neurons})[0];
	}

	@Override
	public Float[] feed(Float[] input) {
		return input;
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
	public Float[] getNeurons() {
		// TODO Auto-generated method stub
		return neurons;
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
	public Float[][] getWeights() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Activator getActivator() {
		return null;
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
