package monkey.nn2.Layers;

import monkey.nn2.Activators.Activator;
import monkey.nn2.Utils.*;

public class Flatten extends Layer {
	private static final long serialVersionUID = -1414865157647278396L;
	
	private Vector<Float> neuron;
	
	public Flatten() {
		// Temporary initialization to avoid null pointer
		neuron = new Vector<Float>(new int[] {1});
	}

	@Override
	public boolean hasWeights() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Shape<Float> feed(Shape<Float> input) {
		int[] iSize = input.getSize();
		
		for (int a = 0; a < iSize[0]; a++)
			for (int b = 0; b < iSize[1]; b++)
				for (int c = 0; c < iSize[2]; c++)
					neuron.set(new int[] {a * b + c}, neuron.get(new int[] {a, b, c}));
		
		return neuron;
	}

	@Override
	public void compile(int[] previousSize, int[] currentSize) {
		neuron = new Vector<Float>(new int[] {previousSize[0] * previousSize[1] * previousSize[2]});
	}

	@Override
	public Shape<Float> getNeurons() {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activator getActivator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shape<Float> getWeights() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shape<Float> getLoss() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shape<Float> getBias() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
