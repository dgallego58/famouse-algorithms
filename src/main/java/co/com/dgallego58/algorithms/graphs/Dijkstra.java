package co.com.dgallego58.algorithms.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Dijkstra {

    public static Graph shortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);
        source.sourceNode(true);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacencyPair :
                    currentNode.adjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.distance();
        if (sourceDistance + edgeWeigh < evaluationNode.distance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.shortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.distance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;

    }

    public record Graph(Set<Node> nodes) {
        public Graph(Set<Node> nodes) {
            this.nodes = nodes == null ? new HashSet<>() : nodes;
        }


        public Graph add(Node node) {
            nodes.add(node);
            return this;
        }

        @Override
        public String toString() {
            return "Graph{" +
                   "nodes=" + nodes +
                   '}';
        }
    }

    public static final class Node {
        private final String name;
        private Integer distance;
        private List<Node> shortestPath;
        private final Map<Node, Integer> adjacentNodes;
        private boolean isSourceNode;

        public boolean isSourceNode() {
            return isSourceNode;
        }

        public Node(String name) {
            this.name = name;
            this.distance = Integer.MAX_VALUE;
            this.adjacentNodes = new HashMap<>();
            this.shortestPath = new LinkedList<>();
            this.isSourceNode = false;
        }

        public Node addDestination(Node destination, int distance) {
            adjacentNodes.put(destination, distance);
            return this;
        }

        public String name() {
            return name;
        }

        public Integer distance() {
            return distance;
        }

        public List<Node> shortestPath() {
            return shortestPath;
        }

        public Map<Node, Integer> adjacentNodes() {
            return adjacentNodes;
        }

        public Node setDistance(Integer distance) {
            this.distance = distance;
            return this;
        }

        public Node setShortestPath(List<Node> shortestPath) {
            this.shortestPath = shortestPath;
            return this;
        }


        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != this.getClass()) {
                return false;
            }
            var that = (Node) obj;
            return Objects.equals(this.name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }


        public void sourceNode(boolean b) {
            this.isSourceNode = b;
        }
    }
}
