package heuristics;

import models.BoardStatus;

public interface Heuristics {
    int compute(BoardStatus status);
}
