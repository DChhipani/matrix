package matrix;

import java.util.Random;

public class MatrixMultiplication {

	public static void main(String[] args) {

		Random rand = new Random();

		int length1;
		int length2;
		int length3;
		/** Randomly generate the number of rows of matrix A and the number of columns of matrix B 
		 The number of rows and columns of the two matrices must be the same
		 */
		length1 = rand.nextInt(9) + 1;
		
		// Randomly generate the number of columns of matrix A
		length2 = rand.nextInt(9) + 1;
		
		// Randomly generate the number of rows of matrix B
		length3 = rand.nextInt(9) + 1;
		int[][] m1 = MatrixGenerator.generateMatrix(length2, length1);
		int[][] m2 = MatrixGenerator.generateMatrix(length1, length3);

		int[][] result = matrixMultiply(m1, m2);

		System.out.println("Matrix 1 : ");
		MatrixGenerator.print(m1);

		System.out.println("\nMatrix 2 : ");
		MatrixGenerator.print(m2);

		System.out.println("\nOutput Matrix : ");
		MatrixGenerator.print(result);

	}

	//main method of multiplication of matrices
	public static int[][] matrixMultiply(int[][] matrix1, int[][] matrix2) {
		int resultRows = matrix1.length;
		int resultColumns = matrix2[0].length;

		int[][] result = new int[resultRows][resultColumns];

		int columns2 = matrix2[0].length;

		for (int i = 0; i < resultRows; i++) {
			for (int j = 0; j < columns2; j++) {
				result[i][j] = 0;
				for (int k = 0; k < resultColumns; k++) {
					result[i][j] += matrix1[i][k] * matrix2[k][j];
				}
			}
		}

		return result;

	}

	public static int[][] parallelMatrixMultiply(int[][] matrix1, int[][] matrix2) {
		int[][] result = new int[matrix1.length][matrix2[0].length];
		MatrixRowMultiplier[] matrixes = new MatrixRowMultiplier[matrix1.length];
		for (int i = 0; i < matrix1.length; i++) {
			matrixes[i] = new MatrixRowMultiplier(matrix1[i], matrix2);
			matrixes[i].start();

		}
		try {
			for (MatrixRowMultiplier matrix : matrixes) {
				// joins the threads together
				matrix.join();
			}
		} catch (InterruptedException e) {
		}

		int i = 0;
		for (MatrixRowMultiplier matrix : matrixes) {
			for (int j = 0; j < matrix.getResult().length; j++) {
				result[i][j] = matrix.getResult()[j];
			}
			i++;
		}
	}
}