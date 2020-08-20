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
        char[][] boardMatrix2 = {{'#', '#', '#', '#', '#', '#', '#'},

                                {'#', '*', ' ', ' ', ' ', '*', '#'},
                                {'#', ' ', '$', '$', '$', ' ', '#'},
                                {'#', ' ', '$', '@', '$', ' ', '#'},
                                {'#', '*', '$', '$', '$', '*', '#',},
                                {'#', '*', '*', ' ', '*', '*', '#',},
                                {'#', '#', '#', '#', '#', '#', '#',}};
        char[][] boardMatrix3 = {{'#', '#', '#', '#', '#', '#', '#'},
                                 {'#', '@', ' ', ' ', ' ', ' ', '#'},
                                 {'#', ' ', ' ', '$', ' ', ' ', '#'},
                                 {'#', ' ', ' ', ' ', ' ', '*', '#'},
                                 {'#', '#', '#', '#', '#', '#', '#',}};

        char[][] boardMatrix = {
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '*', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '*', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#'},
                {'#', '#', ' ', ' ', '#', ' ', '#', ' ', '#', ' ', '#', ' ', ' ', '#', '#'},
                {'#', ' ', ' ', '#', '#', ' ', ' ', ' ', ' ', ' ', '#', '#', ' ', '#', '#'},
                {'#', ' ', '#', '#', ' ', ' ', '#', ' ', '#', ' ', ' ', '#', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', '$', '@', '$', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', '#', '#', ' ', ' ', ' ', '#', '#', '#', ' ', ' ', ' ', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };

        char[][] boardMatrix5 = {
            {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ','$','$','$','$','$',' ','*',' ',' ',' ','*',' ','#'},
                {'#',' ',' ',' ','$',' ',' ',' ','*',' ','*',' ','*',' ','#'},
                {'#',' ',' ',' ','$',' ',' ',' ',' ','*',' ','*',' ',' ','#'},
                {'#',' ',' ',' ','@',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},

        };
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

        BoardStatus initialStatus = new BoardStatus(boxes,goals,player,0);

        Node root = new Node(initialStatus,0,0);
        Set<BoardStatus> movements = new LinkedHashSet<>();
        movements.add(root.getStatus());
        root.setMovements(movements);

        long currentTime;
        BoxesToGoals boxesToGoals = new BoxesToGoals();
        BoxMoved boxMoved = new BoxMoved();
        BoxesToGoalsMinDistance boxesToGoalsMinDistance = new BoxesToGoalsMinDistance();
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
        node = greedy.perform(root, board, boxesToGoals);
        System.out.println("\nGreedy Engine: BoxesToGoals");
        System.out.println(node.toString());
        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);

        node = greedy.perform(root, board, boxMoved);
        System.out.println("\nGreedy Engine: boxMoved");
        System.out.println(node.toString());
        System.out.println("\tTime: " + (System.currentTimeMillis() - currentTime)/1000);

        node = greedy.perform(root, board, boxesToGoalsMinDistance);
        System.out.println("\nGreedy Engine: BoxesToGoalsMinDistance");
        System.out.println(node.toString());
        System.out.println("\tTime: " + (System.currentTimeMillis() - currentTime)/1000);

        AstarEngine astarEngine = new AstarEngine();
        node = astarEngine.perform(root, board, boxesToGoalsMinDistance);
        System.out.println("\nAStar Engine: BoxesToGoalsMinDistance");
        System.out.println(node.toString());
        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);

        currentTime = System.currentTimeMillis();
        node = astarEngine.perform(root, board, boxesToGoals);
        System.out.println("\nAStar Engine: BoxesToGoals");
        System.out.println(node.toString());
        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);

        node = astarEngine.perform(root, board, boxMoved);
        System.out.println("\nAStar Engine: boxMoved");
        System.out.println(node.toString());
        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);



        IDDFSEngine iddfs = new IDDFSEngine();
        currentTime = System.currentTimeMillis();
        node = iddfs.perform(root, board, 100);
        System.out.println("\nIDDFS Engine:");
        System.out.println(node.toString());
        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);
//
        IDAstarEngine idAstarEngine = new IDAstarEngine();
        currentTime = System.currentTimeMillis();
        node = idAstarEngine.perform(root, board, boxesToGoals, 100);
        System.out.println("\nIDAStar Engine: BoxesToGoals");
        System.out.println(node.toString());
        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);

        node = idAstarEngine.perform(root, board, boxMoved, 100);
        System.out.println("\nIDAStar Engine: boxMoved");
        System.out.println(node.toString());
        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);

        node = idAstarEngine.perform(root, board, boxesToGoalsMinDistance, 100);
        System.out.println("\nIDAStar Engine: BoxesToGoalsMinDistance");
        System.out.println(node.toString());
        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);

    }
}

// heruistica
// costo
// config archivo
// definir mapas
// informe + ppt
// rep visual