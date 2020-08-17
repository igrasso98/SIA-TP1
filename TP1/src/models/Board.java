package models;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Board {
    private Set<Coordinate> deadlocks;
    private Set<Coordinate> walls;
    private Set<Coordinate> goals;

    public Board(Set<Coordinate> deadlocks, Set<Coordinate> walls, Set<Coordinate> goals) {
        this.deadlocks = deadlocks;
        this.walls = walls;
        this.goals = goals;
    }

    public Set<Coordinate> getDeadlocks() {
        return deadlocks;
    }

    public Set<Coordinate> getWalls() {
        return walls;
    }

    public Set<Coordinate> getGoals() {
        return goals;
    }

    public List<Directions> getValidDirections(BoardStatus boardStatus) {
        Coordinate player = boardStatus.getPlayer();
        List<Directions> validDirections = new ArrayList<>();
        for (Directions dir : Directions.values()) {
            Coordinate newCoordinate = new Coordinate(player.getX(), player.getY());
            newCoordinate.move(dir);
            //CHEQUEAR DEADLOCK in board
            if (!walls.contains(newCoordinate)) {
                if (boardStatus.getBoxes().contains(newCoordinate)) {
                    Coordinate newBoxCoordinate = new Coordinate(newCoordinate.getX(), newCoordinate.getY());
                    newBoxCoordinate.move(dir);
                    if (!walls.contains(newBoxCoordinate) && !boardStatus.getBoxes().contains(newBoxCoordinate) && !isDeadlock(newBoxCoordinate, boardStatus.getBoxes(), dir)) {
                        validDirections.add(dir);
                    } else {
                        printBoard(boardStatus);
                        System.out.println(boardStatus.getGoals().values());
                    }
                } else {
                    validDirections.add(dir);
                }
            }
        }
        return validDirections;
    }

    private boolean isDeadlock(Coordinate coordinate, Set<Coordinate> boxes, Directions direction) {
        if(deadlocks.contains(coordinate)) {
            return true;
        }

        Coordinate oldCoordinate = new Coordinate(coordinate.getX(), coordinate.getY());
        oldCoordinate.move(direction.getOpossite());
        for(Directions dir : Directions.values()) {
            if(dir != direction.getPartner()) {
                Coordinate firstDirection = new Coordinate(coordinate.getX(), coordinate.getY());
                Coordinate secondDirection = new Coordinate(coordinate.getX(), coordinate.getY());
                firstDirection.move(dir);
                secondDirection.move(dir.getPartner());
                if(!firstDirection.equals(oldCoordinate) && !secondDirection.equals(oldCoordinate)) {
                    if(edgePosition(firstDirection, boxes) && edgePosition(secondDirection, boxes)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean edgePosition(Coordinate coordinate, Set<Coordinate> boxes) {
//        boolean aux1 =  walls.contains(coordinate);
//        boolean aux2 = boxes.contains(coordinate);
        return walls.contains(coordinate) || boxes.contains(coordinate);
    }

    public void printBoard(BoardStatus currentStatus){
        Map<Coordinate,String> board = new HashMap<>();
        int width = 0;
        int height = 0;

        for(Coordinate wall : this.walls){
            board.put(wall,"#");
            if(wall.getX() >= width){
                width = wall.getX();
            }
            if(wall.getY() >= height){
                height = wall.getY();
            }
        }

        for(Coordinate goal : this.goals){
            board.put(goal,"L");
        }

        for(Coordinate box : currentStatus.getBoxes()){
            if(board.containsKey(box)){
                board.replace(box,"E");
            }
            else{
                board.put(box,"F");
            }
        }

        if(board.containsKey(currentStatus.getPlayer())){
            board.replace(currentStatus.getPlayer(),"Ł");
        }
        else{
            board.put(currentStatus.getPlayer(), "/");
        }

        for(int i=0 ; i <= width ; i++){
            for(int j=0 ; j <=height ; j++){
                Coordinate aux = new Coordinate(i,j);
                if(board.containsKey(aux)){
                    System.out.print(board.get(aux));
                } else {
                    System.out.print("_");
                }
            }
            System.out.println();
        }
    }
}