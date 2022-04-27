package monkey.nn2.Structure;

import java.util.ArrayList;
import java.util.Stack;

import monkey.nn2.Layers.*;
import monkey.nn2.LossFunction.*;
import monkey.nn2.Optimizers.*;
import monkey.nn2.Utils.Basic;

public class Sequential extends Structure {
	ArrayList<Layer> layerStack;
	
	LossFunction lossFunction;
	Optimizer optimizer;
	
	/*
	 * Sequential layers constructor (Stack)
	 * 
	 * @param Stack of appropriate layers
	 */
	public Sequential(ArrayList<Layer> layerStack) {
		this.layerStack = layerStack;
	}
	
	/*
	 * Sequential layers constructor (Array)
	 * 
	 * @array Stack of appropriate layers
	 */
	public Sequential(Layer[] layerArr) {
		for (Layer x : layerArr)
			layerStack.add(x);
	}
	
	public Sequential() {
		layerStack = new ArrayList<Layer>();
	}

	@Override
	public void add(Layer layer) {
		layerStack.add(layer);
	}

	@Override
	public void clear() {
		layerStack.clear();
	}
	
	@Override
	public void compile(Optimizer optimizer) {
		//this.lossFunction = lossFunction;
		this.optimizer = optimizer;
		
		// ADD PASSTHROUGH LAYER (INPUT)
		if (!layerStack.get(0).getName().equals("Input"))
			layerStack.add(0, new Input(layerStack.get(0).getNeurons().length));
		
		// INIT WEIGHTS + BIAS (if applicable)
		for (int i = 1; i < layerStack.size(); i++) {
			Layer currentLayer = layerStack.get(i);
			Layer previousLayer = layerStack.get(i - 1);
			
			currentLayer.compile(new int[] {previousLayer.getNeurons().length, currentLayer.getNeurons().length});
		}
	}

	@Override
	public String summary() {
		String out = "Model: Sequential\nLoss Function: " + /*lossFunction.getName() +*/ "\nOptimizer: " + optimizer.getName() + "\n----------------------------\n";
		out += String.format(" %-8s", "Layer") + String.format("%-8s", "Size") + String.format("%-10s", "Weight Size") + "\n----------------------------\n";
		
		for (Layer layer : layerStack) {
			out += String.format(" %-8s", layer.getName()) + String.format("%-8d", layer.getNeurons().length) + String.format("%-10s", Basic.iAtoS(layer.weightSize())) + "\n";
		}
		
		return out;
	}

	@Override
	public Float[] feed(Float[] input) {
		Float[] out = input;
		
		for (Layer layer : layerStack) {
			out = layer.feed(out);
			
		}
		
		return out;
	}
	
	public void backProp(Float[] goal) {
		optimizer.fitOut(layerStack.get(layerStack.size() - 2), layerStack.get(layerStack.size() - 1), goal);
		for (int i = layerStack.size() - 2; i > 0; i--) {
			optimizer.fitHid(layerStack.get(i - 1), layerStack.get(i), layerStack.get(i + 1));
		}
	}
}
