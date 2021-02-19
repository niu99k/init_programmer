/*
题目描述
给定一个括号字符串str，返回最长的能够完全正确匹配括号字符字串的长度。
输入描述:
输出一行字符串，代表str（1 \leq length_{str} \leq 10^5）（1≤length
str
​
 ≤10
5
 ）。
输出描述:
输出一个整数，代表括号字符串的最长有效长度。
示例1
输入
复制
(()())
输出
复制
6
示例2
输入
复制
())
输出
复制
2
备注:
时间复杂度O（n）O（n），额外空间复杂度O（n）O（n）。
 */
import java.util.*;
public class Main{
    static Scanner scanner;
    static String str;
    static int strLen;
    static int[] dpMap;
    public static void main(String[]args){
        init();
        result();
        deInit();
    }
    private static void deInit(){
        scanner.close();
    }
    private static void result(){
        initDpmap();
        printMax();
    }
    private static void printMax(){
        int max=0;
        for(int i=0;i<strLen;i++){
//             System.out.println(i+"  "+dpMap[i]);
            if(dpMap[i]>max){
                max=dpMap[i];
            }
        }
        System.out.println(max);
    }
    private static void initDpmap(){
        for(int i=0;i<strLen;i++){
            final char chr=str.charAt(i);
            if(i==0){
                dpMap[i]=0;
            }else if(chr=='('){
                dpMap[i]=0;
            }else{
                final int preIndex=i-dpMap[i-1]-1;
                if(preIndex<0){
                    dpMap[i]=0;
                }else{
                    final char pre=str.charAt(preIndex);
                    if(pre=='('){
                        final int prePreIndex=i-dpMap[i-1]-2;
                        if(prePreIndex<0){
                            dpMap[i]=dpMap[i-1]+2;
                        }else{
                            dpMap[i]=dpMap[i-1]+2+dpMap[prePreIndex];
                        }
                    }
                }
            }
        }
    }
    private static void init(){
        scanner=new Scanner(System.in);
        str=scanner.next();
        strLen=str.length();
        dpMap=new int[strLen];
    }
}