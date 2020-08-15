//    public void dfs(State state) {
////        long startTime = System.currentTimeMillis();
////
////        Stack<State> stack = new Stack<>();
////        HashSet<State> visited = new HashSet<>();
////        stack.push(state);
////
////        while (!stack.isEmpty()) {
////            State curr = stack.pop();
////            visited.add(curr);
////            if (curr.reachedGoal()) {
////                System.out.println("**************** Solution Found ! ******************");
////                System.out.println(curr.getMove().length());
////                System.out.println("dfs: " + (System.currentTimeMillis() - startTime) + " ms");
//////                System.out.print(" DFS: " + (System.currentTimeMillis() - startTime) + " ");
////                break;
////            } else {
////                for (State e : curr.getNeighbors()) {
////                    if (!visited.contains(e)) stack.push(e);
////                }
////            }
////        }
////    }

//            problem -> An instance of the graph
//            node -> A node with State =problem.InitialState, PathCost =0
//            if(problem.IsGoalState(node.State)){
//                return Solution(node)
//            }
//            frontier -> A LIFO queue with node as the only element
//            explored -> An empty set
//            while(!frontier.IsEmpty?()){
//                node = frontier.Pop()/* Returns the node inserted last */
//                explored.Add(node)
//                for(action : problem.Actions(node.State)){
//                    child = ChildNode(problem, node, action)
//                    if(!(child.State in explored || child.State in frontier)){
//                        if(problem.IsGoalState(child.State)){
//                            return Solution(child)
//                        }
//                        frontier.Insert(child)
//                    }
//                }
//            }
//            return failure




//package engines;
//
//import models.Board;
//import models.BoardStatus;
//import models.Node;
//
//import java.util.*;
//
//public class BFSEngine implements SearchingAlgorithms {
//
//    @Override
//    public Node perform(Node node, Board board) {
//        if (node.getStatus().getBoxes().equals(board.getGoals())) {
//            return node;
//        }
//
//        Queue<Node> frontiers = new LinkedList<>();
//        Set<BoardStatus> explored = new HashSet<>();
//
//        frontiers.offer(node);
//        while (!frontiers.isEmpty()) {
//            Node aux = frontiers.poll();
//            List<Node> nodeChildren = getChildren(node, board);
//            aux.setChildren(nodeChildren);
//            for (Node children : nodeChildren) {
//                frontiers.offer();
//            }
//        }
//
//
//        return null;
//    }
//
//    @Override
//    public List<Node> getChildren(Node node, Board board) {
//
//        return new ArrayList<>();
//    }
//}
