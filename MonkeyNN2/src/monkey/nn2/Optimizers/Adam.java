package monkey.nn2.Optimizers;

import java.util.List;

import monkey.nn2.Initializer.Constant;
import monkey.nn2.Layers.Layer;
import monkey.nn2.LossFunction.LossFunction;
import monkey.nn2.Utils.Matrix;
import monkey.nn2.Utils.Shape;
import monkey.nn2.Utils.Vector;

public class Adam extends Optimizer {
	private static final long serialVersionUID = 4760081661199424968L;
	
	// alpha = tuning req | generally best to start with .003 without adaptive lr
	
	// beta1 = 0.9 decay rates
	// beta2 = 0.999 
	
	// epsilon = 10^-8 | avoid division by 0
	
	// https://arxiv.org/pdf/1412.6980.pdf
	
	/*
	 * V.dw = 0 delta weights
	 * S.dw = 0
	 * V.db = 0 delta bias
	 * S.db = 0 
	 * 
	 * V.dw = b1 * V.dw + (1 - b1) * lf(l) momentum gradient
	 * V.db = b1 * V.db + (1 - b1) * lf(l) 
	 * 
	 * S.dw = b2 * S.dw + (1 - b2) * lf(l)^2 RMSprop
	 * S.db = b2 * S.db + (1 - b2) * lf(l)^2
	 * 
	 * V.cor.dw = V.dw / (1 - b1^t)
	 * V.cor.db = V.db / (1 - b1^t)
	 * 
	 * S.corr.dw = S.dw / (1 - b2^t)
	 * S.corr.db = S.db / (1 - b2^t)
	 * 
	 * w = w - alpha * V.corr.dw / (sqrt(s.corr.dw) + epsilon)
	 * b = b - alpha * V.corr.db / (sqrt(s.corr.db) + epsilon)
	 */
	
	Float alpha;
	Float beta1;
	Float beta2;
	Float epsilon;
	
	LossFunction lossFunction;
	
	List<Layer> layerStack;
	
	Shape<Float>[] histVW;
	Shape<Float>[] histSW;
	Shape<Float>[] histVB;
	Shape<Float>[] histSB;
	
	/*
	 * https://arxiv.org/pdf/1412.6980.pdf Adam Constructor
	 * 
	 * @param Learning Rate
	 * @param Decay Rate 1 Momentum
	 * @param Decay Rate 2 RMSProp
	 * @param Small number to prevent division by zero
	 */
	public Adam(Float alpha, LossFunction lossFunction, Float beta1, Float beta2, Float epsilon) {
		this.alpha = alpha;
		this.beta1 = beta1;
		this.beta2 = beta2;
		this.epsilon = epsilon;
		this.lossFunction = lossFunction;
	}
	
	public Adam(Float alpha, LossFunction lossFunction, Float beta1, Float beta2) {
		this(alpha, lossFunction, beta1, beta2, 10e-8f);
	}
	
	public Adam(Float alpha, LossFunction lossFunction) {
		this(alpha, lossFunction, .9f, .999f);
	}
	
	@Override
	public String getName() {
		return "Adam";
	}

	/*
	 * Gradient Back prop hidden layers
	 */
	public void fitHid(Layer prev, Layer curr, Layer next, int index) {
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
	    		histVW[index].set(new int[] {j, i}, histVW[index].get(new int[] {j, i}) * beta1 + (1 - beta2) * cLoss);
				histSW[index].set(new int[] {j, i}, histSW[index].get(new int[] {j, i}) * beta1 + (1 - beta2) * cLoss * cLoss);
				
	    		Float cWeight = curr.getWeights().get(new int[] {j, i});
	    			
	    		cWeight -= alpha * histVW[index].get(new int[] {j, i}) / ((float)Math.sqrt(histSW[index].get(new int[] {j, i})) + epsilon);
	    		
	    		curr.getWeights().set(new int[] {j, i}, cWeight );
	    		
	    		curr.getWeights().set(new int[] {j, i}, cWeight);
	    	}
	    	
	    	histVB[index].set(new int[] {i}, histVB[index].get(new int[] {i}) * beta1 + (1 - beta2) * cLoss);
			histSB[index].set(new int[] {i}, histSB[index].get(new int[] {i}) * beta1 + (1 - beta2) * cLoss * cLoss);
    						
			Float cBias = curr.getBias().get(new int[] {i});
			
			cBias -= alpha * histVB[index].get(new int[] {i}) / ((float)Math.sqrt(histSB[index].get(new int[] {i})) + epsilon);
	    		
			//cBias -= learningRate * cBias * cLoss;
			
			curr.getBias().set(new int [] {i}, cBias);
	    }
	}
	
	/*
	 * Gradient Back prop Output layer
	 */
	public void fitOut(Layer prev, Layer curr, Shape<Float> goal, int index) {
		for (int i = 0; i < curr.getNeurons().getSize()[0]; i++) {
			Float cLoss = curr.getLoss().get(new int[] {i});
			Float cNeuron = curr.getNeurons().get(new int[] {i});
			
			cLoss = lossFunction.prime(cNeuron, goal.get(new int[] {i})) * curr.getActivator().prime(cNeuron);
	    	
			curr.getLoss().set(new int[] {i}, cLoss);
			
			for (int j = 0; j < prev.getNeurons().getSize()[0]; j++) {
				histVW[index].set(new int[] {j, i}, histVW[index].get(new int[] {j, i}) * beta1 + (1 - beta2) * cLoss);
				histSW[index].set(new int[] {j, i}, histSW[index].get(new int[] {j, i}) * beta1 + (1 - beta2) * cLoss * cLoss);
				
	    		Float cWeight = curr.getWeights().get(new int[] {j, i});
	    			
	    		cWeight -= alpha * histVW[index].get(new int[] {j, i}) / ((float)Math.sqrt(histSW[index].get(new int[] {j, i})) + epsilon);
	    		
	    		curr.getWeights().set(new int[] {j, i}, cWeight );
	    		
	    		//System.out.print(curr.getWeights().get(new int[] {j, i}) + " ");
	    		
	    		
	    		
	    	}
			//System.out.println();
			
			// Bias Update
			
			histVB[index].set(new int[] {i}, histVB[index].get(new int[] {i}) * beta1 + (1 - beta2) * cLoss);
			histSB[index].set(new int[] {i}, histSB[index].get(new int[] {i}) * beta1 + (1 - beta2) * cLoss * cLoss);
    						
			Float cBias = curr.getBias().get(new int[] {i});
			
			cBias -= alpha * histVB[index].get(new int[] {i}) / ((float)Math.sqrt(histSB[index].get(new int[] {i})) + epsilon);
	    		
			//cBias -= learningRate * cBias * cLoss;
			
			curr.getBias().set(new int [] {i}, cBias);
		}
		//System.out.println();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void compile(List<Layer> layerStack) {
		this.layerStack = layerStack;
		
		int size = layerStack.size();
		
		histVW = (Shape<Float>[])(new Object[size]);
		histSW = (Shape<Float>[])(new Object[size]);
		histVB = (Shape<Float>[])(new Object[size]);
		histSB = (Shape<Float>[])(new Object[size]);
		
		for (int i = 0; i < size; i++) {
			int[] weightSize = layerStack.get(i).getWeights().getSize();
			int[] biasSize = layerStack.get(i).getBias().getSize();
			
			histVW[i] = new Matrix<Float>((new Constant(0f)).generate(weightSize));
			histSW[i] = new Matrix<Float>((new Constant(0f)).generate(weightSize));
			histVB[i] = new Vector<Float>((new Constant(0f)).generate(biasSize)[0]);
			histSB[i] = new Vector<Float>((new Constant(0f)).generate(biasSize)[0]);
		}
	}

	@Override
	public void fit(Shape<Float> goal) {
		fitOut(layerStack.get(layerStack.size() - 2), layerStack.get(layerStack.size() - 1), goal, layerStack.size() - 1);
		for (int i = layerStack.size() - 2; i > 0; i--) {
			fitHid(layerStack.get(i - 1), layerStack.get(i), layerStack.get(i + 1), i);
		}
		
	}


}
