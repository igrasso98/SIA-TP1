package parser;

import models.Agents;
import models.Coordinate;
import models.Directions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BoardParser {
    public static Set<Coordinate> findDeadlocks(char[][] board) {
        Set<Coordinate> deadlocks = new HashSet<>();
        int boardWidth = board[0].length;
        int boardHeight = board.length;
        for(int i = 0; i < boardHeight; i++) {
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
            int x1 = i + dir.getX(), y1 = j + dir.getY();
            int x2 = i + dir.getPartner().getX(), y2 = j + dir.getPartner().getY();
            int height = board.length, width = board[0].length;
            if(validCoordinates(x1,y1,height,width) && validCoordinates(x2,y2,height,width)) {
                if(board[x1][y1] == '#' && board[x2][y2] == '#') {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean validCoordinates(int x, int y, int height, int width) {
        return x < height && y < width && x > 0 && y > 0;
    }

    public static Map<String, Set<Coordinate>> getElements(char[][] board) {
        int boardWidth = board[0].length;
        int boardHeight = board.length;

        Map<Character, String> dictionary = new HashMap<>();
        dictionary.put(Agents.WALL.getValue(), "walls");
        dictionary.put(Agents.BOX.getValue(), "boxes");
        dictionary.put(Agents.GOALS.getValue(), "goals");
        dictionary.put(Agents.PLAYER.getValue(), "player");

        Map<String, Set<Coordinate>> elements = new HashMap<>();
        elements.put("walls", new HashSet<>());
        elements.put("boxes", new HashSet<>());
        elements.put("goals", new HashSet<>());
        elements.put("player", new HashSet<>());


        for(int i = 0; i < boardHeight; i++) {
            for(int j = 0; j < boardWidth; j++) {
                if(dictionary.containsKey(board[i][j])) {
                    String agent = dictionary.get(board[i][j]);
                    elements.get(agent).add(new Coordinate(i, j));
                }
            }
        }
        return elements;
    }

}
