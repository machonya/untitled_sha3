package rps;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;

public class GameRPS {
    private final Scanner scanner;


    public GameRPS() {
        scanner = new Scanner(System.in);
    }

    public void startGame() throws NoSuchAlgorithmException, InvalidKeyException {
        String[] moves = setMoves();
        String key = KeyGeneration.randomKeyGeneration();
        String computerMove = getComputerMove(moves);
        String hmac = KeyGeneration.calculateHmac(computerMove, key);
        System.out.println("HMAC: \n" + hmac);
        String playerMove = getPlayerMove(moves);
        System.out.println("Computer move is " + computerMove);
        FindWinner findWinner = new FindWinner(moves);
        System.out.println(findWinner.getResult(playerMove, computerMove));
        System.out.println("HMAC key: " + key.toUpperCase());
        System.out.println();
        playAgain();
        System.out.println();
    }

    public void getMenuGame(String[] moves) {
        System.out.println("Available moves:");
        int count = 1;
        for (String move : moves) {
            System.out.println(count + " - " + move);
            count++;
        }
        System.out.println("0 - exit \n? - help ");
    }

    public String[] setMoves() {
        System.out.println("Enter an odd number of game parameters (>= 3): ");
        int num = scanner.nextInt();
        if (num % 2 == 0 || num < 3) {
            setMoves();
        }
        System.out.println("Enter the parameters of the moves(one move = one word):");
        String[] nameTurns = new String[num];
        for (int i = 0; i < nameTurns.length; i++) {
            String move = scanner.next();
            nameTurns[i] = move;
            for (int j = 0; j < nameTurns.length; j++) {
                if (i != j && nameTurns[i].equals(nameTurns[j])) {
                    System.out.println("The parameters must be different! Enter new value: ");
                    String newMove = scanner.next();
                    nameTurns[i] = newMove;
                }
            }
        }
        return nameTurns;
    }


    public String getPlayerMove(String[] moves) {
        String numPlayerMove = null;
        getMenuGame(moves);
        System.out.print("Make your move : ");
        String playerTurn = scanner.next();
        if (playerTurn.equals("?")) {
            PrintHelpTable.helpTable(moves);
            getPlayerMove(moves);

        } else if (Integer.parseInt(playerTurn) >= 1 && Integer.parseInt(playerTurn) <= moves.length) {
            System.out.println("Your move is " + moves[Integer.parseInt(playerTurn) - 1]);
            numPlayerMove = moves[Integer.parseInt(playerTurn) - 1];
            return numPlayerMove;

        } else if (Integer.parseInt(playerTurn) == 0) {
            System.exit(0);
        } else {
            getMenuGame(moves);
        }
        return null;
    }

    public String getComputerMove(String[] moves) {
        SecureRandom secureRandom = new SecureRandom();
        int computerTurn = secureRandom.nextInt(moves.length);
        return moves[computerTurn];
    }


    public void playAgain() throws NoSuchAlgorithmException, InvalidKeyException {
        System.out.println("Do you want to play again? Enter Yes or No");
        String playerInput = scanner.next();
        playerInput = playerInput.toUpperCase();
        if (playerInput != null && playerInput.length() != 0 && playerInput.charAt(0) == 'Y') {
            startGame();
        } else {
            System.out.println("Game over");
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        GameRPS game = new GameRPS();
        game.startGame();
    }
}

