/*
题目描述
给一个全是小写字母的字符串str，删除多余字符，使得每种字符只保留一个，并且让最终结果字符串字典序最小。
输入描述:
输入包含一行字符串，代表str（1 \leq length_{str} \leq 10^5 ）（1≤length
str
​
 ≤10
5
 ）。
输出描述:
输出一行，代表删除后的字符串。
示例1
输入
复制
acbc
输出
复制
abc
示例2
输入
复制
dbcacbca
输出
复制
dabc
备注:
时间复杂度O（n）O（n），额外空间复杂度O（1）O（1）。
 */
import java.util.*;
public class Main{
    static Scanner scanner;
    static String str;
    static String resultStr;
    static int[] countMap;
    static int charCount;
    public static void main(String args[]){
        init();
        result();
        deInit();
    }
    private static void init(){
        scanner=new Scanner(System.in);
        str=scanner.nextLine();
        resultStr="";
        charCount=0;
        countMap();
    }
    private static void result(){
        solve();
        printResult();
    }
    private static void solve(){
        while(str.length()>0){
            countMap();
            getNextResultCharAnddeletePartStr();
        }
    }
    private static void getNextResultCharAnddeletePartStr(){
        for(int i=0;i<str.length();i++){
            final char chr=str.charAt(i);
            countMap[chr]--;
            if(countMap[chr]==0){
                final String nextChr=getNextResultChar(i);
                resultStr+=nextChr;
                deletePartStr(nextChr);
                break;
            }
        }
    }
    private static void deletePartStr(String nextChr){
        str=str.replace(nextChr,"");
    }
    private static String getNextResultChar(int index){
        String result;
        char min='z'+1;
        for(int i=0;i<=index;i++){
            char chr=str.charAt(i);
            if(chr<min){
                min=chr;
            }
        }
        result=min+"";
        return result;
    }
    private static void countMap(){
        countMap=new int[150];
        for(int i=0;i<str.length();i++){
            final int index=str.charAt(i);
            countMap[index]++;
        }
    }
    private static  void printResult(){
        System.out.println(resultStr);
    }
    private static void deInit(){
        scanner.close();
    }
}
