package chiyt.v1;

import java.util.*;

public class SuperRelationshipAnalyzer {
    private Set<Map.Entry<String, String>> relationships = new HashSet<>();

    public void init(String script){
String[] lines = script.split("\n");
        for (String line : lines) {
            String[] parts = line.split(" -- ");
            if (parts.length == 2) {
                String person1 = parts[0].trim();
                String person2 = parts[1].trim();

                relationships.add(new AbstractMap.SimpleEntry<>(person1, person2));
            }
        }
    }

    public boolean isMutualFriend(String targetName, String name1, String name2) {
        return (relationships.stream().anyMatch(entry -> entry.getKey().equals(targetName) && entry.getValue().equals(name1)) ||
                relationships.stream().anyMatch(entry -> entry.getKey().equals(name1) && entry.getValue().equals(targetName))) &&
                (relationships.stream().anyMatch(entry -> entry.getKey().equals(targetName) && entry.getValue().equals(name2)) ||
                relationships.stream().anyMatch(entry -> entry.getKey().equals(name2) && entry.getValue().equals(targetName)));
    }
}
