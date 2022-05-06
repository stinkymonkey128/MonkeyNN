package monkey.nn2.Initializer;

import java.io.Serializable;

public abstract class Initializer implements Serializable{
	private static final long serialVersionUID = -2841857265170023449L;

	public abstract Float[][] generate(int[] shape);
}
