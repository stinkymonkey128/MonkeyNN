package monkey.nn2.Layers;

import java.io.Serializable;

import monkey.nn2.Activators.Activator;
import monkey.nn2.Utils.Shape;
import monkey.nn2.Utils.Vector;

public abstract class Layer implements Serializable{
	private static final long serialVersionUID = -2390834854910462178L;
	
	public abstract boolean hasWeights();
	public abstract Shape<Float> feed(Shape<Float> input);
	public abstract void compile(int[] previousSize, int[] currentSize);
	
	public abstract Shape<Float> getNeurons();
	public abstract int[] weightSize();
	
	public abstract String getName();
	public abstract Activator getActivator();
	
	public abstract Shape<Float> getWeights();
	public abstract Shape<Float> getLoss();
	public abstract Shape<Float> getBias();

}
