package Prueba;

import java.util.Random;

public class SnakeGameAndLadderGame {

    public static void main(String[] args) {
       
        int[] snakes = {14, 19, 24, 22}; // poosiciones de las serpientes
        int[] ladders = {3, 10, 9, 6}; // posiciones de las escaleras
        int[] snakesEnds = {4, 8, 16, 20}; // posiciones finales de las serpientes
        int[] ladderEnds = {11, 12, 18, 17}; // posiciones finales de las escaleras
        int[] board = new int[26]; // el tablero con 26 casillas (0 a 25)
        int playerPosition = 0; // posición inicial del jugador en 0
        boolean gameOver = false; // estado del juego

        // inicializar el tablero
        for (int i = 0; i < board.length; i++) {
            board[i] = i;
        }
        System.out.println("Inicia el juego");
        // ciclo del juego
        while (!gameOver) {

            // tirar el dado
            int dice = new Random().nextInt(6) + 1;
            System.out.println("Dado arroja " + dice);

            // mover al jugador
            playerPosition += dice;
            if (playerPosition >= board.length) {
                playerPosition = board.length - 1;
                System.out.println("Te has pasado de la meta. Retrocedes ");
            }
            System.out.println("Jugador avanza a cuadro " + board[playerPosition]);

            // comprobar si hay serpiente o escalera
            if (contains(snakes, board[playerPosition])) {
                int snakeIndex = getIndex(snakes, board[playerPosition]);
                playerPosition = getIndex(board, snakesEnds[snakeIndex]);
                System.out.println("Jugador desciende al cuadro " + board[playerPosition]);
            } else if (contains(ladders, board[playerPosition])) {
                int ladderIndex = getIndex(ladders, board[playerPosition]);
                playerPosition = getIndex(board, ladderEnds[ladderIndex]);
                System.out.println("Jugador sube por escalera a cuadro " + board[playerPosition]);
            }

            // comprobar si ha ganado el jugador
            if (playerPosition == board.length - 1) {
                System.out.println("Felicidades! Has llegado a la meta.");
                gameOver = true;
            }
        }
    }

    // método para comprobar si un valor está en un array
    public static boolean contains(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    // método para obtener el índice de un valor en un array
    public static int getIndex(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

}

