package Test;

import java.io.*;

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
        
        Float[][][] dataC = {{{1f, 0f, 1f, 1f, 0f, 1f},
        	{0f, 1f, 0f, 0f, 1f, 0f},
        	{1f, 0f, 1f, 1f, 0f, 1f},
        	{0f, 1f, 0f, 0f, 1f, 0f},
        	{1f, 0f, 1f, 1f, 0f, 1f},
        	{0f, 1f, 0f, 0f, 1f, 0f}},

        	{{1f, 0f, 1f, 1f, 0f, 1f},
        	{0f, 1f, 0f, 0f, 1f, 0f},
        	{1f, 0f, 1f, 1f, 0f, 1f},
        	{0f, 1f, 0f, 0f, 1f, 0f},
        	{1f, 0f, 1f, 1f, 0f, 1f},
        	{0f, 1f, 0f, 0f, 1f, 0f}}};
		
        Float[][][] dataC1 = {{{1f, 6f, 2f}, {5f, 3f, 1f}, {7f, 0f, 4f}}};
		
        /*
		try {
			ObjectInputStream os = new ObjectInputStream(new FileInputStream(new File("C:\\Users\\gener\\Documents\\NNSaves\\0001.nnst")));
			model = (Sequential) os.readObject();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		//Basic.printArray((Vector<Float>)model.feed(new Vector<Float>(samp[0])));
		
        
		Sequential model = new Sequential();
		/*model.add(new Dense(18, new Sigmoid(), new RandomUniform(-.5f, .5f), true, new Constant(1f)));
		model.add(new Dense(324, new Sigmoid(), new RandomUniform(-.5f, .5f), true, new Constant(1f)));
		model.add(new Dense(162, new Sigmoid(), new RandomUniform(-.5f, .5f), true, new Constant(1f)));
		model.add(new Dense(2, new Sigmoid(), new RandomUniform(-.5f, .5f), true, new Constant(1f)));*/
		
		/* model.add(new Input(new int[] {2, 6, 6}));
		model.add(new Conv2D(16, new int[] {3, 3}, new int[] {1, 1}, 3, new Relu(), new RandomUniform(-.5f, .5f), new Constant(1f)));
		*/
		//model.add(new Flatten());
		
		model.add(new Input(new int[] {1, 3, 3}));
		
		Conv2D c1 = new Conv2D(1, new int[] {2, 2}, new int[] {1, 1}, 1, new Relu(), new RandomUniform(-.5f, .5f), new Constant(1f));
		c1.setKernel(new Matrix3D<Float>(new Float[][][] {{{1f, 2f}, {-1f, 0f}}}));
		
		model.add(c1);
		model.compile(new GradientDescent(.003f, new MSE()));
		
		System.out.println(model.summary());
		
		//model.feed(new Vector<Float>(samp[0]));
		//model.backProp(new Vector<Float>(goal[0]));
		
		//Basic.printArray((Vector<Float>)model.feed(new Matrix3D<Float>(dataC)));
		printM3D(((Matrix3D<Float>) model.feed(new Matrix3D<Float>(dataC1))).dump());
		/*
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < samp.length; j++) {
				model.feed(new Vector<Float>(samp[j]));
				model.backProp(new Vector<Float>(goal[j]));
			}
		}
		
		Basic.printArray((Vector<Float>)model.feed(new Vector<Float>(samp[0])));
		*/
	}
	
	public static void printV(Object[] x) {
		for (int i = 0; i < x.length; i++)
			System.out.print(x[i] + " ");
	}
	
	public static void printM(Object[][] x) {
		for (int i = 0; i < x.length; i++) {
			printV(x[i]);
			System.out.println();
		}
	}
	
	public static void printM3D(Object[][][] x) {
		for (int i = 0; i < x.length; i++) {
			printM(x[i]);
			System.out.println();
		}
	}
}
