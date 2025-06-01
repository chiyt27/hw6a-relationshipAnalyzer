package chiyt;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class RelationshipGraphAdapter implements RelationshipGraph {
    private Graph<String, DefaultEdge> friendGraph;

    public RelationshipGraphAdapter() {
        friendGraph = new SimpleGraph<>(DefaultEdge.class);
    }

    public void parse(String script) {
        String[] lines = script.split("\n");
        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String person = parts[0].trim();
                String[] friends = parts[1].trim().split(" ");
                
                // Add the person as a vertex
                friendGraph.addVertex(person);

                // Add edges between the person and their friends
                for (String friend : friends) {
                    friendGraph.addVertex(friend);
                    friendGraph.addEdge(person, friend);
                }
            }
        }
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
