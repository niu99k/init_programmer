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
    static Scanner scanner;
    static int count;
    static int result;

    public static void main(String strs[]) {
        init();
        result();
    }

    private static void result() {
        moveFromLeft2Right(count);
        System.out.println("It will move " + result + " steps.");
    }

    private static void moveFromLeft2Right(int count) {
        int numIndex = count;
        if (count != 0) {
            moveFromLeft2Right(count - 1);
            doMoveFromLeft2Middle(numIndex);
            moveFromRight2Left(count - 1);
            doMoveFromMiddle2Right(numIndex);
            moveFromLeft2Right(count - 1);
        }
    }

    private static void moveFromRight2Left(int count) {
        int numIndex = count;
        if (count != 0) {
            moveFromRight2Left(count - 1);
            doMoveFromRigth2Middle(numIndex);
            moveFromLeft2Right(count - 1);
            doMoveFromMiddle2Left(numIndex);
            moveFromRight2Left(count - 1);
        }
    }

    private static void doMoveFromMiddle2Left(int numIndex) {
        result += 1;
        System.out.println("Move " + numIndex + " from mid to left");
    }

    private static void doMoveFromRigth2Middle(int numIndex) {
        result += 1;
        System.out.println("Move " + numIndex + " from right to mid");
    }

    private static void doMoveFromMiddle2Right(int numIndex) {
        result += 1;
        System.out.println("Move " + numIndex + " from mid to right");
    }

    private static void doMoveFromLeft2Middle(int numIndex) {
        result += 1;
        System.out.println("Move " + numIndex + " from left to mid");
    }

    private static void init() {
        scanner = new Scanner(System.in);
        count = scanner.nextInt();
    }
}