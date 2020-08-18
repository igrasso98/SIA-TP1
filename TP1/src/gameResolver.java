import heuristics.*;
import models.*;
import engines.*;
import models.Board;
import models.BoardStatus;
import models.Coordinate;
import models.Node;
import parser.BoardParser;

import java.util.*;

public class gameResolver {
    public static void main(String[] args) {
         Map<Coordinate, Boolean> goals = new HashMap<>();

        char[][] boardMatrix = {{'#', '#', '#', '#', '#', '#', '#'},
                                {'#', '*', ' ', ' ', ' ', '*', '#'},
                                {'#', ' ', '$', '$', '$', ' ', '#'},
                                {'#', ' ', '$', '@', '$', ' ', '#'},
                                {'#', '*', '$', '$', '$', '*', '#',},
                                {'#', '*', '*', ' ', '*', '*', '#',},
                                {'#', '#', '#', '#', '#', '#', '#',}};
        char[][] boardMatrix2 = {{'#', '#', '#', '#', '#', '#', '#'},
                                 {'#', '@', ' ', ' ', ' ', ' ', '#'},
                                 {'#', ' ', ' ', '$', ' ', ' ', '#'},
                                 {'#', ' ', ' ', ' ', ' ', '*', '#'},
                                 {'#', '#', '#', '#', '#', '#', '#',}};
        Set<Coordinate> walls = BoardParser.getObject(boardMatrix, '#');
        Set<Coordinate> goalsUbication = BoardParser.getObject(boardMatrix, '*');
        Set<Coordinate> boxes = BoardParser.getObject(boardMatrix, '$');
        Coordinate player = BoardParser.getPlayer(boardMatrix, '@');

        Set<Coordinate> deadlocks = BoardParser.findDeadlocks(boardMatrix);
        Board board = new Board(deadlocks,walls,goalsUbication);
        for (char[] aBoardMatrix : boardMatrix) {
            for (int j = 0; j < boardMatrix[0].length; j++) {
                System.out.print(aBoardMatrix[j] + "   ");
            }
            System.out.println();
        }


        for(Coordinate coordinate : goalsUbication) {
            goals.put(coordinate, false);
        }

        BoardStatus initialStatus = new BoardStatus(boxes,goals,player);

        Node root = new Node(initialStatus,0,0);
        Set<BoardStatus> movements = new LinkedHashSet<>();
        movements.add(root.getStatus());
        root.setMovements(movements);

        long currentTime;
        Manhattan manhattan = new Manhattan();
        Euclidean euclidean = new Euclidean();
        Answer node;

        DFSEngine dfs = new DFSEngine();
        currentTime = System.currentTimeMillis();
        node = dfs.perform(root,board);
        System.out.println("\nDFS Engine: ");
        System.out.println("\t" + node.toString());
        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);

        BFSEngine bfs = new BFSEngine();
        currentTime = System.currentTimeMillis();
        node = bfs.perform(root,board);
        System.out.println("\nBFS Engine: ");
        System.out.println("\t" + node.toString());
        System.out.println("\tTime: " + (System.currentTimeMillis() - currentTime)/1000);

        GreedyEngine greedy = new GreedyEngine();
        currentTime = System.currentTimeMillis();
        node = greedy.perform(root, board, manhattan);
        System.out.println("\nGreedy Engine: Manhattan");
        System.out.println("\t" + node.toString());
        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);

        node = greedy.perform(root, board, euclidean);
        System.out.println("\nGreedy Engine: Euclidean");
        System.out.println("\t" + node.toString());
        System.out.println("\tTime: " + (System.currentTimeMillis() - currentTime)/1000);

        AstarEngine astarEngine = new AstarEngine();
        currentTime = System.currentTimeMillis();
        Node node = astarEngine.perform(root, board, manhattan);
        System.out.println("\nAStar Engine: Manhattan");
        System.out.println("\tDepth:" + node.getDepth());
        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);

        node = astarEngine.perform(root, board, euclidean);
        System.out.println("\nAStar Engine: Euclidean");
        System.out.println("\tDepth:" + node.getDepth());
        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);

        IDDFSEngine iddfs = new IDDFSEngine();
        currentTime = System.currentTimeMillis();
        node = iddfs.perform(root, board, 100);
        System.out.println("\nIDDFS Engine Engine:");
        System.out.println("\tDepth:" + node.getDepth());
        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);

        IDAstarEngine idAstarEngine = new IDAstarEngine();
        currentTime = System.currentTimeMillis();
        node = idAstarEngine.perform(root, board, euclidean, 100);
        System.out.println("\nIDAStar Engine: Euclidean");
        System.out.println("\tDepth:" + node.getDepth());
        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);

        node = idAstarEngine.perform(root, board, manhattan, 100);
        System.out.println("\nIDAStar Engine: Manhattan");
        System.out.println("\tDepth:" + node.getDepth());
        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);

    }
}

// heruistica
// costo
// config archivo
// definir mapas
// informe + ppt
// rep visual