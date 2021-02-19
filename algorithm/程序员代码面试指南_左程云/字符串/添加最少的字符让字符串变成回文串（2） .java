/*
题目描述
给定一个字符串str，再给定str的最长回文子序列字符串strlps, 请返回在添加字符最少的情况下，让str整体都是回文字符串的一种结果。进阶问题比原问题多了一个参数，请做到时间复杂度比原问题的实现低。
输入描述:
输出包含两行，第一行包含一个字符串代表str（ 1 \leq length_{str} \leq 5000）（1≤length
str
​
 ≤5000）,第二行包含一个字符串，代表strips。
输出描述:
输出一行，代表返回的值。
示例1
输入
复制
A1B21C
121
输出
复制
AC1B2B1CA
说明
str=“A1B21C"，strlps="121"，返回“AC1B2B1CA”或者“CA1B2B1AC”，总之，只要是添加的字符数最少，只返回其中一种结果即可。
 */
import java.util.*;
public class Main{
    static String str;
    static String huiwen;
    static Scanner scanner;
    static int strLen;
    static String resultStr;
    public static void main(String[]args){
        init();
        result();
        deInit();
    }
    private static void deInit(){
        scanner.close();
    }
    private static void result(){
        solve();
        print();
    }
    private static void print(){
        System.out.println(resultStr);
    }
    private static void solve(){
        int beginIndex=0;
        int endIndex=strLen-1;
        int preBeginIndex=0;
        int preEndIndex=strLen-1;
        int searchIndexInHuiwen=0;
        char chr=huiwen.charAt(searchIndexInHuiwen);
        StringBuffer leftResult=new StringBuffer();
        StringBuffer rightResult=new StringBuffer();
        while(true){
            beginIndex=left2Right(preBeginIndex,chr);
            endIndex=right2Left(preEndIndex,chr);
            if(beginIndex>endIndex){
                resultStr=leftResult.toString()+rightResult.toString();
                break;
            }else {
                String leftPart=leftPart(beginIndex,preBeginIndex);
                String rightPart=rightPart(endIndex,preEndIndex);
                leftResult.append(leftPart+new StringBuffer(rightPart).reverse().toString()+chr);
                if(beginIndex==endIndex){
                    rightResult.insert(0,rightPart.toString()+new StringBuffer(leftPart).reverse().toString());
                }else{
                    rightResult.insert(0,chr+rightPart.toString()+new StringBuffer(leftPart).reverse().toString());
                }

                beginIndex++;
                endIndex--;
                preBeginIndex=beginIndex;
                preEndIndex=endIndex;
                searchIndexInHuiwen++;
                chr=huiwen.charAt(searchIndexInHuiwen);
            }
        }
    }
    private static String leftPart(int beginIndex,int preBeginIndex){
        String result;
        result=(str.substring(preBeginIndex,beginIndex));
        return result;
    }
    private static String rightPart(int endIndex,int preEndIndex){
        String result;
        result=(str.substring(endIndex+1,preEndIndex+1));
        return result;
    }
    private static int left2Right(int preBeginIndex,char chr){
        int result=0;
        for(int i=preBeginIndex;i<strLen;i++){
            final char chrTemp=str.charAt(i);
            if(chrTemp==chr){
                result=i;
                break;
            }
        }
        return result;
    }
    private static int right2Left(int preEndIndex,char chr){
        int result=0;
        for(int i=preEndIndex;i>=0;i--){
            final char chrTemp=str.charAt(i);
            if(chrTemp==chr){
                result=i;
                break;
            }
        }
        return result;
    }
    private static void init(){
        scanner=new Scanner(System.in);
        str=scanner.next();
        huiwen=scanner.next();
        strLen=str.length();
        resultStr="";
    }
}