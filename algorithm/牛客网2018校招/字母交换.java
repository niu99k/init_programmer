import java.util.*;

/*
题目描述
【编码题】字符串S由小写字母构成，长度为n。定义一种操作，每次都可以挑选字符串中任意的两个相邻字母进行交换。询问在至多交换m次之后，字符串中最多有多少个连续的位置上的字母相同？


输入描述:
第一行为一个字符串S与一个非负整数m。(1 <= |S| <= 1000, 1 <= m <= 1000000)
输出描述:
一个非负整数，表示操作之后，连续最长的相同字母数量。
示例1
输入
复制
abcbaa 2
输出
复制
2
说明
使2个字母a连续出现，至少需要3次操作。即把第1个位置上的a移动到第4个位置。
所以在至多操作2次的情况下，最多只能使2个b或2个a连续出现。
oslxlucjsqmfbglzihhxtjwehboynx 20
hkdbqojqvxlfulshrhpysezhlyzolb 20
gvlnzdgwdlpiqhimiaeirrgzbtiyky 20
3
* */
public class Main {
    static int maxMoveCount;
    static String str;
    static Map<String, Map<Integer, Integer>> positionMap;

    public static void main(String[] args) {
        init();
        int max = getMaxLength4AllChars();
        System.out.println(max);
    }

    private static int getMaxLength4AllChars() {
        return positionMap.keySet().stream()
                .mapToInt(key -> maxLength4OneKey(key))
                .max()
                .orElse(-1);
    }

    private static int maxLength4OneKey(String key) {
        int result;
        int maxContinuesCount4Key = 0;
        Map<Integer, Integer> positionMap4Key = positionMap.get(key);
        int count = positionMap4Key.keySet().size();
        int[][] dpMap = new int[1001][1001];
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                int removeCountBetweenTwoCountIndex = removeCountBetweenTwoCountIndex(i, j, positionMap4Key, dpMap);
                if (removeCountBetweenTwoCountIndex <= maxMoveCount &&
                        j - i + 1 > maxContinuesCount4Key
                ) {
                    maxContinuesCount4Key = j - i + 1;
                }
            }
        }
        result = maxContinuesCount4Key;
        return result;
    }

    private static int removeCountBetweenTwoCountIndex(int left, int right, Map<Integer, Integer> positionMap4Key, int[][] dpMap) {
        int result;
        if (right <= left) {
            result = 0;
        } else if (right == left + 1) {
            result = positionMap4Key.get(right) - positionMap4Key.get(left) - 1;
        } else {
            int moveCountifNotThingBetweem = positionMap4Key.get(right) - positionMap4Key.get(left) - 1;
            int countBetween = right - left - 1;
            if (dpMap[left + 1][right - 1] != 0) {
                result = dpMap[left + 1][right - 1];
            } else {
                result = removeCountBetweenTwoCountIndex(left + 1, right - 1, positionMap4Key, dpMap) + moveCountifNotThingBetweem - countBetween;
            }
        }
        dpMap[left][right] = result;
        return result;
    }

    private static void init() {
        input();
        initPositionMap();
    }

    private static void initPositionMap() {
        positionMap = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            String key = str.substring(i, i + 1);
            if (positionMap.containsKey(key)) {
                continue;
            } else {
                Map<Integer, Integer> positionMap4Key = positionMap4Key(key);
                positionMap.put(key, positionMap4Key);
            }
        }
    }

    private static Map<Integer, Integer> positionMap4Key(String key) {
        Map<Integer, Integer> result = new HashMap<>();
        int countIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.substring(i, i + 1).equals(key)) {
                result.put(countIndex, i);
                countIndex++;
            }
        }
        return result;
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        str = scanner.next();
        maxMoveCount = scanner.nextInt();
    }
}