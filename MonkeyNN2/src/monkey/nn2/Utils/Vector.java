package monkey.nn2.Utils;

public class Vector<T> {
	private T[] vector;
	
	public Vector(T[] value) {
		vector = value;
	}
	
	public Vector(int size) {
		vector = (T[])new Object[size];
	}
	
	public T get(int pos) {
		return vector[pos];
	}
	
	public void set(int pos, T value) {
		vector[pos] = value;
	}
	
	public T[] toType() {
		return vector;
	}
}
