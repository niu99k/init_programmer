/*
链接：https://www.nowcoder.com/questionTerminal/21ae9898b374498dbea97831e7ec3fcd
来源：牛客网

快速排序
快速排序又是一种分而治之思想在排序算法上的典型应用。
算法步骤
1、从数列中挑出一个元素，称为 “基准”（pivot）。
2、重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
3、递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
4、递归的最底部情形，是数列的大小是零或一，也就是永远都已经被排序好了。虽然一直递归下去，但是这个算法总会退出，因为在每次的迭代（iteration）中，它至少会把一个元素摆到它最后的位置去。


输入描述:
20,413,3,53,90,324


输出描述:
[3, 20, 53, 90, 324, 413]
示例1
输入
20,413,3,53,90,324
输出
[3, 20, 53, 90, 324, 413]

备注:
特别提醒：注意输入输出，严格按照示例。
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        myCheck();
        answer();
    }

    private static void answer() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] strs = str.split(",");
        long[] array = new long[strs.length];
        for (int i = 0; i < strs.length; i++) {
            array[i] = Long.valueOf(strs[i]);
        }
        System.out.println(Arrays.toString(solve2(array)));

    }

    private static void myCheck() {
        for (int i = 0; i < 1000; i++) {
            long[] randomArray = randomArray();
            Long[] result1 = solve1(randomArray);
            long[] result2 = solve2(randomArray);
            if (!Arrays.toString(result1).equals(Arrays.toString(result2))) {
                System.out.println(Arrays.toString(randomArray).replace(",", " ").replace("[", "").replace("]", ""));
                System.out.println(Arrays.toString(result1));
                System.out.println(Arrays.toString(result2));
                System.out.println("gg");
                System.out.println(i);
                break;
            }
        }
    }

    private static long[] solve2(long[] randomArray) {
        long[] result = new long[randomArray.length];
        for (int i = 0; i < randomArray.length; i++) {
            result[i] = randomArray[i];
        }
        quickSort(0, randomArray.length - 1, result);
        return result;
    }

    private static void quickSort(int begin, int end, long[] randomArray) {
        if (begin < end) {
            int[] patition = patition(begin, end, randomArray, begin + new Random().nextInt(end - begin + 1));
            quickSort(begin, patition[0], randomArray);
            quickSort(patition[1], end, randomArray);
        }
    }

    private static int[] patition(int begin, int end, long[] array, int randomIndex) {
        int[] result = new int[2];
        final long flag = array[randomIndex];
        swap(end, randomIndex, array);
        int leftIndex = begin - 1;
        int rightIndex = end;

        int index = begin;
        while (index < rightIndex) {
            if (array[index] < flag) {
                swap(index++, ++leftIndex, array);
            } else if (array[index] > flag) {
                swap(index, --rightIndex, array);
            } else {
                index++;
            }
        }
        swap(rightIndex, end, array);
        result[0] = leftIndex;
        result[1] = rightIndex;
        return result;
    }

    private static void swap(int index1, int index2, long[] array) {
        long temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static class TestComparator implements Comparator<Long> {
        @Override
        public int compare(Long o1, Long o2) {
            return o1 - o2 < 0 ? -1 : 1;
        }
    }

    private static Long[] solve1(long[] array) {
        Long[] result = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        Arrays.sort(result, new TestComparator());
        return result;
    }

    private static long[] randomArray() {
        long[] result;
        final int maxLen = 10;
//        final long maxNum = Long.MAX_VALUE;
        final long maxNum = 20;
        final int len = (int) (Math.random() * maxLen) + 1;
        result = new long[len];
        for (int i = 0; i < len; i++) {
            result[i] = (long) (Math.random() * maxNum) + 1;
        }
        return result;

    }
}