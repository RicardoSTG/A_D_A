import java.util.ArrayList;
import java.util.List;

public class Exercise {
	
	public class TreeNode {
		
		 int val;
		 TreeNode left;
		 TreeNode right;
		 
		 TreeNode(int x) { 
			 val = x;
		 }
		 
		 public String toString() {
			 return val+"";
		 }
	}

	String arbol="";
	List<TreeNode> list;
	int alt;
	String[] niveles;
	boolean a;

    public String allTrees(Integer n) {
    	
        if(n%2==1){
        	
        	ArrayList<TreeNode> arr = (ArrayList<TreeNode>) allPossibleT(n);

        	for(int i=0; i<arr.size(); i++) {
        		alt = calcularAltura(arr.get(i));
        		imprimirNivel(arr.get(i));
        	}
        	
        	return arbol;

        } else {
        	
        	return "";
        	
        }
    }
    
    public List<TreeNode> allPossibleT(int N) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        if (N == 1) {
            TreeNode root = new TreeNode(0);
            list.add(root);
            return list;
        }
        
        for(int i = 1; i < N; i+=2) {
        	
            List <TreeNode> lefts = allPossibleT (i); 
            List <TreeNode> rights = allPossibleT (N-i-1);
            
            for(TreeNode l : lefts)
                for(TreeNode r : rights){               
                    TreeNode root = new TreeNode(0);
                    root.left = l;
                    root.right = r;
                    list.add(root);
                }
        }
 
        return list;
    }
    
    public String impTree(TreeNode t) {
    	if(t!=null) {
    		arbol += t.toString();
    		if(t.left != null) {
    			
        		impTree(t.left);
        		impTree(t.right);
    		}  
    	} else if(calcularAltura(t)<=alt){
    		arbol+= "null,";
    	}
    	return arbol;
    }
      
    public int calcularAltura(TreeNode nodo) {
		if (nodo == null) {
			return 0;
		}
		else {
			int alturaIzquerda = calcularAltura(nodo.left);
			int alturaDercha = calcularAltura(nodo.right);
			if (alturaIzquerda > alturaDercha) {
				return (alturaIzquerda + 1);
			}
			else {
				return (alturaDercha + 1);
			}
		}
	}
    
    public void imprimirNivel(TreeNode t) {
        niveles = new String[alt];
        for(int i=0; i<alt; i++) {
        	niveles[i] = "";
        }
        imprimirNivel(t, 0);

        for(int i=0; i<niveles.length; i++) {
        	arbol += niveles[i]+" ";
        }
        arbol+="\n";
   
    }

    private void imprimirNivel(TreeNode t, int nivel2) {
        if (t != null) {
        	niveles[nivel2] += t.val+" ";
            if(nivel2<alt-1) {
            	a=true;
            	imprimirNivel(t.left, nivel2 + 1);
                imprimirNivel(t.right, nivel2 + 1);
            } 
        } else if(nivel2<alt && a==true){
        	niveles[nivel2] += "null null ";
        	a=false;
        }
    }

}