package monkey.nn2.Structure;

import java.io.Serializable;

import monkey.nn2.Layers.*;
import monkey.nn2.LossFunction.LossFunction;
import monkey.nn2.Optimizers.Optimizer;
import monkey.nn2.Utils.*;

public abstract class Structure implements Serializable {
	private static final long serialVersionUID = -1687852151507957823L;

	public abstract void add(Layer layer);
	public abstract void clear();
	public abstract void compile(Optimizer optimizer);
	public abstract Shape<Float> feed(Shape<Float> input);
	
	public abstract String summary();
}