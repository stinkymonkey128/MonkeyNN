package monkey.nn2.Utils;

import java.io.Serializable;

public abstract class Shape<T> implements Serializable {
	private static final long serialVersionUID = -2806262906807044205L;
	
	public abstract int[] getSize();
	public abstract T get(int[] pos);
	public abstract void set(int[] pos, T value);
}
