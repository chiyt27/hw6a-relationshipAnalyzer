package chiyt.v2;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class RelationshipGraphAdapter implements RelationshipAnalyzer{
	@Override
	public RelationshipGraph parse(String script) {
        SimpleGraph<String, DefaultEdge> friendGraph = new SimpleGraph<>(DefaultEdge.class);
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
		return new RelationshipGraphImpl(friendGraph);
    }
}
