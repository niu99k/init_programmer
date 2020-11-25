/*
题目描述
P为给定的二维平面整数点集。定义 P 中某点x，如果x满足 P 中任意点都不在 x 的右上方区域内（横纵坐标都大于x），则称其为“最大的”。求出所有“最大的”点的集合。（所有点的横坐标和纵坐标都不重复, 坐标轴范围在[0, 1e9) 内）

如下图：实心点为满足条件的点的集合。请实现代码找到集合 P 中的所有 ”最大“ 点的集合并输出。



输入描述:
第一行输入点集的个数 N， 接下来 N 行，每行两个数字代表点的 X 轴和 Y 轴。
对于 50%的数据,  1 <= N <= 10000;
对于 100%的数据, 1 <= N <= 500000;
输出描述:
输出“最大的” 点集合， 按照 X 轴从小到大的方式输出，每行两个数字分别代表点的 X 轴和 Y轴。
示例1
输入
复制
5
1 2
5 3
4 6
7 5
9 0
输出
复制
4 6
7 5
9 0
* */

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static class Point {
        int x;
        int y;
    }

    static Scanner scanner = new Scanner(System.in);
    static int pointCount;
    static List<Point> pointList;
    static List<Point> pointListSortedByX;
    static List<Point> result;

    public static void main(String strs[]) {
        init();
        result();
        printResult();
    }

    private static void printResult() {
        for (int i = result.size() - 1; i >= 0; i--) {
            System.out.println(result.get(i).x + " " + result.get(i).y);
        }
    }

    private static void result() {
        int maxPointy = 0;
        for (int i = 0; i < pointListSortedByX.size(); i++) {
            if (pointListSortedByX.get(i).y >= maxPointy) {
                result.add(pointListSortedByX.get(i));
                maxPointy = pointListSortedByX.get(i).y;
            }
        }

    }

    private static void init() {
        pointCount = scanner.nextInt();
        pointList = new ArrayList<>();
        result = new ArrayList<>();
        for (int i = 0; i < pointCount; i++) {
            Point point = new Point();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            point.x = x;
            point.y = y;
            pointList.add(point);
        }
        pointListSortedByX = pointList.stream()
                .sorted((point1, point2) -> {
                    return point2.x - point1.x;
                }).collect(Collectors.toList());

    }
}
/*
10
298498081 747278511
427131847 460128162
939984059 817455089
911902081 683024728
474941318 6933274
140954425 607811211
336122540 629431445
208240456 458323237
646203300 469339106
106410694 436340495
 */