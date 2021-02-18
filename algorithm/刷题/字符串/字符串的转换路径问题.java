/*
题目描述
给定两个字符串，记为start和to，再给定一个字符串列表list，list中一定包含to，list中没有重复的字符串。所有的字符串都是小写的。规定start每次只能改变一个字符，最终的目标是彻底变成to，但是每次变成新字符串必须在list中存在。请返回所有的最短的变换路径（按照字典序最小的顺序输出）。
输入描述:
输出包含多行，第一行包含一个整数n（ 1 \leq n \leq 5000）（1≤n≤5000），代表list的中字符串的个数，第二行中包含两个字符串，分别代表start和to。接下来n行，每行一个字符串，代表lis[i]（保证字符串长度都为3）。
输出描述:
如果存在转换的路径，请先输出“YES”，然后按照字典序最小的顺序输出所有路径。如果不存在请输出“NO”。
示例1
输入
复制
8
abc cab
cab
acc
cbc
ccc
cac
cbb
aab
abb
输出
复制
YES
abc -> abb -> aab -> cab
abc -> abb -> cbb -> cab
abc -> cbc -> cac -> cab
abc -> cbc -> cbb -> cab
 */
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static Scanner scanner;
    static String begin;
    static String end;
    static String[] strList;
    static int strListLen;
    static Map<String, Integer> strMap;
    static List<String>[] nextStrMap;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        nextStrMap();
        print(sort(shortestList(distanceMap())));
    }

    private static void print(List<List<String>> pathList) {
        if (pathList.isEmpty()) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            for (List<String> path : pathList) {
                for (int i = 0; i < path.size(); i++) {
                    final String str = path.get(i);
                    if (i == path.size() - 1) {
                        System.out.print(str);
                    } else {
                        System.out.print(str + " -> ");
                    }
                }
                System.out.println();
            }
        }
    }

    private static List<List<String>> sort(List<List<String>> shortestPathList) {
        List<List<String>> result;
        result = shortestPathList.stream()
                .sorted((list1, list2) -> {
                    return toPathStr(list1).compareTo(toPathStr(list2));
                })
                .collect(Collectors.toList());
        return result;
    }

    private static String toPathStr(List<String> path) {
        String result = "";
        for (String str : path) {
            result += str;
        }
        return result;
    }

    private static List<List<String>> shortestList(int[] distanceMap) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs(result, strListLen, distanceMap, path);
        return result;
    }

    private static void dfs(List<List<String>> result, int index, int[] distanceMap, List<String> path) {
        final List<String> nextList = nextStrMap[index];
        if (strMap.get(end) == index) {
            result.add(strList(path));
        } else {
            for (int i = 0; i < nextList.size(); i++) {
                final int nextIndex = strMap.get(nextList.get(i));
                if (distanceMap[nextIndex] == distanceMap[index] + 1) {
                    path.add(strList[nextIndex]);
                    dfs(result, nextIndex, distanceMap, path);
                    if (!path.isEmpty()) {
                        path.remove(path.size() - 1);
                    }
                }
            }
        }

    }

    private static List<String> strList(List<String> strList) {
        List<String> result = new ArrayList<>();
        result.add(begin);
        for (String str : strList) {
            result.add(str);
        }
        return result;
    }

    private static int[] distanceMap() {
        int[] result = new int[strListLen + 1];
        int[] searchedFlagMap = new int[strListLen + 1];
        bfs(searchedFlagMap, result, nextStrMap);
        return result;
    }

    private static void bfs(int[] searchedFlagMap, int[] result, List<String>[] nextStrMap) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(strListLen);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            searchedFlagMap[index] = 1;
            for (String str : nextStrMap[index]) {
                final int nextIndex = strMap.get(str);
                if (searchedFlagMap[nextIndex] == 0) {
                    queue.offer(nextIndex);
                    result[nextIndex] = result[index] + 1;
                    searchedFlagMap[nextIndex] = 1;
                }
            }
        }
    }

    private static void nextStrMap() {
        for (int i = 0; i <= strListLen; i++) {
            nextStrMap[i] = nextList(strList[i]);
        }
    }

    private static List<String> nextList(String str) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                StringBuilder stringBuilder = new StringBuilder(str);
                stringBuilder.replace(i, i + 1, j + "");
                String str2Test = stringBuilder.toString();
                if (strMap.containsKey(str2Test) &&
                        !str2Test.equals(str)
                ) {
                    result.add(str2Test);
                }
            }
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        strListLen = scanner.nextInt();
        strList = new String[strListLen + 1];
        begin = scanner.next();
        end = scanner.next();
        strList[strListLen] = begin;
        strMap = new HashMap<>();
        strMap.put(begin, strListLen);
        nextStrMap = new List[strListLen + 1];
        for (int i = 0; i < strListLen; i++) {
            final String strTemp = scanner.next();
            strMap.put(strTemp, i);
            strList[i] = strTemp;
        }
    }
}