import java.util.*;
import java.util.stream.Collectors;


public class Solution {

    static class Node {
        int index;
        List<Node> next = new ArrayList<>();

        Node(int index) {
            this.index = index;
        }
    }

    static class Edge {
        Node from;
        Node to;
        int value;

        public Edge(Node from, Node to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }

    static class Graph {
        List<Node> nodeList = new ArrayList<>();
        List<Edge> edgeList = new ArrayList<>();

        public Graph(int n, int m, int[][] cost) {
            for (int i = 0; i < n; i++) {
                nodeList.add(new Node(i));
            }

            for (int i = 0; i < cost.length; i++) {
                int from = cost[i][0];
                int to = cost[i][1];
                int value = cost[i][2];
                nodeList.get(from - 1).next.add(nodeList.get(to - 1));
                edgeList.add(new Edge(nodeList.get(from - 1), nodeList.get(to - 1), value));
            }
        }
    }

    public int miniSpanningTree(int n, int m, int[][] cost) {
        // write code here
        int result = 0;
        Graph graph = new Graph(n, m, cost);
        int[] nextNodeMap = nextNodeMap(graph.nodeList);
        int[] setCount = new int[n];
        for (int i = 0; i < n; i++) {
            setCount[i] = 1;
        }
        List<Edge> edgeList = graph.edgeList.stream().sorted((edge1, edge2) -> {
            return edge1.value - edge2.value;
        }).collect(Collectors.toList());
        for (Edge edge : edgeList) {
            if (!isSame(edge.from, edge.to, nextNodeMap)) {
                union(edge.from.index, edge.to.index, nextNodeMap, setCount);
                result += edge.value;
            }
        }
        return result;
    }

    private void union(int node1, int node2, int[] nextNodeMap, int[] setCount) {
        int root1 = root(node1, nextNodeMap);
        int root2 = root(node2, nextNodeMap);
        if (setCount[root1] > setCount[root2]) {
            nextNodeMap[root2] = root1;
            setCount[root1] += setCount[root2];
        } else {
            nextNodeMap[root1] = root2;
            setCount[root2] += setCount[root1];
        }
    }

    private boolean isSame(Node node1, Node node2, int[] nextNodeMap) {
        int root1 = root(node1.index, nextNodeMap);
        int root2 = root(node2.index, nextNodeMap);
        return root1 == root2;
    }

    private int root(int nodeIndex, int[] nextNodeMap) {
        int result;
        Stack<Integer> stack = new Stack<>();
        while (nextNodeMap[nodeIndex] != nodeIndex) {
            stack.push(nodeIndex);
            nodeIndex = nextNodeMap[nodeIndex];
        }
        while (!stack.isEmpty()) {
            nextNodeMap[stack.pop()] = nodeIndex;
        }
        result = nodeIndex;
        return result;
    }

    private int[] nextNodeMap(List<Node> nodeList) {
        int[] result = new int[nodeList.size()];
        for (int i = 0; i < nodeList.size(); i++) {
            result[i] = i;
        }
        return result;
    }
}