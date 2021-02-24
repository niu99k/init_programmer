/*
题目描述
给定一个数组arr，返回arr的最长无的重复子串的长度(无重复指的是所有字母都不相同)。
输入描述:
输入包含两行，第一行包含一个整数n（1 \leq n \leq 10^5)（1≤n≤10
5
 )，代表数组arr的长度,第二行包含n个整数，代表数组arr（ 1 \leq arr[i] \leq 10^6 ）（1≤arr[i]≤10
6
 ）。
输出描述:
输出一个整数，代表arr的最长无重复字符的长度。
示例1
输入
复制
4
2 3 4 5
输出
复制
4
示例2
输入
复制
5
2 2 3 4 3
输出
复制
3
备注:
时间复杂度O（n）O（n），额外空间复杂度O（MAX（arr[i]））O（MAX（arr[i]））。
 */

import java.util.*;
public class Main{
    static Scanner scanner;
    static int arrayLen;
    static int[] array;
    static int[] arrayMap;

    public static void main(String[]args){
        init();
        result();
        deInit();
    }
    private static void deInit(){
        scanner.close();
    }
    private static void result(){
        System.out.println(solve());
    }
    private static int solve(){
        int result;
        int index=0;
        int beginIndex=-1;
        int maxLen=0;
        while(index<arrayLen){
            beginIndex=Math.max(beginIndex,arrayMap[array[index]]);
            int len=index-beginIndex;
            maxLen=Math.max(maxLen,len);
            arrayMap[array[index]]=index;
            index++;
        }
        result=maxLen;
        return result;
    }
    private static void init(){
        scanner=new Scanner(System.in);
        arrayLen=scanner.nextInt();
        array=new int[arrayLen];
        arrayMap=new int[100000];

        for(int i=0;i<arrayLen;i++){
            array[i]=scanner.nextInt();
        }
        for(int i=0;i<100000;i++){
            arrayMap[i]=-1;
        }
    }
}