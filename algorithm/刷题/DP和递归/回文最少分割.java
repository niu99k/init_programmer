/*
题目描述
给定一个字符串，返回把str全部切割成回文串的最少切割数。
输入描述:
输出包含一行字符串，代表str(1 \leq length_{str} \leq 5000)(1≤length
str
​
 ≤5000)。
输出描述:
输出一个整数，代表把str全部切割成回文串的最小切割数。
示例1
输入
复制
ABA
输出
复制
0
说明
本身是回文串，不需要切割，直接输出0
示例2
输入
复制
ABCBAEEE
输出
复制
1
说明
切割1次，变为“ABCBA”和“EEE”
备注:
时间复杂度O(n^2)O(n
2
 ),额外空间复杂度O(n^2)O(n
2
 )。
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static String str;
    static int[] dpMap;
    static boolean[][] palindroomMap;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        System.out.println(solve());
    }

    private static int solve() {
        int result;
        initPalindroomMap();
        initDpMap();
        result = dpMap[str.length() - 1];
        return result;
    }

    private static void initPalindroomMap() {
        int initEndIndex = 0;
        while (initEndIndex < str.length()) {
            int beginIndex = 0;
            int endIndex = initEndIndex;
            while (endIndex < str.length()) {
                if (beginIndex == endIndex) {
                    palindroomMap[beginIndex][endIndex] = true;
                } else {
                    if (str.charAt(beginIndex) == str.charAt(endIndex)) {
                        if (endIndex == beginIndex + 1) {
                            palindroomMap[beginIndex][endIndex] = true;
                        } else {
                            palindroomMap[beginIndex][endIndex] = palindroomMap[beginIndex + 1][endIndex - 1];
                        }
                    } else {
                        palindroomMap[beginIndex][endIndex] = false;
                    }
                }
                beginIndex++;
                endIndex++;
            }
            initEndIndex++;
        }
    }

    private static void initDpMap() {
        int endIndex = 0;
        while (endIndex < str.length()) {
            if (palindroomMap[0][endIndex]) {
                dpMap[endIndex] = 0;
            } else {
                dpMap[endIndex] = minCut(endIndex);
            }
            endIndex++;
        }
    }

    private static int minCut(int endIndex) {
        int result;
        int minCut = Integer.MAX_VALUE;
        for (int leftPartSize = 1; leftPartSize <= endIndex; leftPartSize++) {
            if (palindroomMap[leftPartSize][endIndex]) {
                minCut = Math.min(minCut, 1 + dpMap[leftPartSize - 1]);
            }
        }
        result = minCut;
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        str = scanner.next();
        dpMap = new int[str.length()];
        palindroomMap=new boolean[str.length()][str.length()];
    }
}
