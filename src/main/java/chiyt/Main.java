package chiyt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import chiyt.v1.SuperRelationshipAdapter;
import chiyt.v1.SuperRelationshipAnalyzer;
import chiyt.v2.RelationshipGraph;
import chiyt.v2.RelationshipGraphAdapter;

public class Main
{
    public static void main(String[] args) {
        try {
            String script = new String(Files.readAllBytes(Paths.get("script.txt")), StandardCharsets.UTF_8);

            //v1
            chiyt.v1.RelationshipAnalyzer superAnalyzer = new SuperRelationshipAdapter(new SuperRelationshipAnalyzer());
            superAnalyzer.parse(script);
            String name1 = "A";
            String name2 = "K";
            List<String> mutualFriends = superAnalyzer.getMutualFriends(name1, name2);
            System.out.println("Mutual friends between " + name1 + " and " + name2 + ": " + mutualFriends);

            //v2
            chiyt.v2.RelationshipAnalyzer graphAnalyzer = new RelationshipGraphAdapter();
            RelationshipGraph friendGraph = graphAnalyzer.parse(script);
            boolean result = friendGraph.hasConnection(name1, name2);
            System.out.println("Are " + name1 + " and " + name2 + " connected? " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
