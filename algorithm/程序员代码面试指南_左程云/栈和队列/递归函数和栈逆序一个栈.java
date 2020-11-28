/*
题目描述
一个栈依次压入1,2,3,4,5，那么从栈顶到栈底分别为5,4,3,2,1。将这个栈转置后，从栈顶到栈底为1,2,3,4,5，也就是实现栈中元素的逆序，但是只能用递归函数来实现，不能用其他数据结构。
输入描述:
输入数据第一行一个整数N为栈中元素的个数。

接下来一行N个整数Xi
 表示从栈顶依次到栈底的每个元素。
输出描述:
输出一行表示栈中元素逆序后的每个元素
示例1
输入
复制
5
1 2 3 4 5
输出
复制
5 4 3 2 1
 */

import java.util.*;

public class Main {
    static int numCount;
    static Scanner scanner;
    static Stack<Integer> stack;

    public static void main(String strs[]) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        reverse(stack);
        printResult();
    }

    private static void printResult() {
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        for (int i = result.size() - 1; i >= 0; i--) {
            System.out.print(result.get(i)+" ");
        }
    }

    private static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        } else {
            int bottom = getBottomAndRemove(stack);
            reverse(stack);
            stack.push(bottom);
        }
    }

    private static int getBottomAndRemove(Stack<Integer> stack) {
        int result;
        if (stack.size() == 1) {
            result = stack.pop();
        } else {
            int top = stack.pop();
            result = getBottomAndRemove(stack);
            stack.push(top);
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        numCount = scanner.nextInt();
        stack = new Stack<>();
        for (int i = 0; i < numCount; i++) {
            stack.push(scanner.nextInt());
        }
    }
}