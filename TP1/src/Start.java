import exceptions.NoAlgorithmException;
import exceptions.NoHeuristicException;
import exceptions.NoSuchLevelException;
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
        JSONParser parser = new JSONParser();
        String filePath;
        filePath = args[0];
//        if(args.length != 0) {
//            filePath = args[0];
//        } else {
//
//        }
        try {
            Reader reader = new FileReader(filePath);
            JSONObject json = (JSONObject) parser.parse(reader);

            JSONObject configs = (JSONObject) json.get("config");
            List<String> levelArgs = Arrays.asList("timelimit", "limit", "step", "algorithm-name", "heuristic", "level-number");

            Map<String, Object> levelInfo = getArgs(configs, levelArgs);
            if(!levelInfo.containsKey("algorithm-name")) {
                throw new NoAlgorithmException();
            }
            if(!levelInfo.containsKey("heuristic")) {
                throw new NoHeuristicException();
            }
            if(levelInfo.containsKey("level-number")) {
                if((Long) levelInfo.get("level-number") > 9 || (Long) levelInfo.get("level-number") <= 0) {
                   throw new NoSuchLevelException();
                }
            }

            long time = System.currentTimeMillis();
            Answer ans = GameResolver.resolve(levelInfo);
            System.out.println(ans);
            System.out.println((System.currentTimeMillis() - time)/1000.0);
        } catch (IOException | NoHeuristicException | ParseException | NoAlgorithmException | NoSuchLevelException e) {
            System.out.println(e.getMessage());
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