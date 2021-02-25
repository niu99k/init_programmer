/*
题目描述
一个字符串可以分解为多种二叉树。如果str长度为1，认为不可分解；如果str长度为N(N>1),左半部分长度可以为1~N-1，右半部分为剩下的长度，然后你可以交换左右两部分。并且左右部分可以按照同样的逻辑，继续分解。每一个形成的字符串都可以是原字符串的旋变字符串。现在给你两个字符串str1和str2，判断str2是否为str1的旋变字符串。
输入描述:
输入包括两行，第一行一个字符串，代表str1( 1 \leq length_{str1} \leq 100 )(1≤length
str1
​
 ≤100)，第二行一个字符串，代表str2(1 \leq length_{str2} \leq 100 )(1≤length
str2
​
 ≤100)。
输出描述:
如果str2是str1的旋变字符串请输出“YES”，否则输出“NO”。
示例1
输入
复制
abcd
dbac
输出
复制
YES
说明
abcd->d...abc->d...ab...c->d...b...a...c
示例2
输入
复制
IJz
JzI
输出
复制
YES
说明
左边为l右边为Jz交换  变Jzl
备注:
时间复杂度O（N^4）O（N
4
 ）,额外空间复杂度O（N^3）O（N
3
 ）。
 */

import java.util.*;

public class Main {
    static Scanner scanner;
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
        System.out.println(solve()?"YES":"NO");
    }

    private static boolean solve() {
        boolean result;
        result = isPossible() && isValid();
        return result;
    }

    private static boolean isValid() {
        boolean result;
        final int len = str1.length();
        boolean[][][] dpMap = new boolean[len][len][len + 1];
        initSizeEquals1(dpMap);
        for (int size = 2; size <= len; size++) {
            for (int leftIndex = 0; leftIndex <= len - size; leftIndex++) {
                for (int rightIndex = 0; rightIndex <= len - size; rightIndex++) {
                    for (int leftSize = 1; leftSize <= size; leftSize++) {
                        if ((dpMap[leftIndex][rightIndex][leftSize] && dpMap[leftIndex + leftSize][rightIndex + leftSize][size - leftSize])
                                || (dpMap[leftIndex][size - leftSize + rightIndex][leftSize] && dpMap[leftSize + leftIndex][rightIndex][size - leftSize])
                        ) {
                            dpMap[leftIndex][rightIndex][size] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dpMap[0][0][len];
    }

    private static void initSizeEquals1(boolean[][][] dpMap) {
        final int len = str1.length();
        for (int leftIndex = 0; leftIndex < len; leftIndex++) {
            for (int rightIndex = 0; rightIndex < len; rightIndex++) {
                if (str1.charAt(leftIndex) == str2.charAt(rightIndex)) {
                    dpMap[leftIndex][rightIndex][1] = true;
                } else {
                    dpMap[leftIndex][rightIndex][1] = false;
                }
            }
        }
    }

    private static boolean isPossible() {
        boolean result = true;
        final int len1 = str1.length();
        final int len2 = str2.length();
        if (len1 != len2) {
            result = false;
        } else {
            Map<Integer, Integer> map1 = map(str1);
            Map<Integer, Integer> map2 = map(str2);
            if (map1.keySet().size() != map2.keySet().size()) {
                result = false;
            } else {
                boolean isMapSame = true;
                for (Integer key : map1.keySet()) {
                    if (!map2.get(key).equals(map1.get(key))) {
                        isMapSame = false;
                        break;
                    }
                }
                result = isMapSame;
            }
        }
        return result;
    }

    private static Map<Integer, Integer> map(String str) {
        Map<Integer, Integer> result = new HashMap<>();
        int[] resultMap = new int[256];
        for (int i = 0; i < str.length(); i++) {
            resultMap[str.charAt(i)]++;
        }
        for (int i = 0; i < 256; i++) {
            if (resultMap[i] != 0 ) {
                result.put(i, resultMap[i]);
            }
        }
        return result;
    }

    public static void init() {
        scanner = new Scanner(System.in);
        str1 = scanner.next();
        str2 = scanner.next();
    }
}