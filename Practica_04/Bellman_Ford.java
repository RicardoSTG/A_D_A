public class Bellman_Ford {
	
	static void BellmanFord(int graf[][], int V, int E, int src) {
		
		int[] dis = new int[V];//  Inicializa la distancia de todos los vértices como infinito.
		for (int i = 0; i < V; i++)
			dis[i] = Integer.MAX_VALUE;
		
		dis[src] = 0;// Inicializa la distancia de la fuente como 0

		for (int i = 0; i < V - 1; i++) {
			for (int j = 0; j < E; j++) {
				if (dis[graf[j][0]] != Integer.MAX_VALUE && dis[graf[j][0]] + graf[j][2] < dis[graf[j][1]])
					dis[graf[j][1]] = dis[graf[j][0]] + graf[j][2];
			}
		}

		// Comprueba si hay ciclos de peso negativo.
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

	public static void main(String[] args) {
		int V = 5; // Número de vértices en el gráfico.
		int E = 8; // Número de aristas en el gráfico.

		/*Cada arista tiene tres valores (u, v, w) donde
	    la arista es del vértice u al v. Y peso
	    del borde es w.*/
		int graf[][] = { { 0, 1, -1 }, { 0, 2, 4 }, 
						{ 1, 2, 3 }, { 1, 3, 2 }, 
						{ 1, 4, 2 }, { 3, 2, 5 }, 
						{ 3, 1, 1 }, { 4, 3, -3 } };

		File gnu2 = new File ("BellmanFord.txt");
		gnu2.createNewFile();
    	BufferedWriter bw2 = new BufferedWriter(new FileWriter(gnu2));
    	long Tinicio2, Tfinal2;
    	long tiempo2;
    	Tinicio2 = System.nanoTime();
    	BellmanFord(graf, V, E, 0);
    	Tfinal2 = System.nanoTime();
    	tiempo2 = Tfinal2-Tinicio2;
    	bw2.write(tiempo2+"\n");
    	bw2.close();
    	Desktop.getDesktop().open(gnu2);
	}
}