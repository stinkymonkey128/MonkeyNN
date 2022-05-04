package monkey.nn2.Layers;

import monkey.nn2.Activators.*;
import monkey.nn2.Initializer.*;
import monkey.nn2.Utils.Basic;

public class Dense implements Layer {
	Initializer weightInit;
	
	Float[] neurons;
	Float[] bias;
	Float[][] weights;
	Float[] loss;
	
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
		this.neurons = new Float[neurons];
		this.loss = (new Constant(0f)).generate(new int[] {1, neurons})[0];
		this.activator = activator;
		biasUse = bias;
		this.bias = biasInit.generate(new int[] {1, neurons})[0];
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
	public Float[] feed(Float[] input) {
		System.out.println(input[0]);
		Float[][] mat = new Float[][] {input};
		System.out.println(mat[0][0]);
		Float[] dotted = Basic.dot(mat, weights)[0];
		System.out.println(dotted[0]);
		neurons = activator.calc(Basic.converge(dotted, bias));
		System.out.println(neurons[0] + "\n");
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
