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
	public Shape<Float> feed(Shape<Float> input) {
		Vector<Float> tV = (Vector<Float>) input;
		Float[] oVector = Basic.fArrayCastFix(tV.dump());
		Matrix<Float> inputMatrix = new Matrix<Float>(new Float[][] {oVector} );
		Matrix<Float> dotProduct = Basic.dot(inputMatrix, weights);
		
		neurons = activator.calc(Basic.converge(dotProduct.degrade(0), bias));
		return neurons;
	}
	
	public void compile(int[] weightShape) {
		weights = new Matrix<Float>(weightInit.generate(weightShape));
	}

	@Override
	public boolean hasWeights() {
		return true;
	}

	@Override
	public Shape<Float> getNeurons() {
		return neurons;
	}

	@Override
	public int[] weightSize() {
		return weights.getSize();
	}
	
	@Override
	public String getName() {
		return "Dense";
	}

	@Override
	public Shape<Float> getWeights() {
		return weights;
	}
	
	@Override
	public Activator getActivator() {
		return activator;
	}

	@Override
	public Shape<Float> getLoss() {
		return loss;
	}

	@Override
	public Shape<Float> getBias() {
		return bias;
	}
}
