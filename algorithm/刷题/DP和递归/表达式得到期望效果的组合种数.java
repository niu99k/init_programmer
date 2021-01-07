/*
题目描述
给定一个只由0（假）、1（真）、&（逻辑与）、|（逻辑或）和^（异或）五种字符组成的字符串express，再给定一个布尔值desired。求出express能有多少种组合方式，可以达到desired的结果。并输出你所求出的总方案数对10^{9}+710
9
 +7取模后的值。
输入描述:
输出两行，第一行包含一个只有0、1、&、|和^组成的字符串。其长度小于500，第二行只有一个布尔值，代表desired。
输出描述:
输出一个整数，表示取模后的答案。
示例1
输入
复制
1^0|0|1
false
输出
复制
2
说明
1^((0|0)|1)和1^(0|(0|1))可以得到false
示例2
输入
复制
1
false
输出
复制
0
备注:
时间复杂度O(n^{3})O(n
3
 ),空间复杂度O(n^2)O(n
2
 )。

 1^1&1|1|0&1|1|1|0&1&0^0|1^0&0^0^0&0&1|1&1|1^0&1^0|0|1^0&1|0^0&1^1&1^1^1^1^0^1^1&1&1&1|1|0|1^1|0|1&0&1^0|0&1&1&0|0|1&0&0|1&1|1|1&0|0&0&0^0^1&1^0&0^0^0^1&1^0^1&0|0&1&1&1&0^1|1^0|1^0|0|0^1|1&0|0^0|1&1&0^1|1&0^0&0^0|1^0&1^0&1&1^0|1|0&1&1&1&1|0^1^0^0&1|1|1&0|1|0|1^0&0&1|1^1&1&1^0&1^0^0&1&0^1^0|1|1|0^0|0&0^1|1|0^0|1^1&0^1^1^1&0&1^1&0&1&0^0|1|1^0|0^1|0^0|1&0|0|0|0&1^0&0|0^0&1&0^0^0&0|0^0^1|1&0&1|0^1&1|0|1|0^1^1&1&1^1^0|1^0&1^0^0^0^0&1|0|0^1^0^1^1|0&0^1|1|1^0&0^0|1^1|1^1|1^1^0^0|1|0^1&1^1^1|1&0&1^0^0^0
true

320147386
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static String str;
    static boolean desired;
    static final int mod = 1000000007;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        if (isValid(str)) {
            System.out.println(possibilities());
        } else {
            System.out.println(0);
        }
    }

    private static long possibilities() {
        long result;
        long[][] trueDpMap = initTrueDpMap();
        long[][] falseDpMap = initFalseDpMap();
        int len = (str.length() + 1) / 2;
        calcDpMap(trueDpMap, falseDpMap, len);
        if (desired) {
            result = trueDpMap[0][len - 1];
        } else {
            result = falseDpMap[0][len - 1];
        }
        return result;
    }

    private static void calcDpMap(long[][] trueDpMap, long[][] falseDpMap, int len) {
        int baseEndIndex = 1;
        while (baseEndIndex < len) {
            int endIndex = baseEndIndex;
            int beginIndex = 0;
            while (endIndex < len) {
                trueDpMap[beginIndex][endIndex] = calcTrue(trueDpMap, falseDpMap, beginIndex, endIndex);
                falseDpMap[beginIndex][endIndex] = calcFalse(trueDpMap, falseDpMap, beginIndex, endIndex);
                beginIndex++;
                endIndex++;
            }
            baseEndIndex++;
        }

    }

    private static long calcFalse(long[][] trueDpMap, long[][] falseDpMap, int beginIndex, int endIndex) {
        long result = 0;
        int strBeginIndex = beginIndex * 2;
        int strEndIndex = endIndex * 2;
        for (int i = strBeginIndex + 1; i < strEndIndex; i = i + 2) {
            if (str.charAt(i) == '&') {
                result += (falseDpMap[beginIndex][(i - 1) / 2] * falseDpMap[(i + 1) / 2][endIndex]) % mod +
                        (trueDpMap[beginIndex][(i - 1) / 2] * falseDpMap[(i + 1) / 2][endIndex]) % mod +
                        (falseDpMap[beginIndex][(i - 1) / 2] * trueDpMap[(i + 1) / 2][endIndex]) % mod;
            } else if (str.charAt(i) == '|') {
                result += (falseDpMap[beginIndex][(i - 1) / 2] * falseDpMap[(i + 1) / 2][endIndex]) % mod;
                result = result % mod;
            } else {
                result += (trueDpMap[beginIndex][(i - 1) / 2] * trueDpMap[(i + 1) / 2][endIndex]) % mod +
                        (falseDpMap[beginIndex][(i - 1) / 2] * falseDpMap[(i + 1) / 2][endIndex]) % mod;
                result = result % mod;
            }
        }
        result = result % mod;
        return result;
    }

    private static long calcTrue(long[][] trueDpMap, long[][] falseDpMap, int beginIndex, int endIndex) {
        long result = 0;
        int strBeginIndex = beginIndex * 2;
        int strEndIndex = endIndex * 2;
        for (int i = strBeginIndex + 1; i < strEndIndex; i = i + 2) {
            if (str.charAt(i) == '&') {
                result += (trueDpMap[beginIndex][(i - 1) / 2] * trueDpMap[(i + 1) / 2][endIndex]) % mod;
                result = result % mod;
            } else if (str.charAt(i) == '|') {
                result += (trueDpMap[beginIndex][(i - 1) / 2] * trueDpMap[(i + 1) / 2][endIndex]) % mod +
                        (trueDpMap[beginIndex][(i - 1) / 2] * falseDpMap[(i + 1) / 2][endIndex]) % mod +
                        (falseDpMap[beginIndex][(i - 1) / 2] * trueDpMap[(i + 1) / 2][endIndex]) % mod;
                result = result % mod;
            } else {
                result += (trueDpMap[beginIndex][(i - 1) / 2] * falseDpMap[(i + 1) / 2][endIndex]) % mod +
                        (falseDpMap[beginIndex][(i - 1) / 2] * trueDpMap[(i + 1) / 2][endIndex]) % mod;
                result = result % mod;
            }
        }
        result = result % mod;
        return result;
    }

    private static long[][] initFalseDpMap() {
        long[][] result = new long[(str.length() + 1) / 2][(str.length() + 1) / 2];
        for (int i = 0; i < str.length(); i = i + 2) {
            if (str.charAt(i) == '0') {
                result[i / 2][i / 2] = 1;
            }
        }
        return result;
    }

    private static long[][] initTrueDpMap() {
        long[][] result = new long[(str.length() + 1) / 2][(str.length() + 1) / 2];
        for (int i = 0; i < str.length(); i = i + 2) {
            if (str.charAt(i) == '1') {
                result[i / 2][i / 2] = 1;
            }
        }
        return result;
    }

    private static boolean isValid(String str) {
        boolean result;
        if (str.length() % 2 == 0) {
            result = false;
        } else if (isNumNotValid(str)) {
            result = false;
        } else if (isSimbolNotValid(str)) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    private static boolean isSimbolNotValid(String str) {
        boolean result = false;
        for (int i = 1; i < str.length(); i = i + 2) {
            if (str.charAt(i) != '^' && str.charAt(i) != '&' && str.charAt(i) != '|') {
                result = true;
                break;
            }
        }
        return result;
    }

    private static boolean isNumNotValid(String str) {
        boolean result = false;
        for (int i = 0; i < str.length(); i = i + 2) {
            if (str.charAt(i) != '0' && str.charAt(i) != '1') {
                result = true;
                break;
            }
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        str = scanner.next();
        desired = scanner.nextBoolean();
    }
}