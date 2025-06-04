package chiyt.v2;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;

public class RelationshipGraphImpl implements RelationshipGraph {

    private final Graph<String, DefaultEdge> friendGraph;
    public RelationshipGraphImpl(Graph<String, DefaultEdge> graph) {
        this.friendGraph = graph;
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
