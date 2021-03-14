/*
BFS
 */

import java.util.*;

public class Main {

    static class Graph {
        List<Node> nodeList = new ArrayList<>();
        List<Edge> edgeList = new ArrayList<>();

        Node fromNode;
    }

    static class Node {
        int inCount;
        int outCount;
        List<Node> next = new ArrayList<>();
        int dis;
    }

    static class Edge {
        int value;
        Node from;
        Node to;
    }

    public static void main(String[] args) {
        mycheck();
    }

    private static void mycheck() {
        boolean flag = true;
        for (int i = 0; i < 5000; i++) {
            Graph randomGraph = randomGraph();
            HashMap<Node, Integer> disMap = disMap(randomGraph);
            for (Node node : randomGraph.nodeList) {
                if (!disMap.containsKey(node) || !disMap.get(node).equals(node.dis)) {
                    flag = false;
                    System.out.println("gg");
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }
        if (flag) {
            System.out.println("nice!");
        }
    }

    private static HashMap<Node, Integer> disMap(Graph randomGraph) {
        HashMap<Node, Integer> result = new HashMap<>();
        Set<Node> nodeSet = new HashSet<>();
        nodeSet.add(randomGraph.fromNode);
        result.put(randomGraph.fromNode, 0);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(randomGraph.fromNode);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Node next : node.next) {
                if (!nodeSet.contains(next)) {
                    nodeSet.add(next);
                    result.put(next, node.dis + 1);
                    queue.offer(next);
                }
            }

        }
        return result;
    }

    private static Graph randomGraph() {
        Graph result = new Graph();
        Random random = new Random();
        int dis = random.nextInt(7) + 1;

        Node root = new Node();
        result.fromNode = root;
        result.nodeList.add(root);

        List<Node> nodeListInLevel = new ArrayList<>();
        nodeListInLevel.add(root);

        for (int i = 0; i < dis; i++) {
            randomEdgeInLevelNodeList(nodeListInLevel);
            nodeListInLevel = refreshLevelNodeList(nodeListInLevel, i + 1, result);
        }
        return result;
    }

    private static List<Node> refreshLevelNodeList(List<Node> nodeListInLevel, int dis, Graph graph) {
        Random random = new Random();
        int count = random.nextInt(5);
        List<Node> result = new ArrayList<>();
        for (int i = 0; i < nodeListInLevel.size(); i++) {
            Node node = nodeListInLevel.get(i);
            for (int j = 0; j < count; j++) {
                Node newNode = new Node();
                node.next.add(newNode);
                newNode.next.add(node);
                result.add(newNode);
                newNode.dis = dis;
                graph.nodeList.add(newNode);
            }
        }
        return result;
    }

    private static void randomEdgeInLevelNodeList(List<Node> nodeListInLevel) {
        Random random = new Random();
        for (int i = 0; i < nodeListInLevel.size(); i++) {
            for (int j = i + 1; j < nodeListInLevel.size(); j++) {
                if (random.nextBoolean()) {
                    Node node1 = nodeListInLevel.get(i);
                    Node node2 = nodeListInLevel.get(j);
                    node1.next.add(node2);
                    node2.next.add(node1);
                }
            }
        }
    }
}