import java.util.*;
public class MCM_Euclides {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese el primer numero");
		int num1 = scan.nextInt();
		System.out.println("Ingrese el segundo numero");
		int num2 = scan.nextInt();
		int min = Math.min(num1, num2);// Seleccionamos el menor entre num1 y num2
		int mcm = 0;

		for (int i = 1; i <= min; i++) {
			if (num1 % i == 0 && num2 % i == 0) {// Condición para saber si el módulo de num1 y num2 es equivalente a 0
				int mcd = i;//el Máximo Común Divisor será solamente el último número que las cumpla antes de finalizar ciclo
				mcm = (num1 * num2) / mcd;// Se calcula el mínimo común múltiplo
			}
		}
		System.out.println("El M.C.M. entre " + num1 + " y " + num2 + " es: " + mcm);
	}

}