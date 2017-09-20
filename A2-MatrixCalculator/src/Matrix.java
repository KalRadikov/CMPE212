import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Matrix {
	private int m;
	private int n;
	private double a[][];

	public Matrix(int m, int n) {
		this.m = m;
		this.n = n;
		a = new double[m][n];
	}

	public Matrix() {
		Scanner in = new Scanner(System.in);
		System.out.println("Number of row:");
		m = in.nextInt();
		System.out.println("Number of col:");
		n = in.nextInt();
		a = new double[m][n];

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.println("a[" + i + "][" + j + "]=?");
				a[i][j] = in.nextDouble();
			}
		}
	}

	public Matrix(String fileName) {
		Scanner in = null;
		try {
			in = new Scanner(new File(fileName));
			String line = in.nextLine();
			String sp[] = line.split(",");
			m = Integer.parseInt(sp[0]);
			n = Integer.parseInt(sp[1]);
			a = new double[m][n];

			for (int i = 0; i < a.length; i++) {
				String line2 = in.nextLine();
				String sp2[] = line2.split(",");

				for (int j = 0; j < a[i].length; j++) {

					a[i][j] = Double.parseDouble(sp2[j]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public double get(int i, int j) {
		return a[i][j];
	}

	public int getM() {
		return m;
	}

	public int getN() {
		return n;
	}

	public void print(String file) {
		try {
			PrintWriter out = new PrintWriter(new File(file));
			out.print(this);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		String s = "";
		s += m + "," + n + "\n";
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				s += a[i][j];
				if (j != a[i].length - 1)
					s += ",";
				else
					s += "\n";
			}
		}

		return s;
	}

	public static Matrix identity(int size) {
		Matrix m = new Matrix(size, size);
		for (int i = 0; i < m.a.length; i++) {
			m.a[i][i] = 1;
		}
		return m;
	}

	Matrix add(Matrix m) {
		Matrix res = new Matrix(m.m, m.n);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				res.a[i][j] = a[i][j] + m.a[i][j];
			}
		}
		return res;
	}

	 Matrix subtract(Matrix m) {
		Matrix res = new Matrix(m.m, m.n);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				res.a[i][j] = a[i][j] - m.a[i][j];
			}
		}
		return res;
	}

	Matrix multiply(Matrix m) {
		Matrix res = new Matrix(this.m, m.n);
		for (int i = 0; i < res.m; i++) {
			for (int j = 0; j < res.n; j++) {
				for (int k = 0; k < this.n; k++) {
					res.a[i][j] += a[i][k] * m.a[k][j];
				}
			}
		}
		return res;
	}

	Matrix multiply(double x) {
		Matrix res = new Matrix(this.m, this.n);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				res.a[i][j] = x * a[i][j];
			}
		}
		return res;
	}

	Matrix divide(Matrix m) {
		Matrix b = m.inverse();
		return multiply(b);
	}

	// extract submatrix by eliminating row x and col y
	private Matrix subMatrix(int x, int y) {
		Matrix r = new Matrix(m - 1, n - 1);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if (i == x || j == y)
					continue;
				int newI = 0;
				int newJ = 0;

				if (i < x)
					newI = i;
				else
					newI = i - 1;

				if (j < y)
					newJ = j;
				else
					newJ = j - 1;

				r.a[newI][newJ] = a[i][j];

			}
		}
		return r;
	}

	double determinant() {
		if (m == 1)
			return a[0][0];
		double res = 0;
		for (int i = 0; i < a.length; i++) {
			double x = a[0][i] * subMatrix(0, i).determinant();
			if (i % 2 == 0)
				res += x;
			else
				res -= x;
		}
		return res;
	}

	Matrix inverse() {
		Matrix r = new Matrix(m, n);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				r.a[i][j] = this.subMatrix(i, j).determinant();
				if ((i + j) % 2 != 0)
					r.a[i][j] = -1 * r.a[i][j];
			}
		}
		r = r.transpose().multiply(1 / this.determinant());
		return r;
	}

	boolean isSquare() {
		return m == n;
	}

	Matrix transpose() {
		Matrix res = new Matrix(n, m);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				res.a[j][i] = a[i][j];
			}
		}
		return res;
	}

	@Override
	public boolean equals(Object obj) {
		Matrix m = (Matrix) obj;
		if(this.m != m.m || this.n != m.n)
			return false;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (Math.abs(a[i][j]-m.a[i][j])>0.0001)
					return false;
			}
		}
		return true;
	}
}
