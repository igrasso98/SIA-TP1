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
            char[][] map = getBoard(filePath);

            Map<String, Set<Coordinate>> elements = BoardParser.getElements(map);
            Set<Coordinate> deadlocks = BoardParser.findDeadlocks(map);
            Board board = new Board(deadlocks, elements.get("walls"), elements.get("goals"));

            BoardStatus initialStatus = initializeStatus(board, elements);
            Node root = initializeRoot(initialStatus);

            long timeLimit = (Long) engineInfo.get("timelimit");
            Engines engine = (Engines) engineInfo.get("algorithm-name");
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
        int cols = getColumns(path);
        int rows = getRows(path) + 1;
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

    private static int getColumns(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        int i;
        int size = 0;
        while ((i=fileReader.read()) != -1 && (char)i != '\n') {
            size++;
        }
        return size;
    }

    private static int getRows(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        int i;
        int size = 0;
        while ((i=fileReader.read()) != -1) {
            if((char) i == '\n') {
                size++;
            }
        }
        return size;
    }
}
