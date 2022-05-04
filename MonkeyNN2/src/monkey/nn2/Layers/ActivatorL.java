package monkey.nn2.Layers;

import monkey.nn2.Activators.Activator;
import monkey.nn2.Initializer.Constant;

public class ActivatorL implements Layer {
	
	Float[] neurons;
	
	Activator activator;
	
	public ActivatorL(int neurons, Activator activator) {
		this.neurons = (new Constant(0f)).generate(new int[] {1, neurons})[0];
		this.activator = activator;
	}
	
	@Override
	public Float[] feed(Float[] input) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean hasWeights() {
		return false;
	}

	@Override
	public void compile(int[] weightShape) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Float[] getNeurons() {
		return neurons;
	}

	@Override
	public int[] weightSize() {
		return null;
	}

	@Override
	public String getName() {
		return "Activator";
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
