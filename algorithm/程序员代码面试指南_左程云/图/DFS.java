/*
dfs
 */


import java.util.*;

public class Main {
    static class Graph {
        List<Node> nodeList = new ArrayList<>();
        Node rootNode;
    }

    static class Node {
        List<Node> next = new ArrayList<>();
    }

    public static void main(String[] args) {
        boolean flag = true;
        if (flag) {
            for (int i = 0; i < 5000; i++) {
                Graph randomGraph = randomGraph();
                int nodeCount = dfs(randomGraph.rootNode);
                if (randomGraph.nodeList.size() != nodeCount) {
                    flag = false;
                    System.out.println("gg");
                    break;
                }
            }
            System.out.println("nice!");
        }
    }

    private static int dfs(Node rootNode) {
        int count = 0;
        Stack<Node> stack = new Stack<>();
        Set<Node> nodeSet = new HashSet<>();

        stack.push(rootNode);
        nodeSet.add(rootNode);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            count++;
            for (Node next : node.next) {
                if (!nodeSet.contains(next)) {
                    stack.push(next);
                    nodeSet.add(next);
                }
            }
        }
        return count;
    }

    private static Graph randomGraph() {
        Graph result = new Graph();
        Random random = new Random();
        int maxNodeCount = 1000;
        int nodeCount = random.nextInt(maxNodeCount) + 1;
        result.nodeList.add(new Node());
        result.rootNode = result.nodeList.get(0);
        for (int i = 1; i < nodeCount; i++) {
            Node node = new Node();
            for (Node pre : result.nodeList) {
                if (random.nextBoolean()) {
                    pre.next.add(node);
                    node.next.add(pre);
                }
            }
            Node guard = result.nodeList.get((result.nodeList.size() - 1));
            node.next.add(guard);
            guard.next.add(node);
            result.nodeList.add(node);
        }
        return result;
    }

}