package monkey.nn2.Layers;

import monkey.nn2.Activators.Activator;

public interface Layer {
	public abstract boolean hasWeights();
	public abstract Float[] feed(Float[] input);
	public abstract void compile(int[] weightShape);
	
	public abstract Float[] getNeurons();
	public abstract int[] weightSize();
	
	public abstract String getName();
	public abstract Activator getActivator();
	
	public abstract Float[][] getWeights();
	public abstract Float[] getLoss();
	public abstract Float[] getBias();
}
