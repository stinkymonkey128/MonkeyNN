package monkey.nn2.Layers;

import monkey.nn2.Activators.Activator;
import monkey.nn2.Initializer.Initializer;
import monkey.nn2.Utils.*;

public class Conv2D extends Layer {
	private static final long serialVersionUID = -866424006190212993L;
	
	int[] stride;
	int paddingSize;
	Matrix3D<Float> kernels;
	Matrix3D<Float> neurons;
	Vector<Float> bias;
	Activator activator;
	
	/*
	 * 
	 */
	public Conv2D(int filters, int[] kernelSize, int[] stride, int paddingSize, Activator activator, Initializer kernelInit, Initializer biasInit) {
		kernels = new Matrix3D<Float>(new int[] {filters, kernelSize[0], kernelSize[1]});
				
		for (int i = 0; i < filters; i++) {
			Float[][] currentGen = kernelInit.generate(new int[] {kernelSize[0], kernelSize[1]});
			
			for (int x = 0; x < kernelSize[0]; x++) 
				for (int y = 0; y < kernelSize[1]; y++) 
					kernels.set(new int[] {i, x, y}, currentGen[x][y]);
		}
		
		this.stride = stride;
		this.paddingSize = paddingSize;
		this.activator = activator;
		
		bias = new Vector<Float>(biasInit.generate(new int[] {0, filters})[0]);
		
		neurons = null;
	}

	
	
	@Override
	public boolean hasWeights() {
		return true;
	}

	@Override
	public Vector<Float> getNeurons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] weightSize() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getName() {
		return "Conv2D";
	}

	@Override
	public Matrix<Float> getWeights() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Activator getActivator() {
		return activator;
	}

	@Override
	public Vector<Float> getLoss() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Float> getBias() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Shape<Float> feed(Shape<Float> input) {
		// input is a 3d matrix
		
		Matrix3D<Float> cInput = (Matrix3D<Float>) input;
		
		if (paddingSize != 0)
			cInput = padInput(cInput);
		
		// dimensions of the padded or not padded input
		int cZ = cInput.getSize()[0];
		int cX = cInput.getSize()[1];
		int cY = cInput.getSize()[2];
		
		Matrix3D<Float> outShape = new Matrix3D<Float>(new int[] {cZ, cX / stride[0], cY / stride[1]});
		
		// out shape indexing
		
		int oX = 0;
		int oY = 0;
		
		// iterate through 3d matrix		
		for (int i = 0; i < cZ; i++) {
			for (int x = 0; x < cX - (kernels.getSize()[1] - 1); x += stride[0]) {
				for (int y = 0; y < cY - (kernels.getSize()[2] - 1); y += stride[1]) {
					// sum of applied kernels and matrix
					
					Float cSum = 0f;
					
					for (int kX = 0; kX < kernels.getSize()[1]; kX++) {
						for (int kY = 0; kY < kernels.getSize()[2]; kY++) {
							cSum += cInput.get(new int[] {i, x + kX, y + kY}) * kernels.get(new int[] {i, kX, kY});
						}
					}
					
					outShape.set(new int[] {i, oX, oY++}, cSum);
				}
				oX++;
			}
			oX = 0;
			oY = 0;
		}
		
		neurons = outShape;
		return outShape;
	}



	@Override
	public void compile(int[] previousSize, int[] currentSize) {
		// TODO Auto-generated method stub
		
	}
	
	private Matrix3D<Float> padInput(Shape<Float> input) {
		int[] iSize = input.getSize();
		Matrix3D<Float> out = new Matrix3D<Float>(new int[] {iSize[0], iSize[1] + paddingSize * 2, iSize[2] + paddingSize * 2});
		
		for (int i = 0; i < iSize[0]; i++) 
			for (int x = paddingSize; x < iSize[1] + paddingSize; x++)
				for (int y = paddingSize; y < iSize[2] + paddingSize; y++) {
					int rX = x - paddingSize;
					int rY = y - paddingSize;
					
					out.set(new int[] {i, x, y}, input.get(new int[] {i, rX, rY}));
				}
	
		return out;
	}
}
