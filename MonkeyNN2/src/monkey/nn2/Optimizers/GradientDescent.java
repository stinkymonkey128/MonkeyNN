package monkey.nn2.Optimizers;

import monkey.nn2.Exceptions.IllegalLength;
import monkey.nn2.Layers.Layer;
import monkey.nn2.LossFunction.*;
import monkey.nn2.Structure.Structure;

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
	
	public void fitHid(Layer prev, Layer curr, Layer next) {
		for (int i = 0; i < curr.getNeurons().length; i++) {
			for (int j = 0; j < next.getLoss().length; j++)
				curr.getLoss()[i] += next.getLoss()[j] * next.getWeights()[i][j];
			curr.getLoss()[i] *= curr.getActivator().prime(curr.getNeurons()[i]);
	    	 
	    	  for (int j = 0; j < prev.getNeurons().length; j++)
	    		  curr.getWeights()[j][i] -= learningRate * curr.getLoss()[i] * prev.getNeurons()[j];
	    	curr.getBias()[i] -= curr.getLoss()[i] * curr.getBias()[i] * learningRate;
	    }
	}
	
	public void fitOut(Layer prev, Layer curr, Float[] goal) {
		for (int i = 0; i < curr.getNeurons().length; i++) {
			curr.getLoss()[i] = lossFunction.prime(curr.getNeurons()[i], goal[i]) * curr.getActivator().prime(curr.getNeurons()[i]);
	    		for (int j = 0; j < prev.getNeurons().length; j++) {
	    			curr.getWeights()[j][i] -= learningRate * curr.getLoss()[i] * prev.getNeurons()[j];
	    			//System.out.print(curr.getWeights()[j][i] + " ");
	    		}
	    		//System.out.println();
	    	curr.getBias()[i] -= learningRate * curr.getBias()[i] * curr.getLoss()[i];
		}
		//System.out.println();
	}
	
	@Override
	public String getName() {
		return "GradientDescent";
	}
}
