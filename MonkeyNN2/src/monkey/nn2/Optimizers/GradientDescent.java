package monkey.nn2.Optimizers;

import monkey.nn2.Exceptions.IllegalLength;
import monkey.nn2.Layers.Layer;
import monkey.nn2.LossFunction.*;
import monkey.nn2.Structure.Structure;
import monkey.nn2.Utils.*;

public class GradientDescent implements Optimizer {
	/*
	 * Loss Function everything connected to it combined and compared to expected
	 * a = learning rate
	 * n = neuron of weight
	 * d = delta of future weight *LOSS FUNCTION ALREADY CALCULATES ALL*
	 * 
	 * d = loss_function * n
	 * 
	 * w = w - a * gradient (d)
	 */
	
	Float learningRate;
	LossFunction lossFunction;
	
	public GradientDescent(Float learningRate, LossFunction lossFunction) {
		this.learningRate = learningRate;
		this.lossFunction = lossFunction;
	}
	
	/*
	 * Gradient Back prop hidden layers
	 */
	public void fitHid(Layer prev, Layer curr, Layer next) {
		for (int i = 0; i < curr.getNeurons().getSize()[0]; i++) {
			Float cLoss = curr.getLoss().get(new int[] {i});
			
			for (int j = 0; j < next.getLoss().getSize()[0]; j++) {
				Float nLoss = next.getLoss().get(new int[] {j});
				Float nWeight = next.getWeights().get(new int[] {i, j});
				
				cLoss += nLoss * nWeight;
			}
			
			cLoss *= curr.getActivator().prime(curr.getNeurons().get(new int[] {i}));
			
			curr.getLoss().set(new int[] {i}, cLoss);
	    	 
	    	for (int j = 0; j < prev.getNeurons().getSize()[0]; j++) {
	    		Float cWeight = curr.getWeights().get(new int[] {j, i});
	    		Float pNeuron = prev.getNeurons().get(new int[] {j});
	    		
	    		cWeight -= learningRate * cLoss * pNeuron;
	    		
	    		curr.getWeights().set(new int[] {j, i}, cWeight);
	    	}
	    	
	    	Float cBias = curr.getBias().get(new int[] {i});
	    	
	    	cBias -= cLoss * cBias * learningRate;
	    	
	    	curr.getBias().set(new int [] {i}, cBias);
	    }
	}
	
	/*
	 * Gradient Back prop Output layer
	 */
	public void fitOut(Layer prev, Layer curr, Shape<Float> goal) {
		for (int i = 0; i < curr.getNeurons().getSize()[0]; i++) {
			Float cNeuron = curr.getNeurons().get(new int[] {i});
			
			// TODO fix loss function prime or calc
			Float cLoss = lossFunction.prime(cNeuron, goal.get(new int[] {i})) * curr.getActivator().prime(cNeuron);
	    	
			curr.getLoss().set(new int[] {i}, cLoss);
			
			for (int j = 0; j < prev.getNeurons().getSize()[0]; j++) {
	    		Float cWeight = curr.getWeights().get(new int[] {j, i});
	    		Float pNeuron = prev.getNeurons().get(new int[] {j});
	    			
	    		cWeight -= learningRate * cLoss * pNeuron;
	    		
	    		curr.getWeights().set(new int[] {j, i}, cWeight);
	    		
	    		//System.out.print(curr.getWeights().get(new int[] {j, i}) + " ");
	    	}
			//System.out.println();
			
			// Bias Update
			
			Float cBias = curr.getBias().get(new int[] {i});
	    		
			cBias -= learningRate * cBias * cLoss;
			
			curr.getBias().set(new int [] {i}, cBias);
		}
		//System.out.println();
	}
	
	@Override
	public String getName() {
		return "GradientDescent";
	}
}
