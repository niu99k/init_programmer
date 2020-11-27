/*
题目描述
输入一个字符串，输出该字符串中相邻字符的所有组合。
举个例子，如果输入abc，它的组合有a、b、c、ab、bc、abc。（注意：输出的组合需要去重）（40分）
输入描述:
一个字符串
输出描述:
一行，每个组合以空格分隔，相同长度的组合需要以字典序排序，且去重。
示例1
输入
复制
bac
输出
复制
a b c ac ba bac
* */

import java.util.*;

public class Main {
    static String input;

    public static void main(String strs[]) {
        init();
        result();
    }

    private static void result() {
        for (int i = 0; i < input.length(); i++) {
            List<String> allStrsWithSize = allStrsWithSize(i + 1);
            Collections.sort(allStrsWithSize);
            removeAdditionAndPrint(allStrsWithSize);
        }
    }

    private static void removeAdditionAndPrint(List<String> allStrsWithSize) {
        String pre = "";
        for (int i = 0; i < allStrsWithSize.size(); i++) {
            if (allStrsWithSize.get(i).equals(pre)) {
                continue;
            } else {
                pre = allStrsWithSize.get(i);
                System.out.print(allStrsWithSize.get(i) + " ");
            }
        }
    }

    private static List<String> allStrsWithSize(int len) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i <= input.length() - len; i++) {
            result.add(input.substring(i, i + len));
        }
        return result;
    }

    private static void init() {
        Scanner scanner = new Scanner(System.in);
        input = scanner.next();
        scanner.close();
    }
}