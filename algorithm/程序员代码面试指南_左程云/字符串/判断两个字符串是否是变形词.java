/*
题目描述
给定两个字符串str1和str2，如果str1和str2中出现的字符种类出现的一样且每种字符出现的次数也一样，那么str1和str2互为变形词。请判断str1和str2是否为变形词。
输入描述:
输入包括3行，第一行包含两个整数n，m(1 \leq n,m\leq 10^5)(1≤n,m≤10
5
 )分别代表str1和str2的长度，第二行和第三行为两个字符串，分别代表str1和str2。
输出描述:
如果str1和str2互为变形词，请输出“true”，否则输出“false”。
示例1
输入
复制
3 3
123
321
输出
复制
true
示例2
输入
复制
3 4
123
2331
输出
复制
false
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int str1Len;
    static int str2Len;
    static String str1;
    static String str2;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        if (str1Len != str2Len) {
            System.out.println(false);
        } else {
            System.out.println(solve());
        }
    }

    private static boolean solve() {
        boolean result;
        Map<Integer, Integer> str1Map = strMap(str1);
        Map<Integer, Integer> str2Map = strMap(str2);
        if (str1Map.keySet().size() != str2Map.keySet().size()) {
            result = false;
        } else {
            result = true;
            for (Integer chr : str1Map.keySet()) {
                if (!str2Map.containsKey(chr)) {
                    result = false;
                    break;
                } else if (!str1Map.get(chr).equals(str2Map.get(chr))) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private static Map<Integer, Integer> strMap(String str) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            int chr = str.charAt(i);
            if (result.containsKey(chr)) {
                result.put(chr, result.get(chr) + 1);
            } else {
                result.put(chr, 1);
            }
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        str1Len = scanner.nextInt();
        str2Len = scanner.nextInt();
        str1 = scanner.next();
        str2 = scanner.next();
    }
}