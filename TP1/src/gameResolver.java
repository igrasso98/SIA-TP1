import Heuristics.Manhattan;
import engines.BFSEngine;
import engines.DFSEngine;
import engines.GreedyEngine;
import models.Board;
import models.BoardStatus;
import models.Coordinate;
import models.Node;

import java.util.*;

public class gameResolver {
    public static void main(String[] args) {
         Set<Coordinate> boxes = new HashSet<>();
         boxes.add(new Coordinate(2,2));
         Map<Coordinate, Boolean> goals = new HashMap<>();
         goals.put(new Coordinate(3,3), false);
         Coordinate player = new Coordinate(1,1);

         Set<Coordinate> deadlocks = new HashSet<>();
        Set<Coordinate> walls = new HashSet<>();
        walls.add(new Coordinate(0,0));
        walls.add(new Coordinate(1,0));
        walls.add(new Coordinate(2,0));
        walls.add(new Coordinate(3,0));
        walls.add(new Coordinate(4,0));
        walls.add(new Coordinate(0,1));
        walls.add(new Coordinate(4,1));
        walls.add(new Coordinate(0,2));
        walls.add(new Coordinate(4,2));
        walls.add(new Coordinate(0,3));
        walls.add(new Coordinate(4,3));
        walls.add(new Coordinate(0,4));
        walls.add(new Coordinate(1,4));
        walls.add(new Coordinate(2,4));
        walls.add(new Coordinate(3,4));
        walls.add(new Coordinate(4,4));

        Set<Coordinate> goalsUbication = new HashSet<>();
        goalsUbication.add(new Coordinate(3,3));
        Board board = new Board(deadlocks,walls,goalsUbication);

        BoardStatus initialStatus = new BoardStatus(boxes,goals,player);

        Node root = new Node(initialStatus,0,0);
        Set<BoardStatus> movements = new LinkedHashSet<>();
        movements.add(root.getStatus());
        root.setMovements(movements);

        DFSEngine dfs = new DFSEngine();
        Node node = dfs.perform(root,board);
        System.out.println(node.getDepth());

//        BFSEngine bfs = new BFSEngine();
//        Node node = bfs.perform(root,board);
//        System.out.println(node.getDepth());

//        GreedyEngine greedy = new GreedyEngine();
//        Manhattan manhattan = new Manhattan();
//        Node node = greedy.perform(root, board, manhattan);
    }
}
