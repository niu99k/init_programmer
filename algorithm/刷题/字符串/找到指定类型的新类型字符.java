/*
题目描述
      新类型字符的定义如下:
      1.新类型字符是长度为1或者2的字符串。
      2. 表现形式可以仅是小写字母，例如，"e"; 也可以是大写字母+小写字母，例如，"Ab";还可以是大写字母+大写字母，例如，"DC"。
      现在给定一个字符串str, str 一定是若干新类型字符 正确组合的结果。比如"eaCCBi"，由新类型字符"e"、"a”、"CC"和"Bi"拼成。 再给定一个整数k，代表str中的位置。请返回第k个位置的新类型字符。

输入描述:
输入包含两行，第一行两个整数n，k(1 \leq n \leq 10^5,0 \leq k \leq n-1)(1≤n≤10
5
 ,0≤k≤n−1)n代表字符串str的长度,第二行包含一个字符串，代表字符串str。
输出描述:
输出包含一个被k位置指定的新型字符。
示例1
输入
复制
11 7
aaABCDEcBCg
输出
复制
Ec
备注:
时间复杂度O（1）O（1），空间复杂度O（1）O（1）。
 */

import java.util.*;
public class Main{
    static Scanner scanner;
    static int strLen;
    static int index;
    static String str;

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
    private static String solve(){
        String result="";
        int len=beforeLowerLetter();
        if(len%2!=0){
            result+=str.substring(index-1,index+1);
        }else{
            if(str.charAt(index)>='A'&&str.charAt(index)<='Z'){
                result+=str.substring(index,index+2);
            }else{
                result+=str.charAt(index);
            }
        }
        return result;
    }
    private static int beforeLowerLetter(){
        int result;
        int endIndex=index-1;
        int beginIndex=endIndex;
        while(beginIndex>=0){
            if(str.charAt(beginIndex)<='z'&&str.charAt(beginIndex)>='a'){
                break;
            }
            beginIndex--;
        }
        result=endIndex-beginIndex;
        return result;
    }
    private static void init(){
        scanner=new Scanner(System.in);
        strLen=scanner.nextInt();
        index=scanner.nextInt();
        str=scanner.next();
    }
}