package chiyt.v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SuperRelationshipAdapter implements RelationshipAnalyzer {
    Map<String, List<String>> relationships;
    SuperRelationshipAnalyzer superAnalyzer;

    public SuperRelationshipAdapter(SuperRelationshipAnalyzer analyzer) {
        this.superAnalyzer = analyzer;
    }

    @Override
    public void parse(String script) {
        relationships = new HashMap<>();
        StringBuilder result = new StringBuilder();
        Set<String> edges = new HashSet<>();

        String[] lines = script.split("\n");
        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String person = parts[0].trim();
                String[] friends = parts[1].trim().split(" ");

                // 備份一份原始的關係
                relationships.put(person, Arrays.asList(friends));
                // 準備傳給 SuperRelationshipAnalyzer 的 script
                for (String friend : friends) {
                    String edge = person + " -- " + friend;
                    String reverseEdge = friend + " -- " + person;

                    // 避免重複的關係
                    if (!edges.contains(reverseEdge)) {
                        edges.add(edge);
                        result.append(edge).append("\n");
                    }
                }
            }
        }

        String superAnalyzerScript = result.toString();
        superAnalyzer.init(superAnalyzerScript);
    }

    @Override
    public List<String> getMutualFriends(String name1, String name2) {
        List<String> friends1 = relationships.get(name1);
        List<String> friends2 = relationships.get(name2);
        if (friends1 == null || friends2 == null) {
            return Arrays.asList();
        }

        Set<String> combinedSet = new HashSet<>(friends1);
        combinedSet.addAll(friends2);
        List<String> combined = new ArrayList<>(combinedSet);

        List<String> mutualFriends = new ArrayList<>();
        for(String friend : combined){
            if (superAnalyzer.isMutualFriend(friend, name1, name2)) {
                mutualFriends.add(friend);
            }
        }
        return mutualFriends;
    }
}
