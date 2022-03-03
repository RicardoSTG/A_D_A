public class Floyd_Warshall {
	public int I = 99999;
	public int n = 5;
	public int[][] next = new int[n][n];

	public void FloydWarshall(int g[][]) {
		int[][] dist = new int[n][n];
		int i, j, k;
		dist = g;
		int[][] predMatr = new int[n][n];
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				if (i != j)
					predMatr[i][j] = j + 1;
		}
		for (k = 0; k < n; k++) {
			for (j = 0; j < n; j++) {
				for (i = 0; i < n; i++) {
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						predMatr[i][j] = predMatr[i][k];
					}
				}
			}
		}
		printSolution(dist);
		printResult(dist, predMatr);
	}

	public void printResult(int[][] dist, int[][] pred) {
		System.out.println("par     dist    path");
		for (int i = 0; i < pred.length; i++) {
			for (int j = 0; j < pred.length; j++) {
				if (i != j) {
					int u = i + 1;
					int v = j + 1;
					String path = String.format("%d -> %d    %2d     %s", u, v, (int) dist[i][j], u);
					do {
						u = pred[u - 1][v - 1];
						path += " -> " + u;
					} while (u != v);
					System.out.println(path);
				}
			}
		}
	}

	public void Path(int[][] dist, int[][] pred) {
		for (int i = 0; i < pred.length; i++) {
			for (int j = 0; j < pred.length; j++) {
				if (i != j) {
					int u = i + 1;
					int v = j + 1;
					String path = String.format("%d -> %d    %2d     %s", u, v, (int) dist[i][j], u);
					do {
						System.out.println("  " + pred[i][j]);
						u = pred[u - 1][v - 1];
						path += " -> " + u;
					} while (u != v);
					System.out.println(path);
				}
			}
		}

	}

	public void printSolution(int dist[][]) {
		System.out.println("Matriz siguiente: ");
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (dist[i][j] == I)
					System.out.print("I   \t");
				else
					System.out.print(dist[i][j] + "   \t");
			}
			System.out.println();
		}
	}
}