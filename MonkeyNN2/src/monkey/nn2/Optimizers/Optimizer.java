package monkey.nn2.Optimizers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import monkey.nn2.Layers.Layer;
import monkey.nn2.Utils.Shape;

public abstract class Optimizer  implements Serializable{
	private static final long serialVersionUID = -6383335617947578983L;
	
	//public abstract float correctionValue();
	public abstract String getName();
	public abstract void compile(List<Layer> layerStack);
	
	public abstract void fit(Shape<Float> goal);
}
