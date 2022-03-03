import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
	
	public static void main(String[] args) throws IOException {
		
		  //Son los mismos grafos
		  int graf[][] = new int[][] { 
			  { 0, 0, 1, 2, 0, 0, 0 }, 
			  { 0, 0, 2, 0, 0, 3, 0 }, 
			  { 1, 2, 0, 1, 3, 0, 0 },
	          { 2, 0, 1, 0, 0, 0, 1 }, 
	          { 0, 0, 3, 0, 0, 2, 0 }, 
	          { 0, 3, 0, 0, 2, 0, 1 }, 
	          { 0, 0, 0, 1, 0, 1, 0 } };
	      //Son los mismos grafos  
	      int graf2[][] = new int[][] { 
	    	  {0,2,1}, {0,3,2},
	    	  {1,2,2}, {1,5,3},
	    	  {2,3,1}, {2,4,3},
	    	  {3,6,1}, {4,5,2},
	    	  {5,6,1}
	      };
	      
	    Dijkstra T = new Dijkstra();
	    File gnu = new File ("a.txt");
	    gnu.createNewFile();
	    FileWriter fw = new FileWriter(gnu.getAbsoluteFile(), true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    long Tinicio1, Tfinal1;
	    long tiempo;
	    Tinicio1 = System.currentTimeMillis();
	    T.dijkstra(graf, 0);
	    Tfinal1 = System.currentTimeMillis();
	    tiempo = Tfinal1-Tinicio1;
	    bw.write(tiempo+"\n");
	      
	    Bellman_Ford b = new Bellman_Ford();
	    long Tinicio2, Tfinal2;
	    long tiempo2;
	    Tinicio2 = System.currentTimeMillis();
	    b.BellmanFord(graf2, 7, 9, 0);
	    Tfinal2 = System.currentTimeMillis();
	    tiempo2 = Tfinal2-Tinicio2;
	    bw.write(tiempo2+"\n");
	    
	    Floyd_Warshall f = new  Floyd_Warshall();
	    long Tinicio3, Tfinal3;
	    long tiempo3;
	    Tinicio3 = System.currentTimeMillis();
	    f.printSolution(graf);
	    Tfinal3 = System.currentTimeMillis();
	    tiempo3 = Tfinal3-Tinicio3;
	    bw.write(tiempo3+"\n");
	    bw.close();
	    
	    Desktop.getDesktop().open(gnu);

	}

}