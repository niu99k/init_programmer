/*
题目描述
汉诺塔问题比较经典，这里修改一下游戏规则：现在限制不能从最左侧的塔直接移动到最右侧，也不能从最右侧直接移动到最左侧，而是必须经过中间。求当塔有n层的时候，打印最优移动过程和最优移动总步数。
输入描述:
输入一个数n，表示塔层数
输出描述:
按样例格式输出最优移动过程和最优移动总步数
示例1
输入
复制
2
输出
复制
Move 1 from left to mid
Move 1 from mid to right
Move 2 from left to mid
Move 1 from right to mid
Move 1 from mid to left
Move 2 from mid to right
Move 1 from left to mid
Move 1 from mid to right
It will move 8 steps.
说明
当塔数为两层时，最上层的塔记为1，最下层的塔记为2
备注:
1\le n \le 121≤n≤12/
 */

import java.util.*;

public class Main {
    static class Operation {
        String from;
        String to;
        Stack<Integer> fromStack;
        Stack<Integer> toStack;

        void printOp(int numIndex) {
            System.out.println("Move " + numIndex + " from " + from + " to " + to);
        }
    }

    static int count;
    static Scanner scanner;
    static List<Operation> operationList;
    static Stack<Integer> left;
    static Stack<Integer> mid;
    static Stack<Integer> right;
    static Operation lastOperation;
    static int result;

    public static void main(String strs[]) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        firstStep();
        otherSteps();
        printResult();
    }

    private static void printResult() {
        System.out.println("It will move " + result + " steps.");
    }

    private static void otherSteps() {
        while (!left.isEmpty() || !mid.isEmpty()) {
            excuteOp(operationList.stream()
                    .filter(op -> !op.fromStack.isEmpty())
                    .filter(op -> numLimited(op))
                    .filter(op -> !(op.from == lastOperation.to && op.to == lastOperation.from))
                    .findFirst().get());
        }
    }

    private static void excuteOp(Operation op) {
        result += 1;
        lastOperation = op;
        op.printOp(op.fromStack.peek());
        op.toStack.push(op.fromStack.pop());
    }

    private static boolean numLimited(Operation op) {
        boolean result;
        if (op.toStack.isEmpty()) {
            result = true;
        } else {
            result = op.fromStack.peek() <= op.toStack.peek();
        }
        return result;
    }

    private static void firstStep() {
        int numIndex = left.pop();
        mid.push(numIndex);
        operationList.get(0).printOp(numIndex);
        lastOperation = operationList.get(0);
        result += 1;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        count = scanner.nextInt();
        operationList = new ArrayList<>();
        left = new Stack<>();
        mid = new Stack<>();
        right = new Stack<>();

        Operation op1 = new Operation();
        op1.from = "left";
        op1.to = "mid";
        op1.fromStack = left;
        op1.toStack = mid;
        Operation op2 = new Operation();
        op2.from = "mid";
        op2.to = "left";
        op2.fromStack = mid;
        op2.toStack = left;
        Operation op3 = new Operation();
        op3.from = "mid";
        op3.to = "right";
        op3.fromStack = mid;
        op3.toStack = right;
        Operation op4 = new Operation();
        op4.from = "right";
        op4.to = "mid";
        op4.fromStack = right;
        op4.toStack = mid;
        operationList.add(op1);
        operationList.add(op2);
        operationList.add(op3);
        operationList.add(op4);

        for (int i = count; i > 0; i--) {
            left.push(i);
        }
    }
}