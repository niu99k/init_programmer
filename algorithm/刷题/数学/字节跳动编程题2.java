/*
* 题目描述
有n个房间，现在i号房间里的人需要被重新分配，分配的规则是这样的：先让i号房间里的人全都出来，接下来按照 i+1, i+2, i+3, ... 的顺序依此往这些房间里放一个人，n号房间的的下一个房间是1号房间，直到所有的人都被重新分配。

现在告诉你分配完后每个房间的人数以及最后一个人被分配的房间号x，你需要求出分配前每个房间的人数。数据保证一定有解，若有多解输出任意一个解。

输入描述:
第一行两个整数n, x (2<=n<=10^5, 1<=x<=n)，代表房间房间数量以及最后一个人被分配的房间号；
第二行n个整数 a_i(0<=a_i<=10^9) ，代表每个房间分配后的人数。
输出描述:
输出n个整数，代表每个房间分配前的人数。
示例1
输入
复制
3 1
6 5 1
输出
复制
4 4 4*/

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int roomCount;
    static int lastPersonRoomIndex;
    static List<Long> rooms = new ArrayList<>();

    public static void main(String[] args) {
        initArgs();
        recoverRooms(leaveDoorIndex());
        System.out.println(rooms.toString().replace("[", "").replace("]", "").replace(",", ""));
    }

    private static void recoverRooms(int leaveDoorIndex) {
        long round = rooms.get(leaveDoorIndex);
        List<Integer> firstPartDoorIndexList = firstPartDoorIndexList(leaveDoorIndex);
        List<Integer> secondPartDoorIndexList = secondPartDoorIndexList(leaveDoorIndex);
        long immigrantCountInFirstPart = revoverRooms(firstPartDoorIndexList, round + 1);
        long immigrantCountInSecondPart = revoverRooms(secondPartDoorIndexList, round);
        rooms.set(leaveDoorIndex, immigrantCountInFirstPart + immigrantCountInSecondPart);
    }

    private static long revoverRooms(List<Integer> roomIndexList, long round) {
        long result = 0;
        for (int roomIndex : roomIndexList) {
            rooms.set(roomIndex, rooms.get(roomIndex) - round);
            result += round;
        }
        return result;
    }

    private static List<Integer> secondPartDoorIndexList(int leaveDoorIndex) {
        List<Integer> result = new ArrayList<>();
        if (lastPersonRoomIndex > leaveDoorIndex) {
            for (int i = 0; i < roomCount; i++) {
                if (i > lastPersonRoomIndex || i <= leaveDoorIndex) {
                    result.add(i);
                }
            }
        } else if (lastPersonRoomIndex < leaveDoorIndex) {
            for (int i = 0; i < roomCount; i++) {
                if (i <= leaveDoorIndex && i > lastPersonRoomIndex) {
                    result.add(i);
                }
            }
        } else {
            for (int i = 0; i < roomCount; i++) {
                result.add(i);
            }
        }
        return result;
    }

    private static List<Integer> firstPartDoorIndexList(int leaveDoorIndex) {
        List<Integer> result = new ArrayList<>();
        if (lastPersonRoomIndex > leaveDoorIndex) {
            for (int i = 0; i < roomCount; i++) {
                if (i > leaveDoorIndex && i <= lastPersonRoomIndex) {
                    result.add(i);
                }
            }
        } else if (lastPersonRoomIndex < leaveDoorIndex) {
            for (int i = 0; i < roomCount; i++) {
                if (i > leaveDoorIndex || i <= lastPersonRoomIndex) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    private static int leaveDoorIndex() {
        int result;
        long min = rooms.stream().min(Long::compareTo).get();
        List<Integer> minPeopleCountDoorsIndex = new ArrayList<>();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i) == min) {
                minPeopleCountDoorsIndex.add(i);
            }
        }
        result = firstIndexBeforeLastPersonRoomIndex(minPeopleCountDoorsIndex);
        return result;
    }

    private static int firstIndexBeforeLastPersonRoomIndex(List<Integer> minPeopleCountDoorsIndex) {
        int result;
        result = minPeopleCountDoorsIndex.stream()
                .min((index1, index2) -> distance2LastPersonRoomIndexComparator(index1, index2))
                .get();
        return result;
    }

    private static int distance2LastPersonRoomIndexComparator(int index1, int index2) {
        return distanceFromLastPersonRoom(index1) - distanceFromLastPersonRoom(index2);
    }

    private static int distanceFromLastPersonRoom(int index) {
        int result = (lastPersonRoomIndex + roomCount - index) % roomCount;
        return result;
    }

    private static void initArgs() {
        Scanner scanner = new Scanner(System.in);
        roomCount = scanner.nextInt();
        lastPersonRoomIndex = scanner.nextInt() - 1;
        for (int i = 0; i < roomCount; i++) {
            rooms.add(scanner.nextLong());
        }
    }
}
