/*
题目描述
给定一个字符串数组strs，再给定两个字符串str1和str2，返回在strs中str1和str2的最小距离，如果str1或str2为null，或不在strs中，返回-1。
输入描述:
输入包含有多行，第一输入一个整数n（1 \leq n \leq 10^5）（1≤n≤10
5
 ），代表数组strs的长度，第二行有两个字符串分别代表str1和str2，接下来n行，每行一个字符串，代表数组strs (保证题目中出现的所有字符串长度均小于等于10)。
输出描述:
输出一行，包含一个整数，代表返回的值。
示例1
输入
复制
1
CD AB
CD
输出
复制
-1
示例2
输入
复制
5
QWER 666
QWER
1234
qwe
666
QWER
输出
复制
1
备注:
时间复杂度O（n）O（n），额外空间复杂度O（1）O（1）
 */
import java.util.*;
public class Main{
    static Scanner scanner;
    static int strLen;
    static String[] str;
    static String str1;
    static String str2;
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
        int minDis=-1;
        int str1Index=-1;
        int str2Index=-1;
        for(int i=0;i<strLen;i++){
            if(str[i].equals(str1)){
                str1Index=i;
                if(str2Index!=-1&&str1Index-str2Index<minDis
                        ||str2Index!=-1&&minDis==-1
                ){
                    minDis=str1Index-str2Index;
                }
            }else if(str[i].equals(str2)){
                str2Index=i;
                if(str1Index!=-1&&str2Index-str1Index<minDis
                        ||str1Index!=-1&&minDis==-1
                ){
                    minDis=str2Index-str1Index;
                }
            }
        }
        result=minDis;
        return result;
    }
    private static void init(){
        scanner=new Scanner(System.in);
        strLen=scanner.nextInt();
        str1=scanner.next();
        str2=scanner.next();
        str=new String[strLen];
        for(int i=0;i<strLen;i++){
            str[i]=scanner.next();
        }
    }
}