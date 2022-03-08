import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Prim {
	static class Borde {
		int hacia;
		int peso;

		public Borde(int hacia, int peso) {
			this.hacia = hacia;
			this.peso = peso;
		}
	}

	static class E {
		int vertice;
		int min;

		public E(int vertice, int min) {
			this.vertice = vertice;
			this.min = min;
		}
	}

	private static void findPrintMST(ArrayList<Borde> grafo[]) {
		// arbol de expansion minimo = MST
		int n = grafo.length;
		int principal[] = new int[n];
		int minBorde[] = new int[n];
		boolean MST[] = new boolean[n];
		for (int i = 0; i < n; i++) {
			minBorde[i] = Integer.MAX_VALUE;
			MST[i] = false;
		}
		minBorde[0] = 0;
		principal[0] = -1;

		PriorityQueue<E> minSuperior = new PriorityQueue<>(new Comparator<E>() {
			@Override
			public int compare(E o1, E o2) {
				return Integer.compare(o1.min, o2.min);
			}
		});
		for (int i = 0; i < n; i++)
			minSuperior.add(new E(i, minBorde[i]));
		while (!minSuperior.isEmpty()) {
			int u = minSuperior.poll().vertice;
			for (int j = 0; j < grafo[u].size(); j++) {
				Borde curr = grafo[u].get(j);
				if (minBorde[curr.hacia] > curr.peso && !MST[curr.hacia]) {
					minBorde[curr.hacia] = curr.peso;
					principal[curr.hacia] = u;
				}
			}
		}
		for (int i = 1; i < n; i++) {
			System.out.println("El Borde de " + principal[i] + " hacia " + i + " pesa " + minBorde[i]);
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		ArrayList<Borde> grafo[] = new ArrayList[4];
		for (int i = 0; i < 4; i++)
			grafo[i] = new ArrayList<>();

		grafo[0].add(new Borde(1, 5));
		grafo[0].add(new Borde(2, 8));
		grafo[1].add(new Borde(0, 5));
		grafo[1].add(new Borde(2, 10));
		grafo[1].add(new Borde(3, 15));
		grafo[2].add(new Borde(0, 8));
		grafo[2].add(new Borde(1, 10));
		grafo[2].add(new Borde(3, 20));
		grafo[3].add(new Borde(1, 15));
		grafo[3].add(new Borde(2, 20));
		findPrintMST(grafo);
	}

	static class Edge {
		int to;
		int weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	static class Element {
		int vertex;
		int minEdgeCutValue;

		public Element(int vertex, int minEdgeCutValue) {
			this.vertex = vertex;
			this.minEdgeCutValue = minEdgeCutValue;
		}
	}
}
