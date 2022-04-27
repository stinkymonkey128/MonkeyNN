package monkey.nn2.Layers;

import monkey.nn2.Activators.*;
import monkey.nn2.Initializer.*;
import monkey.nn2.Utils.*;

public class Dense implements Layer {
	Initializer weightInit;
	
	Vector<Float> neurons;
	Vector<Float> bias;
	Matrix<Float> weights;
	Vector<Float> loss;
	
	Activator activator;
	boolean biasUse;
	
	/*
	 * Fully Connected Layer
	 * 
	 * @param number of neurons
	 * @param activator function
	 * @param boolean statement of using bias
	 * @param filling bias with
	 */
	public Dense(int neurons, Activator activator, Initializer weightInit, boolean bias, Initializer biasInit) {
		this.neurons = new Vector<Float>(new int[] {neurons});
		this.loss = new Vector<Float>((new Constant(0f)).generate(new int[] {1, neurons})[0]);
		this.activator = activator;
		biasUse = bias;
		this.bias = new Vector<Float>(biasInit.generate(new int[] {1, neurons})[0]);
		this.weightInit = weightInit;
	}
	
	/*
	 * Fully Connected Layer
	 * 
	 * @param number of neurons
	 * @param activator function
	 */
	public Dense(int neurons, Activator activator, Initializer weightInit) {
		this(neurons, activator, weightInit, false, new Constant());
	}
	
	/*
	 * Fully Connected Layer
	 * 
	 * @param number of neurons
	 */
	public Dense(int neurons) {
		this(neurons, new Identity(), new RandomUniform() ,false, new Constant());
	}

	@Override
	public Vector<Float> feed(Vector<Float> input) {
		neurons = activator.calc(Basic.converge(Basic.dot(new Matrix<Float>(new Float[][] {{input}}), weights)[0], bias));
		return neurons;
	}
	
	public void compile(int[] weightShape) {
		weights = weightInit.generate(weightShape);
	}

	@Override
	public boolean hasWeights() {
		return true;
	}

	@Override
	public Float[] getNeurons() {
		return neurons;
	}

	@Override
	public int[] weightSize() {
		return new int[] {weights.length, weights[0].length};
	}
	
	@Override
	public String getName() {
		return "Dense";
	}

	@Override
	public Float[][] getWeights() {
		return weights;
	}
	
	@Override
	public Activator getActivator() {
		return activator;
	}

	@Override
	public Float[] getLoss() {
		return loss;
	}

	@Override
	public Float[] getBias() {
		return bias;
	}
}
