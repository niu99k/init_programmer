/*
题目描述
给定一个字符串str，如果可以在str的任意位置添加字符，请返回在添加字符最少的情况下，让str整体都是回文字符串的一种结果。
输入描述:
输入包含一行字符串，代表str（1 \leq length_{str} \leq 5000）（1≤length
str
​
 ≤5000）。
输出描述:
输出一行，代表返回的字符串。
示例1
输入
复制
ABA
输出
复制
ABA
示例2
输入
复制
AB
输出
复制
ABA
备注:
时间复杂度O（n^2）O（n
2
 ）,空间复杂度O（n^2）O（n
2
 ）
 */
import java.util.*;
public class Main{
    static Scanner scanner;
    static String str;
    static int strLen;
    static int[][] distanceDpMap;
    public static void main(String []args){
        init();
        result();
        deInit();
    }
    private static void deInit(){
        scanner.close();
    }
    private static void result(){
        initDpMap();
//         for(int i=0;i<strLen;i++){
//             for(int j=0;j<strLen;j++){
//                 System.out.print(distanceDpMap[i][j]+" ");
//             }
//             System.out.println();
//         }
        System.out.println(path());
    }
    private static String path(){
        String result;
        StringBuffer stringBuffer=new StringBuffer();
        int rowIndex=0;
        int columnIndex=strLen-1;
        while(columnIndex>rowIndex){
            if(str.charAt(columnIndex)==str.charAt(rowIndex)){
                stringBuffer.append(str.charAt(columnIndex));
                columnIndex--;
                rowIndex++;
            }else{
                if(distanceDpMap[rowIndex+1][columnIndex]>distanceDpMap[rowIndex][columnIndex-1]){
                    stringBuffer.append(str.charAt(columnIndex));
                    columnIndex--;
                }else {
                    stringBuffer.append(str.charAt(rowIndex));
                    rowIndex++;
                }
            }
        }
        int begin;
        if(rowIndex==columnIndex){
            stringBuffer.append(str.charAt(rowIndex));
            begin=stringBuffer.length()-2;
        }else{
            begin=stringBuffer.length()-1;
        }
        StringBuffer reverse=new StringBuffer();
        for(int i=begin;i>=0;i--){
            reverse.append(stringBuffer.charAt(i));
        }
        result=stringBuffer.toString()+reverse.toString();
        return result;
    }
    private static void initDpMap(){
        int rowIndex=0;
        int columnIndex=0;
        int beginColumnIndex=0;
        while(beginColumnIndex<=strLen-1){
            rowIndex=0;
            columnIndex=beginColumnIndex;
            while(columnIndex<=strLen-1){
                initDpMap(rowIndex,columnIndex);
                rowIndex++;
                columnIndex++;
            }
            beginColumnIndex++;
        }
    }
    private static void initDpMap(int rowIndex,int columnIndex){
        final int len=columnIndex-rowIndex+1;
        if(len==1){
            distanceDpMap[rowIndex][columnIndex]=0;
        }else if(len==2){
            if(str.charAt(rowIndex)==str.charAt(columnIndex)){
                distanceDpMap[rowIndex][columnIndex]=0;
            }else{
                distanceDpMap[rowIndex][columnIndex]=1;
            }
        }else{
            if(str.charAt(rowIndex)==str.charAt(columnIndex)){
                distanceDpMap[rowIndex][columnIndex]=distanceDpMap[rowIndex+1][columnIndex-1];
            }else{
                if(distanceDpMap[rowIndex+1][columnIndex]<distanceDpMap[rowIndex][columnIndex-1]){
                    distanceDpMap[rowIndex][columnIndex]=distanceDpMap[rowIndex+1][columnIndex]+1;
                }else{
                    distanceDpMap[rowIndex][columnIndex]=distanceDpMap[rowIndex][columnIndex-1]+1;
                }
            }
        }
    }
    private static void init(){
        scanner=new Scanner(System.in);

        str=scanner.next();
        strLen=str.length();
        distanceDpMap=new int[strLen][strLen];
    }
}