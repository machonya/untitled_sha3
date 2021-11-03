package rps;

import com.jakewharton.fliptables.FlipTable;

public class PrintHelpTable {
    public static void helpTable(String[] moves){
        String[] headers = new String[moves.length+1];
        headers[0] = "Player/\nComp";
        for (int i = 0; i < moves.length; i++){
            headers[i+1] = moves[i];
        }
        String[][] dataForHelp = new String[moves.length][headers.length];
        FindWinner findWinner = new FindWinner(moves);

        for (int i = 0; i < moves.length; i++){
            for (int j = 0; j < headers.length; j++){
                if (j == 0) {
                    dataForHelp[i][j] = moves[i];
                }else {
                    dataForHelp[i][j] = String.valueOf(findWinner.getResult(moves[i], headers[j]));
                }
            }
        }
        System.out.println(FlipTable.of(headers, dataForHelp));
    }
}
