package Test;

import monkey.nn2.Activators.*;
import monkey.nn2.Structure.*;
import monkey.nn2.Utils.*;
import monkey.nn2.Layers.*;
import monkey.nn2.LossFunction.*;
import monkey.nn2.Optimizers.*;
import monkey.nn2.Initializer.*;

public class Test {
	public static void main(String[] args) {
		
		Float[][] goal = {{0f, 0f}, {0f, 1f}, {0f, 1f}, {1f, 0f}, {0f, 0f}, {0f, 0f}, {1f, 0f}, {0f, 1f}, {0f, 1f}, {1f, 0f}, {0f, 1f}};
        
        Float[][] samp = {
        		/*
        		 * x
        		 * o
        		 * 
        		 * o x x
        		 * x o o
        		 * x o x
        		 */
        { 0f, 1f, 1f, 1f, 0f, 0f, 1f, 0f, 1f, /**/ 1f, 0f, 0f, 0f, 1f, 1f, 0f, 1f, 0f},
        { 0f, 1f, 1f, 0f, 1f, 0f, 0f, 1f, 0f, /**/ 1f, 0f, 0f, 0f, 0f, 1f, 1f, 0f, 0f},
        { 0f, 1f, 1f, 0f, 0f, 1f, 0f, 0f, 1f, /**/ 1f, 0f, 0f, 0f, 1f, 0f, 1f, 0f, 0f},
        { 0f, 1f, 0f, 0f, 0f, 1f, 0f, 0f, 1f, /**/ 0f, 0f, 1f, 0f, 1f, 0f, 1f, 0f, 0f},
        { 0f, 1f, 0f, 0f, 1f, 1f, 1f, 0f, 1f, /**/ 1f, 0f, 1f, 1f, 0f, 0f, 0f, 1f, 0f},
        { 1f, 0f, 1f, 1f, 0f, 0f, 0f, 1f, 1f, /**/ 0f, 1f, 0f, 0f, 1f, 1f, 1f, 0f, 0f},
        { 0f, 0f, 0f, 1f, 1f, 0f, 1f, 0f, 0f, /**/ 0f, 0f, 1f, 0f, 0f, 1f, 0f, 0f, 1f},
        { 1f, 1f, 1f, 0f, 0f, 0f, 0f, 0f, 0f, /**/ 0f, 0f, 0f, 0f, 1f, 1f, 0f, 0f, 0f},
        { 0f, 0f, 0f, 1f, 1f, 1f, 0f, 0f, 0f, /**/ 0f, 0f, 0f, 0f, 0f, 0f, 1f, 1f, 0f},
        { 0f, 1f, 0f, 1f, 0f, 0f, 0f, 0f, 1f, /**/ 0f, 0f, 1f, 0f, 1f, 0f, 1f, 0f, 0f},
        { 0f, 0f, 1f, 0f, 1f, 0f, 1f, 0f, 0f, /**/ 1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 1f}
        };
		
		Sequential model = new Sequential();
		model.add(new Dense(18, new Sigmoid(), new RandomUniform(-.5f, .5f), true, new Constant(1f)));
		model.add(new Dense(324, new Sigmoid(), new RandomUniform(-.5f, .5f), true, new Constant(1f)));
		model.add(new Dense(162, new Sigmoid(), new RandomUniform(-.5f, .5f), true, new Constant(1f)));
		model.add(new Dense(2, new Sigmoid(), new RandomUniform(-.5f, .5f), true, new Constant(1f)));
		model.compile(new GradientDescent(.003f, new MSE()));
		
		System.out.println(model.summary());
		
		model.feed(new Vector<Float>(samp[0]));
		model.backProp(new Vector<Float>(goal[0]));
		
		Basic.printArray((Vector<Float>)model.feed(new Vector<Float>(samp[0])));
	}
	
	private static void printMatrix(Float[][] x) {
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[0].length; j++) 
				System.out.print(x[i][j] + " ");
			System.out.println();
		}
	}
	
	
}
