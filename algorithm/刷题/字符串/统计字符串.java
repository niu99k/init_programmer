/*
题目描述
给定一个字符串str，返回str的统计字符串。例如“aaabbbbcccd”的统计字符串为“a_3_b_4_c_3_d_1”。
输入描述:
输入一行字符串，代表str1\leq length（str）\leq 10^51≤length（str）≤10
5
 。
输出描述:
输出一行字符串，代表统计字符串。
示例1
输入
复制
offerofferzainaliiiiii
输出
复制
o_1_f_2_e_1_r_1_o_1_f_2_e_1_r_1_z_1_a_1_i_1_n_1_a_1_l_1_i_6
示例2
输入
复制
hhhaaa
输出
复制
h_3_a_3
备注:
时间复杂度O（n）O（n），空间复杂度O（n）O（n）。
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static String str;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void init() {
        scanner = new Scanner(System.in);
        str = scanner.next();
    }

    private static void result() {
        System.out.println(solve());
    }

    private static String solve() {
        String result;
        StringBuffer stringBuffer = new StringBuffer();
        List<Character> chrList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        initChrListAndCountList(chrList, countList);
        for (int i = 0; i < chrList.size(); i++) {
            if (i != chrList.size() - 1) {
                stringBuffer.append(chrList.get(i) + "_" + countList.get(i) + "_");
            } else {
                stringBuffer.append(chrList.get(i) + "_" + countList.get(i));
            }
        }
        result = stringBuffer.toString();
        return result;
    }

    private static void initChrListAndCountList(List<Character> chrList, List<Integer> countList) {
        int count = 0;
        char chr = str.charAt(0);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == chr) {
                count++;
            } else {
                chrList.add(chr);
                countList.add(count);
                chr = str.charAt(i);
                count = 1;
            }
        }
        chrList.add(chr);
        countList.add(count);

    }

}
