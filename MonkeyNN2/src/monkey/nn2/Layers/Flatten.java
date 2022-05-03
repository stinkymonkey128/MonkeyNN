package monkey.nn2.Layers;

import monkey.nn2.Activators.Activator;
import monkey.nn2.Utils.Shape;

public class Flatten implements Layer {

	@Override
	public boolean hasWeights() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Shape<Float> feed(Shape<Float> input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void compile(int[] weightShape) {
		// TODO Auto-generated method stub
		
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
