package engines;

import models.Answer;
import models.Board;
import models.Node;

import java.util.Map;

public interface Engines {

    public Answer perform(Node node, Board board, long timeLimit, Map<String, Object> info);
}
