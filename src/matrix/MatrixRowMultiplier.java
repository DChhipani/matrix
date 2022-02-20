package matrix;

public class MatrixRowMultiplier extends Thread {

	private final int[][] result;
	private int[][] matrix1;
	private int[][] matrix2;
	private final int row;

	//Stores the given row and matrix for use in the multiplication process
	public MatrixRowMultiplier(int[][] result, int[][] matrix1, int[][] matrix2, int row) {
		this.result = result;
		this.matrix1 = matrix1;
		this.matrix2 = matrix2;
		this.row = row;
	}

	/**computes the product between the stored row and matrix and stores the result so
	that it can be returned later
	*/

	public void multiplyByRow() {
		for (int i = 0; i < matrix2[0].length; i++) {
			result[row][i] = 0;
			for (int j = 0; j < matrix1[row].length; j++) {
				result[row][i] += matrix1[row][j] * matrix2[j][i];

			}
		}
	}

	//returns the row vector that corresponds to the product of row and matrix
	public int[][] getResult() {
		return result;
	}

	@Override
	//used to  call the method multiplyByRow()
	public void run() {
		this.multiplyByRow();
	}

}