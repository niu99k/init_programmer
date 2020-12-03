import java.util.Scanner;

/*
题目描述
一个不含有负数的数组可以代表一圈环形山，每个位置的值代表山的高度。比如，{3,1,2,4,5}，{4,5,3,1,2}或{1,2,4,5,3}都代表同样结构的环形山。3->1->2->4->5->3 方向叫作 next 方向(逆时针)，3->5->4->2->1->3 方向叫作 last 方向(顺时针)。
山峰 A 和 山峰 B 能够相互看见的条件为:
1. 如果 A 和 B 是同一座山，认为不能相互看见。
2. 如果 A 和 B 是不同的山，并且在环中相邻，认为可以相互看见。
3. 如果 A 和 B 是不同的山，并且在环中不相邻，假设两座山高度的最小值为 min。如果 A 通过 next 方向到 B 的途中没有高度比 min 大的山峰，或者 A 通过 last 方向到 B 的途中没有高度比 min 大的山峰，认为 A 和 B 可以相互看见。
问题如下：
给定一个含有负数可能有重复值的数组 arr，请问有多少对山峰能够相互看见？
输入描述:
第一行给出一个整数 n，表示山峰的数量。

以下一行 n 个整数表示各个山峰的高度。
输出描述:
输出一行表示答案。
示例1
输入
复制
5
3 1 2 4 5
输出
复制
7
备注:
1 \le n \le 10000001≤n≤1000000
-1000000 \le arr_i \le 1000000−1000000≤arr
i
​
 ≤1000000

5
5 1 1 1 2
 */
import java.util.*;

public class Main {
    static int mountainCount;
    static Scanner scanner;
    static int result;
    static List<Integer> mountainList;
    static Stack<Record> mpStack;

    static class Record {
        public Record(int height, int count) {
            this.height = height;
            this.count = count;
        }

        int height;
        int count;
    }

    public static void main(String strs[]) {
        init();
        result();
        printResult();
        deInit();
    }

    private static void printResult() {
        System.out.println(result);
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        mountainList = modifiedMountainListWithHighestMountainFirstIndex(highestMountainIndex());
        for (int i = 0; i < mountainCount; i++) {
            pushOneMountain2Stack(mountainList.get(i));
        }
        cleanStack();
    }

    private static void cleanStack() {
        while (!mpStack.isEmpty()) {
            if (mpStack.size() > 2) {
                while (mpStack.size() > 2) {
                    result += (2 + pairCount(mpStack.pop()));
                }
            } else if (mpStack.size() == 2) {
                Record record = mpStack.pop();
                result += (pairCount(record));
                if (mpStack.peek().count == 1) {
                    result += record.count;
                } else {
                    result += record.count * 2;
                }
            } else {
                result += pairCount(mpStack.pop());
            }
        }
    }

    private static void pushOneMountain2Stack(Integer mountainHeight) {
        if (mpStack.isEmpty()) {
            mpStack.push(new Record(mountainHeight, 1));
        } else if (mpStack.peek().height > mountainHeight) {
            mpStack.push(new Record(mountainHeight, 1));
        } else if (mpStack.peek().height == mountainHeight) {
            mpStack.peek().count += 1;
        } else {
            while (!mpStack.isEmpty()) {
                if (mpStack.peek().height < mountainHeight) {
                    result += 2 + pairCount(mpStack.pop());
                } else {
                    break;
                }
            }
            if (mpStack.peek().height > mountainHeight) {
                mpStack.push(new Record(mountainHeight, 1));
            } else if (mpStack.peek().height == mountainHeight) {
                mpStack.peek().count += 1;
            }
        }
    }

    private static int pairCount(Record record) {
        int result;
        if (record.count == 1) {
            result = 0;
        } else {
            result = combination(record.count, 2);
        }
        return result;
    }

    private static int combination(int all, int select) {
        int result;
        int member = 1;
        int denominator = 1;

        for (int i = 1; i <= select; i++) {
            member *= i;
        }
        for (int i = all; i > all - select; i--) {
            denominator *= i;
        }

        result = denominator / member;
        return result;
    }

    private static List<Integer> modifiedMountainListWithHighestMountainFirstIndex(int highestMountainIndex) {
        List<Integer> result = new ArrayList<>();
        for (int i = highestMountainIndex; i < mountainCount; i++) {
            result.add(mountainList.get(i));
        }
        for (int i = 0; i < highestMountainIndex; i++) {
            result.add(mountainList.get(i));
        }
        return result;
    }

    private static int highestMountainIndex() {
        int result = -1;
        int maxValue = mountainList.stream()
                .max((o1, o2) -> {
                    return o1 - o2;
                })
                .get();
        for (int i = 0; i < mountainCount; i++) {
            if (mountainList.get(i).equals(maxValue)) {
                result = i;
                break;
            }
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        mountainList = new ArrayList<>();
        mpStack = new Stack<>();
        mountainCount = scanner.nextInt();
        for (int i = 0; i < mountainCount; i++) {
            mountainList.add(scanner.nextInt());
        }
    }
}