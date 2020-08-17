package models;

import java.util.*;

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

    public void setDeadlocks(Set<Coordinate> deadlocks) {
        this.deadlocks = deadlocks;
    }

    public Set<Coordinate> getWalls() {
        return walls;
    }

    public void setWalls(Set<Coordinate> walls) {
        this.walls = walls;
    }

    public Set<Coordinate> getGoals() {
        return goals;
    }

    public void setGoals(Set<Coordinate> goals) {
        this.goals = goals;
    }

    public boolean validCoordinate(Coordinate coordinate, Directions dir) {
        return !deadlocks.contains(coordinate) && !walls.contains(coordinate);
    }

    public List<Directions> getValidDirections(BoardStatus boardStatus) {
        Coordinate player = boardStatus.getPlayer();
        List<Directions> validDirections = new ArrayList<>();
        for (Directions dir : Directions.values()) {
            Coordinate newCoordinate = new Coordinate(player.getX(), player.getY());
            newCoordinate.move(dir);
            //CHEQUEAR DEADLOCK
            boolean aux = walls.contains(newCoordinate);
            if (!walls.contains(newCoordinate)) {
                if (boardStatus.getBoxes().contains(newCoordinate)) {
                    Coordinate newBoxCoordinate = new Coordinate(newCoordinate.getX(), newCoordinate.getY());
                    newBoxCoordinate.move(dir);
                    //CHEQUEAR DEADLOCK
                    if (!walls.contains(newBoxCoordinate) && !boardStatus.getBoxes().contains(newBoxCoordinate)) {
                        validDirections.add(dir);
                    }
                } else {
                    validDirections.add(dir);
                }
            }
        }
        return validDirections;
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
