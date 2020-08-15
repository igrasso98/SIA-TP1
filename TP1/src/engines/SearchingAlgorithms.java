package engines;

import models.Board;
import models.Node;

import java.util.List;

public interface SearchingAlgorithms {
    Node perform(Node root, Board board);
    List<Node> getChildren(Node node, Board board);
}
