package monkey.nn2.Optimizers;

import java.io.Serializable;

import monkey.nn2.Layers.Layer;
import monkey.nn2.Utils.Shape;

public abstract class Optimizer  implements Serializable{
	private static final long serialVersionUID = -6383335617947578983L;
	
	//public abstract float correctionValue();
	public abstract String getName();
	
	public abstract void fitHid(Layer prev, Layer curr, Layer next);
	public abstract void fitOut(Layer prev, Layer curr, Shape<Float> goal);
}
