package Prueba;

import java.util.ArrayList;
import java.util.List;
	// Clase elemento y constructor
class Elemento {
    int peso;
    int valor;

    public Elemento(int peso, int valor) {
        this.peso = peso;
        this.valor = valor;
    }
}
public class Mochila {

    // Función para resolver el problema de la mochila
    public static List<Elemento> resolverMochila(List<Elemento> elementos, int capacidadMaxima) {
        int n = elementos.size(); // Cantidad de elementos
        int[][] dp = new int[n + 1][capacidadMaxima + 1]; // Matriz para almacenar los resultados parciales

        // Llenar la matriz dp con los valores óptimos
        for (int i = 0; i <= n; i++) { // Recorrer filas
            for (int j = 0; j <= capacidadMaxima; j++) { // Recorrer columnas
                if (i == 0 || j == 0) {
                    // Caso base: si no hay elementos o capacidad es 0, valor es 0
                    dp[i][j] = 0;
                } else if (elementos.get(i - 1).peso <= j) {
                    // Si el peso del elemento actual es menor o igual a la capacidad actual
                    // Seleccionar el valor máximo entre incluir el elemento actual o excluirlo
                    dp[i][j] = Math.max(elementos.get(i - 1).valor + dp[i - 1][j - elementos.get(i - 1).peso], dp[i - 1][j]);
                } else {
                    // Si el peso del elemento actual es mayor a la capacidad actual
                    // El valor óptimo es el mismo que el valor obtenido hasta el elemento anterior
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Recuperar los elementos seleccionados en la mochila
        List<Elemento> elementosSeleccionados = new ArrayList<>();
        int i = n;
        int j = capacidadMaxima;
        while (i > 0 && j > 0) {
            if (dp[i][j] != dp[i - 1][j]) {
                // Si el valor en la matriz dp cambia en la misma columna, significa que el elemento actual fue seleccionado
                elementosSeleccionados.add(elementos.get(i - 1));
                j -= elementos.get(i - 1).peso; // Restar el peso del elemento actual a la capacidad
                i--; // Moverse a la fila anterior
            } else {
                // Si el valor en la matriz dp no cambia en la misma columna, significa que el elemento actual no fue seleccionado
                i--; // Moverse a la fila anterior
            }
        }

        return elementosSeleccionados;
    }


	    public static void main(String[] args) {
	        // Ejemplo de uso crea una lista llamada elementos y luego la llena.
	        List<Elemento> elementos = new ArrayList<>();
	        elementos.add(new Elemento(2, 10));
	        elementos.add(new Elemento(5, 20));
	        elementos.add(new Elemento(10, 30));
	        elementos.add(new Elemento(5, 40));
	        elementos.add(new Elemento(3, 50));
	        // Capacidad mochila para el ejemplo
	        int capacidadMaxima = 16;
	       
	        // Se usa resolverMochila y se le pasa la lista y la capacidad maxima, para que seleccione los elementos
	        List<Elemento> elementosSeleccionados = resolverMochila(elementos, capacidadMaxima);

	        System.out.println("Elementos seleccionados en la mochila: ");
	        // ForEach
	        for (Elemento elemento : elementosSeleccionados) {
	            System.out.println("Peso: " + elemento.peso + ", Valor: " + elemento.valor);
	        }
	    }
	}

