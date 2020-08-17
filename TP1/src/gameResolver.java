import engines.AstarEngine;
import engines.BFSEngine;
import engines.DFSEngine;
import heuristics.*;
import engines.GreedyEngine;
import models.*;
import models.Board;
import models.BoardStatus;
import models.Coordinate;
import models.Node;
import models.*;
import parser.BoardParser;

import java.util.*;

public class gameResolver {
    public static void main(String[] args) {
         Set<Coordinate> boxes = new HashSet<>();
         Map<Coordinate, Boolean> goals = new HashMap<>();
         Coordinate player = new Coordinate(3,3);

        Set<Coordinate> walls = new HashSet<>();
        walls.add(new Coordinate(0,0));
        walls.add(new Coordinate(0,1));
        walls.add(new Coordinate(0,2));
        walls.add(new Coordinate(0,3));
        walls.add(new Coordinate(0,4));
        walls.add(new Coordinate(0,5));
        walls.add(new Coordinate(0,6));

        walls.add(new Coordinate(1,0));
        walls.add(new Coordinate(1,6));

        walls.add(new Coordinate(2,0));
        walls.add(new Coordinate(2,6));

        walls.add(new Coordinate(3,0));
        walls.add(new Coordinate(3,6));

        walls.add(new Coordinate(4,0));
        walls.add(new Coordinate(4,6));

        walls.add(new Coordinate(5,0));
        walls.add(new Coordinate(5,6));

        walls.add(new Coordinate(6,0));
        walls.add(new Coordinate(6,1));
        walls.add(new Coordinate(6,2));
        walls.add(new Coordinate(6,3));
        walls.add(new Coordinate(6,4));
        walls.add(new Coordinate(6,5));
        walls.add(new Coordinate(6,6));

        char[][] boardMatrix = {{'#', '#', '#', '#', '#', '#', '#'},
                                {'#', '*', ' ', ' ', ' ', '*', '#'},
                                {'#', ' ', '$', '$', '$', ' ', '#'},
                                {'#', ' ', '$', '@', '$', ' ', '#'},
                                {'#', '*', '$', '$', '$', '*', '#',},
                                {'#', '*', '*', ' ', '*', '*', '#',},
                                {'#', '#', '#', '#', '#', '#', '#',}};
        Set<Coordinate> goalsUbication = new HashSet<>();
        goalsUbication.add(new Coordinate(1,1));
        goalsUbication.add(new Coordinate(1,5));
        goalsUbication.add(new Coordinate(4,1));
        goalsUbication.add(new Coordinate(4,5));
        goalsUbication.add(new Coordinate(5,1));
        goalsUbication.add(new Coordinate(5,2));
        goalsUbication.add(new Coordinate(5,4));
        goalsUbication.add(new Coordinate(5,5));

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

        boxes = BoardParser.getBoxes(boardMatrix);
//        System.out.println(boxes);
        BoardStatus initialStatus = new BoardStatus(boxes,goals,player);

        Node root = new Node(initialStatus,0,0);
        Set<BoardStatus> movements = new LinkedHashSet<>();
        movements.add(root.getStatus());
        root.setMovements(movements);

//        DFSEngine dfs = new DFSEngine();
//        Answer answer = dfs.perform(root,board);
//        System.out.println(answer.toString());

        long currentTime = System.currentTimeMillis();
//        BFSEngine bfs = new BFSEngine();
//        Node node = bfs.perform(root,board);
//        System.out.println(node.getDepth());
//        System.out.println(System.currentTimeMillis() - currentTime);

//        GreedyEngine greedy = new GreedyEngine();
////        Manhattan manhattan = new Manhattan();
//        Euclidean manhattan = new Euclidean();
//        Node node = greedy.perform(root, board, manhattan);
//        System.out.println(node.getDepth());
//        System.out.println(System.currentTimeMillis() - currentTime);

        GreedyEngine greedy = new GreedyEngine();
        Manhattan manhattan = new Manhattan();
        Answer answer = greedy.perform(root, board, manhattan);
        System.out.println(answer.toString());
    }
}
