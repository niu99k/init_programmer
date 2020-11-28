/*
题目描述
实现一个特殊功能的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
输入描述:
第一行输入一个整数N，表示对栈进行的操作总数。

下面N行每行输入一个字符串S，表示操作的种类。

如果S为"push"，则后面还有一个整数X表示向栈里压入整数X。

如果S为"pop"，则表示弹出栈顶操作。

如果S为"getMin"，则表示询问当前栈中的最小元素是多少。
输出描述:
对于每个getMin操作，输出一行表示当前栈中的最小元素是多少。
示例1
输入
复制
6
push 3
push 2
push 1
getMin
pop
getMin
输出
复制
1
2
备注:
1<=N<=1000000

-1000000<=X<=1000000

数据保证没有不合法的操作
 */

import java.util.*;

public class Main {
    static int operationCount;
    static Stack<Integer> inputStack;
    static Stack<Integer> minStack;
    static Scanner scanner = new Scanner(System.in);


    public static void main(String strs[]) {
        init();
        result();
        deinit();
    }

    private static void deinit() {
        scanner.close();
    }

    private static void result() {
        for (int i = 0; i < operationCount; i++) {
            String operation = scanner.next();
            switch (operation) {
                case "push":
                    pushQueues(scanner.nextInt());
                    break;
                case "pop":
                    popQueues();
                    break;
                case "getMin":
                    getMin();
                    break;
                default:
                    break;

            }
        }
    }

    private static void init() {
        operationCount = scanner.nextInt();
        inputStack = new Stack<>();
        minStack = new Stack<>();

    }

    private static void getMin() {
        System.out.println(minStack.peek());
    }

    private static void popQueues() {
        inputStack.pop();
        minStack.pop();
    }

    private static void pushQueues(int newNum) {
        pushInputStack(newNum);
        pushMinStack(newNum);
    }

    private static void pushMinStack(int newNum) {
        if (minStack.isEmpty()) {
            minStack.push(newNum);
        } else if (newNum <= minStack.peek()) {
            minStack.push(newNum);
        } else {
            minStack.push(minStack.peek());
        }
    }

    private static void pushInputStack(int newNum) {
        inputStack.push(newNum);
    }
}