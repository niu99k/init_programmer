
/*
题目描述
有一个推箱子的游戏, 一开始的情况如下图:

上图中, '.' 表示可到达的位置, '#' 表示不可到达的位置，其中 S 表示你起始的位置, 0表示初始箱子的位置, E表示预期箱子的位置，你可以走到箱子的上下左右任意一侧, 将箱子向另一侧推动。如下图将箱子向右推动一格;
..S0.. -> ...S0.

注意不能将箱子推动到'#'上, 也不能将箱子推出边界;

现在, 给你游戏的初始样子, 你需要输出最少几步能够完成游戏, 如果不能完成, 则输出-1。

输入描述:
第一行为2个数字,n, m, 表示游戏盘面大小有n 行m 列(5< n, m < 50);
后面为n行字符串,每行字符串有m字符, 表示游戏盘面;
输出描述:
一个数字,表示最少几步能完成游戏,如果不能,输出-1;
示例1
输入
复制
3 6
.S#..E
.#..0.
......
输出
复制
11
 */

import java.util.*;
import java.util.stream.Collectors;

class Point {
    int x;
    int y;
    String content;
    boolean isSearched;
    int distanceFromStart = 0;
    int personStepCount = 0;
    List<Point> prePoint = new ArrayList<>();

    Point(int x, int y, String content) {
        this.x = x;
        this.y = y;
        this.content = content;
        isSearched = false;
        personStepCount = 0;

    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;

    }
}

class PushStatus {
    Point person;
    Point box;

    PushStatus() {
    }

    PushStatus(Point person, Point box) {
        this.person = person;
        this.box = box;
    }
}

public class Main {
    static Point[][] problemMap = new Point[55][55];
    static Point beginPoint;
    static Point boxPoint;
    static Point targetPoint;
    final static String beginStr = "S";
    final static String boxStr = "0";
    final static String targetStr = "E";
    final static String barrier = "#";
    final static String path = ".";
    static int n;
    static int m;
    static PushStatus initPushingStatus;
    static int[][][][] initpushStatusMap = new int[55][55][55][55];

    public static void main(String[] args) {
        int result;
        initMap();
        result = bfsPushStatus();
        System.out.println(result);
    }

    private static int bfsPushStatus() {
        int result = -1;
        Queue<PushStatus> pushingStatuseQueue = new LinkedList<>();
        pushingStatuseQueue.offer(initPushingStatus);
        while (pushingStatuseQueue.peek() != null) {
            PushStatus tempPushStatus = pushingStatuseQueue.poll();
            if (pushStatusSuc(tempPushStatus)) {
                result = initpushStatusMap[tempPushStatus.person.x][tempPushStatus.person.y][tempPushStatus.box.x][tempPushStatus.box.y];
                break;
            } else {
                addUnsearchedPushStatus2Queue(tempPushStatus, pushingStatuseQueue);
            }
        }
        return result;
    }

    private static void addUnsearchedPushStatus2Queue(PushStatus pushStatus, Queue<PushStatus> queue) {
        List<PushStatus> allPersonMovePossibility = allPersonMovePossibility(pushStatus);
        List<PushStatus> feasiblePersonMovePossibility = feasiblePersonMovePossibility(pushStatus, allPersonMovePossibility);
        feasiblePersonMovePossibility.stream()
                .forEach(status -> queue.offer(status));
    }

    private static List<PushStatus> feasiblePersonMovePossibility(PushStatus startPushStatus, List<PushStatus> allPersonMovePossibility) {
        List<PushStatus> result = new ArrayList<>();
        int startStepCount = initpushStatusMap[startPushStatus.person.x][startPushStatus.person.y][startPushStatus.box.x][startPushStatus.box.y];
        result = allPersonMovePossibility.stream()
                .filter(pushStatus -> pushStatus.person.x >= 0 && pushStatus.person.x <= n - 1)
                .filter(pushStatus -> pushStatus.box.x >= 0 && pushStatus.box.x <= n - 1)
                .filter(pushStatus -> pushStatus.person.y >= 0 && pushStatus.person.y <= m - 1)
                .filter(pushStatus -> pushStatus.box.y >= 0 && pushStatus.box.y <= m - 1)
                .filter(pushStatus -> initpushStatusMap[pushStatus.person.x][pushStatus.person.y][pushStatus.box.x][pushStatus.box.y] == 0)
                .filter(pushStatus -> !problemMap[pushStatus.box.x][pushStatus.box.y].content.equals(barrier))
                .filter(pushStatus -> !problemMap[pushStatus.person.x][pushStatus.person.y].content.equals(barrier))
                .collect(Collectors.toList());
        result.stream()
                .forEach(pushStatus -> initpushStatusMap[pushStatus.person.x][pushStatus.person.y][pushStatus.box.x][pushStatus.box.y] = startStepCount + 1);
        return result;
    }

    private static List<PushStatus> allPersonMovePossibility(PushStatus pushStatus) {
        List<PushStatus> result = new ArrayList<>();
        Point person = pushStatus.person;
        Point box = pushStatus.box;

        Point personReduceXMove = new Point(person.x - 1, person.y);
        Point personAddXMove = new Point(person.x + 1, person.y);
        Point personReduceYMove = new Point(person.x, person.y - 1);
        Point personAddYMove = new Point(person.x, person.y + 1);

        Point boxAfterPersonReduceXMove = arePointsIdentical(personReduceXMove, box) ? new Point(box.x - 1, box.y) : new Point(box.x, box.y);
        Point boxAfterPersonAddXMove = arePointsIdentical(personAddXMove, box) ? new Point(box.x + 1, box.y) : new Point(box.x, box.y);
        Point boxAfterPersonReduceYMove = arePointsIdentical(personReduceYMove, box) ? new Point(box.x, box.y - 1) : new Point(box.x, box.y);
        Point boxAfterPersonAddYMove = arePointsIdentical(personAddYMove, box) ? new Point(box.x, box.y + 1) : new Point(box.x, box.y);

        PushStatus pushStatusAfterPersonReduceXMove = new PushStatus(personReduceXMove, boxAfterPersonReduceXMove);
        PushStatus pushStatusAfterPersonAddXMove = new PushStatus(personAddXMove, boxAfterPersonAddXMove);
        PushStatus pushStatusAfterPersonReduceYMove = new PushStatus(personReduceYMove, boxAfterPersonReduceYMove);
        PushStatus pushStatusAfterPersonAddYMove = new PushStatus(personAddYMove, boxAfterPersonAddYMove);

        result.add(pushStatusAfterPersonReduceXMove);
        result.add(pushStatusAfterPersonAddXMove);
        result.add(pushStatusAfterPersonReduceYMove);
        result.add(pushStatusAfterPersonAddYMove);
        return result;
    }

    private static boolean arePointsIdentical(Point a, Point b) {
        return a.x == b.x && a.y == b.y;
    }

    private static boolean pushStatusSuc(PushStatus pushStatus) {
        if (problemMap[pushStatus.box.x][pushStatus.box.y].content.equals(targetStr)) {
            return true;
        } else {
            return false;
        }
    }

    private static void initMap() {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        for (int i = 0; i < n; i++) {
            String temp = in.next();
            for (int j = 0; j < m; j++) {
                String content = temp.substring(j, j + 1);
                Point tempPoint = new Point(i, j, content);
                if (content.equals(boxStr)) {
                    boxPoint = tempPoint;
                } else if (content.equals(targetStr)) {
                    targetPoint = tempPoint;
                } else if (content.equals(beginStr)) {
                    beginPoint = tempPoint;
                }
                tempPoint.prePoint = new ArrayList<>();
                problemMap[i][j] = tempPoint;
            }
        }
        initPushingStatus();
    }

    private static void initPushingStatus() {
        initPushingStatus = new PushStatus(beginPoint, boxPoint);
    }
}
