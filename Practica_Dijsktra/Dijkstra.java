
public class Dijkstra {
  public static void dijkstra(int[][] graf, int fuente) {
    int cont = graf.length;
    boolean[] verticeVisitado = new boolean[cont];
    int[] distancia = new int[cont];
    for (int i = 0; i < cont; i++) {
      verticeVisitado[i] = false;
      distancia[i] = Integer.MAX_VALUE;
    }

    distancia[fuente] = 0; // La distancia del bucle propio es cero
    for (int i = 0; i < cont; i++) {

      // Actualiza la distancia entre el vértice vecino y el vértice de origen
      int u = findMinDistance(distancia, verticeVisitado);
      verticeVisitado[u] = true;

      // Actualiza todas las distancias de los vértices vecinos
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
  
  // Clase para encontrar la distancia mínima
  private static int findMinDistance(int[] distancia, boolean[] verticeVisitado) {
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

  public static void main(String[] args) {
    int graf[][] = new int[][] { { 0, 0, 1, 2, 0, 0, 0 }, { 0, 0, 2, 0, 0, 3, 0 }, { 1, 2, 0, 1, 3, 0, 0 },
        { 2, 0, 1, 0, 0, 0, 1 }, { 0, 0, 3, 0, 0, 2, 0 }, { 0, 3, 0, 0, 2, 0, 1 }, { 0, 0, 0, 1, 0, 1, 0 } };
    Dijkstra T = new Dijkstra();
    T.dijkstra(graf, 0);
  }
}