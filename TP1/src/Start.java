import heuristics.*;

import models.*;
import engines.*;
import org.json.simple.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;
public class Start {
    public static void main(String[] args) {
        char[][] boardMatrix = {{'#', '#', '#', '#', '#', '#', '#'},
                                {'#', '*', ' ', ' ', ' ', '*', '#'},
                                {'#', ' ', '$', '$', '$', ' ', '#'},
                                {'#', ' ', '$', '@', '$', ' ', '#'},
                                {'#', '*', '$', '$', '$', '*', '#',},
                                {'#', '*', '*', ' ', '*', '*', '#',},
                                {'#', '#', '#', '#', '#', '#', '#',}};

        char[][] boardMatrix2 = {{'#', '#', '#', '#', '#', '#', '#'},
                                 {'#', '@', ' ', ' ', ' ', ' ', '#'},
                                 {'#', ' ', ' ', '$', ' ', ' ', '#'},
                                 {'#', ' ', ' ', ' ', ' ', '*', '#'},
                                 {'#', '#', '#', '#', '#', '#', '#',}};

        char[][] euge = {{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                         {'#', '#', '#', '#', '#', '#', '#', '*', '#', '#', '#', '#', '#', '#', '#'},
                         {'#', '#', '#', '#', '#', '#', '#', '*', '#', '#', '#', '#', '#', '#', '#'},
                         {'#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#'},
                         {'#', '#', ' ', ' ', '#', ' ', '#', ' ', '#', ' ', '#', ' ', ' ', '#', '#'},
                         {'#', ' ', ' ', '#', '#', ' ', ' ', ' ', ' ', ' ', '#', '#', ' ', '#', '#'},
                         {'#', ' ', '#', '#', ' ', ' ', '#', ' ', '#', ' ', ' ', '#', '#', ' ', '#'},
                         {'#', ' ', ' ', ' ', ' ', ' ', '$', '@', '$', ' ', ' ', ' ', ' ', ' ', '#'},
                         {'#', '#', '#', ' ', ' ', ' ', '#', '#', '#', ' ', ' ', ' ', '#', '#', '#'},
                         {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};

//        Set<Coordinate> walls = BoardParser.getObject(euge, '#');
//        Set<Coordinate> goalsUbication = BoardParser.getObject(euge, '*');
//        Set<Coordinate> boxes = BoardParser.getObject(euge, '$');
//        Coordinate player = BoardParser.getPlayer(euge, '@');
//
//        Set<Coordinate> deadlocks = BoardParser.findDeadlocks(boardMatrix);
//        Board board = new Board(deadlocks,walls,goalsUbication);
//        for (char[] aBoardMatrix : euge) {
//            for (int j = 0; j < euge[0].length; j++) {
//                System.out.print(aBoardMatrix[j] + "   ");
//            }
//            System.out.println();
//        }
//
//
//        for(Coordinate coordinate : goalsUbication) {
//            goals.put(coordinate, false);
//        }
//
//        BoardStatus initialStatus = new BoardStatus(boxes,goals,player);
//
//        Node root = new Node(initialStatus,0,0);
//        Set<BoardStatus> movements = new LinkedHashSet<>();
//        movements.add(root.getStatus());
//        root.setMovements(movements);
//
//        long currentTime;
//        Manhattan manhattan = new Manhattan();
//        Euclidean euclidean = new Euclidean();
//        Answer node;
//
//        DFSEngine dfs = new DFSEngine();
//        currentTime = System.currentTimeMillis();
//        Map<String, Object> map = new HashMap<>();
//        map.put("heuristic", euclidean);
//        map.put("limit", 100);
//        node = dfs.perform(root,board, 0, map);
//        System.out.println("\nDFS Engine: ");
//        System.out.println("\t" + node.toString());
//        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);
//
//        BFSEngine bfs = new BFSEngine();
//        currentTime = System.currentTimeMillis();
//        node = bfs.perform(root,board);
//        System.out.println("\nBFS Engine: ");
//        System.out.println("\t" + node.toString());
//        System.out.println("\tTime: " + (System.currentTimeMillis() - currentTime)/1000);
//
//        GreedyEngine greedy = new GreedyEngine();
//        currentTime = System.currentTimeMillis();
//        node = greedy.perform(root, board, manhattan);
//        System.out.println("\nGreedy Engine: Manhattan");
//        System.out.println("\t" + node.toString());
//        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);
//
//        node = greedy.perform(root, board, euclidean);
//        System.out.println("\nGreedy Engine: Euclidean");
//        System.out.println("\t" + node.toString());
//        System.out.println("\tTime: " + (System.currentTimeMillis() - currentTime)/1000);
//
//        AstarEngine astarEngine = new AstarEngine();
//        currentTime = System.currentTimeMillis();
//        node = astarEngine.perform(root, board, manhattan);
//        System.out.println("\nAStar Engine: Manhattan");
//        System.out.println(node.toString());
//        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);
//
//        node = astarEngine.perform(root, board, euclidean);
//        System.out.println("\nAStar Engine: Euclidean");
//        System.out.println("\t" + node.toString());
//        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);
//
//        IDDFSEngine iddfs = new IDDFSEngine();
//        currentTime = System.currentTimeMillis();
//        Map<String, Object> map = new HashMap<>();
//        map.put("heuristic", euclidean);
//        map.put("limit", 100);
//        node = iddfs.perform(root, board, 0, map);
//        System.out.println("\nIDDFS Engine Engine:");
//        System.out.println(node.toString());
//        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);
//
//        IDAstarEngine idAstarEngine = new IDAstarEngine();
//        currentTime = System.currentTimeMillis();
//        Map<String, Object> map = new HashMap<>();
//        map.put("heuristic", euclidean);
//        map.put("limit", 100);
//        node = idAstarEngine.perform(root, board, 0, map);
//        System.out.println("\nIDAStar Engine: Euclidean");
//        System.out.println(node.toString());
//        System.out.println("Time: " + (double)(System.currentTimeMillis() - currentTime)/1000);
//
//        node = idAstarEngine.perform(root, board, manhattan, 100);
//        System.out.println("\nIDAStar Engine: Manhattan");
//        System.out.println("\t" + node.toString());
//        System.out.println("\tTime: " + (double)(System.currentTimeMillis() - currentTime)/1000);

        JSONParser parser = new JSONParser();
        try {
            String filePath = new File("").getAbsolutePath();
            filePath += "/TP1/src/config.json";
            Reader reader = new FileReader(filePath);
            JSONObject json = (JSONObject) parser.parse(reader);

            JSONObject configs = (JSONObject) json.get("config");
            List<String> levelArgs = Arrays.asList("timelimit", "limit", "step", "algorithm-name", "heuristic", "level-number");

            Map<String, Object> levelInfo = getArgs(configs, levelArgs);
            if(!levelInfo.containsKey("algorithm-name")) {
                System.out.println("Algorithm not found");
                return;
            }
            if(!levelInfo.containsKey("heuristic")) {
                System.out.println("Heuristic not found");
                return;
            }
            if(levelInfo.containsKey("level-number")) {
                if((Long) levelInfo.get("level-number") > 9 || (Long) levelInfo.get("level-number") < 0) {
                    System.out.println("No such level, levels 1 to 9 are available");
                    return;
                }
            }

            Answer ans = GameResolver.resolve(levelInfo);
            System.out.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private static Map<String, Object> getArgs(JSONObject json, List<String> args) {
        Map<String, Object> argsToReturn = new HashMap<>();
        for(String arg : args) {
            switch (arg) {
                case "heuristic":
                    String heuristicName = ((String) json.get(arg)).toUpperCase();
                    if(!heuristicName.isEmpty() && HeuristicType.contains(heuristicName)) {
                        HeuristicType heuristics = HeuristicType.valueOf(heuristicName);
                        argsToReturn.put(arg, heuristics.getHeuristic());
                    }
                    break;
                case "algorithm-name":
                    String algorithmName = ((String) json.get(arg));
                    if(!algorithmName.isEmpty() && EngineType.contains(algorithmName)) {
                        EngineType engine = EngineType.valueOf(algorithmName.toUpperCase());
                        argsToReturn.put(arg, engine.getEngine());
                    }
                    break;
                default:
                    argsToReturn.put(arg, json.get(arg));
                    break;
            }
        }
        return argsToReturn;
    }
}