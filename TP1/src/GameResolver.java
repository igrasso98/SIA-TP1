import engines.EngineType;
import engines.Engines;
import models.*;
import parser.BoardParser;
import utils.FilesInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GameResolver {
    public static Answer resolve(Map<String, Object> engineInfo, Map<String, Object> levelInfo) {
        try {
            String filePath = new File("").getAbsolutePath();
            filePath += FilesInfo.baseLevelsPath + levelInfo.get("level-number") + FilesInfo.levelExtension;
            FileReader fileReader = new FileReader(filePath);
            int i;
            while ((i=fileReader.read()) != -1) {
                char c = (char) i;
                System.out.print(c);
            }

            char[][] map = getBoard(filePath);
//            for(char[] row : map) {
//                for(i = 0; i < row.length; i++) {
//                    System.out.print(row[i] + " ");
//                }
//                System.out.println();
//            }
            Map<String, Set<Coordinate>> elements = BoardParser.getElements(map);
            Set<Coordinate> deadlocks = BoardParser.findDeadlocks(map);
            Board board = new Board(deadlocks, elements.get("walls"), elements.get("goals"));

            BoardStatus initialStatus = initializeStatus(board, elements);
            Node root = initializeRoot(initialStatus);

            int timeLimit = (Integer) engineInfo.get("timelimit");
            Engines engine = (Engines) engineInfo.get("algorithm");
            if(engine != null && EngineType.contains(engine.toString())) {
                return engine.perform(root, board, timeLimit, engineInfo);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BoardStatus initializeStatus(Board board, Map<String, Set<Coordinate>> elements) {
        Coordinate player = (Coordinate) elements.get("player").toArray()[0];
        Map<Coordinate, Boolean> goals = new HashMap<>();
        for(Coordinate coordinate : elements.get("goals")) {
            goals.put(coordinate, false);
        }
        return new BoardStatus(elements.get("boxes"), goals, player);
    }

    private static Node initializeRoot(BoardStatus initialStatus) {
        Node root =  new Node(initialStatus,0,0);
        Set<BoardStatus> movements = new LinkedHashSet<>();
        movements.add(root.getStatus());
        root.setMovements(movements);

        return root;
    }

    private static char[][] getBoard(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        int i;
        int indexRow = 0, indexCols = 0;
        int cols = getColumns(fileReader);
        int rows = getRows(fileReader);
        char[][] table = new char[rows][cols];
        while ((i=fileReader.read()) != -1) {
            char c = (char) i;
            if(c == '\n') {
                indexCols = 0;
                indexRow++;
            } else {
                table[indexRow][indexCols++] = c;
            }
        }
        return table;
    }

    private static int getColumns(FileReader fr) throws IOException {
        int i;
        int size = 0;
        while ((i=fr.read()) != -1 && (char)i != '\n') {
            size++;
        }
        return size;
    }

    private static int getRows(FileReader fr) throws IOException {
        int i;
        int size = 0;
        while ((i=fr.read()) != -1) {
            int a = i;
            System.out.print((char) i);
            if((char) i == '\n') {
                size++;
            }
        }
        return size;
    }
}
