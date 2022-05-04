package monkey.nn2.Optimizers;

import monkey.nn2.Layers.Layer;
import monkey.nn2.Utils.Shape;

public class Adam implements Optimizer {
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
	 * S.db = b2 * S.db + (1 - b2) * lf(l)
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
	
	/*
	 * https://arxiv.org/pdf/1412.6980.pdf Adam Constructor
	 * 
	 * @param Learning Rate
	 * @param Decay Rate 1 Momentum
	 * @param Decay Rate 2 RMSProp
	 * @param Small number to prevent division by zero
	 */
	public Adam(Float alpha, Float beta1, Float beta2, Float epsilon) {
		this.alpha = alpha;
		this.beta1 = beta1;
		this.beta2 = beta2;
		this.epsilon = epsilon;
	}
	
	public Adam(Float alpha, Float beta1, Float beta2) {
		this(alpha, beta1, beta2, 10e-8f);
	}
	
	public Adam(Float alpha) {
		this(alpha, .9f, .999f);
	}
	
	public Adam() {
		this(.03f);
	}
	
	@Override
	public String getName() {
		return "Adam";
	}

	@Override
	public void fitHid(Layer prev, Layer curr, Layer next) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fitOut(Layer prev, Layer curr, Shape<Float> goal) {
		// TODO Auto-generated method stub
		
	}


}
