package chiyt;

import java.util.List;

public interface RelationshipAnalyzer {
    public void parse(String script);
    public List<String> getMutualFriends(String name1, String name2);
}
