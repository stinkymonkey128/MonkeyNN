package monkey.nn2.Layers;

import monkey.nn2.Activators.Activator;
import monkey.nn2.Initializer.Constant;
import monkey.nn2.Utils.*;

public class ActivatorL extends Layer {
	private static final long serialVersionUID = -2109267474806616052L;

	Vector<Float> neurons;
	
	Activator activator;
	
	public ActivatorL(int neurons, Activator activator) {
		this.neurons = new Vector<Float>((new Constant(0f)).generate(new int[] {1, neurons})[0]);
		this.activator = activator;
	}

	
	@Override
	public boolean hasWeights() {
		return false;
	}

	@Override
	public Vector<Float> getNeurons() {
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


	@Override
	public void compile(int[] previousSize, int[] currentSize) {
		// TODO Auto-generated method stub
		
	}
}
