package monkey.nn2.Initializer;

public class Constant extends Initializer {
	private static final long serialVersionUID = 7841213305664943446L;
	
	Float constant;
	
	/*
	 * Fill using constant values
	 * 
	 * @param constant to fill with
	 */
	public Constant(Float constant) {
		this.constant = constant;
	}
	
	public Constant() {
		this(0f);
	}

	@Override
	public Float[][] generate(int[] shape) {
		Float[][] out = new Float[shape[0]][shape[1]];
		for (int i = 0; i < out.length; i++)
			for (int j = 0; j < out[i].length; j++)
				out[i][j] = constant;
		return out;
	}
}
