package models;

public class StatusValidation {
    public static BoardStatus validate(Board board, BoardStatus boardStatus, Directions dir) {
        Coordinate coordinateCopy = boardStatus.getPlayer();
        coordinateCopy.move(dir);
        BoardStatus newBoardStatus = null;
        if (isValid(boardStatus, board, dir, coordinateCopy)) {
            newBoardStatus = new BoardStatus(boardStatus.getBoxes(), boardStatus.getGoals(), coordinateCopy);
        }
        return newBoardStatus;
    }

    private static boolean isValid(BoardStatus boardStatus, Board board, Directions dir, Coordinate coordinate) {
        // 1. mueve una caja
        //     a. hay que ver:
        //         i. si se forma un deadlock <--
        //         ii. Si se puede mover la caja
        //         iii. si cuando muevo la caja caigo en un deadlock <--
        // ---
        if (!board.getWalls().contains(coordinate)) {
            if (boardStatus.getBoxes().contains(coordinate)) {  // 1. mueve una caja (ii)
                Coordinate boxCoordinate = new Coordinate(coordinate.getX(), coordinate.getY());
                boxCoordinate.move(dir);
                if (!board.getWalls().contains(boxCoordinate)) {
                    boardStatus.getBoxes().remove(coordinate);
                    boardStatus.getBoxes().add(boxCoordinate);

                    if (board.getGoals().contains(boxCoordinate)) {
                        boardStatus.getGoals().replace(boxCoordinate, true);
                    }
                    return true;
                }
            } else {  // 2. no mueve caja
                coordinate.move(dir);
                return true;
            }
        }
        return false;
    }


}
