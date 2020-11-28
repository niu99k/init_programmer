/*
题目描述
用两个栈实现队列，支持队列的基本操作。
输入描述:
第一行输入一个整数N，表示对队列进行的操作总数。

下面N行每行输入一个字符串S，表示操作的种类。

如果S为"add"，则后面还有一个整数X表示向队列尾部加入整数X。

如果S为"poll"，则表示弹出队列头部操作。

如果S为"peek"，则表示询问当前队列中头部元素是多少。
输出描述:
对于每一个为"peek"的操作，输出一行表示当前队列中头部元素是多少。
示例1
输入
复制
6
add 1
add 2
add 3
peek
poll
peek
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
    static Scanner scanner;
    static int operationCount;
    static Stack<Integer> pushStack;
    static Stack<Integer> pollStack;

    public static void main(String strs[]) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        for (int i = 0; i < operationCount; i++) {
            String operation = scanner.next();
            switch (operation) {
                case "add":
                    addQueue(scanner.nextInt());
                    break;
                case "peek":
                    peekQueue();
                    break;
                case "poll":
                    pollQueue();
                    break;
                default:
                    break;
            }
        }
    }

    private static void pollQueue() {
        if (pollStack.isEmpty()) {
            pushStack2PollStack();
            pollStack.pop();
        } else {
            pollStack.pop();
        }
    }

    private static void peekQueue() {
        if (pollStack.isEmpty()) {
            pushStack2PollStack();
            System.out.println(pollStack.peek());
        } else {
            System.out.println(pollStack.peek());
        }
    }

    private static void pushStack2PollStack() {
        while (!pushStack.isEmpty()) {
            pollStack.push(pushStack.pop());
        }
    }

    private static void addQueue(int newNum) {
        if (pushStack.isEmpty()) {
            pollStack2PushStack();
            pushStack.push(newNum);
        } else {
            pushStack.push(newNum);
        }
    }

    private static void pollStack2PushStack() {
        while (!pollStack.isEmpty()) {
            pushStack.push(pollStack.pop());
        }
    }

    private static void init() {
        scanner = new Scanner(System.in);
        pushStack = new Stack<>();
        pollStack = new Stack<>();
        operationCount = scanner.nextInt();
    }
}