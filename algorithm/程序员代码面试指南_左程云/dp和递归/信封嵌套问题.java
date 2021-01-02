/*
题目描述
给n个信封的长度和宽度。如果信封A的长和宽都小于信封B，那么信封A可以放到信封B里，请求出信封最多可以嵌套多少层。
输入描述:
输出包含多行，第一行包括一个整数，代表信封的个数n\left(1 \leq n\leq  10^{5}\right)(1≤n≤10
5
 )。接下来n行，每行两个整数l_il
i
​
 和w_iw
i
​
 ，代表信封的长度和宽度\left( -1e9\leq l_i,w_i \leq 1e9 \right)(−1e9≤l
i
​
 ,w
i
​
 ≤1e9)。
输出描述:
输出包括一行，代表信封最多嵌套多少层。
示例1
输入
复制
9
3 4
2 3
4 5
1 3
2 2
3 6
1 2
3 2
2 4
输出
复制
4
说明
从里到外分别是{1，2}，{2，3}，{3，4}，{4，5}。
示例2
输入
复制
2
1 4
4 1
输出
复制
1
备注:
时间复杂度O(n\log n)O(nlogn)，空间复杂度O(n)O(n)。
 */

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static class Envelope {
        long len;
        long width;

        Envelope(long len, long width) {
            this.len = len;
            this.width = width;
        }
    }

    static Scanner scanner;
    static int arrayLen;
    static List<Envelope> envelopeList;

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

    private static long solve() {
        long result;
        envelopeList = envelopeList.stream()
                .sorted((e1, e2) -> {
                    if (e1.len != e2.len) {
                        return (e1.len - e2.len) < 0 ? -1 : 1;
                    } else {
                        return e1.width - e2.width < 0 ? -1 : 1;
                    }
                })
                .collect(Collectors.toList());
        long[] dpMap = dpMap(widthList());
        result = 0;
        for (int i = 0; i < arrayLen; i++) {
            result = Math.max(dpMap[i], result);
        }
        return result;
    }

    private static long[] dpMap(long[] array) {
        long[] dpMap = new long[arrayLen];
        long[] memoMap = new long[arrayLen];
        int maxLen = 0;
        for (int i = 0; i < arrayLen; i++) {
            int firstBiggerIndex = fistBiggerIndex(memoMap, maxLen, array[i]);
            if (firstBiggerIndex == -1) {
                maxLen += 1;
                memoMap[maxLen - 1] = array[i];
                dpMap[i] = maxLen;
            } else {
                memoMap[firstBiggerIndex] = array[i];
                dpMap[i] = firstBiggerIndex + 1;
            }
        }
        return dpMap;
    }

    private static int fistBiggerIndex(long[] memoMap, int maxLen, long baseNum) {
        int result = -1;
        if (maxLen == 0) {
            result = -1;
        } else {
            int beginIndex = 0;
            int endIndex = maxLen - 1;
            while (endIndex >= beginIndex) {
                int midIndex = (beginIndex + endIndex) / 2;
                if (memoMap[midIndex] >= baseNum) {
                    if (midIndex == 0) {
                        result = midIndex;
                        break;
                    } else if (memoMap[midIndex - 1] < baseNum) {
                        result = midIndex;
                        break;
                    } else {
                        endIndex = midIndex - 1;
                    }
                } else {
                    beginIndex = midIndex + 1;
                }
            }
        }
        return result;
    }

    private static long[] widthList() {
        long[] result = new long[arrayLen];
        for (int i = 0; i < arrayLen; i++) {
            result[i] = envelopeList.get(i).width;
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        arrayLen = scanner.nextInt();
        envelopeList = new ArrayList<>(arrayLen);
        for (int i = 0; i < arrayLen; i++) {
            envelopeList.add(new Envelope(scanner.nextLong(), scanner.nextLong()));
        }
    }
}