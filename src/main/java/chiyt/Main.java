package chiyt;

import java.util.List;

import chiyt.v1.SuperRelationshipAnalyzeAdapter;
import chiyt.v1.SuperRelationshipAnalyzer;
import chiyt.v2.RelationshipGraphAdapter;

public class Main
{
    public static void main(String[] args) {
        String script = "A: B C D\n" +
                        "B: A D E\n" +
                        "C: A E G K M\n" +
                        "D: A B K P\n" +
                        "E: B C J K L\n" +
                        "F: Z";
        //v1
        SuperRelationshipAnalyzer analyzer = new SuperRelationshipAnalyzer();
        SuperRelationshipAnalyzeAdapter adapter = new SuperRelationshipAnalyzeAdapter(analyzer);
        adapter.parse(script);
        String name1 = "A";
        String name2 = "F";
        List<String> mutualFriends = adapter.getMutualFriends(name1, name2);
        System.out.println("Mutual friends between " + name1 + " and " + name2 + ": " + mutualFriends);

        //v2
        RelationshipGraphAdapter graphAdapter = new RelationshipGraphAdapter();
        graphAdapter.parse(script);
        boolean result = graphAdapter.hasConnection(name1, name2);
        System.out.println("Are " + name1 + " and " + name2 + " connected? " + result);
        // String script = "A -- B\n" + //
        //                 "B -- E";
        // analyzer.init(script);
        // boolean result = analyzer.isMutualFriend("B", "A", "E");
        // System.out.println(result);
    }
}
