package practica5;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {
        List<intervalos_ponderados> ip = Arrays.asList(
                new intervalos_ponderados( 0, 6, 60 ),
                new intervalos_ponderados( 1, 4, 30 ),
                new intervalos_ponderados( 3, 5, 10 ),
                new intervalos_ponderados( 5, 7, 30 ),
                new intervalos_ponderados( 5, 9, 50 ),
                new intervalos_ponderados( 7, 8, 10 )
        );
 
        System.out.print("El mas util es " + masUtil(ip));
    }
	
	public static int ultimoSinConflicto(List<intervalos_ponderados> ip, int n) {
        
        for (int i = n - 1; i >= 0; i--) {
            if (ip.get(i).fin <= ip.get(n).inicio) {
                return i;
            }
        }
        
        return -1;
    }

    public static int masUtil(List<intervalos_ponderados> ip) {
        
        Collections.sort(ip, Comparator.comparingInt(x -> x.fin));
 
        int n = ip.size();
 
        if (n == 0) {
            return 0;
        }

        int[] masUtil = new int[n];
 
        masUtil[0] = ip.get(0).util;

        for (int i = 1; i < n; i++) {

            int index = ultimoSinConflicto(ip, i);

            int incl = ip.get(i).util;
            if (index != -1) {
                incl += masUtil[index];
            }

            masUtil[i] = Math.max(incl, masUtil[i - 1]);
        }

        return masUtil[n - 1];
    }
 
}
