package matrix;

import java.util.Date;

public class TestMatrixMultiply {

	public static void main(String[] args) {
		
		//to measure the run time
		Date start = new Date();

		int[][] m1 = MatrixGenerator.generateMatrix(1000, 1000);
		int[][] m2 = MatrixGenerator.generateMatrix(1000, 1000);
		int[][] result = new int[m1.length][m2[0].length];
		MatrixMultiplication.matrixMultiply(m1, m2, result);

		Date end = new Date();
		System.out.println("\nTime taken in milli seconds: " + (end.getTime() - start.getTime()));

	}

}