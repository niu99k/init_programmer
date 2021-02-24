/*
题目描述
给定一个字符串的数组strs，请找到一种拼接顺序，使得所有的字符串拼接起来组成的字符串是所有可能性中字典序最小的，并返回这个字符串。
输入描述:
输入包含多行，第一行包含一个整数n（ 1 \leq n \leq 10^5 ）（1≤n≤10
5
 ），代表字符串数组strs的长度，后面n行，每行一个字符串，代表strs[i]（保证所有字符串长度都小于10）。
输出描述:
输出一行，包含一个字符串，代表返回的字典序最小的字符串。
示例1
输入
复制
2
abc
de
输出
复制
abcde
示例2
输入
复制
2
b
ba
输出
复制
bab
备注:
时间复杂度O（nlog_2n）O（nlog
2
​
 n），额外空间复杂度O（1）O（1）。
 */
import java.util.*;
import java.util.stream.Collectors;
public class Main{
    static Scanner scanner;
    static List<String> strArray;
    static int arrayLen;
    public static void main(String[]args){
        init();
        result();
        deInit();
    }
    private static void deInit(){
        scanner.close();
    }
    private static void init(){
        scanner=new Scanner(System.in);
        arrayLen=scanner.nextInt();
        strArray=new ArrayList<>(arrayLen);
        for(int i=0;i<arrayLen;i++){
            strArray.add(scanner.next());
        }
    }
    private static void result(){
        List<String>result=solve();
        for(int i=0;i<arrayLen;i++){
            System.out.print(result.get(i));
        }
    }
    private static List<String> solve(){
        List<String> result;
        result=strArray.stream()
                .sorted((str1,str2)->{return (str1+str2).compareTo(str2+str1);})
                .collect(Collectors.toList());
        return result;
    }
}