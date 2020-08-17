package parser;

import models.Board;
import models.Coordinate;
import models.Directions;

import java.util.HashSet;
import java.util.Set;

public class BoardParser {
    public static Board parseBoard() {


        return null;
    }

    public static Set<Coordinate> findDeadlocks(char[][] board) {
        Set<Coordinate> deadlocks = new HashSet<>();
        int boardWidth = board[0].length;
        int boardHeigh = board.length;
        for(int i = 0; i < boardHeigh; i++) {
            for(int j = 0; j < boardWidth; j++) {
                if(board[i][j] != '#' && board[i][j] != '*' && isDeadlock(board, i, j)) {
                    deadlocks.add(new Coordinate(i, j));
                }
            }
        }
        return deadlocks;
    }

    private static boolean isDeadlock(char[][] board, int i, int j) {
        for(Directions dir : Directions.values()) {
            if(board[i + dir.getX()][j + dir.getY()] == '#' && board[i + dir.getPartner().getX()][j + dir.getPartner().getY()] == '#') {
                return true;
            }
        }
        return false;
    }

    public static Set<Coordinate> getBoxes(char[][] board) {
        int boardWidth = board[0].length;
        int boardHeigh = board.length;

        Set<Coordinate> boxes = new HashSet<>();
        for(int i = 0; i < boardHeigh; i++) {
            for(int j = 0; j < boardWidth; j++) {
                if(board[i][j] == '$') {
                    boxes.add(new Coordinate(i, j));
                }
            }
        }

        return boxes;
    }


}
