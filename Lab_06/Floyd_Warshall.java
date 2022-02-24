public class Floyd_Warshall {
	static int I = 99999;
	static int n = 5;
	public static int[][] next = new int[n][n];

	static void FloydWarshall(int g[][]) {
		int[][] dist = new int[n][n];
		int i, j, k;
		dist = g;
		int[][] predMatr = new int[n][n];
		/*
		 Matriz predecesora: se define como la predecesora del vértice i en un mínimo
		 camino desde el vértice j con todos los vértices intermedios en el conjunto 1,2,...,k
		 */
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				if (i != j)
					predMatr[i][j] = j + 1;
		}
		for (k = 0; k < n; k++) {
			for (j = 0; j < n; j++) {
				for (i = 0; i < n; i++) {
					/*Si el vértice k está en el camino más corto desde
					 i a j, luego actualice el valor de dist[i][j]
					 */
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

	static void printResult(int[][] dist, int[][] pred) {
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

	static void Path(int[][] dist, int[][] pred) {
	//el bucle salta accede a la matriz cuando i=j (0,0)(1,1)(2,2)...(n,n)
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

	static void printSolution(int dist[][]) {
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

	public static void main(String[] args) {
		/* 	Asumir una representación de matriz de adyacencia
			Supongamos que los vértices están numerados 1,2,…,n
			La entrada es una matriz n x n
		*/	
		int graph2[][] = { { 0, 3, 8, I, -4 }, { I, 0, I, 1, 7 }, { I, 4, 0, I, I }, { 2, I, -5, 0, I },
				{ I, I, I, 6, 0 } };
		int graph3[][] = { { 0, I, -2, I }, { 4, 0, 3, I }, { I, I, 0, 2 }, { I, -1, I, 0 } };

		int[][] weights = { { 1, 3, -2 }, { 2, 1, 4 }, { 2, 3, 3 }, { 3, 4, 2 }, { 4, 2, -1 } };

		printSolution(graph2);
		System.out.println("\n\tMatriz de solución:");
		FloydWarshall(graph2);

	}