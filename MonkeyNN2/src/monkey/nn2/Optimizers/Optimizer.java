package monkey.nn2.Optimizers;

import monkey.nn2.Layers.Layer;
import monkey.nn2.Utils.Shape;

public interface Optimizer {
	//public abstract float correctionValue();
	public abstract String getName();
	
	public abstract void fitHid(Layer prev, Layer curr, Layer next);
	public abstract void fitOut(Layer prev, Layer curr, Shape<Float> goal);
}
