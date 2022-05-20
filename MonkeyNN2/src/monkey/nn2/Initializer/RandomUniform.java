package monkey.nn2.Initializer;

import java.util.*;

public class RandomUniform extends Initializer {
	private static final long serialVersionUID = -6975253487309733938L;
	
	Random random;
	Float min, max;
	
	/*
	 * Generate using min max random values
	 * 
	 * @param minimum value
	 * @param maximum value
	 * @param Random generation seed using java.util.Random
	 */
	public RandomUniform(Float min, Float max, int seed) {
		this.min = min;
		this.max = max;
		random = new Random(seed);
	}
	
	public RandomUniform(Float min, Float max) {
		this(min, max, 1);
	}
	
	public RandomUniform() {
		this(-.05f, .05f, 1);
	}

	@Override
	public Float[][] generate(int[] shape) {
		Float[][] out = new Float[shape[0]][shape[1]];
		for (int i = 0; i < out.length; i++)
			for (int j = 0; j < out[i].length; j++)
				out[i][j] = min + random.nextFloat() * (max - min);
		return out;
	}

}
