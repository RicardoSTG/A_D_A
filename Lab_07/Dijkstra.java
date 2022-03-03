public class Dijkstra {
	public void dijkstra(int[][] graf, int fuente) {
	    int cont = graf.length;
	    boolean[] verticeVisitado = new boolean[cont];
	    int[] distancia = new int[cont];
	    for (int i = 0; i < cont; i++) {
	      verticeVisitado[i] = false;
	      distancia[i] = Integer.MAX_VALUE;
	    }

	    distancia[fuente] = 0;
	    for (int i = 0; i < cont; i++) {

	      int u = findMinDistance(distancia, verticeVisitado);
	      verticeVisitado[u] = true;

	      for (int v = 0; v < cont; v++) {
	        if (!verticeVisitado[v] && graf[u][v] != 0 && (distancia[u] + graf[u][v] < distancia[v])) {
	          distancia[v] = distancia[u] + graf[u][v];
	        }
	      }
	    }
	    for (int i = 0; i < distancia.length; i++) {
	      System.out.println(String.format("La distancia de %s a %s es %s", fuente, i, distancia[i]));
	    }
	  }
	  
	public int findMinDistance(int[] distancia, boolean[] verticeVisitado) {
	    int minDist = Integer.MAX_VALUE;
	    int minDistanceVertex = -1;
	    for (int i = 0; i < distancia.length; i++) {
	      if (!verticeVisitado[i] && distancia[i] < minDist) {
	        minDist = distancia[i];
	        minDistanceVertex = i;
	      }
	    }
	    return minDistanceVertex;
	  }
}