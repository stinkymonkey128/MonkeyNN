package monkey.nn2.Utils;

public interface Shape<T> {
	public abstract int[] getSize();
	public abstract T get(int[] pos);
	public abstract void set(int[] pos, T value);
}
