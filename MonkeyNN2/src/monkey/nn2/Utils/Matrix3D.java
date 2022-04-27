package monkey.nn2.Utils;

public class Matrix3D<T> implements Shape {
	
	T[][][] matrix;
	
	public Matrix3D(T[][][] matrix) {
		this.matrix = matrix;
	}
	
	public Matrix3D(int[] size) {
		matrix = (T[][][])new Object[size[0]][size[1]][size[2]];
	}

	@Override
	public int[] getSize() {
		return new int[] {matrix.length, matrix[0].length, matrix[0][0].length};
	}

	@Override
	public Object get(int[] pos) {
		return matrix[pos[0]][pos[1]][pos[2]];
	}

	@Override
	public void set(int[] pos, Object value) {
		matrix[pos[0]][pos[1]][pos[2]] = (T) value;
	}
	
	public T[][][] dump() {
		return matrix;
	}
}
