package chiyt.v2;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class RelationshipGraphAdapter implements RelationshipAnalyzer, RelationshipGraph{
    private Graph<String, DefaultEdge> friendGraph;

    public RelationshipGraphAdapter() {
        this.friendGraph = new SimpleGraph<>(DefaultEdge.class);
    }

    @Override
    public RelationshipGraph parse(String script) {
        this.friendGraph = new SimpleGraph<>(DefaultEdge.class);
        String[] lines = script.split("\n");
        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String person = parts[0].trim();
                String[] friends = parts[1].trim().split(" ");
                
                friendGraph.addVertex(person);
                for (String friend : friends) {
                    friendGraph.addVertex(friend);
                    friendGraph.addEdge(person, friend);
                }
            }
        }
        return this;
    }

    @Override
    public boolean hasConnection(String name1, String name2) {
        if (!friendGraph.containsVertex(name1) || !friendGraph.containsVertex(name2)) {
            return false;
        }
        ConnectivityInspector<String, DefaultEdge> inspector = new ConnectivityInspector<>(friendGraph);
        // 取得 name1 所在的連通分量
        Set<String> connectedSet = inspector.connectedSetOf(name1);
        // 檢查 name2 是否在這個 set 裡
        return connectedSet.contains(name2);
    }
}
