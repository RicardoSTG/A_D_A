public class Bellman_Ford {
	public void BellmanFord(int graf[][], int V, int E, int src) {
		
		int[] dis = new int[V];
		for (int i = 0; i < V; i++)
			dis[i] = Integer.MAX_VALUE;
		
		dis[src] = 0;

		for (int i = 0; i < V - 1; i++) {
			for (int j = 0; j < E; j++) {
				if (dis[graf[j][0]] != Integer.MAX_VALUE && dis[graf[j][0]] + graf[j][2] < dis[graf[j][1]])
					dis[graf[j][1]] = dis[graf[j][0]] + graf[j][2];
			}
		}

		for (int i = 0; i < E; i++) {
			int x = graf[i][0];
			int y = graf[i][1];
			int peso = graf[i][2];
			if (dis[x] != Integer.MAX_VALUE && dis[x] + peso < dis[y])
				System.out.println("El gráfico contiene negativo" + " ciclo de peso");
		}

		System.out.println("Distancia del vértice desde el origen");
		for (int i = 0; i < V; i++)
			System.out.println(i + "\t\t" + dis[i]);
	}
}