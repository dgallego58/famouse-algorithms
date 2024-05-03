package co.com.dgallego58.algorithms.graphs;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.stream.Collectors;

class DijkstraTest {


    @Test
    void dijkstra() {
        Dijkstra.Node nodeA = new Dijkstra.Node("A");
        Dijkstra.Node nodeB = new Dijkstra.Node("B");
        Dijkstra.Node nodeC = new Dijkstra.Node("C");
        Dijkstra.Node nodeD = new Dijkstra.Node("D");
        Dijkstra.Node nodeE = new Dijkstra.Node("E");
        Dijkstra.Node nodeF = new Dijkstra.Node("F");

        nodeA.addDestination(nodeB, 10)
                .addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12)
                .addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2)
                .addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        Dijkstra.Graph graph = new Dijkstra.Graph(new HashSet<>());
        graph.add(nodeA)
                .add(nodeB)
                .add(nodeC)
                .add(nodeD)
                .add(nodeE)
                .add(nodeF);

        graph = Dijkstra.shortestPathFromSource(graph, nodeA);

        System.out.println("--------------------------------------");
        graph.nodes()
                .forEach(node -> {

                    var s = node.shortestPath()
                            .stream()
                            .map(n -> n.name() + ":" + n.distance())
                            .collect(Collectors.joining(",", "[", "]"));
                    String x = node.shortestPath()
                            .stream()
                            .map(Dijkstra.Node::name)
                            .collect(Collectors.joining("->"));

                    boolean preXEmpty = x.isEmpty();
                    x = x + "->" + node.name();

                    if (node.isSourceNode() && preXEmpty) {
                        x = node.name() + x;
                    }

                    System.out.println("Node: " + x + " Total Distance: " + node.distance());
                    System.out.println("Shortest Path from Node: " + s);
                    System.out.println("--------------------------------------");
                });
    }
}
