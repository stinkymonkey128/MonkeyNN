package monkey.nn2.Utils;

public class Matrix<T> implements Shape {
	
	private T[][] matrix;
	
	public Matrix(T[][] matrix) {
		this.matrix = matrix;
	}
	
	public Matrix(int[] size) {
		matrix = (T[][])new Object[size[0]][size[1]];
	}

	@Override
	public int[] getSize() {
		return new int[] {matrix.length, matrix[0].length};
	}

	@Override
	public T get(int[] pos) {
		return matrix[pos[0]][pos[1]];
	}

	@Override
	public void set(int[] pos, Object value) {
		matrix[pos[0]][pos[1]] = (T) value;
	}
	
	
	public T[][] dump() {
		return matrix;
	}

}
